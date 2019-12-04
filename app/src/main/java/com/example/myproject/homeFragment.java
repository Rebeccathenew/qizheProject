package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;

import org.w3c.dom.Text;

public class homeFragment extends Fragment {

    private ImageButton searchButton;
    private TextView todayPlanTextView;
    private TextView planTextView;
    private TextView lastDaysTextView;
    private ProgressBar progressBar;
    private Button startButton;
    private ImageButton searchBtn;
    private ImageButton menuBtn;

    public homeFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        todayPlanTextView = view.findViewById(R.id.today_plan);
        planTextView = view.findViewById(R.id.total_plan);
        lastDaysTextView = view.findViewById(R.id.last_days);
        searchButton = view.findViewById(R.id.search);
        progressBar = view.findViewById(R.id.progress_bar);
        startButton = view.findViewById(R.id.start_button);
        searchBtn = view.findViewById(R.id.search);
        menuBtn = view.findViewById(R.id.menu_btn);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //设置事件监听接口当按钮按下的时候跳转到背单词的页面
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WordsActivity.class);
                startActivity(intent);

            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), wordSearchActivity.class);
                startActivity(intent);
            }
        });

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new XPopup.Builder(getContext()).popupPosition(PopupPosition.Left).hasStatusBarShadow(true).asCustom(new MenuDrawerLayoutView(getContext())).show();
            }
        });
    }

}
