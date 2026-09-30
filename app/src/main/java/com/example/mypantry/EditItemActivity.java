package com.example.mypantry;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;

public class EditItemActivity extends AppCompatActivity {

    EditText edName, edQuantity;
    Spinner spinnerUnit;
    TextView tvExpiry;
    Button btnSave, btnCancel;

    Database db;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_item);


        // Connect Java to XML
        edName = findViewById(R.id.editTextIngredientName2);
        edQuantity = findViewById(R.id.editTextQuantity2);
        spinnerUnit = findViewById(R.id.spinnerUnit2);
        tvExpiry = findViewById(R.id.textViewExpiryDate2);
        btnSave = findViewById(R.id.buttonSave1);
        btnCancel = findViewById(R.id.buttonCancel1);

        // Database
        db = new Database(this, "pantry", null, 1);

        // Get the ID sent from PantryAdapter
        itemId = getIntent().getIntExtra("ITEM_ID", -1);

        // Load existing item information
        loadItem();

        // Click expiry date
        tvExpiry.setOnClickListener(v -> showDatePicker());

        // Save changes
        btnSave.setOnClickListener(v -> {

            String name = edName.getText().toString().trim();
            String quantity = edQuantity.getText().toString().trim();
            String unit = spinnerUnit.getSelectedItem().toString();
            String expiry = tvExpiry.getText().toString().trim();

            // Validation
            if (name.isEmpty()) {
                edName.setError("Enter item name");
                return;
            }

            if (quantity.isEmpty()) {
                edQuantity.setError("Enter quantity");
                return;
            }

            if (expiry.isEmpty() || expiry.equals("Select expiry date")) {
                Toast.makeText(
                        this,
                        "Please select an expiry date",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            // Update database
            db.updateItem(
                    itemId,
                    name,
                    quantity,
                    unit,
                    expiry
            );

            Toast.makeText(
                    this,
                    "Item updated successfully!",
                    Toast.LENGTH_SHORT
            ).show();

            // Return to pantry list
            finish();
        });

        // Cancel
        btnCancel.setOnClickListener(v -> finish());
    }

    private void loadItem() {

        SQLiteDatabase database = db.getReadableDatabase();

        Cursor cursor = database.rawQuery(
                "SELECT * FROM pantry_items WHERE id=?",
                new String[]{String.valueOf(itemId)}
        );

        if (cursor.moveToFirst()) {

            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );

            String quantity = cursor.getString(
                    cursor.getColumnIndexOrThrow("quantity")
            );

            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow("unit")
            );

            String expiry = cursor.getString(
                    cursor.getColumnIndexOrThrow("expiry_date")
            );

            // Put existing values into the form
            edName.setText(name);
            edQuantity.setText(quantity);
            tvExpiry.setText(expiry);

            // Select existing unit in Spinner
            for (int i = 0; i < spinnerUnit.getCount(); i++) {

                if (spinnerUnit.getItemAtPosition(i)
                        .toString()
                        .equals(unit)) {

                    spinnerUnit.setSelection(i);
                    break;
                }
            }
        }

        cursor.close();
        database.close();
    }

    private void showDatePicker() {

        final Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String date = selectedDay + "/" +
                            (selectedMonth + 1) + "/" +
                            selectedYear;

                    tvExpiry.setText(date);
                },
                year,
                month,
                day
        );

        datePickerDialog.show();
    }
}