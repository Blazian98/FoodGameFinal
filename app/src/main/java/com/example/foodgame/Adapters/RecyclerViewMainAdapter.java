package com.example.foodgame.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgame.Model.Categories;
import com.example.foodgame.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMainAdapter extends RecyclerView.Adapter<RecyclerViewMainAdapter.RecyclerViewHolder> {

        private List<Categories.Category> categories;
        private Context context;
        private static ClickListener clickListener;

    public RecyclerViewMainAdapter(List<Categories.Category> categories, Context context) {
            this.categories = categories;
            this.context = context;
        }

        @NonNull
        @Override
        public RecyclerViewMainAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_category,
                    viewGroup, false);
            return new RecyclerViewHolder(view);
        }




//Code modified from ..



    @Override
        public void onBindViewHolder(@NonNull RecyclerViewMainAdapter.RecyclerViewHolder viewHolder, int i) {

            String strCategoryThum = categories.get(i).getStrCategoryThumb();
            Picasso.get().load(strCategoryThum).into(viewHolder.categoryThumb);

            String strCategoryName = categories.get(i).getStrCategory();
            viewHolder.categoryName.setText(strCategoryName);
        }


        @Override
        public int getItemCount() {
            return categories.size();
        }

        static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            @BindView(R.id.categoryThumb)
            ImageView categoryThumb;
            @BindView(R.id.categoryName)
            TextView categoryName;
            RecyclerViewHolder(@NonNull View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                clickListener.onClick(v, getAdapterPosition());
            }
        }


        public void setOnItemClickListener(ClickListener clickListener) {
            RecyclerViewMainAdapter.clickListener = clickListener;
        }


        public interface ClickListener {
            void onClick(View view, int position);
        }
    }

