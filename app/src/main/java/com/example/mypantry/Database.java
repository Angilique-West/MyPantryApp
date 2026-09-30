package com.example.mypantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //users table
        String qry1 = "CREATE TABLE users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        insertUser(sqLiteDatabase,
                "Administrator",
                "yaya.gmail.com",
                "0777");

        insertUser(sqLiteDatabase,
                "Yaya",
                "angie@gamil.com",
                "10102");
        insertUser(sqLiteDatabase,
                "Admin",
                "angie@gamil.com",
                "12345");
        // PANTRY table

        String qry2 = "CREATE TABLE pantry_items(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "quantity TEXT," +
                "unit TEXT," +
                "expiry_date TEXT)";
        sqLiteDatabase.execSQL(qry2);
        insertItem(sqLiteDatabase,
                "Rice", "5", "kg", "2026-12-15");

        insertItem(sqLiteDatabase,
                "Sugar", "2", "kg", "2027-03-10");

        insertItem(sqLiteDatabase,
                "Milk", "2", "litres", "2026-10-05");

        insertItem(sqLiteDatabase,
                "Eggs", "12", "pieces", "2026-10-08");

        insertItem(sqLiteDatabase,
                "Flour", "2", "kg", "2027-01-20");

        insertItem(sqLiteDatabase,
                "Cooking Oil", "1", "litre", "2027-05-12");

        insertItem(sqLiteDatabase,
                "Tomatoes", "8", "pieces", "2026-10-02");

        insertItem(sqLiteDatabase,
                "Potatoes", "3", "kg", "2026-10-20");

        insertItem(sqLiteDatabase,
                "Pasta", "4", "packets", "2027-02-18");

        insertItem(sqLiteDatabase,
                "Beans", "3", "cans", "2027-04-25");

        insertItem(sqLiteDatabase,
                "Bread", "2", "loaves", "2026-10-01");

        insertItem(sqLiteDatabase,
                "Coffee", "500", "g", "2027-06-30");

        insertItem(sqLiteDatabase,
                "Salt", "1", "kg", "2028-01-15");

        insertItem(sqLiteDatabase,
                "Orange Juice", "2", "litres", "2026-11-12");

        insertItem(sqLiteDatabase,
                "Chicken", "2", "kg", "2026-10-10");

    String qry3 = "CREATE TABLE recipes(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "recipeName TEXT," +
            "instructions TEXT)";
          sqLiteDatabase.execSQL(qry3);
    insertRecipe(sqLiteDatabase,
        "Chicken Rice",
                "Cook the rice. Fry the chicken with vegetables and seasoning. Mix together and serve.");

    insertRecipe(sqLiteDatabase,
        "Vegetable Pasta",
                "Cook pasta until soft. Fry vegetables. Mix pasta with vegetables and serve.");

    insertRecipe(sqLiteDatabase,
        "Egg Sandwich",
                "Boil the eggs. Mash them and mix with seasoning. Place between slices of bread.");

    insertRecipe(sqLiteDatabase,
        "Tomato Pasta",
                "Cook pasta. Prepare tomato sauce with tomatoes and seasoning. Mix with pasta.");

    insertRecipe(sqLiteDatabase,
        "Chicken Sandwich",
                "Cook the chicken. Place chicken, tomatoes and seasoning between slices of bread.");

    insertRecipe(sqLiteDatabase,
        "Rice and Beans",
                "Cook the rice. Heat the beans and season them. Serve the beans with rice.");

    insertRecipe(sqLiteDatabase,
        "Pancakes",
                "Mix flour, milk and eggs. Cook small portions in a pan until golden.");

    insertRecipe(sqLiteDatabase,
        "French Toast",
                "Beat eggs with milk. Dip bread into the mixture and fry until golden.");

    insertRecipe(sqLiteDatabase,
        "Mashed Potatoes",
                "Boil potatoes until soft. Mash them and add milk and seasoning.");

    insertRecipe(sqLiteDatabase,
        "Chicken Pasta",
                "Cook pasta. Cook chicken separately. Mix chicken and pasta with seasoning.");

    insertRecipe(sqLiteDatabase,
        "Vegetable Rice",
                "Cook rice. Fry vegetables and mix them with the cooked rice.");

    insertRecipe(sqLiteDatabase,
        "Egg Fried Rice",
                "Cook rice. Fry eggs and vegetables. Add rice and stir-fry together.");

    insertRecipe(sqLiteDatabase,
        "Tomato Omelette",
                "Beat eggs. Add chopped tomatoes and seasoning. Cook in a pan until set.");

    insertRecipe(sqLiteDatabase,
        "Chicken and Potatoes",
                "Cook chicken and potatoes with seasoning until fully cooked.");

    insertRecipe(sqLiteDatabase,
        "Pasta with Beans",
                "Cook pasta. Heat beans and combine with pasta and seasoning.");

    insertRecipe(sqLiteDatabase,
        "Tomato Sandwich",
                "Slice tomatoes. Place tomatoes on bread and add seasoning.");

    insertRecipe(sqLiteDatabase,
        "Chicken Salad",
                "Cook chicken. Chop tomatoes and vegetables. Combine everything and serve.");

    insertRecipe(sqLiteDatabase,
        "Potato and Egg",
                "Boil potatoes and eggs. Chop them and mix together with seasoning.");

    insertRecipe(sqLiteDatabase,
        "Milk Pancakes",
                "Mix flour, milk and eggs. Cook pancakes in a lightly oiled pan.");

    insertRecipe(sqLiteDatabase,
        "Rice and Chicken Bowl",
                "Cook rice and chicken separately. Combine them with vegetables and seasoning.");

        String qry4 = "CREATE TABLE recipeingredients(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "recipeid INTEGER," +
                "ingredientname TEXT," +
                "requiredAmount TEXT," +
                "unit TEXT)";

        sqLiteDatabase.execSQL(qry4);
