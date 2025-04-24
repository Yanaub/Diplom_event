package com.example.myapplication.ui.database;


import android.content.Context;
import android.util.Log;

import com.example.myapplication.ui.clases.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserStorage {
    private static final String FILE_NAME = "users1.json";
    private Context context;
    private Gson gson;
    private int lastUserId;  // Переменная для хранения последнего использованного ID

    public UserStorage(Context context) {
        this.context = context;
        this.gson = new Gson();
        this.lastUserId = loadLastUserId();  // Загружаем последний ID из файла пользователей или инициализируем его
    }

    // Метод для загрузки последнего ID пользователя из файла
    private int loadLastUserId() {
        List<User> users = getAllUsers();
        if (users.isEmpty()) {
            return 0;  // Начинаем с 0, если пользователей нет
        } else {
            return users.size();  // Последний ID равен размеру списка (инкрементальный)
        }
    }

    public void addUser(User user) {
        List<User> users = getAllUsers();
        lastUserId++; // Увеличиваем последний использованный ID на 1
        user.setId(String.valueOf(lastUserId)); // Устанавливаем новый ID пользователю
        users.add(user);
        saveUsers(users);
    }

    public List<User> getAllUsers() {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(br, userListType);
        } catch (IOException e) {
            Log.e("UserStorage", "Error reading users from file", e);
            return new ArrayList<>();
        }
    }

    private void saveUsers(List<User> users) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            Log.e("UserStorage", "Error saving users to file", e);
        }
    }
    public User getUserById(String userId) {
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null; // Если пользователь не найден
    }

    public void updateUser(User updatedUser) {
        List<User> users = getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(updatedUser.getId())) {
                users.set(i, updatedUser); // Заменяем старые данные на обновленные
                break;
            }
        }
        saveUsers(users); // Сохраняем изменения
    }
}
