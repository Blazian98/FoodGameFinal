package com.example.foodgame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.foodgame.Activities.MainCategory.MainCategoryActivity;
import com.example.foodgame.Activities.Quiz.QuizStartingScreenActivity;
import com.example.foodgame.Activities.Randomiser.RandomActivity;
import com.example.foodgame.R;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    //code for OnclickListener for CardView modified from https://www.youtube.com/watch?v=d6CfaWW7G5Q to suit the application

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CardView recipeCard = findViewById(R.id.recipe_card);
        recipeCard.setOnClickListener(this);

        CardView quizCard = findViewById(R.id.quiz_card);
        quizCard.setOnClickListener(this);

        CardView randomCard = findViewById(R.id.random_card);
        randomCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId()) {
            case R.id.recipe_card : i = new Intent(this, MainCategoryActivity.class);
            startActivity(i);
            break ;
            case R.id.quiz_card : i = new Intent(this, QuizStartingScreenActivity.class);
            startActivity(i);
            break ;
            case R.id.random_card : i = new Intent(this, RandomActivity.class);
            startActivity(i);
            break ;
            default:break;
        }

    }
}

