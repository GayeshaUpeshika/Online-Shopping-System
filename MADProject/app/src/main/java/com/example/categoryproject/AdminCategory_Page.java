package com.example.categoryproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminCategory_Page extends AppCompatActivity {

    Button btnmore1,btntrouser,btnskirts,btnblouse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category_page);

        btnmore1=(Button) findViewById(R.id.btnmore1);

        btnmore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory_Page.this,AdminFrock_Page.class);
                startActivity(intent);
            }
        });

        btntrouser=(Button) findViewById(R.id.buttontrouser);

        btntrouser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory_Page.this,AdminTrouser_Page.class);
                startActivity(intent);
            }
        });

        btnskirts=(Button) findViewById(R.id.buttonskirts);
        btnskirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory_Page.this,AdminSkirt_Page.class);
                startActivity(intent);
            }
        });

        btnblouse=(Button)findViewById(R.id.buttonblouses);
        btnblouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminCategory_Page.this,AdminBlouse_Page.class);
                startActivity(intent);
            }
        });








    }
}