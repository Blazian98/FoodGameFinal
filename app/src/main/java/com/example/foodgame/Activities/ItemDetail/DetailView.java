package com.example.foodgame.Activities.ItemDetail;

import com.example.foodgame.Model.Meals;

public interface DetailView {

    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
