package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    Button signInButton;
    EditText login;
    EditText pswrd;
    Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        signInButton = findViewById(R.id.signInButton);
        login = findViewById(R.id.loginField);
        pswrd = findViewById(R.id.pswrdField);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
            }
        });

        Context context = this;
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!login.getText().toString().equals("")){
                    User user = new User(login.getText().toString(),
                            pswrd.getText().toString());
                    if (!user.isUserUniq(context)){
                        Log.d("lib777", "юзер есть");
                        startActivity(new Intent(SignInActivity.this,MainActivity.class));
                    }else {
                        Log.d("lib777", "вошли");
                        Toast.makeText(getApplicationContext(), "Ошибка входа", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }



}