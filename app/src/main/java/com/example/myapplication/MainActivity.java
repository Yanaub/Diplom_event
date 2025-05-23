package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;

import com.example.myapplication.ui.ProfileViewModel;
import com.example.myapplication.ui.clases.User;
import com.example.myapplication.ui.database.UserStorage;
import com.example.myapplication.ui.eventfun.EventAddActivity;
import com.example.myapplication.ui.transform.TransformViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ProfileViewModel mViewModel;

    private TransformViewModel tViewModel;
    public boolean role=false;
    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String userId=intent.getStringExtra("userId");
        UserStorage userStorage = new UserStorage(this);

        User user = userStorage.getUserById(userId);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setUserId(userId);
        profileViewModel.setUserName(user.getName());
        profileViewModel.setUserEmail(user.getEmail());
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        mViewModel.getUserRole().observe(this, role -> {
            this.role=role;
            TransformViewModel transformViewModel = new ViewModelProvider(this).get(TransformViewModel.class);
            transformViewModel.setUserId(userId);
            transformViewModel.setUserRole(role);
        });








        setSupportActionBar(binding.appBarMain.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_content_main);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_transform, R.id.nav_ex, R.id.nav_profile)
                .setOpenableLayout(binding.drawerLayout)
                .build();


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FloatingActionButton fab = findViewById(R.id.fab);

        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем intent для перехода в новую активность
                Intent intent = new Intent(MainActivity.this, EventAddActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent); // Запуск новой активности
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* boolean result = super.onCreateOptionsMenu(menu);
        // Using findViewById because NavigationView exists in different layout files
        // between w600dp and w1240dp
        NavigationView navView = findViewById(R.id.nav_view);
        if (navView == null) {
            // The navigation drawer already has the items including the items in the overflow menu
      */      // We only inflate the overflow menu if the navigation drawer isn't visible
        getMenuInflater().inflate(R.menu.overflow, menu);
        // }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_settings) {
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            navController.navigate(R.id.nav_settings);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

