package com.example.categoryproject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminBlouse_Adapter extends FirebaseRecyclerAdapter<AdminBlouse_Model,AdminBlouse_Adapter.myviewholder> {
    public AdminBlouse_Adapter(FirebaseRecyclerOptions<AdminBlouse_Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull AdminBlouse_Model model) {
        holder.name.setText(model.getName());
        holder.size.setText(model.getSize());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext()).load(model.getIurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1300)
                        .create();

                View myview=dialogPlus.getHolderView();
                final EditText iurl=myview.findViewById(R.id.uiurl);
                final EditText name=myview.findViewById(R.id.uname);
                final EditText size=myview.findViewById(R.id.usize);
                final EditText price=myview.findViewById(R.id.uprice);
                Button submit=myview.findViewById(R.id.usubmit);

                iurl.setText(model.getIurl());
                name.setText(model.getName());
                size.setText(model.getSize());
                price.setText(model.getPrice());

                dialogPlus.show();


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Map<String,Object> map=new HashMap<>();
                        map.put("iurl",iurl.getText().toString());
                        map.put("name",name.getText().toString());
                        map.put("size",size.getText().toString());
                        map.put("price",price.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("blouseCategory")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });

                    }
                });

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("blouseCategory")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);

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
