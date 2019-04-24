package com.example.foodgame.Activities.Main;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.foodgame.Adapters.RecyclerViewMainAdapter;
import com.example.foodgame.Activities.Category.CategoryActivity;
import com.example.foodgame.Model.Categories;
import com.example.foodgame.Model.Meals;
import com.example.foodgame.R;
import com.example.foodgame.Util;

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    ViewPager viewPagerMeal;
    RecyclerView recyclerViewCategory;

    MainPresenter presenter;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPagerMeal = findViewById(R.id.viewPagerHeader);
        recyclerViewCategory = findViewById(R.id.recyclerCategory);

        presenter = new MainPresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {

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