// Chicken Rice
        insertRecipeIngredient(sqLiteDatabase, 1, "Chicken", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 1, "Rice", "2", "kg");

// Vegetable Pasta
        insertRecipeIngredient(sqLiteDatabase, 2, "Pasta", "1", "packets");
        insertRecipeIngredient(sqLiteDatabase, 2, "Tomatoes", "2", "pieces");

// Egg Sandwich
        insertRecipeIngredient(sqLiteDatabase, 3, "Eggs", "2", "pieces");
        insertRecipeIngredient(sqLiteDatabase, 3, "Bread", "1", "loaves");

// Tomato Pasta
        insertRecipeIngredient(sqLiteDatabase, 4, "Pasta", "1", "packets");
        insertRecipeIngredient(sqLiteDatabase, 4, "Tomatoes", "3", "pieces");

// Chicken Sandwich
        insertRecipeIngredient(sqLiteDatabase, 5, "Chicken", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 5, "Bread", "1", "loaves");
        insertRecipeIngredient(sqLiteDatabase, 5, "Tomatoes", "1", "pieces");

// Rice and Beans
        insertRecipeIngredient(sqLiteDatabase, 6, "Rice", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 6, "Beans", "1", "cans");

// Pancakes
        insertRecipeIngredient(sqLiteDatabase, 7, "Flour", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 7, "Milk", "1", "litres");
        insertRecipeIngredient(sqLiteDatabase, 7, "Eggs", "2", "pieces");

// French Toast
        insertRecipeIngredient(sqLiteDatabase, 8, "Bread", "1", "loaves");
        insertRecipeIngredient(sqLiteDatabase, 8, "Eggs", "2", "pieces");
        insertRecipeIngredient(sqLiteDatabase, 8, "Milk", "0.5", "litres");

// Mashed Potatoes
        insertRecipeIngredient(sqLiteDatabase, 9, "Potatoes", "2", "kg");
        insertRecipeIngredient(sqLiteDatabase, 9, "Milk", "0.5", "litres");

// Chicken Pasta
        insertRecipeIngredient(sqLiteDatabase, 10, "Chicken", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 10, "Pasta", "1", "packets");

