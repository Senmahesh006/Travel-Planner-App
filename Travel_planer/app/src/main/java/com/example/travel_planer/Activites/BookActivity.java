package com.example.travel_planer.Activites;

import static android.content.ContentValues.TAG;
import static com.example.travel_planer.Activites.MyIteams.Globles_iteams;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {


    TextView title, des, price, txtCount;
    ImageView img;

    Button addCount, subCount, btnPay;

    int mCount = 1;

    IteamDomain iteamDomain = null;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        title = findViewById(R.id.name_tour);
        des = findViewById(R.id.desc_tour);
        price = findViewById(R.id.price_tour);
        addCount = findViewById(R.id.btn_addCount);
        subCount = findViewById(R.id.btn_subCount);
        btnPay = findViewById(R.id.btn_pay);

        img = findViewById(R.id.img_tour);
        txtCount = findViewById(R.id.txt_count);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        getdata();
        Log.i(TAG, "onCreate: " + Globles_iteams);

        addCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount=Integer.parseInt(txtCount.getText().toString());
                mCount++;
                txtCount.setText(mCount+"");
                price.setText(mCount*iteamDomain.getPrice()+"");
            }
        });
        subCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount=Integer.parseInt(txtCount.getText().toString());
                mCount--;
                txtCount.setText(mCount+"");
                price.setText(mCount*iteamDomain.getPrice()+"");
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String totalItemsValue = txtCount.getText().toString();
                String totalPriceValue = price.getText().toString();




                Intent intent = new Intent(BookActivity.this,ReceiptActivity.class);
                intent.putExtra("object", (Serializable) iteamDomain);
                intent.putExtra("itemsValue",totalItemsValue );
                intent.putExtra("priceValue",totalPriceValue );
                startActivity(intent);
            }
        });


    }

    private void getdata() {

        iteamDomain = (IteamDomain) getIntent().getSerializableExtra("object");
        Log.i(TAG, "onCreate: " + iteamDomain);


        title.setText(iteamDomain.getTitle());
        price.setText(iteamDomain.getPrice() + "");
        des.setText(iteamDomain.getDescription());




        int drawbleResId = getResources().getIdentifier(iteamDomain.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawbleResId)
                .into(img);


    }
}