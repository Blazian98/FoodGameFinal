package com.example.foodgame.Activities.Category;

import com.example.foodgame.Model.Meals;
import com.example.foodgame.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    //code modified from https://www.youtube.com/watch?v=4JGvDUlfk7Y to suit the application

    private CategoryView view;

    public CategoryPresenter(CategoryView view) {
        this.view = view;
    }

    void getMealByCategory(String category) {

       Call<Meals> mealsCall = Util.getApi().getMealByCategory(category);
       mealsCall.enqueue(new Callback<Meals>() {
           @Override
           public void onResponse(Call<Meals> call, Response<Meals> response) {
               if (response.isSuccessful() && response.body() != null){
                   view.setMeals(response.body().getMeals());
               }
               else {
                   view.onErrorLoading(response.message());
               }
           }

           @Override
           public void onFailure(Call<Meals> call, Throwable t) {
               view.onErrorLoading(t.getLocalizedMessage());

           }
       });

    }
}
