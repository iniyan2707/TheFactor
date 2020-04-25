package com.example.thefactor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    private static final String keyscore="keyscore";
    private static final String keymillis="keymilis";
    private static final String keytotal="keytotal";


    private EditText ed;
    CountDownTimer s;
    Vibrator vibrator;
    private long backPressedTime;
    private Button submit;
    private TextView textviewscore;
    private TextView timer;
    private Button check;
    private RadioGroup rbgroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private TextView show;
    private ColorStateList textColourDefaultRb;
    private ColorStateList textColourDefaulttimer;

    private ArrayList<Integer> list=new ArrayList<Integer>();
    private ArrayList<Integer> arr=new ArrayList<Integer>();
    private ArrayList<Integer> a=new ArrayList<Integer>();
    private Random rand=new Random();
     private int score;
    private int option;
    private int i;
    private int l;
    private int k;
    private int m;
    private int n;
    private long timeLeftinmillis;
    private long Countdowntime=20000;

    private boolean answered;
    private Button finishquiz;
    private Handler mHandler = new Handler();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.editText);
        submit=findViewById(R.id.button2);
        textviewscore=findViewById(R.id.textView);
        timer=findViewById(R.id.textView2);
        check=findViewById(R.id.button3);
        rbgroup=findViewById(R.id.radioGroup);
        rb1=findViewById(R.id.radioButton5);
        rb2=findViewById(R.id.radioButton6);
        rb3=findViewById(R.id.radioButton7);
        textColourDefaultRb=rb1.getTextColors();
        textColourDefaulttimer=timer.getTextColors();
        finishquiz=findViewById(R.id.button);
        show=findViewById(R.id.textView3);



        vibrator =(Vibrator)getSystemService(VIBRATOR_SERVICE);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeLeftinmillis=Countdowntime;
                ed.onEditorAction(EditorInfo.IME_ACTION_DONE);

                clear();
            }
        });
        check.setEnabled(false);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.cancel();
                if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                    checkAnswer();
                    check.setEnabled(false);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please select an answer",Toast.LENGTH_LONG).show();
                }


            }
        });
        finishquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s!=null)
                s.cancel();

                finishgame();
            }
        });
        if(savedInstanceState != null) {
            score = savedInstanceState.getInt(keyscore);
            timeLeftinmillis = savedInstanceState.getLong(keymillis);


            s = new CountDownTimer(timeLeftinmillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftinmillis = millisUntilFinished;
                    String time = String.format(Locale.getDefault(), "00:%02d", timeLeftinmillis / 1000);
                    timer.setText(time);
                    if (timeLeftinmillis < 10000)
                        timer.setTextColor(Color.RED);
                    else
                        timer.setTextColor(textColourDefaulttimer);

                }

                @Override
                public void onFinish() {
                    Intent f = new Intent(getApplicationContext(), Main3Activity.class);
                    f.putExtra("Score", score);
                    finish();
                    startActivity(f);

                }
            }.start();
        }







    }

    private void clear() {

        show.setText("");
        if(!ed.getText().toString().equals("")&&ed.getText().toString().length()>0) {
            try {
                n = Integer.parseInt(ed.getText().toString());


                if (s != null)
                    s.cancel();


                list.clear();
                arr.clear();
                a.clear();
                s = new CountDownTimer(timeLeftinmillis, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        timeLeftinmillis = millisUntilFinished;
                        String time = String.format(Locale.getDefault(), "00:%02d", timeLeftinmillis / 1000);
                        timer.setText(time);
                        if (timeLeftinmillis < 10000)
                            timer.setTextColor(Color.RED);
                        else
                            timer.setTextColor(textColourDefaulttimer);

                    }

                    @Override
                    public void onFinish() {
                        Intent f = new Intent(getApplicationContext(), Main3Activity.class);
                        f.putExtra("Score", score);
                        finish();
                        startActivity(f);

                    }
                }.start();


                rb1.setTextColor(textColourDefaultRb);
                rb2.setTextColor(textColourDefaultRb);
                rb3.setTextColor(textColourDefaultRb);
                if (n == 0)
                {
                    Toast.makeText(getApplicationContext(), "Number 0 has infinite factors", Toast.LENGTH_SHORT).show();
                    if (s != null)
                        s.cancel();
                    check.setEnabled(false);

                }
                else
                    {
                    if (n == 1)
                    {
                        a.add(1);
                        a.add(2);
                        a.add(3);
                    }
                    else if (n == 2 || n == 3 || n == 4)
                    {
                        a.add(2);
                        a.add(3);
                        a.add(5);
                    }
                    else
                    {
                        int count=0;
                        for(int i=2;i<sqrt(n);i++)
                        {
                            if(n%i==0) {
                                count++;
                                break;
                            }
                        }
                        if(count==0)
                        {
                            l=n;
                        }
                        else {
                            l = rand.nextInt(n) + 1;
                            while (n % l != 0) {
                                l = rand.nextInt(n) + 1;

                            }
                        }
                        a.add(l);
                        m = rand.nextInt(n) + 1;
                        while (n % m == 0) {
                            m = rand.nextInt(n) + 1;
                        }
                        a.add(m);
                        k = rand.nextInt(n) + 1;
                        while (k == m || n % k == 0) {
                            k = rand.nextInt(n) + 1;
                        }
                        a.add(k);


                    }
                   /* for (i = 2; i <= n; i++) {
                        if (n % i == 0) {
                            list.add(i);

                        } else {
                            arr.add(i);
                        }
                    }
                    l = rand.nextInt(list.size());
                    a.add(list.get(l));
                    k = rand.nextInt(arr.size());
                    a.add(arr.get(k));


                    m = rand.nextInt(arr.size());
                    while (m == k) {
                        m = rand.nextInt(arr.size());
                    }
                    a.add(arr.get(m));
                }*/

                    Collections.shuffle(a);
                    Integer[] array = a.toArray(new Integer[0]);

                    rb1.setText(String.valueOf(array[0]));
                    rb2.setText(String.valueOf(array[1]));
                    rb3.setText(String.valueOf(array[2]));

                    if (n % array[0] == 0) {
                        option = 1;
                    } else if (n % array[1] == 0) {
                        option = 2;
                    } else {
                        option = 3;
                    }
                    answered = false;
                    check.setEnabled(true);
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(),"Invalid input",Toast.LENGTH_SHORT).show();
                if(s!=null)
                    s.cancel();
                check.setEnabled(false);

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter a number",Toast.LENGTH_SHORT).show();
            if(s!=null)
            s.cancel();
            check.setEnabled(false);

        }
    }






    private void checkAnswer()
    {

        RadioButton selected= findViewById(rbgroup.getCheckedRadioButtonId());
        int ans= rbgroup.indexOfChild(selected)+1;


        if(ans== option)
        {
            score++;
            showSolution();



        }
        else
        {
            vibrator.vibrate(1000);
            showSolution();
            if(s!=null)
                s.cancel();
            submit.setEnabled(false);
            finishquiz.setEnabled(false);
            mHandler.postDelayed(mLaunchTask,1000);


        }





    }
    private Runnable mLaunchTask = new Runnable() {
        public void run() {
            finishgame();

        }
    };

    private void showSolution()
    {
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        textviewscore.setText("Score:"+score);
        switch(option)
        {
            case 1:
                rb1.setTextColor(getResources().getColor(R.color.green));
                show.setText("Option 1 is correct");
                break;
            case 2:
                rb2.setTextColor(getResources().getColor(R.color.green));
                show.setText("Option 2 is correct");
                break;
            case 3:
                rb3.setTextColor(getResources().getColor(R.color.green));

                show.setText("Option 3 is correct");
                break;
        }
    }
    private void finishgame()
    {
        Intent intent=new Intent(getApplicationContext(),Main3Activity.class);
        int o=score;

        intent.putExtra("Score",o);
        finish();

        startActivity(intent);
    }
    protected void onDestroy() {

        super.onDestroy();
        if(s!=null)
            s.cancel();

    }
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Press back again to finish", Toast.LENGTH_SHORT).show();
        }
            backPressedTime = System.currentTimeMillis();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(keyscore,score);
        outState.putLong(keymillis,timeLeftinmillis);
    }
}
