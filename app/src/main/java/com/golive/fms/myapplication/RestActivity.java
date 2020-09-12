package com.golive.fms.myapplication;

import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RestActivity extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewCapital;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        textViewName = findViewById(R.id.textview_name);
        textViewCapital = findViewById(R.id.textview_capital);
        imageView = findViewById(R.id.imageview_flag);


        final AlertDialog.Builder builder = new AlertDialog.Builder(RestActivity.this);
        builder.setTitle("Yeni Ülke Giriniz");

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

    private void sendDataDialog(String text) {

        Retrofit retrofit = push();
        CountryInterface countryInterface = retrofit.create(CountryInterface.class);

        Call<List<ResponseList>> call = countryInterface.getCountry(text, "application/json");

        /*ResponseList responseList = new ResponseList();
        responseList.name = "Turkey";
        Call<List<ResponseList>> call1 = countryInterface.postCapital(responseList);
        call1.enqueue(new Callback<List<ResponseList>>() {
            @Override
            public void onResponse(Call<List<ResponseList>> call, Response<List<ResponseList>> response) {

            }

            @Override
            public void onFailure(Call<List<ResponseList>> call, Throwable t) {

                Toast.makeText(RestActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });*/


        call.enqueue(new Callback<List<ResponseList>>() {

            @Override
            public void onResponse(Call<List<ResponseList>> call, Response<List<ResponseList>> response) {

                if (response.isSuccessful() && response.body().size() > 0){

                    String name = response.body().get(0).name;
                    String capital = response.body().get(0).capital;
                    int population = response.body().get(0).population;
                    String flag = response.body().get(0).flag;

                    flag = "https://productimages.hepsiburada.net/s/10/375/9059526639666.jpg";

                    textViewName.setText(name);
                    textViewCapital.setText(capital + " - " + (population));

                    Picasso.get().load(flag).into(imageView);

                }

                else {

                    try {
                        Toast.makeText(RestActivity.this,(response.errorBody().string()), Toast.LENGTH_LONG).show();
                    }

                    catch (IOException|NullPointerException e) {
                        e.printStackTrace();
                    }


                }

            }

            @Override
            public void onFailure(Call<List<ResponseList>> call, Throwable t) {

                Toast.makeText(RestActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });



    }


    public Retrofit push(){

        String url = "https://restcountries.eu/rest/v2/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        return retrofit;
    }

    public interface CountryInterface{

        @GET("name/{name}")
        Call<List<ResponseList>> getCountry(@Path("name") String name, @Query("$format") String format);

        @GET("capital/{capital}")
        Call<List<ResponseList>> getCapital(@Path("capital") String capital);

        @POST("capital/{capital}")
        Call<List<ResponseList>> postCapital(ResponseList requestBody);

    }

}