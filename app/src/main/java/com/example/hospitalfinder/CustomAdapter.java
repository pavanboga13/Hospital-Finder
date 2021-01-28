package com.example.hospitalfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList user_name, user_blood, user_age;

    CustomAdapter(Context context, ArrayList user_name, ArrayList user_blood, ArrayList user_age) {
        this.context = context;
        this.user_name = user_name;
        this.user_blood = user_blood;
        this.user_age = user_age;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.user_blood_txt.setText(String.valueOf(user_blood.get(position)));
        holder.user_age_txt.setText(String.valueOf(user_age.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_name.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_name_txt, user_blood_txt, user_age_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_name_txt = itemView.findViewById(R.id.user_name_txt);
            user_blood_txt = itemView.findViewById(R.id.user_blood_txt);
            user_age_txt = itemView.findViewById(R.id.user_age_txt);
        }
    }
}
