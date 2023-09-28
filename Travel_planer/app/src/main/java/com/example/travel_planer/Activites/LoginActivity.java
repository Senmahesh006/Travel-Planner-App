package com.example.travel_planer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_planer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText name,lopassword;


    private  TextView btn_signUp;
    Button forget,login;
    FirebaseAuth fauth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name=findViewById(R.id.Lo_Name);
        lopassword=findViewById(R.id.Lo_Password);
        forget=findViewById(R.id.btn_forgotPass);
        login=findViewById(R.id.btn_login);
        btn_signUp=findViewById(R.id.btn_signUp);

        fauth =FirebaseAuth.getInstance();


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = name.getText().toString().trim();
                String password = lopassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    name.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    lopassword.setError("Password Is Required");
                    return;
                }
                if (password.length()<8){
                    lopassword.setError("Password mast be >=8 Characters");
                    return;
                }

               fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(LoginActivity.this,"Login Succcessfully",Toast.LENGTH_LONG).show();
                           startActivity(new Intent(getApplicationContext(),MainActivity.class));
                       }else {
                           Toast.makeText(LoginActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                       }
                   }
               });

            }
        });






        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });


    }
}