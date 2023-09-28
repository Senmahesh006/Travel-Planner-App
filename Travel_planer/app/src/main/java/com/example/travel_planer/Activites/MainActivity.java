package com.example.travel_planer.Activites;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.travel_planer.Activites.Adapter.IteamAdapter;
import com.example.travel_planer.Activites.Domain.IteamDomain;
import com.example.travel_planer.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  RecyclerView.Adapter adapterview;
    private RecyclerView recyclerViewview;
    Button check;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    String userID;





    MyIteams myIteams = (MyIteams) this.getApplication();
    ArrayList<IteamDomain> viewiteams ;
    @SuppressLint({"NonConstantResourceId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fAuth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();



        userID =fAuth.getCurrentUser().getUid();







        viewiteams= myIteams.getGlobles_iteams();

        recyclerViewview = findViewById(R.id.viewCategory);
        recyclerViewview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapterview = new IteamAdapter(viewiteams);
        recyclerViewview.setAdapter(adapterview);





    }




}