// Vegetable Rice
        insertRecipeIngredient(sqLiteDatabase, 11, "Rice", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 11, "Tomatoes", "2", "pieces");

// Egg Fried Rice
        insertRecipeIngredient(sqLiteDatabase, 12, "Rice", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 12, "Eggs", "2", "pieces");

// Tomato Omelette
        insertRecipeIngredient(sqLiteDatabase, 13, "Eggs", "3", "pieces");
        insertRecipeIngredient(sqLiteDatabase, 13, "Tomatoes", "2", "pieces");

// Chicken and Potatoes
        insertRecipeIngredient(sqLiteDatabase, 14, "Chicken", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 14, "Potatoes", "2", "kg");

// Pasta with Beans
        insertRecipeIngredient(sqLiteDatabase, 15, "Pasta", "1", "packets");
        insertRecipeIngredient(sqLiteDatabase, 15, "Beans", "1", "cans");

// Tomato Sandwich
        insertRecipeIngredient(sqLiteDatabase, 16, "Bread", "1", "loaves");
        insertRecipeIngredient(sqLiteDatabase, 16, "Tomatoes", "2", "pieces");

// Chicken Salad
        insertRecipeIngredient(sqLiteDatabase, 17, "Chicken", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 17, "Tomatoes", "2", "pieces");

// Potato and Egg
        insertRecipeIngredient(sqLiteDatabase, 18, "Potatoes", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 18, "Eggs", "2", "pieces");

// Milk Pancakes
        insertRecipeIngredient(sqLiteDatabase, 19, "Flour", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 19, "Milk", "1", "litres");
        insertRecipeIngredient(sqLiteDatabase, 19, "Eggs", "2", "pieces");

// Rice and Chicken Bowl
        insertRecipeIngredient(sqLiteDatabase, 20, "Rice", "1", "kg");
        insertRecipeIngredient(sqLiteDatabase, 20, "Chicken", "1", "kg");

    }


    private void insertUser(SQLiteDatabase db,
                            String username,
                            String email,
                            String password) {

        ContentValues cv = new ContentValues();

        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);

        db.insert("users", null, cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE username=? and password =?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;

    }
    private void insertItem(SQLiteDatabase db,
                            String name,
                            String quantity,
                            String unit,
                            String expiry) {

        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("quantity", quantity);
        cv.put("unit", unit);
        cv.put("expiry_date", expiry);

        db.insert("pantry_items", null, cv);
    }
    public void newitem(String name,
                        String quantity,
                        String unit,
                        String expiry) {

        ContentValues cv = new ContentValues();

        cv.put("name", name);
        cv.put("quantity", quantity);
        cv.put("unit", unit);
        cv.put("expiry_date", expiry);

        SQLiteDatabase db = getWritableDatabase();

        db.insert("pantry_items", null, cv);

        db.close();
    }
        public Cursor getAllPantryItems() {
            SQLiteDatabase db = getReadableDatabase();
            return db.rawQuery("SELECT * FROM pantry_items", null);
        }
    public void deleteItem(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("pantry_items", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void updateItem(int id, String name, String quantity, String unit, String expiry) {
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("quantity", quantity);
        cv.put("unit", unit);
        cv.put("expiry_date", expiry);

        SQLiteDatabase db = getWritableDatabase();
        db.update("pantry_items", cv, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }
    private void insertRecipe(SQLiteDatabase db,
                              String recipeName,
                              String instructions) {

        ContentValues cv = new ContentValues();

        cv.put("recipeName", recipeName);
        cv.put("instructions", instructions);

        db.insert("recipes", null, cv);
    }
    private void insertRecipeIngredient(SQLiteDatabase db,
                                        int recipeId,
                                        String ingredientName,
                                        String requiredAmount,
                                        String unit) {

        ContentValues cv = new ContentValues();

        cv.put("recipeid", recipeId);
        cv.put("ingredientname", ingredientName);
        cv.put("requiredAmount", requiredAmount);
        cv.put("unit", unit);

        db.insert("recipeingredients", null, cv);
    }

}





