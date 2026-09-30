package com.example.mypantry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignInActivity2 extends AppCompatActivity {
EditText edUsername,edEmail,edPassword,edConfirmPassword;
Button btn;
TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in2);

        edUsername = findViewById(R.id.editTextSignInUsername);
        edEmail = findViewById(R.id.editTextEmailAddress);
        edPassword = findViewById(R.id.editTextPassword);
        edConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        btn = findViewById(R.id.SignInbtn);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignInActivity2.this,loginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirmPassword.getText().toString();
                Database db = new Database(getApplicationContext(), "pantry",null, 1);
                if (username.length() == 0 || email.length() == 0 || password.length() == 0 || confirm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        db.register(username, email, password);
                        Toast.makeText(getApplicationContext(), "Account Created Successfully, Please Login!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignInActivity2.this, loginActivity.class));

                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match!", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });


    }
}