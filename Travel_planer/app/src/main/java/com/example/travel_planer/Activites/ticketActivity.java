package com.example.travel_planer.Activites;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ticketActivity extends AppCompatActivity {

    TextView name, email, phone, nameTours, totalPeople, totalPrice;
    Button btnBack;

    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    String userID;
    IteamDomain iteamDomain = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        nameTours = findViewById(R.id.tic_name_tour);
        totalPeople = findViewById(R.id.tic_total_people);
        totalPrice = findViewById(R.id.tic_total_price);
        btnBack = findViewById(R.id.btn_back);

        fAuth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        userID =fAuth.getCurrentUser().getUid();

        String totalPrices = getIntent().getStringExtra("priceValue");
        String totalpeoples = getIntent().getStringExtra("itemsValue");







        DocumentReference documentReference=firestore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot , @Nullable FirebaseFirestoreException error) {
                phone.setText(documentSnapshot.getString("fphone"));
                name.setText(documentSnapshot.getString("fname"));
                email.setText(documentSnapshot.getString("femail"));
                 totalPrice.setText(totalPrices);
                totalPeople.setText(totalpeoples);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ticketActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


        getdata();

    }

    private void getdata() {



        iteamDomain = (IteamDomain) getIntent().getSerializableExtra("object");
        Log.i(TAG, "onCreate: " + iteamDomain);


        nameTours.setText(iteamDomain.getTitle());






    }
}