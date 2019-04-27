package com.example.foodgame.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodgame.API.MealApi;
import com.example.foodgame.Model.Meals;
import com.example.foodgame.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomActivity extends AppCompatActivity {

    Button button;
    TextView Category;
    TextView Country;
    TextView Instructions;
    TextView ingredients;
    TextView measures;
    TextView Youtube;
    TextView Source;
    ImageView mealThumb;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        button = findViewById(R.id.button);

    }

    private class MealsClass extends AsyncTask<Void, Void, List<Meals.Meal>> {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MealApi service = retrofit.create(MealApi.class);

        private ProgressDialog dialog = new ProgressDialog(RandomActivity.this);

        protected void onPreExecute() {
            this.dialog.setMessage("Getting a perfect recipe for you!! Please Wait");
            this.dialog.show();
        }

        @Override
        protected List<Meals.Meal> doInBackground(Void... voids) {
            Call<Meals> RandomCall = service.getRandom();
            try {
                Response<Meals> MealResponse = RandomCall.execute();
                List<Meals.Meal> myMeal = MealResponse.body().getMeals();
                return myMeal;
            }
            catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Meals.Meal> result) {
            Category = findViewById(R.id.category);
            Country = findViewById(R.id.country);
            Instructions = findViewById(R.id.instructions);
            ingredients = findViewById(R.id.ingredient);
            measures = findViewById(R.id.measure);
            Youtube = findViewById(R.id.youtube);
            Source = findViewById(R.id.source);
            mealThumb = findViewById(R.id.mealThumb);
            collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

            collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
            collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));

            Category.setText(result.get(0).getStrCategory());
            Country.setText(result.get(0).getStrArea());
            Instructions.setText(result.get(0).getStrInstructions());
            collapsingToolbarLayout.setTitle(result.get(0).getStrMeal());
            Picasso.get().load(result.get(0).getStrMealThumb()).into(mealThumb);

            Youtube.setOnClickListener(v -> {
                Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                intentYoutube.setData(Uri.parse(result.get(0).getStrYoutube()));
                startActivity(intentYoutube);
            });

            Source.setOnClickListener(v -> {
                Intent intentSource = new Intent(Intent.ACTION_VIEW);
                intentSource.setData(Uri.parse(result.get(0).getStrSource()));
                startActivity(intentSource);
            });

            ingredients.setText(" ");
            measures.setText(" ");

                if (!result.get(0).getStrIngredient1().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient1());
                }

                if (!result.get(0).getStrIngredient2().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient2());
                }

                if (result.get(0).getStrIngredient3().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient3());
                }


                if (result.get(0).getStrIngredient4().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient4());
                }


                if (result.get(0).getStrIngredient5().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient5());
                }


                if (result.get(0).getStrIngredient6().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient6());
                }


                if (result.get(0).getStrIngredient7().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient7());
                }


                if (result.get(0).getStrIngredient8().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient8());
                }


                if (result.get(0).getStrIngredient9().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient9());
                }


                if (result.get(0).getStrIngredient10().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient10());
                }


                if (result.get(0).getStrIngredient11().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient11());
                }


                if (result.get(0).getStrIngredient12().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient12());
                }


                if (result.get(0).getStrIngredient13().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient13());
                }


                if (result.get(0).getStrIngredient14().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient14());
                }


                if (result.get(0).getStrIngredient15().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient15());
                }


                if (result.get(0).getStrIngredient16().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient16());
                }


                if (result.get(0).getStrIngredient17().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient17());
                }


                if (result.get(0).getStrIngredient18().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient18());
                }


                if (result.get(0).getStrIngredient19().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient19());
                }


                if (result.get(0).getStrIngredient20().isEmpty()) {
                    ingredients.append("\n \u2022 " + result.get(0).getStrIngredient20());
                }


            if (!result.get(0).getStrMeasure1().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure1().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure1());
            }
            if (!result.get(0).getStrMeasure2().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure2().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure2());
            }
            if (!result.get(0).getStrMeasure3().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure3().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure3());
            }
            if (!result.get(0).getStrMeasure4().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure4().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure4());
            }
            if (!result.get(0).getStrMeasure5().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure5().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure5());
            }
            if (!result.get(0).getStrMeasure6().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure6().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure6());
            }
            if (!result.get(0).getStrMeasure7().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure7().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure7());
            }
            if (!result.get(0).getStrMeasure8().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure8().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure8());
            }
            if (!result.get(0).getStrMeasure9().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure9().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure9());
            }
            if (!result.get(0).getStrMeasure10().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure10().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure10());
            }
            if (!result.get(0).getStrMeasure11().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure11().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure11());
            }
            if (!result.get(0).getStrMeasure12().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure12().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure12());
            }
            if (!result.get(0).getStrMeasure13().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure13().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure13());
            }
            if (!result.get(0).getStrMeasure14().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure14().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure14());
            }
            if (!result.get(0).getStrMeasure15().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure15().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure15());
            }
            if (!result.get(0).getStrMeasure16().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure16().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure16());
            }
            if (!result.get(0).getStrMeasure17().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure17().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure17());
            }
            if (!result.get(0).getStrMeasure18().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure18().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure18());
            }
            if (!result.get(0).getStrMeasure19().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure19().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure19());
            }
            if (!result.get(0).getStrMeasure20().isEmpty() && !Character.isWhitespace(result.get(0).getStrMeasure20().charAt(0))) {
                measures.append("\n : " + result.get(0).getStrMeasure20());
            }
            dialog.dismiss();
        }
    }
    public void launchMeal(View view) {
        MealsClass myMealsClass = new MealsClass();
        myMealsClass.execute();
    }
}
