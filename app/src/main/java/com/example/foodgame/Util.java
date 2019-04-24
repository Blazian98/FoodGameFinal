package com.example.foodgame;

import android.app.AlertDialog;
import android.content.Context;

import com.example.foodgame.API.FoodClient;
import com.example.foodgame.API.MealApi;

public class Util {

    public static MealApi getApi() {
        return FoodClient.getFoodClient().create(MealApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }
}
