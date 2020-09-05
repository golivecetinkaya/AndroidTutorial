package com.golive.fms.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText edittextLogin;
    private EditText edittextPassword;
    private Button   buttonLogin;

    private TextView textviewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edittextLogin = findViewById(R.id.edittext_username);
        edittextPassword = findViewById(R.id.edittext_password);
        buttonLogin = findViewById(R.id.button_login);


        textviewUser = findViewById(R.id.textview_user);

        textviewUser.setText("Kullanıcı");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = edittextLogin.getText().toString();
                String password = edittextPassword.getText().toString();

                if (username.equals("123") && password.equals("123")){

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("USERNAME",username);
                    startActivity(intent);

                }
                else {
                    Toast.makeText(LoginActivity.this,"Hatalı Kullanıcı Adı ve ya şifre", Toast.LENGTH_LONG).show();

                }


            }
        });


    }
}