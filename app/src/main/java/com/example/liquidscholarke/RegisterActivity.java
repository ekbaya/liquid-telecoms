package com.example.liquidscholarke;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    //Views
    Button registerBTN;
    EditText userEmailET,userPasswordET;
    TextView alreadyHaveAccountTV;

    // Progression Dialog
    ProgressDialog progressDialog;

    //Declare an instance of FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Actionbar and its tittle
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("New User Registration");

        //enable back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //Initialisation
        registerBTN = (Button) findViewById(R.id.registerBTN);
        userEmailET = (EditText) findViewById(R.id.userEmailET);
        userPasswordET = (EditText) findViewById(R.id.userPasswordET);
        alreadyHaveAccountTV = (TextView) findViewById(R.id.alreadyHaveAccountTV);

        //In the onCreate() method, initialize the FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering User...");

        //Handling Register Button  Onclick Events
        registerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // input email , password
                String email = userEmailET.getText().toString().trim();
                String password = userPasswordET.getText().toString().trim();

                // Validate
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    //set error and forcus to email edittext
                    userEmailET.setError("Invalid Email");
                    userEmailET.setFocusable(true);
                }
                else if (password.length() < 6){
                    //set error and forcus to password edittext
                    userPasswordET.setError("Password length at least 6 characters");
                    userPasswordET.setFocusable(true);
                }
                else {
                    registerUser(email,password); // register the user
                }

            }
        });


        //Handling Textview  Onclick Events
        alreadyHaveAccountTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void registerUser(String email, String password) {
        //Email and password is valid ,show progression dialog and start registering
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, dismiss progress dialog and start registration
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();

                            //get user email and user id from auth
                            String email = user.getEmail();
                            String uid = user.getUid();
                            // when user is registered store user info in firebase realtime database too
                            //using HashMap
                            HashMap<Object, String> hashMap = new HashMap<>();
                            //put info in hasmap
                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
                            hashMap.put("name",""); // will add later (e.g edit profile)
                            hashMap.put("onlineStatus","online"); // will add later (e.g edit profile)
                            hashMap.put("typingTo","noOne"); // will add later (e.g edit profile)
                            hashMap.put("phone","");// will add later (e.g edit profile)
                            hashMap.put("university","");// will add later (e.g edit profile)
                            hashMap.put("course","");// will add later (e.g edit profile)
                            hashMap.put("scholarshipType","");// will add later (e.g edit profile)
                            hashMap.put("country","");// will add later (e.g edit profile)
                            hashMap.put("career","");// will add later (e.g edit profile)
                            hashMap.put("profile","");// will add later (e.g edit profile)
                            hashMap.put("image","");// will add later (e.g edit profile)
                            hashMap.put("cover","");// will add later (e.g edit profile)
                            //Firebase database instant
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            // path to store user data named "Users"
                            DatabaseReference reference = database.getReference("Users");
                            //put data within hashMap in database
                            reference.child(uid).setValue(hashMap);



                            Toast.makeText(RegisterActivity.this,"Registered...\n"+user.getEmail(),Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,DashboardActivity.class));
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //error , dismiss progress dialog and get and show the error message
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // go previous activity
        return super.onSupportNavigateUp();
    }
}
