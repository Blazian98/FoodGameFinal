package com.example.foodgame.Activities.ItemDetail;

import android.support.annotation.NonNull;

import com.example.foodgame.Model.Meals;
import com.example.foodgame.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {

    //code modified from https://www.youtube.com/watch?v=4JGvDUlfk7Y to suit the application

    private DetailView view;

    public DetailPresenter(DetailView view) {
        this.view = view;
    }

    void getMealById(String mealName) {

        Util.getApi().getMealByName(mealName)
                .enqueue(new Callback<Meals>() {
                    @Override
                    public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            view.setMeal(response.body().getMeals().get(0));
                        }
                        else {
                            view.onErrorLoading(response.message());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                        view.onErrorLoading(t.getLocalizedMessage());

                    }
                });
    }
}
