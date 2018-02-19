package com.example.dellpc.locabus;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class createAccount extends AppCompatActivity {
    private EditText txtemail, txtpass,txtname,txtno;
    private Spinner sp;
    String type="Customers";
String []typer={"Customer","Service Provider","Driver"};
    private static final String TAG = "EmailPassword";
    private String email, password,name,number;

    private FirebaseAuth mAuth;
   DatabaseReference databaseCustomers;



    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        mAuth = FirebaseAuth.getInstance();
        txtemail = (EditText) findViewById(R.id.edittxtemail);
        txtpass = (EditText) findViewById(R.id.edittxtpass);
        txtname=(EditText)findViewById(R.id.edittxtname);
        txtno=(EditText)findViewById(R.id.edittxtphone);
        sp=(Spinner)findViewById(R.id.spinner);

        mAuth = FirebaseAuth.getInstance();




    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id)
    {
        type=typer[position];
    }
    public void signin(View v)
    {
        databaseCustomers=FirebaseDatabase.getInstance().getReference(type);
        email = txtemail.getText().toString();
        password = txtpass.getText().toString();
        name=txtname.getText().toString();
        number=txtno.getText().toString();
        createAccount(email, password);
        String id=databaseCustomers.push().getKey();
        Customer customer=new Customer(id,name,email,number);
        databaseCustomers.child(id).setValue(customer);
        Intent i = new Intent(this, Profile.class);
        startActivity(i);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }
    // [END on_start_check_user]

    private void createAccount(String email, String password)
    {
        Log.d(TAG, "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(createAccount.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }

                // [START_EXCLUDE]
                // [END_EXCLUDE]
            }
        });
        // [END create_user_with_email]
    }

}

// [START create_user_with_email]




