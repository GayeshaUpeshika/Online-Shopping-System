package com.example.categoryproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserTrouser_Adapter extends FirebaseRecyclerAdapter<UserTrouser_Model,UserTrouser_Adapter.myviewholder> {
    public UserTrouser_Adapter(FirebaseRecyclerOptions<UserTrouser_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull UserTrouser_Model model) {
        holder.name.setText(model.getName());
        holder.size.setText(model.getSize());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext()).load(model.getIurl()).into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_singlerow,parent,false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,size,price;
        ImageView edit,delete;

        public myviewholder(@NonNull View itemView) {


            super(itemView);

            img=(CircleImageView) itemView.findViewById(R.id.img1);
            name=(TextView) itemView.findViewById(R.id.nametext);
            size=(TextView) itemView.findViewById(R.id.sizetext);
            price=(TextView) itemView.findViewById(R.id.pricetext);

            edit=(ImageView) itemView.findViewById(R.id.editicon);
            delete=(ImageView) itemView.findViewById(R.id.deleteicon);
        }
    }
}
