package com.example.liquidscholarke;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.liquidscholarke.adapters.AdapterUser;
import com.example.liquidscholarke.adapters.AdapterUserInfo;
import com.example.liquidscholarke.models.ModelUserInfo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity {

    //views
    ImageView userImageIv;
    RecyclerView userinfoRecyclerview;

    AdapterUserInfo adapterUserInfo;
    List<ModelUserInfo> infoList;

    FirebaseAuth firebaseAuth;


    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("About User");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        //init
        userImageIv = findViewById(R.id.userImageIv);

        //get uid of clicked user to retrieve his posts
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");

        firebaseAuth = FirebaseAuth.getInstance();
        checkUserstatus();

        //init recyclerview
        userinfoRecyclerview = findViewById(R.id.usrInfo_recyclerview);
        //setting its properties
        userinfoRecyclerview.setHasFixedSize(true);
        userinfoRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        //init user list
        infoList = new ArrayList<>();

        DatabaseReference imageRef = FirebaseDatabase.getInstance().getReference("Users");
        Query query = imageRef.orderByChild("uid").equalTo(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    //get data
                    String image = "" + ds.child("image").getValue();

                    //set data
                    try{
                        Picasso.get().load(image).placeholder(R.drawable.ic_account_circle_white).into(userImageIv);
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        getAllUserInfo();
    }

    private void getAllUserInfo() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        Query query = ref.orderByChild("uid").equalTo(uid); // uid of the clicked user
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    ModelUserInfo modelUserInfo = ds.getValue(ModelUserInfo.class);
                    infoList.add(modelUserInfo);


                    // adapter
                    adapterUserInfo = new AdapterUserInfo(UserInfoActivity.this, infoList);
                    //set adapter to recyclerview
                    userinfoRecyclerview.setAdapter(adapterUserInfo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    protected void checkUserstatus() {
        // get current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // user is signed in ... stay here
        } else {
            // user is not signed in...
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        menu.findItem(R.id.action_add_post).setVisible(false);//hide add post from this activity
        menu.findItem(R.id.action_search).setVisible(false);//hide search action from this activity
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout){
            firebaseAuth.signOut();
            checkUserstatus();
        }
        return super.onOptionsItemSelected(item);
    }
}
