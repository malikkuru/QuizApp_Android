package com.example.mesihmalikkuru.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private HashMap<String, String> dictionary;

    private Button btnLearn;
    private Button btnAddItem;
    private Button btnQuiz;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLearn = (Button) findViewById(R.id.learn);
        btnLearn.setOnClickListener(this);
        btnAddItem = (Button) findViewById(R.id.addItem);
        btnAddItem.setOnClickListener(this);
        btnQuiz = (Button) findViewById(R.id.quiz);
        btnQuiz.setOnClickListener(this);

        dictionary = new HashMap<String, String>();
        readAll();
    }



    public void readAll() {
        Scanner s = new Scanner(getResources().openRawResource(R.raw.dictionary));
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] word = line.split("-");
            dictionary.put(word[0], word[1]);
        }
        Toast.makeText(this, "sdfa", Toast.LENGTH_SHORT);
        s.close();
    }

    @Override
    public void onClick(View v) {

        Intent intent = null;
        switch(v.getId()){
            case R.id.learn:
                intent = new Intent(this, LearnActivity.class);
                Log.i("Test", "Learn");
                break;
            case R.id.addItem:
                intent = new Intent(this, AddItemActivity.class);
                Log.i("Test", "add item");
                break;
            case R.id.quiz:
                intent = new Intent(this, QuizActivity.class);
                Log.i("Test", "quiz");
                break;
            default:
                break;

        }
        if(intent != null)
            this.startActivity(intent);

        /*
        Toast.makeText(this, "aaaaaa", Toast.LENGTH_SHORT);
        if (v.equals(btnLearn)) {
            Toast.makeText(this, "LEARN", Toast.LENGTH_SHORT);
        }
        else if (v.equals(btnAddItem)) {
            Toast.makeText(this, "ADD ITEM", Toast.LENGTH_SHORT);
        }
        else if (v.equals(btnQuiz)) {
            Toast.makeText(this, "QUIZ", Toast.LENGTH_SHORT);
        }*/
    }
}