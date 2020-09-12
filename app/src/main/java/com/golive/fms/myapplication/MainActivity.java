package com.golive.fms.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUsername;
    private TextView textViewAdet;

    private ListView listView;

    private Toolbar toolbar;

    private ArrayList<NotModel> arrayList = new ArrayList();
    private NotAdapter notAdapter;

    private FloatingActionButton floatingActionButton;

    private int time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        textViewUsername = findViewById(R.id.textview_user);
        textViewAdet = findViewById(R.id.textview_adet);
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.listView_main);

        floatingActionButton = findViewById(R.id.floating_add);

        String username = getIntent().getStringExtra("USERNAME");

        textViewUsername.setText(username);

        //setSupportActionBar(toolbar);

        arrayList = new ArrayList<>();

        arrayList.add(new NotModel("Notum 1","11/12/2020" ,0));
        arrayList.add(new NotModel("Notum 2","12/12/2020", R.drawable.hp));
        arrayList.add(new NotModel("Notum 3","13/12/2020", 0));
        arrayList.add(new NotModel("Notum 4","14/12/2020", 0));

        notAdapter = new NotAdapter(MainActivity.this,0,arrayList);

        listView.setAdapter(notAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Yeni Not Giriniz");

                final View view1 = getLayoutInflater().inflate(R.layout.dialog, null);
                builder.setView(view1);

                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText editTextDialog = view1.findViewById(R.id.edittext_dialog);

                        String text = editTextDialog.getText().toString();
                        sendDataDialog(text);

                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                NotModel notModel = arrayList.get(i);

                Intent intent = new Intent(MainActivity.this, RestActivity.class);
                startActivity(intent);

            }
        });

    }

    private void sendDataDialog(String text) {

        Date dateFormat = new Date();

        String date = new SimpleDateFormat("dd/MM/yyyy", (Locale.getDefault())).format(dateFormat);

        NotModel notModel = new NotModel(text, date,0);

        arrayList.add(notModel);

        notAdapter.notifyDataSetChanged();



    }


    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.
     */
    @Override
    protected void onResume() {
        super.onResume();

        time++;
        String timeValue = String.valueOf(time);

        try {
            textViewAdet.setText(timeValue);
        }
        catch (NullPointerException e){
            Toast.makeText(MainActivity.this, "Null kullanmayınız", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home){

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("USERNAME","");
            startActivity(intent);
            finish();


        }

        if (id == R.id.toolbar_sil){

        }

        if (id == R.id.toolbar_geri){

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.putExtra("USERNAME","");
            startActivity(intent);
            finish();

        }



        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key. The {@link #getOnBackPressedDispatcher() OnBackPressedDispatcher} will be given a
     * chance to handle the back button before the default behavior of
     * {@link Activity#onBackPressed()} is invoked.
     *
     * @see #getOnBackPressedDispatcher()
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra("USERNAME","");
        startActivity(intent);
        finish();
    }
}