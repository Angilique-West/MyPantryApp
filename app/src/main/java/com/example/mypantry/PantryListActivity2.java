package com.example.mypantry;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.database.Cursor;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PantryListActivity2 extends AppCompatActivity {
    RecyclerView pantryRecyclerView;
    PantryAdapter adapter;
    List<PantryItem> pantryList;


    {
        pantryList = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pantry_list2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(v -> finish());

        pantryRecyclerView = findViewById(R.id.pantryRecyclerView);
        pantryRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Database db = new Database(this, "pantry", null, 1);
        SQLiteDatabase database = db.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM pantry_items", null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String quantity = cursor.getString(cursor.getColumnIndexOrThrow("quantity"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
            String expiry = cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"));

            pantryList.add(new PantryItem(id, name, quantity, unit, expiry));
        }
        cursor.close();
        database.close();

        adapter = new PantryAdapter(this, pantryList);
        pantryRecyclerView.setAdapter(adapter);
    }
}








