package com.alpine.team1.timeshowdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView time,date;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        /**********************时间更新线程*****************************/
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Message msg = new Message();
                        msg.what = 123;
                        handler.sendMessage(msg);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
        //1.显示时间和日期
        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.what == 123) {
                    showtime();
                }
            }
        };
    }
    //1.显示时间和日期
    private void showtime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);//日期
        date.setText(simpleDateFormat.format(new Date()));
        DateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm", Locale.CHINA);//HH:MM
        time.setText(simpleDateFormat1.format(new Date()));
    }
}