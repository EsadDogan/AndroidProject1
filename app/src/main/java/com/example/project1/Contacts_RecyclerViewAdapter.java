package com.example.project1;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Contacts_RecyclerViewAdapter extends RecyclerView.Adapter<Contacts_RecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Contacts> contacts;

    /**
     * Constructor which holds the data from activity
     * @param context
     * @param contacts arraylist of objects
     */
    public Contacts_RecyclerViewAdapter(Context context, ArrayList<Contacts> contacts){

        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public Contacts_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // this is where you inflate your layout (giving a look to our rows)

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview,parent, false);

        return new Contacts_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Contacts_RecyclerViewAdapter.MyViewHolder holder, int position) {

        // assigning values to the views we created in the recylerview.xml
        // based on the position of the recycler view

        String nameLastname = contacts.get(position).getName() +" "+ contacts.get(position).getLastName();
        holder.txtNameSurname.setText(nameLastname);
        holder.txtMail.setText(contacts.get(position).getMail());
    }

    @Override
    public int getItemCount() {
        // Recyclerview just wants to know the number of items you want
        return contacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        // grabbing the views from our recyclerview.xml file
        // kinda like in the onCreate method

        ImageView imageView;
        TextView txtNameSurname, txtMail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            txtNameSurname = itemView.findViewById(R.id.txtNameSurname);
            txtMail = itemView.findViewById(R.id.txtEmail);

        }
    }
}
