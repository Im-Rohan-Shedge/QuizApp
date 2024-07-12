package com.example.indiaquiz;
//git
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<modelclass> listofq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listofq = new ArrayList<>();
        listofq.add(new modelclass("What is the size of int variable?", "8 bit", "16 bit", "32 bit", "64 bit", "32 bit"));
        listofq.add(new modelclass("What is the default value of float variable?", "0.0d", "0.0f", "0", "0.0", "0.0f"));
        listofq.add(new modelclass("Which data type value is returned by all transcendental math functions?", "int", "float", "double", "long", "double"));
        listofq.add(new modelclass("What is the default value of byte variable?", "0", "0.0", "null", "not defined", "0"));
        listofq.add(new modelclass("What is the range of short data type in Java?", "-128 to 127", "-32768 to 32767", "-2147483648 to 2147483647", "None of the mentioned", "-32768 to 32767"));
        listofq.add(new modelclass("Which of the following is a valid long literal?", "ABH8097", "L990023", "904423", "0xnf029L", "0xnf029L"));
        listofq.add(new modelclass("What is the default value of boolean variable?", "true", "false", "null", "not defined", "false"));
        listofq.add(new modelclass("Which method must be implemented by all threads?", "wait()", "start()", "stop()", "run()", "run()"));
        listofq.add(new modelclass("What is the default value of char variable?", "'\\u0000'", "0", "null", "not defined", "'\\u0000'"));
        listofq.add(new modelclass("Which keyword is used to prevent inheritance in Java?", "continue", "final", "super", "this", "final"));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isLoggedIn = getSharedPreferences("QuizAppPrefs", Context.MODE_PRIVATE)
                        .getBoolean("isLoggedIn", false);

                Intent intent;
                {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 1500);
    }
}
