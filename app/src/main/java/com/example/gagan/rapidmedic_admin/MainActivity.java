package com.example.gagan.rapidmedic_admin;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private DatabaseReference reference;
    private ArrayList<String> dataCred;
    private ArrayList<String> age;
    private ArrayList<String> gender;
    private ArrayList<String> temp;
    private ArrayList<String> heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sweep);
        dataCred=new ArrayList<>();
        age=new ArrayList<>();
        gender=new ArrayList<>();
        temp=new ArrayList<>();
        heart=new ArrayList<>();
        listView= (ListView) findViewById(R.id.list);
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        reference=database.getReference("sixth-bone-163313");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfo();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    private void getInfo(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("MainActivityy",""+dataSnapshot.child("name").getValue().toString());
                dataCred.add(dataSnapshot.child("name").getValue().toString());
                age.add(dataSnapshot.child("age").getValue().toString());
                gender.add(dataSnapshot.child("gender").getValue().toString());
                temp.add(dataSnapshot.child("temp").getValue().toString());
                heart.add(dataSnapshot.child("hearRate").getValue().toString());
                for(DataSnapshot data:dataSnapshot.getChildren()){
                    Log.d("MainActivity",data.getValue().toString());
                    //dataCred.add(data.getValue().toString());
                }
                CustomAdapter customAdapter=new CustomAdapter(MainActivity.this,dataCred,age,gender,temp,heart);
                listView.setAdapter(customAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent=new Intent(MainActivity.this,SmsActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
