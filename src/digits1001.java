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

public class digits1001 extends AppCompatActivity {

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
    private final String hundredDigitsOfPiString = "82148086513282306647093844609550582231725359408128" +
            "48111745028410270193852110555964462294895493038196" +
            "44288109756659334461284756482337867831652712019091" +
            "45648566923460348610454326648213393607260249141273" +
            "72458700660631558817488152092096282925409171536436" +
            "78925903600113305305488204665213841469519415116094" +
            "33057270365759591953092186117381932611793105118548" +
            "07446237996274956735188575272489122793818301194912" +
            "98336733624406566430860213949463952247371907021798" +
            "60943702770539217176293176752384674818467669405132" +
            "00056812714526356082778577134275778960917363717872" +
            "14684409012249534301465495853710507922796892589235" +
            "42019956112129021960864034418159813629774771309960" +
            "51870721134999999837297804995105973173281609631859" +
            "50244594553469083026425223082533446850352619311881" +
            "71010003137838752886587533208381420617177669147303" +
            "59825349042875546873115956286388235378759375195778" +
            "18577805321712268066130019278766111959092164201989";
    private final String[] hundredDigitsOfPi = hundredDigitsOfPiString.split("");
    private boolean clicked =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digits1001);

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
            startTimer(10000000);
        });

        pause.setOnClickListener(v -> {
            play.setVisibility(View.VISIBLE);
            timerPause();
        });

        button3.setOnClickListener(v->{
            count=count-2;
            if (count<0){
                count=899;
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
                count=899;
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
                if (count > 899) {
                    count = 0;
                }
                textView4.setText(hundredDigitsOfPi[count]);
                if(clicked==true)
                    textToSpeech.speak(textView4.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);
                textView3.setText("Digit Number: " + (count+102));
                count++;
            }

            @Override
            public void onFinish() {
                count=0;
                play.setVisibility(View.VISIBLE);
                textView4.setText("8");
                textView3.setText("Digit Number: 102");
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