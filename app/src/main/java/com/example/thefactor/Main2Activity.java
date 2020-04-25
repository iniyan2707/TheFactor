package com.example.thefactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView textViewHighScore;
    private Button start;
    private int highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        start=findViewById(R.id.button4);
        textViewHighScore=findViewById(R.id.textView6);
        loadHighScore();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x=new Intent(getApplicationContext(),MainActivity.class);
                finish();
                startActivity(x);
            }
        });




    }


    private void loadHighScore()
    {
        SharedPreferences prefs =getSharedPreferences(Main3Activity.SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(Main3Activity.KEY_HIGHSCORE,0);
        textViewHighScore.setText("High Score:"+highscore);
    }
}
