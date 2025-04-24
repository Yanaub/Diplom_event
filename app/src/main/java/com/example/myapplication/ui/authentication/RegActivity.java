package com.example.myapplication.ui.authentication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.ui.clases.User;
import com.example.myapplication.ui.database.UserStorage;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegActivity extends AppCompatActivity {
    EditText username;
    EditText email;
    EditText password;
    Button regButton;
    TextView inAcc;

    UserStorage userStorage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg);

        // Инициализация элементов
        username = findViewById(R.id.nameR);
        email = findViewById(R.id.emailR);
        password = findViewById(R.id.passwordR);
        regButton = findViewById(R.id.regButton);

        // Инициализация UserStorage
        userStorage = new UserStorage(this);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email2 = email.getText().toString().trim();
                String p = password.getText().toString().trim();
                String n = username.getText().toString().trim();

                if (isValidEmail(email2)) {
                    // Проверка на существующего пользователя
                    List<User> users = userStorage.getAllUsers();
                    boolean userExists = false;

                    for (User user : users) {
                        if (user.getEmail().equals(email2)) {
                            userExists = true;
                            break;
                        }
                    }

                    if (userExists) {
                        Toast.makeText(getApplicationContext(), "Пользователь с таким email уже существует!", Toast.LENGTH_SHORT).show();
                        email.setBackgroundResource(R.drawable.edittext_error);
                    } else {
                        // Создаем нового пользователя
                        User newUser = new User("",n, email2, p);
                        userStorage.addUser(newUser); // Метод для добавления пользователя в UserStorage
                        Toast.makeText(getApplicationContext(), "Регистрация успешна!", Toast.LENGTH_SHORT).show();

                        // Переход к главному экрану
                        Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Некорректный адрес электронной почты", Toast.LENGTH_SHORT).show();
                    email.setBackgroundResource(R.drawable.edittext_error);
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
