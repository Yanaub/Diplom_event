package com.example.myapplication.ui.eventfun;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.example.myapplication.ui.clases.Event;
import com.example.myapplication.ui.database.EventStorage;

import java.util.ArrayList;

public class EventAddActivity extends AppCompatActivity {
    private EventStorage eventStorage; // Хранение событий

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_event_add);
        Intent intent = getIntent();
        String userId=intent.getStringExtra("userId");
        // Инициализация класса EventStorage
        eventStorage = new EventStorage(this);

        ImageButton closeButton = findViewById(R.id.arrowShowAdd);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        EditText name = findViewById(R.id.titleEventAdd);
        EditText info = findViewById(R.id.descriptionEventAdd);
        EditText pl = findViewById(R.id.locationEventAdd);
        EditText data = findViewById(R.id.dateEventAdd);
        EditText time = findViewById(R.id.timeEventAdd);
        Button save = findViewById(R.id.EventAddButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получение введенных данных
                String eventName = name.getText().toString().trim();
                String eventInfo = info.getText().toString().trim();
                String eventPlace = pl.getText().toString().trim();
                String eventData = data.getText().toString().trim();
                String eventTime = time.getText().toString().trim();

                // Создание нового события
                Event newEvent = new Event("", userId, eventName, eventInfo,
                        eventPlace, eventData, eventTime, 0, new ArrayList<>());

                // Сохранение события
                eventStorage.addEvent(newEvent);

                // Закрытие активности после сохранения
                finish();
            }
        });
    }
}
