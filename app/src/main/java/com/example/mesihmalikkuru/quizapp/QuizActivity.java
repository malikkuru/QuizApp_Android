package com.example.mesihmalikkuru.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
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

    ArrayList<String> wrongAnswers;
    ArrayList<Integer> willAskQuestionNumbers;

    int choice;

    float point;

    Button trueButton;

    Random random = new Random();


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

        wrongAnswers = new ArrayList<String>();
        willAskQuestionNumbers = new ArrayList<Integer>();

        point = 0f;

        fillWillAskQuestionNumbers();

        newQuestion();
    }

    public void newQuestion() {

        nextButton.setVisibility(View.INVISIBLE);

        int r = random.nextInt(willAskQuestionNumbers.size());
        int n = willAskQuestionNumbers.get(r);

        willAskQuestionNumbers.remove(willAskQuestionNumbers.get(r));

        answer = (String) keys[n];
        question = (String) values[n];

        question_text.setText(question.toUpperCase());

        wrongAnswers.clear();

        for (int i = 0; i < 3; i++) {
            int wa = random.nextInt(MainActivity.dictionary.size());

            if (wa != n && !wrongAnswers.contains((String) keys[wa])) {
                wrongAnswers.add((String) keys[wa]);
            } else {
                i--;
            }
        }

        if(willAskQuestionNumbers.size() == 0) {
            if(point < 0) {
                Intent intent = new Intent(this, LearnActivity.class);
                this.startActivity(intent);
                finish();
            }
            fillWillAskQuestionNumbers();
        }

        setButtons();

        YoYo.with(Techniques.SlideInRight).duration(1000).repeat(1).playOn(findViewById(R.id.quiz_card));
    }

    private void fillWillAskQuestionNumbers() {
        for (int i = 0; i < MainActivity.dictionary.size(); i++) {
            willAskQuestionNumbers.add(i);
        }
    }

    private void setButtons() {
        choice = random.nextInt(4);

        switch (choice){
            case 0:
                answerButtonA.setText(answer);
                answerButtonB.setText(wrongAnswers.get(0));
                answerButtonC.setText(wrongAnswers.get(1));
                answerButtonD.setText(wrongAnswers.get(2));
                trueButton = answerButtonA;
                break;
            case 1:
                answerButtonB.setText(answer);
                answerButtonA.setText(wrongAnswers.get(0));
                answerButtonC.setText(wrongAnswers.get(1));
                answerButtonD.setText(wrongAnswers.get(2));
                trueButton = answerButtonB;
                break;
            case 2:
                answerButtonC.setText(answer);
                answerButtonB.setText(wrongAnswers.get(0));
                answerButtonA.setText(wrongAnswers.get(1));
                answerButtonD.setText(wrongAnswers.get(2));
                trueButton = answerButtonC;
                break;
            case 3:
                answerButtonD.setText(answer);
                answerButtonB.setText(wrongAnswers.get(0));
                answerButtonC.setText(wrongAnswers.get(1));
                answerButtonA.setText(wrongAnswers.get(2));
                trueButton = answerButtonD;
                break;
        }
    }

    public void buttonClicked(View v) {
        switch(v.getId()){
            case R.id.answer_button_a:
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
