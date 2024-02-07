package com.example.categoryproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UserBlouse_Page extends AppCompatActivity {

    RecyclerView recview;
    UserBlouse_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_blouse_page);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<UserBlouse_Model> options =
                new FirebaseRecyclerOptions.Builder<UserBlouse_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("blouseCategory"), UserBlouse_Model.class)
                        .build();

        adapter=new UserBlouse_Adapter(options);
        recview.setAdapter(adapter);
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.searchmenu,menu);

        MenuItem item=menu.findItem(R.id.search);

        SearchView searchView=(SearchView)item.getActionView();

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

    private void processsearch(String s)
    {
        FirebaseRecyclerOptions<UserBlouse_Model> options =
                new FirebaseRecyclerOptions.Builder<UserBlouse_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("blouseCategory").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), UserBlouse_Model.class)
                        .build();

        adapter= new UserBlouse_Adapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }

}