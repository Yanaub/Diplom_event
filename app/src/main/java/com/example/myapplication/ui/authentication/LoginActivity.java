package com.example.myapplication.ui.authentication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.clases.User;
import com.example.myapplication.ui.database.UserStorage;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;
    TextView forgot;
    TextView create;

    UserStorage userStorage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.emailL);
        password = findViewById(R.id.passwordL);
        forgot = findViewById(R.id.forgot);
        create = findViewById(R.id.create);
        loginButton = findViewById(R.id.loginButton);

        userStorage = new UserStorage(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();
                String name = "";
                String id="";

                List<User> users = userStorage.getAllUsers();

                boolean userExists = false;

                for (User user : users) {
                    if (user.getEmail().equals(inputEmail) && user.getPass().equals(inputPassword)) {
                        userExists = true;
                        name=user.getName();
                        id=user.getId();

                        break;
                    }
                }

                if (userExists) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                    intent.putExtra("userId",id);

                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Такого пользователя не существует!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });
    }
}
