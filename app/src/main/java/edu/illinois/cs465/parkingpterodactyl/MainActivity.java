package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    LinkedList<Message> messages;

    LinkedList<ParkingLocations> selectedParkingLocations;

    // TODO - Come up with a cleaner way to do this.
    // Variables keeping track of whether or not the overlays have been seen
    Boolean messageBoardOverlaySeen;
    Boolean parkingSeen;
    Boolean mapOverlaySeen;

    // TODO - this really shouldn't be done this way either...
    String lastSearch;

    // Filter variables
    ParkingLocations.carSize currentCarSize;
    LinkedList<ParkingLocations.parkingType> allowedParkingTypes;

    // List of all parking locations
    LinkedList<ParkingLocations> allParkingLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Help Overlay Code
        final View topLevelLayout = findViewById(R.id.top_layout);
        topLevelLayout.setVisibility(View.VISIBLE);
        topLevelLayout.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                topLevelLayout.setVisibility(View.INVISIBLE);
                return false;
            }

        });

        messageBoardOverlaySeen = false;
        parkingSeen=false;
        mapOverlaySeen=false;
        lastSearch = "Zach";

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();

        getSupportActionBar().setTitle("Parking Pterodactyl");
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        // Create fake starting messages
        messages = new LinkedList<>();
        messages.add(new Message("There is a lot of parking left at the lot on the corner of Springfield and Gregory."));

        //create linked list for parking locations
        allParkingLocations = new LinkedList<>();
        //fake parking locations
        allParkingLocations.add(new ParkingLocations("The Union", 40.110221, -88.226247,
                ParkingLocations.carSize.SMALL, ParkingLocations.parkingType.STREET));
        allParkingLocations.add(new ParkingLocations("Meters on Wright St", 40.1113254,
                -88.228872, ParkingLocations.carSize.LARGE, ParkingLocations.parkingType.STREET));
        allParkingLocations.add(new ParkingLocations("Lot by Walgreens", 40.110056, -88.232529,
                ParkingLocations.carSize.EXTRA_LARGE, ParkingLocations.parkingType.FREE));
        allParkingLocations.add(new ParkingLocations("Grainger Library", 40.112565, -88.226900,
                ParkingLocations.carSize.LARGE, ParkingLocations.parkingType.FREE));


        selectedParkingLocations = new LinkedList<>();

        // Set the default car size and desired parking types
        this.currentCarSize = ParkingLocations.carSize.LARGE;
        this.allowedParkingTypes = new LinkedList<>();
        this.allowedParkingTypes.add(ParkingLocations.parkingType.FREE);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.PAID);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.STREET);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.EVENT);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.messages_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new MessageBoardFragment());
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        item.setChecked(true);
                        drawerLayout.closeDrawers();
                        break;
                }


                return true;
            }
        });


    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
