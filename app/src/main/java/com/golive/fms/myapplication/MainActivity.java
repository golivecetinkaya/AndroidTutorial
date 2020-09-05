package com.golive.fms.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewUsername;
    private TextView textViewAdet;

    private ListView listView;

    private Toolbar toolbar;

    private ArrayList<NotModel> arrayList = new ArrayList();
    private NotAdapter notAdapter;

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