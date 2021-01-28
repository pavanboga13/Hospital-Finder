package com.example.hospitalfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomerReviewList extends RecyclerView.Adapter<CustomerReviewList.MyViewHolder>{

    private Context context;
    private ArrayList user_ans1, user_ans2;
    int position;

    CustomerReviewList(Context context, ArrayList user_ans1, ArrayList user_ans2)
    {
        this.context = context;
        this.user_ans1 = user_ans1;
        this.user_ans2 = user_ans2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =  LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.review_row, parent, false);
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user_ans1.setText(String.valueOf(user_ans1.get(position)));
        holder.user_ans2.setText(String.valueOf(user_ans2.get(position)));
    }

    @Override
    public int getItemCount() {
        return user_ans1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user_ans1, user_ans2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_ans1 = itemView.findViewById(R.id.user_ans1);
            user_ans2 = itemView.findViewById(R.id.user_ans2);

        }
    }
}