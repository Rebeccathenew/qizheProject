package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jaeger.library.StatusBarUtil;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout homeLinear;
    LinearLayout readLinear;
    LinearLayout listLinear;
    LinearLayout userLinear;
    homeFragment fragmentHome;
    FragmentTest fragmentList, fragmentRead, fragmentUser;

    private FragmentManager mfragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int color = getResources().getColor(R.color.main_background);
        StatusBarUtil.setColor(MainActivity.this, color,0);
        homeLinear = (LinearLayout) findViewById(R.id.linear_home);
        readLinear = (LinearLayout) findViewById(R.id.linear_read);
        listLinear = (LinearLayout) findViewById(R.id.linear_list);
        userLinear = (LinearLayout) findViewById(R.id.linear_user);
        homeLinear.setOnClickListener(this);
        listLinear.setOnClickListener(this);
        readLinear.setOnClickListener(this);
        userLinear.setOnClickListener(this);
        mfragmentManager = getSupportFragmentManager();
        homeLinear.performClick(); //模拟一次点击事件，是使得页面在打开的时候显示一个fragment
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = mfragmentManager.beginTransaction();
        hideAllFragment(fragmentTransaction);
        switch (view.getId()){
            case R.id.linear_home:
                setAllFalse();
                homeLinear.setSelected(true);
                if(fragmentHome == null){
                    fragmentHome = new homeFragment();
                    fragmentTransaction.add(R.id.fragment_frame,fragmentHome);
                }else{
                    fragmentTransaction.show(fragmentHome);
                }
                break;
            case R.id.linear_read:
                setAllFalse();
                readLinear.setSelected(true);
                if(fragmentRead == null){
                    fragmentRead = new FragmentTest("read");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentRead);
                }else{
                    fragmentTransaction.show(fragmentRead);
                }
                break;
            case R.id.linear_list:
                setAllFalse();
                listLinear.setSelected(true);
                if(fragmentList== null){
                    fragmentList = new FragmentTest("list");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentList);
                }else{
                    fragmentTransaction.show(fragmentList);
                }
                break;
            case R.id.linear_user:
                setAllFalse();
                userLinear.setSelected(true);
                if(fragmentUser == null){
                    fragmentUser = new FragmentTest("User");
                    fragmentTransaction.add(R.id.fragment_frame,fragmentUser);
                }else{
                    fragmentTransaction.show(fragmentUser);
                }
                break;
        }

        fragmentTransaction.commitNow(); //必须要commit
    }

    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fragmentHome != null){
            fragmentTransaction.hide(fragmentHome);
        }
        if(fragmentRead != null){
            fragmentTransaction.hide(fragmentRead);
        }
        if(fragmentList != null){
            fragmentTransaction.hide(fragmentList);
        }
        if(fragmentUser != null){
            fragmentTransaction.hide(fragmentUser);
        }
    }

    private void setAllFalse(){
        homeLinear.setSelected(false);
        listLinear.setSelected(false);
        readLinear.setSelected(false);
        userLinear.setSelected(false);
    }
//
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
//    }
}
