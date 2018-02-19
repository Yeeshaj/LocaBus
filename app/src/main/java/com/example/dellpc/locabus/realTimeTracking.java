package com.example.dellpc.locabus;

import android.*;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockPackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class realTimeTracking extends AppCompatActivity {
Button btnshowloaction;
    private static final int REQUEST_CODE_PERMISSION=2;
    String mPERMISSION= Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_tracking);
        try{
            if(ActivityCompat.checkSelfPermission(this,mPERMISSION)!= MockPackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this,new String[]{mPERMISSION},REQUEST_CODE_PERMISSION);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        btnshowloaction=(Button)findViewById(R.id.button);
        btnshowloaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gps=new GPSTracker(realTimeTracking.this);
                location=(TextView)findViewById(R.id.locationText);
                if(gps.canGetLocation())
                {
                    double latitude=gps.getLatitude();
                    double langitude=gps.getLangitude();
                    location.setText(latitude+" "+langitude);
                    database=FirebaseDatabase.getInstance();
                    myRef=database.getReference("Location");
                    myRef.setValue(latitude+","+langitude);
                }
                else
                {
                    //gps.showSettingsAlrt();
                }
            }
        });
    }
}
