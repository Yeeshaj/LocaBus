package com.example.dellpc.locabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class introduction extends AppCompatActivity {

    private Button btnsingin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        btnsingin=(Button)findViewById(R.id.btnsign);
    }
    public void login(View V)
    {
        Intent i = new Intent(this,Login.class);
        startActivity(i);
    }
}
