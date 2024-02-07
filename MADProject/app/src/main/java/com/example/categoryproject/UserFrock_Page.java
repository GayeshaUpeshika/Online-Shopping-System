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

public class UserFrock_Page extends AppCompatActivity {

    RecyclerView recview;
    UserFrock_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_frock_page);

        recview=(RecyclerView)findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<UserFrock_Model> options =
                new FirebaseRecyclerOptions.Builder<UserFrock_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("frockCategory"), UserFrock_Model.class)
                        .build();

        adapter=new UserFrock_Adapter(options);
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
        FirebaseRecyclerOptions<UserFrock_Model> options =
                new FirebaseRecyclerOptions.Builder<UserFrock_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("frockCategory").orderByChild("name").startAt(s).endAt(s+"\uf8ff"), UserFrock_Model.class)
                        .build();

        adapter= new UserFrock_Adapter(options);
        adapter.startListening();
        recview.setAdapter(adapter);
    }


}