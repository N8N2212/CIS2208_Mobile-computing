package com.example.myfitness;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.myfitness.databinding.ActivityMainBinding;
import android.content.Intent;

public class main_activity extends AppCompatActivity {

    private NavController navController;
    private ActivityMainBinding binding;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleIntent(getIntent());
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupNavigation();
    }

    private void setupNavigation() {
        try {
            navController = Navigation.findNavController(this, R.id.nav_host_fragment);
            AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_bmi)
                    .build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController);
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize NavController", e);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (intent != null && intent.getExtras() != null) {
            String navigateTo = intent.getExtras().getString("navigateTo");
            if (navigateTo != null && navController != null) {
                switch (navigateTo) {
                    case "home":
                        navController.navigate(R.id.navigation_home);
                        break;
                    case "dashboard":
                        navController.navigate(R.id.navigation_dashboard);
                        break;
                    case "bmi":
                        navController.navigate(R.id.navigation_bmi);
                        break;
                    default:
                        Log.w(TAG, "Unknown navigation target: " + navigateTo);
                        break;
                }
            } else {
                Log.e(TAG, "NavController is not initialized or navigateTo is null");
            }
        }
    }
}
