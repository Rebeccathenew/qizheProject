package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.jaeger.library.StatusBarUtil;
import com.lxj.xpopup.core.DrawerPopupView;

import java.util.Scanner;

public class MenuDrawerLayoutView extends DrawerPopupView {

    private Button read;
    NavigationView navigationView;
    public MenuDrawerLayoutView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.menu_drawerlayout;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        navigationView = findViewById(R.id.nav_drawer);
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.read).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            //跳转到阅读的界面
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), WordsActivity.class);
                getContext().startActivity(intent);
                return false;
            }
        });

        menu.findItem(R.id.list).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            //跳转到计划的界面
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), WordsActivity.class);
                getContext().startActivity(intent);
                return false;
            }
        });

        menu.findItem(R.id.night_mode).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            //切换到夜晚模式
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), WordsActivity.class);
                getContext().startActivity(intent);
                return false;
            }
        });

        menu.findItem(R.id.scanner).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent = new Intent(getContext(), ScannerActivity.class);
                getContext().startActivity(intent);
                return false;
            }
        });
    }



}
