package com.example.categoryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class AdminSkirt_Page extends AppCompatActivity {

    RecyclerView recview;
    AdminSkirt_Adapter adapter;
    FloatingActionButton fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_skirt_page);

        setTitle("Search here..");

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<AdminSkirt_Model> options =
                new FirebaseRecyclerOptions.Builder<AdminSkirt_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("skirtCategory"), AdminSkirt_Model.class)
                        .build();

        adapter = new AdminSkirt_Adapter(options);
        recview.setAdapter(adapter);

        fb=(FloatingActionButton)findViewById(R.id.fadd);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AdminSkirt_adddata.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu,menu);
        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });




        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {

        FirebaseRecyclerOptions<AdminSkirt_Model> options =
                new FirebaseRecyclerOptions.Builder<AdminSkirt_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("skirtCategory").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), AdminSkirt_Model.class)
                        .build();

        adapter=new AdminSkirt_Adapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);


    }
}