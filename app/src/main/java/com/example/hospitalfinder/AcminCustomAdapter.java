package com.example.hospitalfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AcminCustomAdapter extends RecyclerView.Adapter<AcminCustomAdapter.MyViewHolder> {

    private Context context;
    private ArrayList user_name, user_blood, user_age;
    int position;

    AcminCustomAdapter(Context context, ArrayList user_name, ArrayList user_blood, ArrayList user_age)
    {
        this.context = context;
        this.user_name = user_name;
        this.user_blood = user_blood;
        this.user_age = user_age;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.admin_my_row, parent, false);
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.admin_user_name_txt.setText(String.valueOf(user_name.get(position)));
        holder.admin_user_blood_txt.setText(String.valueOf(user_blood.get(position)));
        holder.admin_user_age_txt.setText(String.valueOf(user_age.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Admin_accpet.class);
                intent.putExtra("name", String.valueOf(user_name.get(position)));
                intent.putExtra("blood", String.valueOf(user_blood.get(position)));
                intent.putExtra("age", String.valueOf(user_age.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return user_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView admin_user_name_txt, admin_user_blood_txt, admin_user_age_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            admin_user_name_txt = itemView.findViewById(R.id.admin_user_name_txt);
            admin_user_blood_txt = itemView.findViewById(R.id.admin_user_blood_txt);
            admin_user_age_txt = itemView.findViewById(R.id.admin_user_age_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }

}
