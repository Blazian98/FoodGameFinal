package com.example.foodgame.Activities.Quiz;

public class QuizCategory {

    //QuizCategory class was established based on https://www.youtube.com/watch?v=VuR1xOnaD0E&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7&index=12&t=0s

    public static final int General = 1;
    public static final int Beef = 2;
    public static final int Chicken = 3;
    public static final int Pork = 4;
    public static final int Lamb = 5;
    public static final int Seafood = 6;
    public static final int Pasta = 7;
    public static final int Vegetarian = 8;


    private int id;
    private String name;

    public QuizCategory() {
    }

    public QuizCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
