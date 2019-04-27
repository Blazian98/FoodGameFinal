package com.example.foodgame.Activities.Quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.foodgame.R;

import java.util.List;

public class QuizStartingScreenActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_CATEGORY_ID = "extraCategoryID";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighscore";

    private TextView textViewHighscore;
    private Spinner spinnerCategory;

    private int highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_starting_screen);

        textViewHighscore = findViewById(R.id.text_view_highscore);
        spinnerCategory = findViewById(R.id.spinner_category);

        loadCategories();
        loadHighscore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(v -> startQuiz());

        Button instructions = findViewById(R.id.instructions);

        instructions.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), PopupActivity.class);
            startActivity(i);
        });
    }

    private void startQuiz() {
        QuizCategory selectedCategory = (QuizCategory) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();
        String categoryName = selectedCategory.getName();

        Intent intent = new Intent(QuizStartingScreenActivity.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highscore) {
                    updateHighscore(score);
                }
            }
        }
    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<QuizCategory> categories = dbHelper.getAllCategories();

        ArrayAdapter<QuizCategory> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapterCategories);
    }

    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighscore.setText(getResources().getString(R.string.high_score) + highscore);
    }

    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;
        textViewHighscore.setText(getResources().getString(R.string.high_score) + highscore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highscore);
        editor.apply();
    }
}