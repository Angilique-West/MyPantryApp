package com.example.mypantry;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SettingsActivity2 extends AppCompatActivity {

    TextView textViewUsername;
    Switch switchExpiryAlerts;
    Spinner spinnerPreferredUnit;
    Button buttonLogout;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings2);
        textViewUsername = findViewById(R.id.textViewUsername);
        switchExpiryAlerts = findViewById(R.id.switchExpiryAlerts);
        spinnerPreferredUnit = findViewById(R.id.spinnerPreferredUnit);
        buttonLogout = findViewById(R.id.buttonLogout);

        sharedPreferences = getSharedPreferences(
                "shared_prefs",
                Context.MODE_PRIVATE
        );

        // Display logged-in username
        String username = sharedPreferences.getString("username", "User");
        textViewUsername.setText("Username: " + username);

        // Load saved alert setting
        boolean alertsEnabled = sharedPreferences.getBoolean(
                "expiry_alerts",
                true
        );

        switchExpiryAlerts.setChecked(alertsEnabled);

        // Save alert setting when changed
        switchExpiryAlerts.setOnCheckedChangeListener((buttonView, isChecked) -> {

            sharedPreferences.edit()
                    .putBoolean("expiry_alerts", isChecked)
                    .apply();
        });

        // Load preferred unit
        String savedUnit = sharedPreferences.getString(
                "preferred_unit",
                "kg"
        );

        for (int i = 0; i < spinnerPreferredUnit.getCount(); i++) {

            if (spinnerPreferredUnit
                    .getItemAtPosition(i)
                    .toString()
                    .equals(savedUnit)) {

                spinnerPreferredUnit.setSelection(i);
                break;
            }
        }

        // Save preferred unit
        spinnerPreferredUnit.setOnItemSelectedListener(
                new android.widget.AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(
                            android.widget.AdapterView<?> parent,
                            android.view.View view,
                            int position,
                            long id) {

                        String unit = parent
                                .getItemAtPosition(position)
                                .toString();

                        sharedPreferences.edit()
                                .putString("preferred_unit", unit)
                                .apply();
                    }

                    @Override
                    public void onNothingSelected(
                            android.widget.AdapterView<?> parent) {
                    }
                });

        // Logout
        buttonLogout.setOnClickListener(v -> {

            sharedPreferences.edit()
                    .remove("username")
                    .apply();

            Intent intent = new Intent(
                    SettingsActivity2.this,
                    loginActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK
            );

            startActivity(intent);
            finish();
        });
    }
}



