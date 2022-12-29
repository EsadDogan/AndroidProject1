package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class homeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            Intent intent;

            switch (item.getItemId()){
                case R.id.contacts:
                    intent = new Intent(homeActivity.this , seeContactsActivity.class);
                    startActivity(intent);
                    break;
                case R.id.add:
                     intent = new Intent(homeActivity.this , addContactsActivity.class);
                    startActivity(intent);
                    break;

                default: break;
            }


            return true;
        });
    }
}