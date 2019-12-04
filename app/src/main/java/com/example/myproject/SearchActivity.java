package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.czp.searchmlist.mSearchLayout;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    mSearchLayout editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        int color = getResources().getColor(R.color.main_background);
        StatusBarUtil.setColor(SearchActivity.this, color, 0);
        editText = (mSearchLayout)findViewById(R.id.search_text);
        initData();
    }

    protected void initData(){
        //历史数据
        String historyData = "many,company,history,any";
        List<String> skills = Arrays.asList(historyData.split(","));


        editText.initData(skills,skills , new mSearchLayout.setSearchCallBackListener() {
            @Override
            public void Search(String s) {
               //进行搜索
            }

            @Override
            public void Back() {

                finish();
            }

            @Override
            public void ClearOldData() {
                //清除历史搜索记录，更新记录原始数据
            }

            @Override
            public void SaveOldData(ArrayList<String> arrayList) {
                //保存所有的搜索记录
            }
        });
    }
}
