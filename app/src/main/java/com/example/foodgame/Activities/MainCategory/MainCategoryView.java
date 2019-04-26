package com.example.foodgame.Activities.MainCategory;

import com.example.foodgame.Model.Categories;
import com.example.foodgame.Model.Meals;

import java.util.List;

public interface MainCategoryView {

    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
