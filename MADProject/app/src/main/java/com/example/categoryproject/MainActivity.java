package com.example.categoryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Login();
    }

    void Login(){
        et_username = (EditText)findViewById(R.id.et_username);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,AdminCategory_Page.class);
                    startActivity(intent);
                }else if(et_username.getText().toString().equals("user") && et_password.getText().toString().equals("user")){
                    Toast.makeText(MainActivity.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this,UserCategory_Page.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}