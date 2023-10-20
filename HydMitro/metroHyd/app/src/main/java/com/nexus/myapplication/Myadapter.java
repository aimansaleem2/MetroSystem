package com.nexus.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Myadapter extends ArrayAdapter {

    List<String> title;
    Context context;
    List<String> change;

    public Myadapter(@NonNull Context context, List<String> title, List<String> change) {
        super(context, R.layout.items, title);
        this.title = title;
        this.context = context;
        this.change = change;
    }

    @NonNull
    @Override

    public View getView(int position, View convertview, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);


        TextView txt = view.findViewById(R.id.textView2);
        //remove codes of stations
        String getStr=title.get(position);
        txt.setText(getStr.substring(0,getStr.length()-4));
        char ch = getStr.charAt(getStr.length() - 3);


        CardView C = (CardView) view.findViewById(R.id.cardView);

        if (ch == 'R') {
            C.setBackgroundResource(R.color.red);
        } else if (ch == 'G') {
            C.setBackgroundResource(R.color.green);
        } else if (ch == 'B') {
            C.setBackgroundResource(R.color.blue);
        }


        TextView t = (TextView) view.findViewById(R.id.alert);

        if (change.contains(txt.getText())) {
            C.setBackgroundResource(R.color.black);
            t.setText("    (change here)");
        }
        TextView t2 = (TextView) view.findViewById(R.id.fairValue);

        return view;
        }
}
