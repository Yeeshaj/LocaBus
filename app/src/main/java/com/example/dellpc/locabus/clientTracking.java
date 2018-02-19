package com.example.dellpc.locabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class clientTracking extends AppCompatActivity {
 String value =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference mRef=database.getReference("Location");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             value=dataSnapshot.getValue(String.class);
                TextView textView=(TextView)findViewById(R.id.loctext);
                textView.setText(value);
                String []seperated=value.split(",");
                String latiPos=seperated[0].trim();
                String langiPos=seperated[1].trim();
                String TAG="VAL";
                double dLat=Double.parseDouble(langiPos);
                double dLag=Double.parseDouble(langiPos);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void  btnclick(View view)
    {
        Intent i= new Intent(this,MapsActivity.class);
        i.putExtra("LOCVAL",value);
        startActivity(i);
    }
}
