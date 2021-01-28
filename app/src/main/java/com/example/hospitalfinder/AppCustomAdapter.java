package com.example.hospitalfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppCustomAdapter extends RecyclerView.Adapter<AppCustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList my_name, my_blood, my_age, my_date, my_time;

    AppCustomAdapter(Context context, ArrayList my_name, ArrayList my_blood, ArrayList my_age, ArrayList my_date, ArrayList my_time) {
        this.context = context;
        this.my_name = my_name;
        this.my_blood = my_blood;
        this.my_age = my_age;
        this.my_date = my_date;
        this.my_time = my_time;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.my_name.setText(String.valueOf(my_name.get(position)));
        holder.my_blood.setText(String.valueOf(my_blood.get(position)));
        holder.my_age.setText(String.valueOf(my_age.get(position)));
        holder.my_date.setText(String.valueOf(my_date.get(position)));
        holder.my_time.setText(String.valueOf(my_time.get(position)));
    }

    @Override
    public int getItemCount() {
        return my_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView my_name, my_blood, my_age, my_date, my_time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            my_name = itemView.findViewById(R.id.my_name);
            my_blood = itemView.findViewById(R.id.my_blood);
            my_age = itemView.findViewById(R.id.my_age);
            my_date = itemView.findViewById(R.id.my_date);
            my_time = itemView.findViewById(R.id.my_time);

        }
    }
}
