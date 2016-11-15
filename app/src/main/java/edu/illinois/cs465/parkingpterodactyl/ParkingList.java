package edu.illinois.cs465.parkingpterodactyl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ParkingList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

    }

    public void buttonPress(View v) {
        switch (v.getId()) {
            case R.id.navigate:
                startNavigation();
                break;
        }
    }

    public void startNavigation() {
        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
    }
}