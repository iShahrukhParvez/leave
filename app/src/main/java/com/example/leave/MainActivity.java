package com.example.leave;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    ImageView backgroundImage,logo;
    TextView collegeName;
    LinearLayout layout1;

    EditText username, pwd;
    Button GotoHomeButton;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.llayout);
        layout1.animate().alphaBy(1).setDuration(3000).setStartDelay(1000);

        collegeName = findViewById(R.id.clgname);
        collegeName.animate().alpha(0f).setDuration(1000).setStartDelay(1000);

        logo = findViewById(R.id.logo);
        logo.animate().alpha(0f).setDuration(1000).setStartDelay(1000);

        backgroundImage = findViewById(R.id.bgimageView);
        backgroundImage.animate().setDuration(2000).translationYBy(-2000).setStartDelay(1000);

        mAuth = FirebaseAuth.getInstance();

        username = findViewById(R.id.uname);
        pwd = findViewById(R.id.pass);
        GotoHomeButton = findViewById(R.id.signin);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        };

        GotoHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoHome();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void GotoHome() {

        String email = username.getText().toString();
        String pass = pwd.getText().toString();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(MainActivity.this, "ENTER EMAIL", Toast.LENGTH_LONG).show();

        } else if(TextUtils.isEmpty(pass)){

            Toast.makeText(MainActivity.this, "ENTER PASSWORD", Toast.LENGTH_LONG).show();

        }
        else {
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "SignIn Error, Contact Admin", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Intent home = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(home);
                        finish();
                    }
                }
            });
        }


    }
}


