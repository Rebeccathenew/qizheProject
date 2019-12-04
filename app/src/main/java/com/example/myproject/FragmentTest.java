package com.example.myproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTest extends Fragment {

    private String fragmentText;
    private TextView fragmentTextView;

    public FragmentTest(String fragmentText){
        this.fragmentText = fragmentText;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test_fragment,container,false);
        fragmentTextView = (TextView) view.findViewById(R.id.fragment_text);
        fragmentTextView.setText(fragmentText);
        return view;
    }
}
