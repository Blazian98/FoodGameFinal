package com.example.foodgame.Activities.Quiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.example.foodgame.R;

public class PopupActivity extends Activity {

    //Instruction's pop-up display was adapted from https://www.youtube.com/watch?v=eX-TdY6bLdg&fbclid=IwAR0x7Nsp7bWJyEp3wS7so-2B8vOXdNS6tGdIcij2OBhNwgYz4jc8wsqMzBQ

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.9), (int)(height*.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

    }
}