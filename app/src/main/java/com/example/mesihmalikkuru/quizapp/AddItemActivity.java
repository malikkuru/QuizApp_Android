package com.example.mesihmalikkuru.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by mesihmalikkuru on 07/04/2017.
 */

public class AddItemActivity extends AppCompatActivity {

    Button save;
    EditText tr;
    EditText en;

    public SharedPreferences sharedPreferences;

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Intent intent = getIntent();

        save = (Button) findViewById(R.id.save);
        tr = (EditText) findViewById(R.id.turkish_word);
        en = (EditText) findViewById(R.id.english_word);

        sharedPreferences = getSharedPreferences("dict", Context.MODE_PRIVATE);
    }

    private void addToList(String turkish, String english) {

        MainActivity.dictionary.put(turkish, english);
    }

    public void saveWords(View v){

        v.startAnimation(buttonClick);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (tr.getText().toString().equals("") || en.getText().toString().equals("")) {
            Toast.makeText(this, "PLEASE FILL ALL BLANKS", Toast.LENGTH_LONG).show();
            return;
        }

        editor.putString(tr.getText().toString(), en.getText().toString());

        editor.commit();
        addToList(tr.getText().toString(), en.getText().toString());
        Toast.makeText(this, "ITEM ADDED", Toast.LENGTH_LONG).show();
        tr.setText("");
        en.setText("");
    }
}
