package com.example.thefactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    private TextView yourscore;
    private TextView textHS;
    private Button mainmenu;
    private Button exit;
    private int highscore;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String KEY_HIGHSCORE="keyHighscore";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        yourscore= findViewById(R.id.textView7);
        textHS = findViewById(R.id.textView8);
        mainmenu =findViewById(R.id.button5);

       loadHighScore();
       updateHighScore();


        mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent f=new Intent(getApplicationContext(),Main2Activity.class);
                finish();
                   startActivity(f);
            }
        });


    }
    private void updateHighScore()
    {
        int score=getIntent().getExtras().getInt("Score");
        yourscore.setText("Your Score:"+score);

        if(score>highscore)
        {
            highscore =score;
            textHS.setText("High Score:"+highscore);

        }

        SharedPreferences prefs= getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =prefs.edit();
        editor.putInt(KEY_HIGHSCORE,highscore);
        editor.apply();
    }
    private void loadHighScore()
    {
        SharedPreferences prefs =getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        highscore=prefs.getInt(KEY_HIGHSCORE,0);
        textHS.setText("High Score:"+highscore);

    }

}
