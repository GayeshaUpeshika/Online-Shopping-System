package com.example.categoryproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AdminFrock_Adddata extends AppCompatActivity {

    EditText name,size,price,iurl;
    Button submit,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_frock_adddata);

        name = (EditText) findViewById(R.id.add_name);
        size = (EditText) findViewById(R.id.add_size);
        price = (EditText) findViewById(R.id.add_price);
        iurl = (EditText)  findViewById(R.id.add_url);

        back =(Button) findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminFrock_Page.class));
                finish();
            }
        });

        submit=(Button) findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processinsert();
            }
        });

    }

    private void processinsert(){
        Map<String,Object> map=new HashMap<>();
        map.put("name",name.getText().toString());
        map.put("size",size.getText().toString());
        map.put("price",price.getText().toString());
        map.put("iurl",iurl.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("frockCategory").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        name.setText("");
                        size.setText("");
                        price.setText("");
                        iurl.setText("");

                        Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();

                    }
                });





    }
}