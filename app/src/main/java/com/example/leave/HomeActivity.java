package com.example.leave;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logoutbtn = findViewById(R.id.logoutbtn);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intomain = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intomain);
    }

    private long backPressTime;
    private Toast onBackPress;
    @Override
    public void onBackPressed() {

        if(backPressTime + 2000 > System.currentTimeMillis())
        {
            onBackPress.cancel();
            super.onBackPressed();
            return;
        }else
        {
            onBackPress = Toast.makeText(HomeActivity.this, "Press back again to exit", Toast.LENGTH_SHORT);
            onBackPress.show();
        }

        backPressTime = System.currentTimeMillis();

    }
}