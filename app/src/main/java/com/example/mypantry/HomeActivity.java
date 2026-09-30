package com.example.mypantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;
public class HomeActivity extends AppCompatActivity {


    Button pantryList,addItems, recipes;

    Toolbar settingsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home2);

         pantryList = findViewById(R.id.buttonPantrylist);
        addItems = findViewById(R.id.buttonAddNewItem);
        recipes = findViewById(R.id.buttonRecipes);
        settingsToolbar = findViewById(R.id.settingsToolbar);

        pantryList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PantryListActivity2.class));
            }
        });
        recipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,SuggestRecipesActivity2.class));
            }
        });
        addItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,AddNewItemActivity2.class));
            }
        });
        settingsToolbar.setNavigationOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, SettingsActivity2.class));
        });

    }
}