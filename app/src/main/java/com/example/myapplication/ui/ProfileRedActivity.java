package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.ui.clases.User;
import com.example.myapplication.ui.database.UserStorage;

public class ProfileRedActivity extends AppCompatActivity {
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_red);

        ImageButton closeButton = findViewById(R.id.arrowShowRedPr);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EditText name = findViewById(R.id.ProfRedName);
        EditText email = findViewById(R.id.ProfRedEm);
        EditText pass = findViewById(R.id.ProfRedPass);
        Button saveButton = findViewById(R.id.ProfRedButton);

        // Получение ID из интента
        String userId = getIntent().getStringExtra("USER_ID");



        UserStorage userStorage = new UserStorage(this);
        User user = userStorage.getUserById(userId);

        if (user != null) {
            name.setText(user.getName());
            email.setText(user.getEmail());
            pass.setText(user.getPass());
        }


       saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String updatedName = name.getText().toString();
                String updatedEmail = email.getText().toString();
                String updatedPass = pass.getText().toString();


                user.setName(updatedName);
                user.setEmail(updatedEmail);
                user.setPass(updatedPass);
                userStorage.updateUser(user);

                finish(); // Закрываем активность
                finish(); // Закрываем активность
            }
        });
    }
}
