package com.example.food_app_23.Adaptor;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_app_23.Domain.FoodDomain;
import com.example.food_app_23.R;
import com.example.food_app_23.ShowDetailActivity;

import java.util.ArrayList;

public class PopularAdaptor extends RecyclerView.Adapter<PopularAdaptor.ViewHolder> {
    private ArrayList<FoodDomain> popularFood;

    public PopularAdaptor(ArrayList<FoodDomain> categoryDomains) {
        this.popularFood = categoryDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdaptor.ViewHolder holder, int position) {
        FoodDomain item = popularFood.get(position);

        holder.title.setText(item.getTitle());
        holder.fee.setText(String.valueOf(item.getFee()));

        int drawableResourceId = holder.itemView.getContext()
                .getResources()
                .getIdentifier(item.getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularFood == null ? 0 : popularFood.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            fee = itemView.findViewById(R.id.fee);
            pic = itemView.findViewById(R.id.pic);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
