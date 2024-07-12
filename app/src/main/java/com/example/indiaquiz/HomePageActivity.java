package com.example.indiaquiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView profileInfo, profileEmail;
    private ImageView profileImage;
    private Button startQuizButton;
    private Button logoutButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        String username = getIntent().getStringExtra("username");

        profileImage = findViewById(R.id.profile_image);
        profileInfo = findViewById(R.id.profile_info);
        profileEmail = findViewById(R.id.profile_email);
        startQuizButton = findViewById(R.id.start_quiz_button);
        logoutButton = findViewById(R.id.logout_button);

        // Assuming user's name and email are stored in SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString("name", "Guest");


        profileInfo.setText("Welcome...! " );
        profileEmail.setText(username);

        startQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("isLoggedIn", false);
                editor.apply();

                Intent intent = new Intent(HomePageActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
