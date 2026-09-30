package com.example.mypantry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SuggestRecipesActivity2 extends AppCompatActivity {
    ListView recipeListView;
    ArrayList<String> suggestedRecipes;
    ArrayAdapter<String> adapter;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_suggest_recipes2);
        recipeListView = findViewById(R.id.recipeListView);

        Toolbar toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar3.setNavigationOnClickListener(v -> finish());

        suggestedRecipes = new ArrayList<>();

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                suggestedRecipes
        );

        recipeListView.setAdapter(adapter);

        db = new Database(this, "pantry", null, 1);

        findSuggestedRecipes();
    }

    private void findSuggestedRecipes() {

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor recipes = database.rawQuery(
                "SELECT id, recipeName FROM recipes",
                null
        );

        while (recipes.moveToNext()) {

            int recipeId = recipes.getInt(
                    recipes.getColumnIndexOrThrow("id")
            );

            String recipeName = recipes.getString(
                    recipes.getColumnIndexOrThrow("recipeName")
            );

            if (canMakeRecipe(database, recipeId)) {
                suggestedRecipes.add(recipeName);
            }
        }

        recipes.close();

        database.close();

        adapter.notifyDataSetChanged();

        if (suggestedRecipes.isEmpty()) {

            Toast.makeText(
                    this,
                    "No recipes can be made with your current pantry.",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    private boolean canMakeRecipe(
            SQLiteDatabase database,
            int recipeId) {

        Cursor ingredients = database.rawQuery(
                "SELECT ingredientname, requiredAmount, unit " +
                        "FROM recipeingredients " +
                        "WHERE recipeid=?",
                new String[]{String.valueOf(recipeId)}
        );

        while (ingredients.moveToNext()) {

            String ingredientName =
                    ingredients.getString(
                            ingredients.getColumnIndexOrThrow(
                                    "ingredientname"
                            )
                    );

            double requiredAmount =
                    Double.parseDouble(
                            ingredients.getString(
                                    ingredients.getColumnIndexOrThrow(
                                            "requiredAmount"
                                    )
                            )
                    );

            String requiredUnit =
                    ingredients.getString(
                            ingredients.getColumnIndexOrThrow(
                                    "unit"
                            )
                    );

            // Look for ingredient in pantry
            Cursor pantry = database.rawQuery(
                    "SELECT quantity, unit FROM pantry_items " +
                            "WHERE LOWER(name)=LOWER(?)",
                    new String[]{ingredientName}
            );

            if (!pantry.moveToFirst()) {

                pantry.close();
                ingredients.close();

                return false;
            }

            double pantryAmount =
                    Double.parseDouble(
                            pantry.getString(
                                    pantry.getColumnIndexOrThrow(
                                            "quantity"
                                    )
                            )
                    );

            String pantryUnit =
                    pantry.getString(
                            pantry.getColumnIndexOrThrow("unit")
                    );

            pantry.close();

            // Units must match
            if (!pantryUnit.equalsIgnoreCase(requiredUnit)) {

                ingredients.close();

                return false;
            }

            // Pantry quantity must be enough
            if (pantryAmount < requiredAmount) {

                ingredients.close();

                return false;
            }
        }

        ingredients.close();

        return true;
    }
}


