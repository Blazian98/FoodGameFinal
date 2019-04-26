package com.example.foodgame.Activities.MainCategory;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodgame.Activities.ItemDetail.DetailActivity;
import com.example.foodgame.Adapters.RecyclerViewMainAdapter;
import com.example.foodgame.Activities.Category.CategoryActivity;
import com.example.foodgame.Adapters.ViewPagerHeaderAdapter;
import com.example.foodgame.Model.Categories;
import com.example.foodgame.Model.Meals;
import com.example.foodgame.R;
import com.example.foodgame.Util;

import java.io.Serializable;
import java.util.List;

public class MainCategoryActivity extends AppCompatActivity implements MainCategoryView {

    //code modified from https://www.youtube.com/watch?v=QTiQawMBPUA to suit the application

    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    MainCategoryPresenter presenter;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_category);

        viewPagerMeal = findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = findViewById(R.id.recyclerCategory);

        presenter = new MainCategoryPresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }


    @Override
    public void setMeal(List<Meals.Meal> meal) {
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        viewPagerMeal.setAdapter(headerAdapter);
        viewPagerMeal.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();

        headerAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        RecyclerViewMainAdapter mainAdapter = new RecyclerViewMainAdapter(category, this);
        recyclerViewCategory.setAdapter(mainAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2,
                GridLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager);
        recyclerViewCategory.setNestedScrollingEnabled(true);
        mainAdapter.notifyDataSetChanged();

        mainAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);
            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Util.showDialogMessage(this,"Title", message);

    }
}
