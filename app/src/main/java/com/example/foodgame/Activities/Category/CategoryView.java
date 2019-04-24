package com.example.foodgame.Activities.Category;

import com.example.foodgame.Model.Meals;

import java.util.List;

public interface CategoryView {

    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
