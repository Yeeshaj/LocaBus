package com.example.dellpc.locabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class introduction extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnsingin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        btnsingin=(Button)findViewById(R.id.btnsign);
        mAuth=mAuth = FirebaseAuth.getInstance();
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
    public void login(View V)
    {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
    public void create(View v)
    {
        Intent i=new Intent(this,createAccount.class);
        startActivity(i);
    }
    public void updateUI( FirebaseUser user ) {
        //hideProgressDialog();

        if (user != null) {
            Intent i = new Intent(this, Profile.class);
            startActivity(i);
        }
            else
            {
                user=null;
                Intent x=new Intent(this,introduction.class);
                startActivity(x);

            }
        }




    }

