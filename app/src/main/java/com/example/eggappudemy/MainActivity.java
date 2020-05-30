package com.example.eggappudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    SeekBar timerSeekbar;
    TextView timerTextView;
    boolean counterisActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        timerTextView.setText("0:30");
        timerSeekbar.setProgress(30);
        timerSeekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("Go!!");
        counterisActive=false;
    }
    public void buttonClicked(View view) {
        if (counterisActive) {

            resetTimer();;

        } else {
            counterisActive = true;
            timerSeekbar.setEnabled(false);
            goButton.setText("Stop");
         countDownTimer= new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }
    public void updateTimer(int secondsleft){
        int minutes=secondsleft/60;
        int seconds=secondsleft-(minutes*60);

        String secondString=Integer.toString(seconds);
        if(seconds <=9)
        {
            secondString="0"+secondString;
        }
        timerTextView.setText(Integer.toString(minutes) + ":" +secondString);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Timer");
        timerSeekbar=(SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        goButton=(Button)findViewById(R.id.button);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
                }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
