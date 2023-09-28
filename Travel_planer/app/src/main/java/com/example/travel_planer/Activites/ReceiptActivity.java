package com.example.travel_planer.Activites;

import static android.content.ContentValues.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.Serializable;

public class ReceiptActivity extends AppCompatActivity {

    ImageView imgTour;
    TextView nameTour, totalPeople, priceTour, totalPrice, name, email, phone;
    Button btnConfirm;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    String userID;
    IteamDomain iteamDomain = null;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        imgTour = findViewById(R.id.img_tour);
        nameTour = findViewById(R.id.name_tour);
        totalPeople = findViewById(R.id.total_people);
        priceTour = findViewById(R.id.price_tour);
        totalPrice = findViewById(R.id.total_price);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnConfirm = findViewById(R.id.btn_confirm);
        builder = new AlertDialog.Builder(this);

        fAuth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        userID =fAuth.getCurrentUser().getUid();
        String toPrice = getIntent().getStringExtra("priceValue");
        String toPeople = getIntent().getStringExtra("itemsValue");


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Alert")
                        .setMessage("Are you sure booked this Ticket").setCancelable(true).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(ReceiptActivity.this,ticketActivity.class);
                                intent.putExtra("object", (Serializable) iteamDomain);
                                intent.putExtra("itemsValue",toPeople );
                                intent.putExtra("priceValue",toPrice );
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();


            }
        });

        DocumentReference documentReference=firestore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot , @Nullable FirebaseFirestoreException error) {
                phone.setText(documentSnapshot.getString("fphone"));
                name.setText(documentSnapshot.getString("fname"));
                email.setText(documentSnapshot.getString("femail"));
                totalPrice.setText(toPrice);
                totalPeople.setText(toPeople);
            }
        });

        getdata();
    }

    private void getdata() {


        iteamDomain = (IteamDomain) getIntent().getSerializableExtra("object");
        Log.i(TAG, "onCreate: " + iteamDomain);


        nameTour.setText(iteamDomain.getTitle());
        priceTour.setText(iteamDomain.getPrice() + "");

        int drawbleResId = getResources().getIdentifier(iteamDomain.getPic(), "drawable", getPackageName());
        Glide.with(this)
                .load(drawbleResId)
                .into(imgTour);



    }
}