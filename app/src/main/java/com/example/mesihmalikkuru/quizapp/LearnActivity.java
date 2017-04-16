package com.example.mesihmalikkuru.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.sleep;

/**
 * Created by mesihmalikkuru on 07/04/2017.
 */

public class LearnActivity extends AppCompatActivity {

    Object[] keys = MainActivity.dictionary.keySet().toArray();
    Object[] values = MainActivity.dictionary.values().toArray();

    TextView trTextView;
    TextView enTextView;

    Button nextButton;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        Intent intent = getIntent();

        trTextView = (TextView) findViewById(R.id.learn_turkish);
        enTextView = (TextView) findViewById(R.id.learn_english);

        nextButton = (Button) findViewById(R.id.next_learn_button);

        nextButton.callOnClick();
    }

    public void nextButtonClicked(View v){

        v.startAnimation(buttonClick);

        YoYo.with(Techniques.SlideInRight).duration(500).repeat(1).playOn(findViewById(R.id.learn_card));

        YoYo.with(Techniques.FlipInX).duration(1000).repeat(1).playOn(findViewById(R.id.learn_english));
        YoYo.with(Techniques.FlipInX).duration(1000).repeat(1).playOn(findViewById(R.id.learn_turkish));

        Random random = new Random();
        int n = random.nextInt(MainActivity.dictionary.size());

        String tr = (String) keys[n];
        String en = (String) values[n];

        trTextView.setText(tr.toUpperCase());
        enTextView.setText(en.toUpperCase());
    }
}
