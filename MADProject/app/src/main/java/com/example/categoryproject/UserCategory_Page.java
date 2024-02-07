package com.example.categoryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserCategory_Page extends AppCompatActivity {

    Button btnmore1,btntrouses,btnblouse,btnskirts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category_page);

        btnmore1=(Button) findViewById(R.id.btnmore1);

        btnmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCategory_Page.this,UserFrock_Page.class);
                startActivity(intent);
            }
        });

        btntrouses=(Button) findViewById(R.id.btntrousers);
        btntrouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCategory_Page.this,UserTrouser_Page.class);
                startActivity(intent);
            }
        });

        btnskirts=(Button)findViewById(R.id.btnskirts);
        btnskirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserCategory_Page.this,UserSkirt_Page.class);
                startActivity(intent);
            }
        });

        btnblouse=(Button)findViewById(R.id.btnblouses);
        btnblouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserCategory_Page.this,UserBlouse_Page.class);
                startActivity(intent);
            }
        });








    }
}