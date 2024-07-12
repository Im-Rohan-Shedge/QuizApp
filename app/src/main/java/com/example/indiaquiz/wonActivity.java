


package com.example.indiaquiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class wonActivity extends AppCompatActivity {

    CircularProgressBar circularProgressBar;
    TextView result;
    int correct,wrong;
    LinearLayout btnshare;
    ImageView icback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);
        correct=getIntent().getIntExtra("correct",0);
        wrong=getIntent().getIntExtra("wrong",0);
        circularProgressBar=findViewById(R.id.circularProgressBar);
        result=findViewById(R.id.result);
        btnshare=findViewById(R.id.btnshare);
        icback = findViewById(R.id.icback);

        circularProgressBar.setProgress(correct);
        result.setText(correct+"/10");

        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nI Got "+correct+"Out Of 20 \nYou Should Try Too...!\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=com.example.indiaquiz\n\n"; // Hardcoded application ID
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wonActivity.this, HomePageActivity.class);

                startActivity(intent);
            }
        });
    }}