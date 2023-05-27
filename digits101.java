package com.example.learnpi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Locale;

public class digits101 extends AppCompatActivity {

    private TextView textView4;
    private TextView textView3;
    private ImageButton play;
    private ImageButton pause;
    private ImageButton ImageButton7;
    private ImageButton ImageButton5;
    private int count = 0;
    private Button button3;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button10;
    private CountDownTimer timer;
    private TextToSpeech textToSpeech;
    private final String hundredDigitsOfPiString = "3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";
    private final String[] hundredDigitsOfPi = hundredDigitsOfPiString.split("");
    private boolean clicked =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits101);

        textView4 = (TextView) findViewById(R.id.textView4);

        textView3 = (TextView) findViewById(R.id.textView3);

        play = (ImageButton) findViewById(R.id.play);

        pause = (ImageButton) findViewById(R.id.pause);

        button3 = (Button) findViewById(R.id.button3);

        button6 = (Button) findViewById(R.id.button6);

        button7 = (Button) findViewById(R.id.button7);

        button8 = (Button) findViewById(R.id.button8);

        button10 = (Button) findViewById(R.id.button10);

        ImageButton7 = (ImageButton) findViewById(R.id.imageButton7);

        ImageButton5 = (ImageButton) findViewById(R.id.imageButton5);

        play.setOnClickListener(v -> {
            startTimer(1000000);
        });

        pause.setOnClickListener(v -> {
            play.setVisibility(View.VISIBLE);
            timerPause();
        });

        button3.setOnClickListener(v->{
            count=count-2;
            if (count<0){
                count=101;
            }
        });

        button6.setOnClickListener(v->{
            count++;
        });

        button7.setOnClickListener(v->{
                count=0;
        });

        button8.setOnClickListener(v->{
            count+=9;
        });

        button10.setOnClickListener(v->{
            count-=11;
            if (count<0){
                count=101;
            }
        });

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i!=TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        ImageButton7.setOnClickListener(v ->{
            ImageButton7.setVisibility(View.GONE);
                clicked=true;
        });

        ImageButton5.setOnClickListener(v ->{
            ImageButton7.setVisibility(View.VISIBLE);
            clicked=false;
        });
    }

    private void startTimer(long timerStartFrom) {
         timer = new CountDownTimer(timerStartFrom, 2000) {
            public void onTick(long millisUntilFinished) {
                play.setVisibility(View.GONE);
                if (count > 101) {
                    count = 0;
                }
                textView4.setText(hundredDigitsOfPi[count]);
                if(clicked==true)
                    textToSpeech.speak(textView4.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                textView3.setText("Digit Number: " + count);
                count++;
            }

            @Override
            public void onFinish() {
                count=0;
                play.setVisibility(View.VISIBLE);
                textView4.setText("3");
                textView3.setText("Digit Number: 0");
            }
        }.start();
    }
    public void timerPause() {
        timer.cancel();
    }

    public void onBackPressed(){
        textToSpeech.shutdown();
        super.onBackPressed();
    }
}