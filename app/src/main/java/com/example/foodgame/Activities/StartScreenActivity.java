package com.example.foodgame.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.foodgame.R;

public class StartScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Button startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(v -> openActivityCategories());

    }

    public void openActivityCategories(){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);

    }
}

