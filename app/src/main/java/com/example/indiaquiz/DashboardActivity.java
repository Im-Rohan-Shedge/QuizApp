package com.example.indiaquiz;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    List<modelclass> allquestionlist;
    CountDownTimer countDownTimer;
    int timervalue = 20;
    private ProgressBar progressBar;

    modelclass model;
    int index = 0;
    TextView cardquestion, cardoptiona, cardoptionb, cardoptionc, cardoptiond, scoreboard;
    CardView Qcard, Acard, Bcard, Ccard, Dcard;
    ImageView icback;
    int correctcount = 0;
    int wrongcount = 0;
    LinearLayout nxtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        allquestionlist = MainActivity.listofq;
        Collections.shuffle(allquestionlist);
        model = allquestionlist.get(index);
        hook();
        setalldata();

        icback = findViewById(R.id.icback);
        scoreboard=findViewById(R.id.scoreboard);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(20); // Set max value of progress bar
        progressBar.setProgress(timervalue);


        startCountDownTimer();
        icback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, HomePageActivity.class);

                startActivity(intent);
            }
        });

    }

    private void updatescore() {
        scoreboard.setText("Score: " + correctcount);
    }


    private void setalldata() {
        timervalue = 20;
        if (progressBar != null) {
            progressBar.setProgress(timervalue);
        }
        cardquestion.setText(model.getQuestion());
        cardoptiona.setText(model.getOA());
        cardoptionb.setText(model.getOB());
        cardoptionc.setText(model.getOC());
        cardoptiond.setText(model.getOD());
    }

    private void hook() {
        cardquestion = findViewById(R.id.cardquestion);
        cardoptiona = findViewById(R.id.cardoptiona);
        cardoptionb = findViewById(R.id.cardoptionb);
        cardoptionc = findViewById(R.id.cardoptionc);
        cardoptiond = findViewById(R.id.cardoptiond);
        nxtbtn = findViewById(R.id.nxtbtn);
        Qcard = findViewById(R.id.Qcard);
        Acard = findViewById(R.id.Acard);
        Bcard = findViewById(R.id.Bcard);
        Ccard = findViewById(R.id.Ccard);
        Dcard = findViewById(R.id.Dcard);

        Acard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(Acard, cardoptiona.getText().toString());
            }
        });

        Bcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(Bcard, cardoptionb.getText().toString());
            }
        });

        Ccard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(Ccard, cardoptionc.getText().toString());
            }
        });

        Dcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAnswerClick(Dcard, cardoptiond.getText().toString());
            }
        });
    }

    private void handleAnswerClick(CardView selectedCard, String selectedAnswer) {
        disablebtn();

        if (selectedAnswer.equals(model.getAns())) {
            selectedCard.setCardBackgroundColor(getResources().getColor(R.color.green));
            correct(selectedCard);
        } else {
            selectedCard.setCardBackgroundColor(getResources().getColor(R.color.red));
            wrong(selectedCard);
        }
    }

    private void correct(CardView selectedCard) {
        correctcount++;
        updatescore();
        nxtbtn.setVisibility(View.VISIBLE);
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index < allquestionlist.size()) {
                    model = allquestionlist.get(index);
                    setalldata();
                    resetcolor();
                    enablebtn();
                    nxtbtn.setVisibility(View.INVISIBLE);
                    startCountDownTimer(); // Restart the timer for the next question
                } else {
                    gamewon();
                }
            }
        });
    }

    private void wrong(CardView selectedCard) {
        wrongcount++;

        nxtbtn.setVisibility(View.VISIBLE);
        nxtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if (index < allquestionlist.size()) {
                    model = allquestionlist.get(index);
                    setalldata();
                    resetcolor();
                    enablebtn();
                    nxtbtn.setVisibility(View.INVISIBLE);
                    startCountDownTimer(); // Restart the timer for the next question
                } else {
                    gamewon();
                }
            }
        });
    }

    private void gamewon() {
        Intent intent = new Intent(DashboardActivity.this, wonActivity.class);
        intent.putExtra("correct", correctcount);
        intent.putExtra("wrong", wrongcount);
        startActivity(intent);
    }

    private void enablebtn() {
        Acard.setClickable(true);
        Bcard.setClickable(true);
        Ccard.setClickable(true);
        Dcard.setClickable(true);
    }

    private void disablebtn() {
        Acard.setClickable(false);
        Bcard.setClickable(false);
        Ccard.setClickable(false);
        Dcard.setClickable(false);
    }

    private void resetcolor() {
        Acard.setCardBackgroundColor(getResources().getColor(R.color.white));
        Bcard.setCardBackgroundColor(getResources().getColor(R.color.white));
        Ccard.setCardBackgroundColor(getResources().getColor(R.color.white));
        Dcard.setCardBackgroundColor(getResources().getColor(R.color.white));
    }

    private void showTimeoutDialog() {
        Dialog dialog = new Dialog(DashboardActivity.this);
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        dialog.setContentView(R.layout.dialoguetimeout);
        dialog.show();
        dialog.findViewById(R.id.btntryagain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish(); // Close current activity after retrying
            }
        });
    }


    private void startCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        countDownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timervalue = (int) (millisUntilFinished / 1000);
                progressBar.setProgress(timervalue);
            }

            @Override
            public void onFinish() {
                showTimeoutDialog();
            }
        }.start();
    }

}