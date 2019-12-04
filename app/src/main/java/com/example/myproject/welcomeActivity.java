package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.jaeger.library.StatusBarUtil;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        StatusBarUtil.setTransparent(welcomeActivity.this);
       // this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(welcomeActivity.this, MainActivity.class));
                welcomeActivity.this.finish();
            }
        },2000);  //持续时间为3秒


        TextView title = (TextView)findViewById(R.id.title);
        TextView present = (TextView)findViewById(R.id.present);
        Typeface type = Typeface.createFromAsset(getAssets(), "ChaoZiSheFengYunFan-2.ttf");
        title.setTypeface(type);
        present.setTypeface(type);
    }

}
