package com.example.swapna.myapplication;


import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView tvDay, tvHour, tvMinute, tvSecond, tvEvent;
    private LinearLayout linearLayout1, linearLayout2;
    private Handler handler;
    private Runnable runnable;
    private CountDownTimer cdt;
    private Date futureDate,currentDate,thiscurrentDate;
    private Button startbutton;
    private EditText future;
    private EditText starttimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
       // countDownStart();
    }

    @SuppressLint("SimpleDateFormat")
    private void initUI() {
        linearLayout1 = (LinearLayout) findViewById(R.id.ll1);
        linearLayout2 = (LinearLayout) findViewById(R.id.ll2);
        tvDay = (TextView) findViewById(R.id.txtTimerDay);
        tvHour = (TextView) findViewById(R.id.txtTimerHour);
        tvMinute = (TextView) findViewById(R.id.txtTimerMinute);
        tvSecond = (TextView) findViewById(R.id.txtTimerSecond);
        tvEvent = (TextView) findViewById(R.id.tvevent);

        future=(EditText)findViewById(R.id.future);
        starttimer=(EditText)findViewById(R.id.starttimer);

        startbutton=(Button)findViewById(R.id.startbutton);

        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String test="2016-10-03 "+"18:"+future.getText()+":00";
                String test1="2016-10-03 "+"18:"+starttimer.getText()+":00";

                countDownStart(test,test1);
            }
        });
    }



    // //////////////COUNT DOWN START/////////////////////////
    public void countDownStart(String test, String test1) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        // Here Set your Event Date
        try {
             futureDate = dateFormat.parse(test);
             currentDate = dateFormat.parse(test1);




            Log.i("MainActivity", "countDownStart: " + futureDate + " " + currentDate + " " + thiscurrentDate);
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(this, 1000);
                    thiscurrentDate = new Date();
                    Log.i("MainActivity", "countDownStart: " + futureDate + " " + currentDate + " " + thiscurrentDate);
                    if (!currentDate.after(futureDate)) {

                        if (thiscurrentDate.after(currentDate)) {


                            final long difference = futureDate.getTime() - thiscurrentDate.getTime();
                            cdt = new CountDownTimer(difference, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {

                                    Log.i("MainActivity", "onTick: "+difference);
                                    int days = 0;
                                    int hours = 0;
                                    int minutes = 0;
                                    int seconds = 0;
                                    String sDate = "";

                                    if (millisUntilFinished > DateUtils.DAY_IN_MILLIS) {
                                        days = (int) (millisUntilFinished / DateUtils.DAY_IN_MILLIS);
                                        sDate += days + "d";
                                    }

                                    millisUntilFinished -= (days * DateUtils.DAY_IN_MILLIS);

                                    if (millisUntilFinished > DateUtils.HOUR_IN_MILLIS) {
                                        hours = (int) (millisUntilFinished / DateUtils.HOUR_IN_MILLIS);
                                    }

                                    millisUntilFinished -= (hours * DateUtils.HOUR_IN_MILLIS);

                                    if (millisUntilFinished > DateUtils.MINUTE_IN_MILLIS) {
                                        minutes = (int) (millisUntilFinished / DateUtils.MINUTE_IN_MILLIS);
                                    }

                                    millisUntilFinished -= (minutes * DateUtils.MINUTE_IN_MILLIS);

                                    if (millisUntilFinished > DateUtils.SECOND_IN_MILLIS) {
                                        seconds = (int) (millisUntilFinished / DateUtils.SECOND_IN_MILLIS);
                                    }

                                    sDate += " " + String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds);

                                    tvDay.setText("" + String.format("%02d", days));
                                    tvHour.setText("" + String.format("%02d", hours));
                                    tvMinute.setText("" + String.format("%02d", minutes));
                                    tvSecond.setText("" + String.format("%02d", seconds));

                                    Log.i("MainActivity", "onTick: " + sDate);
                                }

                                @Override
                                public void onFinish() {
                                    handler.removeCallbacksAndMessages(null);
                                }

                            };

                            cdt.start();
                        } else {

                        }

                    }
                }
            };
            handler.postDelayed(runnable, 0);




        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
       /* handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd HH:mm:ss");
                    // Here Set your Event Date
                    Date futureDate = dateFormat.parse("2016-09-27 05:02:00");
                    Date currentDate = dateFormat.parse("2016-09-27 05:01:00");
                    Log.i("MainActivity", " run: "+" "+futureDate+" "+currentDate);
                    if (!currentDate.after(futureDate)) {
                        Log.i("MainActivity", " 1 run: ");
                        long diff = futureDate.getTime() - currentDate.getTime();

                        Log.i("MainActivity", " time: "+diff);

                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);

                        Log.i("MainActivity", " days: "+diff);

                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);

                        Log.i("MainActivity", " hours: "+diff);

                        long minutes = diff / (60 * 1000);
                        Log.i("MainActivity", " minutes: diff "+minutes);

                        diff -= minutes * (60 * 1000);

                        Log.i("MainActivity", " minutes: "+diff);

                        long seconds = diff / 1000;

                        Log.i("MainActivity", " seconds: "+seconds);

                        tvDay.setText("" + String.format("%02d", days));
                        tvHour.setText("" + String.format("%02d", hours));
                        tvMinute.setText("" + String.format("%02d", minutes));
                        tvSecond.setText("" + String.format("%02d", seconds));
                    } else {
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                        tvEvent.setText("Android Event Start");
                        handler.removeCallbacks(runnable);
                        // handler.removeMessages(0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 0);*/
}

// //////////////COUNT DOWN END/////////////////////////
