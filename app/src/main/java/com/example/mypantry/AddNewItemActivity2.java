package com.example.mypantry;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class AddNewItemActivity2 extends AppCompatActivity {

    EditText edIngredientName, edQuantity;
    Spinner unitSpinner;
    TextView expiryDate;
    Button saveButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_item2);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(v -> finish());


        edIngredientName = findViewById(R.id.editTextIngredientName2);
        edQuantity = findViewById(R.id.editTextQuantity2);
        unitSpinner = findViewById(R.id.spinnerUnit2);
        expiryDate = findViewById(R.id.textViewExpiryDate2);
        saveButton = findViewById(R.id.buttonSave1);
        cancelButton = findViewById(R.id.buttonCancel1);

        expiryDate.setOnClickListener(v -> showDatePicker());


     saveButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = edIngredientName.getText().toString();
            String quantity = edQuantity.getText().toString();
            String unit = unitSpinner.getSelectedItem().toString();
            String expiry = expiryDate.getText().toString();

            Database db = new Database(getApplicationContext(), "pantry", null, 1);

            if (name.length() == 0 || quantity.length() == 0 || unit.length() == 0 || expiry.length() == 0) {
                Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {
                db.newitem(name, quantity, unit, expiry);
                Toast.makeText(getApplicationContext(), "Item saved successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    });
    cancelButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish(); // Simply close the activity without saving
        }
    });
    }

    private void showDatePicker() {

    final Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);

    DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, selectedYear, selectedMonth, selectedDay) -> {
        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
        expiryDate.setText(date);
    }, year, month, day);
        datePickerDialog.show();}
}



