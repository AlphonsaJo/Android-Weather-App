package com.thinkseedsystems.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.thinkseedsystems.weatherapp.databinding.ActivitySplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    // Splash screen display time in milliseconds
    private static final int SPLASH_TIME = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            // Initialize view binding for splash screen layout
            ActivitySplashScreenBinding binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            splashScreen();
        } catch (Exception e) {
            Log.e("SplashScreen", "Error in onCreate: " + e.getMessage(), e);
        }
    }

    private void splashScreen() {
        Log.d("SplashScreen", "Splash screen started, will transition in " + SPLASH_TIME + " ms");

        new Handler().postDelayed(() -> {
            try {
                // Navigating to MainActivity after the splash screen duration
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish(); // Finish this activity so it's not in the back stack
                Log.d("SplashScreen", "Navigating to MainActivity");
            } catch (Exception e) {
                Log.e("SplashScreen", "Error transitioning to MainActivity: " + e.getMessage(), e);
            }
        }, SPLASH_TIME);
    }
}