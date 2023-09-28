package com.example.travel_planer.Activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_planer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    public static final String TAP = "Tap";
    private Button singup,reset;
    private EditText name,Remail,phone,username,Rpassword,Rre_password;
    private TextView signIn;
    FirebaseAuth fAuth;
   FirebaseFirestore firestore;
   String userID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.Re_Name);
        Remail = findViewById(R.id.Re_Email);
        phone = findViewById(R.id.Re_Phone);
        username = findViewById(R.id.Re_Username_regis);
        Rpassword = findViewById(R.id.Re_Password_regis);
        Rre_password = findViewById(R.id.Re_Retype_password);
        singup = findViewById(R.id.btn_signUp);
        reset = findViewById(R.id.btn_reset);
        signIn = findViewById(R.id.btn_signIn);


        fAuth = FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });







        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Remail.getText().toString().trim();
                String password = Rpassword.getText().toString().trim();
                String re_password = Rre_password.getText().toString().trim();
                String  fullname = name.getText().toString().trim();
                String Phone = phone.getText().toString().trim();
                String fullUser = username.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Remail.setError("Email is Required");
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Rpassword.setError("Password Is Required");
                    return;
                }
                if (password.length()<8){
                    Rpassword.setError("Password mast be >=8 Characters");
                    return;
                }

                if (TextUtils.isEmpty(re_password)){
                    Rre_password.setError("Password Is Required");
                    return;
                }

                if (password.equals(re_password)){
                    Toast.makeText(SignupActivity.this," ",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(SignupActivity.this,"Password dose Not Match",Toast.LENGTH_LONG).show();
                }


                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(SignupActivity.this,"User Created",Toast.LENGTH_LONG).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentReference= firestore.collection("Users").document(userID);
                            Map<String,Object>user  = new HashMap<>();
                            user.put("femail",email);
                            user.put("fname",fullname);
                            user.put("fphone",Phone);
                            user.put("fUser",fullUser);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAP,"onSuccess: User Created"+userID);
                                }
                            });

                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));

                        }else {
                            Toast.makeText(SignupActivity.this,"Error !"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                        }
                    }
                });



            }
        });


    }
}