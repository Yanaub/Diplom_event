package com.example.myapplication.ui.database;

import android.content.Context;
import android.util.Log;

import com.example.myapplication.ui.clases.Event;
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

public class EventStorage {
    private static final String FILE_NAME = "events.json";
    private Context context;
    private Gson gson;
    private int lastEventId;  // Переменная для хранения последнего использованного ID события

    public EventStorage(Context context) {
        this.context = context;
        this.gson = new Gson();
        this.lastEventId = loadLastEventId();  // Загружаем последний ID из файла событий или инициализируем его
    }

    // Метод для загрузки последних ID события из файла
    private int loadLastEventId() {
        List<Event> events = getAllEvents();
        if (events.isEmpty()) {
            return 0;  // Начинаем с 0, если событий нет
        } else {
            return events.size();  // Последний ID равен размеру списка (инкрементальный)
        }
    }

    // Метод для добавления нового события
    public void addEvent(Event event) {
        List<Event> events = getAllEvents();
        lastEventId++; // Увеличиваем последний использованный ID на 1
        event.setId(String.valueOf(lastEventId)); // Устанавливаем новый ID событию
        events.add(event);
        saveEvents(events);
    }

    // Метод для получения всех событий
    public List<Event> getAllEvents() {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<Event>();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Type eventListType = new TypeToken<ArrayList<Event>>() {}.getType();
            return gson.fromJson(br, eventListType);
        } catch (IOException e) {
            Log.e("EventStorage", "Error reading events from file", e);
            return new ArrayList<Event>();
        }
    }

    // Метод для сохранения событий в файл
    private void saveEvents(List<Event> events) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(events, writer);
        } catch (IOException e) {
            Log.e("EventStorage", "Error saving events to file", e);
        }
    }

    // Метод для получения события по ID
    public Event getEventById(String eventId) {
        List<Event> events = getAllEvents();
        for (Event event : events) {
            if (event.getId().equals(eventId)) {
                return event;
            }
        }
        return null; // Если событие не найдено
    }

    // Метод для обновления события
    public void updateEvent(Event updatedEvent) {
        List<Event> events = getAllEvents();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getId().equals(updatedEvent.getId())) {
                events.set(i, updatedEvent); // Заменяем старые данные на обновленные
                break;
            }
        }
        saveEvents(events); // Сохраняем изменения
    }}
