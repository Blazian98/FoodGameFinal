package com.example.foodgame.Activities.Quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodgame.Activities.Quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    //Quiz Db class was adapted for our application from https://www.youtube.com/watch?v=rEaCu8itHtU&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7&index=3
    //Categories and questions were based of this video - https://www.youtube.com/watch?v=mONNvMIaulY&list=PLrnPJCHvNZuDCyg4Usq2gHMzz6_CiyQO7&index=14

    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        QuizCategory c1 = new QuizCategory("General");
        insertCategory(c1);
        QuizCategory c2 = new QuizCategory("Beef");
        insertCategory(c2);
        QuizCategory c3 = new QuizCategory("Chicken");
        insertCategory(c3);
        QuizCategory c4 = new QuizCategory("Pork");
        insertCategory(c4);
        QuizCategory c5 = new QuizCategory("Lamb");
        insertCategory(c5);
        QuizCategory c6 = new QuizCategory("Seafood");
        insertCategory(c6);
        QuizCategory c7 = new QuizCategory("Pasta");
        insertCategory(c7);
        QuizCategory c8 = new QuizCategory("Vegetarian");
        insertCategory(c8);
    }

    public void addCategory(QuizCategory category) {
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<QuizCategory> categories) {
        db = getWritableDatabase();

        for (QuizCategory category : categories) {
            insertCategory(category);
        }
    }

    private void insertCategory(QuizCategory category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What is the main ingredient in hummus?",
                "Butter beans", "Chickpeas", "Borlotti beans", "Navy bean", 2,
                QuizCategory.Vegetarian);
        insertQuestion(q1);
        Question q2 = new Question("What is the main ingredient in baba ganoush?",
                "Chickpeas", "Courgette", "Eggplant", "Starch", 3,
                QuizCategory.Vegetarian);
        insertQuestion(q2);
        Question q3 = new Question("What is the main ingredient in falafel?",
                "Lentils", "Couscous", "Chickpeas", "Potatos", 3,
                QuizCategory.Vegetarian);
        insertQuestion(q3);
        Question q4 = new Question("Which veggie provides the most protein per calorie?",
                "Broccoli", "Kale", "Mushrooms", "Lima beans", 3,
                QuizCategory.Vegetarian);
        insertQuestion(q4);
        Question q5 = new Question("Whats the cleanest way to cook bacon and ensure perfect crisp",
                "Pan-fry", "Oven bake", "Rice cooker", "Toaster", 2,
                QuizCategory.Pork);
        insertQuestion(q5);
        Question q6 = new Question("Whats the best tool to ensure the middle of your steak is cooked?",
                "Sharp eyes", "Add water", "Poke it", "Thermometer", 4,
                QuizCategory.Beef);
        insertQuestion(q6);
        Question q7 = new Question("Which one of these helps the MOST in ensuring a perfect steak sear?",
                "Dry it with a paper towel", "Add more water", "Heavily coat in oil", "Lemon juice", 1,
                QuizCategory.Beef);
        insertQuestion(q7);
        Question q8 = new Question("Which of these is statements are correct when mixing cake batter?",
                "'Make sure its completely mixed'", "'Don't over-mix your batter'", "'Using hands is actually more effective'", "'Eggs always make it worse'", 2,
                QuizCategory.General);
        insertQuestion(q8);
        Question q9 = new Question("What exactly does it mean to 'bake' food",
                "Use oil", "To cook by dry heat", "Tenderise with hammer", "Heat in water", 3,
                QuizCategory.General);
        insertQuestion(q9);
        Question q10 = new Question("How many teaspoons in a tablespoon?",
                "2", "3", "4", "5", 2,
                QuizCategory.General);
        insertQuestion(q10);
        Question q11 = new Question("What temperature is ideal for the perfect omelet?",
                "High", "High-medium", "Medium-low", "Low", 2,
                QuizCategory.General);
        insertQuestion(q11);
        Question q12 = new Question("What does it mean to 'sear' a steak",
                "Measurement of meat fattiness", "Cook with water", "A way to cut a steak", "Cook with intense heat", 4,
                QuizCategory.Beef);
        insertQuestion(q12);
        Question q13 = new Question("Whats the final step after cooking your steak?",
                "Slice it", "Add sauce", "Let it rest", "Another quick sear", 3,
                QuizCategory.Beef);
        insertQuestion(q13);
        Question q15 = new Question("How can you fix a soup after adding too much salt?",
                "Pouring out some broth", "Adding green vegetables", "Adding pepper", "Adding potatoes or pasta", 4,
                QuizCategory.General);
        insertQuestion(q15);
        Question q16 = new Question("What size pan is ideal for cooking an omelet",
                "A 3-quart saucier", "A 9-inch pan", "A 12-inch skillet", "Dutch Oven", 2,
                QuizCategory.General);
        insertQuestion(q16);
        Question q17 = new Question("How do you poach an egg?",
                "Break and gently drop it into hot water", "Stir it into a pan with butter", "Remove the shell and microwave", "Illegally hunt the egg", 1,
                QuizCategory.General);
        insertQuestion(q17);
        Question q18 = new Question("How do you caramelise onions?",
                "Cook it with brown sugar", "Boil in red wine", "Simmer in butter/oil", "Bake it before frying", 3,
                QuizCategory.General);
        insertQuestion(q18);
        Question q19 = new Question("When should pasta be removed from boiling water?",
                "After 10min", "When half the water evaporates", "While its still a little firm", "When its really soft", 3,
                QuizCategory.Pasta);
        insertQuestion(q19);
        Question q20 = new Question("What temperature does water boil?",
                "100 C", "250 C", "50 C", "500 C", 1,
                QuizCategory.General);
        insertQuestion(q20);
        Question q21 = new Question("What is the term used to describe pasta that is “firm to the bite?”",
                "Squishy", "Al Dente", "Fin Firm", "La Stronga", 2,
                QuizCategory.Pasta);
        insertQuestion(q21);
        Question q22 = new Question("In baking recipes, what is usually the first step?",
                "Preheat the oven", "Crack eggs", "Measure everything into bowls", "Put everything in the counter", 1,
                QuizCategory.General);
        insertQuestion(q22);
        Question q23 = new Question("How long does a perfect hardboiled egg take?",
                "7-10min", "10-20min", "1-2min", "3-5min", 1,
                QuizCategory.General);
        insertQuestion(q23);
        Question q24 = new Question("What are the two most common items placed in water to make it “refreshing?”",
                "Apple and avocado slices", "Potato and radish slices", "Lemon and cucumber slices", "Banana and pear slices", 3,
                QuizCategory.General);
        insertQuestion(q24);
        Question q25 = new Question("Which color of veggies should we eat the most of?",
                "Yellows and oranges", "Greens", "A mix of colors", "Bright reds", 3,
                QuizCategory.Vegetarian);
        insertQuestion(q25);
        Question q26 = new Question("Which veggie is considered as a meat 'fill-in' to many?",
                "Jackfruit", "Mushrooms", "A mix of colors", "Both Option 1 and 2", 4,
                QuizCategory.Vegetarian);
        insertQuestion(q26);
        Question q27 = new Question("Which color of veggies should we eat the most of?",
                "Yellows and oranges", "Greens", "A mix of colors", "Bright reds", 3,
                QuizCategory.Vegetarian);
        insertQuestion(q27);
        Question q28 = new Question("Which veggie is considered as a meat 'fill-in' to many?",
                "Jackfruit", "Mushrooms", "A mix of colors", "Both Option 1 and 2", 4,
                QuizCategory.Vegetarian);
        insertQuestion(q28);
        Question q29 = new Question("How many times should you flip your steak?",
                "1", "2", "3", "4", 1,
                QuizCategory.Beef);
        insertQuestion(q29);
        Question q30 = new Question("How long should beef be defrosted for?",
                "15min", "30min", "60min", "0min", 2,
                QuizCategory.Beef);
        insertQuestion(q30);
        Question q31 = new Question("You're cooking chicken. How hot should the centre be?",
                "63C", "75C", "85C", "100C", 2,
                QuizCategory.Chicken);
        insertQuestion(q31);
        Question q32 = new Question("You found last year's lamb chops in the freezer. Can you eat it?",
                "No – clean your freezer more often, mate", "Yes, if it's not freezer-burnt", "Yes, but two years is too long", "Yes – but the taste and texture might be off", 4,
                QuizCategory.Lamb);
        insertQuestion(q32);
        Question q33 = new Question("Should you wash chicken before cooking it?",
                "Yes - it removes surface bacteria", "Yes - only if there's blood.", "No - it can introduce bacteria into the meat", "No - it spreads germs in your kitchen", 4,
                QuizCategory.Chicken);
        insertQuestion(q33);
        Question q34 = new Question("How to preserve a salad?",
                "In cold water", "Keeping it completely dry", "In hot water", "Vinegar", 2,
                QuizCategory.Vegetarian);
        insertQuestion(q34);
        Question q35 = new Question("Once you've cooked food, when should you put it into the fridge?",
                "Once its stopped steaming", "As soon as possible", "Once it reaches room temperature", "Anytime after four hours of cooking", 1,
                QuizCategory.General);
        insertQuestion(q35);
        Question q36 = new Question("When it comes to Italian pasta and sauces:",
                "the redder the sauce, the longer the pasta should be", "long pastas are best with cream sauces or olive oil", "It's always good!", "Both Option 1 and 2", 2,
                QuizCategory.Pasta);
        insertQuestion(q36);
        Question q37 = new Question("What should you look for when buying fresh mussels?",
                "mussels with widely opened shells", "mussels that are light-colored", "mussels with closed shells or shells that snap shut when tapped", "Both Option 1 and 2", 3,
                QuizCategory.Seafood);
        insertQuestion(q37);
        Question q38 = new Question("What type of sauce is best for long, narrowly shaped pasta?",
                "oil-based sauces (thin and smooth)", "thick sauce that contains chunks of meat", "Both Option 1 and 2 are great.", "None of the above", 1,
                QuizCategory.Pasta);
        insertQuestion(q38);
        Question q39 = new Question("When doubling a recipe which of the following should you NEVER double?",
                "flour", "alcohol", "water", "All the above are fine to double", 2,
                QuizCategory.General);
        insertQuestion(q39);
        Question q40 = new Question("The golden rule is to never marinate chicken for more than:",
                "6 hours", "12 hours", "24 hours", "48hours", 3,
                QuizCategory.Chicken);
        insertQuestion(q40);
        Question q41 = new Question("For roasting chicken, how long should you leave it to rest after being cooked?",
                "Less than 10min", "10-20min", "20-30min", "30-45min", 2,
                QuizCategory.Chicken);
        insertQuestion(q41);
        Question q42 = new Question("What should you look for when buying chicken?",
                "Chicken is bright pink all over", "Flesh should be firm but not stiff and springy to touch", "No odour and no visible blood", "All of the above", 4,
                QuizCategory.Chicken);
        insertQuestion(q42);
        Question q43 = new Question("What are the uses of chicken skin during cooking?",
                "Acts as a kind of sponge for the juices", "Protects the meat from the heat", "Cook at a slower pace and retains moisture", "All of the above", 4,
                QuizCategory.Chicken);
        insertQuestion(q43);
        Question q44 = new Question("Which of the following is not a advantage of cooking with chicken thighs?",
                "They have a fantastic texture", "They season easily", "Often taste better than chicken parts", "Easier to cook than other parts of the chicken", 4,
                QuizCategory.Chicken);
        insertQuestion(q44);
        Question q45 = new Question("Once meat begins defrosting, any bacteria present can begin multiplying at ",
                "Room temperature", "30C", "40C", "50C", 1,
                QuizCategory.Chicken);
        insertQuestion(q45);
        Question q46 = new Question("You're cooking chicken. How hot should the centre be?",
                "63C", "75C", "85C", "100C", 2,
                QuizCategory.Chicken);
        insertQuestion(q46);
        Question q47 = new Question("You're cooking chicken. How hot should the centre be?",
                "63C", "75C", "85C", "100C", 2,
                QuizCategory.Chicken);
        insertQuestion(q47);
        Question q48 = new Question("What's the best way to thaw frozen seafood, according to cooks?",
                "in cold water", "in milk", "in corn syrup", "in tomato sauce", 2,
                QuizCategory.Seafood);
        insertQuestion(q48);
        Question q49 = new Question("What can you add to your scrambled eggs to make them creamier?",
                "Salt", "Cornstarch", "Chives", "Dairy", 4,
                QuizCategory.General);
        insertQuestion(q49);
        Question q50 = new Question("Most rice requires _______ as much water to cook correctly",
                "Half", "Twice", "Three time", "Just as much (1:1)", 2,
                QuizCategory.Vegetarian);
        insertQuestion(q50);
        Question q51 = new Question("When cutting vegetables and raw meats, what should you do?",
                "Wash the meat with soap first", "Use separate cutting boards", "Defrost vegetables after cutting meat", "Vacuum seal the meat", 2,
                QuizCategory.General);
        insertQuestion(q51);
        Question q52 = new Question("The three common ways to cook a steak are: ",
                "Cold, Warm and Hot", "Bloody, Mushy and Burnt", "Rare, Medium-rare and Well-done", "Rare, Uncommon, Common", 3,
                QuizCategory.Beef);
        insertQuestion(q52);
        Question q53 = new Question("Nuts are a good source of: ",
                "Protein", "Fat", "Vitamins", "All of the above", 4,
                QuizCategory.General);
        insertQuestion(q53);
        Question q54 = new Question("When cooking pasta, you should add a pinch of ______ to the water ",
                "Oil", "Vinegar", "Paprika", "Salt", 4,
                QuizCategory.General);
        insertQuestion(q54);
        Question q55 = new Question("Most rice requires _______ as much water to cook correctly",
                "Half", "Twice", "Three time", "Just as much (1:1)", 2,
                QuizCategory.Vegetarian);
        insertQuestion(q55);
        Question q56 = new Question("Beat cream long enough, and it will turn into",
                "Yoghurt", "Sour Cream", "Whipped Cream", "Cheese", 3,
                QuizCategory.General);
        insertQuestion(q56);
        Question q57 = new Question("Steak is best paired with what kind of wine?",
                "Rosé", "White", "Dessert Wine", "Red", 4,
                QuizCategory.Beef);
        insertQuestion(q57);
        Question q58 = new Question("How should you keep roasts together?",
                "Squeeze it thoroughly before cooking", "Meat rope", "Tenderise it before freezing", "Butcher's twine", 4,
                QuizCategory.Chicken);
        insertQuestion(q58);
        Question q59 = new Question("How should you keep roasts together?",
                "Squeeze it thoroughly before cooking", "Meat rope", "Tenderise it before freezing", "Butcher's twine", 4,
                QuizCategory.Beef);
        insertQuestion(q59);
        Question q60 = new Question("How should you keep roasts together?",
                "Squeeze it thoroughly before cooking", "Meat rope", "Tenderise it before freezing", "Butcher's twine", 4,
                QuizCategory.Pork);
        insertQuestion(q60);
        Question q61 = new Question("How should you keep roasts together?",
                "Squeeze it thoroughly before cooking", "Meat rope", "Tenderise it before freezing", "Butcher's twine", 4,
                QuizCategory.Lamb);
        insertQuestion(q61);
        Question q62 = new Question("What type of food is a slow cooker best used for?",
                "Steak", "Stews", "Pizza", "Fries", 2,
                QuizCategory.General);
        insertQuestion(q62);
        Question q63 = new Question("Nuts are a good source of: ",
                "Protein", "Fat", "Vitamins", "All of the above", 4,
                QuizCategory.Vegetarian);
        insertQuestion(q63);
        Question q64 = new Question("Chicken is best paired with what kind of wine?",
                "Rosé", "White", "Dessert Wine", "Red", 2,
                QuizCategory.Chicken);
        insertQuestion(q64);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();

        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<QuizCategory> getAllCategories() {
        List<QuizCategory> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                QuizCategory category = new QuizCategory();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID)};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
