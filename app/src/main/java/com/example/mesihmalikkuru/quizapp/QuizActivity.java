package com.example.mesihmalikkuru.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by mesihmalikkuru on 07/04/2017.
 */

public class QuizActivity extends AppCompatActivity {

    TextView question_text;
    TextView point_text;

    Button answerButtonA;
    Button answerButtonB;
    Button answerButtonC;
    Button answerButtonD;
    Button nextButton;

    Object[] keys = MainActivity.dictionary.keySet().toArray();
    Object[] values = MainActivity.dictionary.values().toArray();

    String answer;
    String question;

    String[] wrongAnswers;

    int choice;

    float point;

    Button trueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Intent intent = getIntent();


        question_text = (TextView) findViewById(R.id.question);

        answerButtonA = (Button) findViewById(R.id.answer_button_a);
        answerButtonB = (Button) findViewById(R.id.answer_button_b);
        answerButtonC = (Button) findViewById(R.id.answer_button_c);
        answerButtonD = (Button) findViewById(R.id.answer_button_d);

        point_text = (TextView) findViewById(R.id.point);
        nextButton = (Button) findViewById(R.id.next_quiz_button);

        point = 0f;


        newQuestion();


    }

    public void newQuestion() {

        nextButton.setVisibility(View.INVISIBLE);

        Random random = new Random();
        int n = random.nextInt(MainActivity.dictionary.size());

        answer = (String) keys[n];
        question = (String) values[n];

        question_text.setText(question.toUpperCase());

        wrongAnswers = new String[3];

        for (int i = 0; i < 3; i++) {
            int wa = random.nextInt(MainActivity.dictionary.size());

            if (wa != n) {
                wrongAnswers[i] = (String) keys[wa];
            } else {
                i--;
            }
        }

        setButtons();
    }

    private void setButtons() {
        Random random = new Random();
        choice = random.nextInt(4);

        switch (choice){
            case 0:
                answerButtonA.setText(answer);
                answerButtonB.setText(wrongAnswers[0]);
                answerButtonC.setText(wrongAnswers[1]);
                answerButtonD.setText(wrongAnswers[2]);
                trueButton = answerButtonA;
                break;
            case 1:
                answerButtonB.setText(answer);
                answerButtonA.setText(wrongAnswers[0]);
                answerButtonC.setText(wrongAnswers[1]);
                answerButtonD.setText(wrongAnswers[2]);
                trueButton = answerButtonB;
                break;
            case 2:
                answerButtonC.setText(answer);
                answerButtonB.setText(wrongAnswers[0]);
                answerButtonA.setText(wrongAnswers[1]);
                answerButtonD.setText(wrongAnswers[2]);
                trueButton = answerButtonC;
                break;
            case 3:
                answerButtonD.setText(answer);
                answerButtonB.setText(wrongAnswers[0]);
                answerButtonC.setText(wrongAnswers[1]);
                answerButtonA.setText(wrongAnswers[2]);
                trueButton = answerButtonD;
                break;
        }
    }

    public void buttonClicked(View v) {
        Log.i("button", "buton2");
        switch(v.getId()){
            case R.id.answer_button_a:
                Log.i("button", "buttonclicked2");
                if (answerButtonA.equals(trueButton)){
                    answerButtonA.setBackground(getResources().getDrawable(R.drawable.answer_true_bg));
                    point += 1;
                } else {
                    answerButtonA.setBackground(getResources().getDrawable(R.drawable.answer_false_bg));
                    point -= 0.5f;
                }
                break;
            case R.id.answer_button_b:
                if (answerButtonB.equals(trueButton)){
                    answerButtonB.setBackground(getResources().getDrawable(R.drawable.answer_true_bg));
                    point += 1;
                } else {
                    answerButtonB.setBackground(getResources().getDrawable(R.drawable.answer_false_bg));
                    point -= 0.5f;
                }
                break;
            case R.id.answer_button_c:
                if (answerButtonC.equals(trueButton)){
                    answerButtonC.setBackground(getResources().getDrawable(R.drawable.answer_true_bg));
                    point += 1;
                } else {
                    answerButtonC.setBackground(getResources().getDrawable(R.drawable.answer_false_bg));
                    point -= 0.5f;
                }
                break;
            case R.id.answer_button_d:
                if (answerButtonD.equals(trueButton)){
                    answerButtonD.setBackground(getResources().getDrawable(R.drawable.answer_true_bg));
                    point += 1;
                } else {
                    answerButtonD.setBackground(getResources().getDrawable(R.drawable.answer_false_bg));
                    point -= 0.5f;
                }
                break;
            default:
                break;

        }

        answerButtonA.setClickable(false);
        answerButtonB.setClickable(false);
        answerButtonC.setClickable(false);
        answerButtonD.setClickable(false);

        point_text.setText("POINT : " + point);
        trueButton.setBackground(getResources().getDrawable(R.drawable.answer_true_bg));

        nextButton.setVisibility(View.VISIBLE);
    }

    public void nextButtonClicked(View v) {
        answerButtonA.setBackground(getResources().getDrawable(R.drawable.answer_bg));
        answerButtonB.setBackground(getResources().getDrawable(R.drawable.answer_bg));
        answerButtonC.setBackground(getResources().getDrawable(R.drawable.answer_bg));
        answerButtonD.setBackground(getResources().getDrawable(R.drawable.answer_bg));

        answerButtonA.setClickable(true);
        answerButtonB.setClickable(true);
        answerButtonC.setClickable(true);
        answerButtonD.setClickable(true);

        newQuestion();
    }

}
