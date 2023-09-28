package com.example.travel_planer.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;

import java.io.Serializable;

public class CategoryDetails extends AppCompatActivity {

    private TextView titleTxt, locationtxt, bedTxt, guideTxt, wifiTxt, descriptionTxt, scoreTxt, priceTxt;

    private ImageView picImg, backBtn;

    private IteamDomain iteamDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);


        View book;
        book = findViewById(R.id.book_btn);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryDetails.this, BookActivity.class);
                intent.putExtra("object", (Serializable) iteamDomain);
                startActivity(intent);
            }
        });

        initView();
        setVariable();
    }


    private void setVariable() {

        iteamDomain = (IteamDomain) getIntent().getSerializableExtra("object");
        titleTxt.setText(iteamDomain.getTitle());
        scoreTxt.setText("" + iteamDomain.getScore());
        locationtxt.setText(iteamDomain.getLacation());
        bedTxt.setText(iteamDomain.getBed() + "Bed");
        descriptionTxt.setText(iteamDomain.getDescription());
        priceTxt.setText("" + iteamDomain.getPrice());


        if (iteamDomain.isGuide()) {
            guideTxt.setText("Guide");
        } else {
            guideTxt.setText("No-Guide");
        }
        if (iteamDomain.isWifi()) {
            wifiTxt.setText("Wifi");
        } else {
            wifiTxt.setText("No-Wifi");
        }

        int drawbleResId = getResources().getIdentifier(iteamDomain.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawbleResId)
                .into(picImg);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {

        titleTxt = findViewById(R.id.titleTxt);
        locationtxt = findViewById(R.id.locationTxt);
        bedTxt = findViewById(R.id.bedTxt);
        guideTxt = findViewById(R.id.guideTxt);
        wifiTxt = findViewById(R.id.wifiTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        picImg = findViewById(R.id.picImg);
        backBtn = findViewById(R.id.backbtn);
        scoreTxt = findViewById(R.id.scoreTxt);
        priceTxt = findViewById(R.id.priceTxt);

    }
}