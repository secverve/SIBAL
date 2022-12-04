package org.pytorch.demo.objectdetection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.hide();

        EditText nameText, phoneNum, add1, add2, add3, add4;
        ImageButton backImgBtn, myImgBtn, removeBtn;
        Button manBtn, womanBtn, signupBtn;
        Spinner imgSelect, year, month, day;

        backImgBtn = findViewById(R.id.backImgBtn);
        backImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

        myImgBtn = findViewById(R.id.myImgBtn);
//        imgSelect = findViewById(R.id.myImgBtn);
//        String[] option_list = getResources().getStringArray(R.array.이미지옵션);
//        @SuppressLint("ResourceType") ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_spinner_dropdown_item,
//                option_list
//        );
//        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        day.setAdapter(adapter3);


        myImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        nameText = findViewById(R.id.nameText);
        phoneNum = findViewById(R.id.phoneNum);
        removeBtn = findViewById(R.id.removeBtn);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameText.setText(null);
            }
        });

        manBtn = findViewById(R.id.manBtn);
        womanBtn = findViewById(R.id.womanBtn);

        manBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manBtn.setBackgroundColor(Color.LTGRAY);
                womanBtn.setBackgroundColor(Color.TRANSPARENT);

            }
        });

        womanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                womanBtn.setBackgroundColor(Color.LTGRAY);
                manBtn.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        // 스피너 뷰 구현
        year = findViewById(R.id.year);
        String[] year_list = getResources().getStringArray(R.array.생년);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                year_list
                );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(adapter1);

        year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                year.setText
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        month = findViewById(R.id.month);
        String[] month_list = getResources().getStringArray(R.array.월);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                month_list
                );
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(adapter2);

        month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        day = findViewById(R.id.day);
        String[] day_list = getResources().getStringArray(R.array.일);
        @SuppressLint("ResourceType") ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                day_list
        );
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day.setAdapter(adapter3);

        day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 세부 사항 구현
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);
        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

