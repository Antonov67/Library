package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button regButton;
    EditText login, pswrd1, pswrd2, fio, adress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        regButton = findViewById(R.id.regButton);
        login = findViewById(R.id.regLoginField);
        pswrd1 = findViewById(R.id.regPswrdField1);
        pswrd2 = findViewById(R.id.regPswrdField2);
        fio = findViewById(R.id.fio);
        adress = findViewById(R.id.adress);


        Context context = this;
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(login.getText().toString(),pswrd1.getText().toString());
                user.setFio(fio.getText().toString());
                user.setAdress(adress.getText().toString());
                if (!login.getText().toString().equals("")
                        && !fio.getText().toString().equals("")
                        && pswrd1.getText().toString().equals(pswrd2.getText().toString())){
                    if (user.isUserUniq(context)){
                        DB.addUser(user,context);
                        Toast.makeText(getApplicationContext(),"Пользователь успешно добавлен",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                    }else{
                        Toast.makeText(getApplicationContext(),"Такой логин уже существует",Toast.LENGTH_LONG).show();
                    }

                }
                else {
                    Toast.makeText(getApplicationContext(),"Заполните правильно поля",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}