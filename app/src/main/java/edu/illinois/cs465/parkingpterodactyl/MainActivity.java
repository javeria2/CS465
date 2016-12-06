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

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;

    // TODO(Zach): Store data in a database
    // List of messages
    LinkedList<Message> messages;
    // List of all parking locations
    LinkedList<ParkingLocations> allParkingLocations;
    // List of selected parking locations
    LinkedList<ParkingLocations> selectedParkingLocations;
    // Last search term
    String lastSearch;

    // Variables keeping track of whether or not the overlays have been seen
    Boolean parkingSeen;
    Boolean mapOverlaySeen;
    Boolean homeOverlaySeen;

    // Filter variables
    ParkingLocations.carSize currentCarSize;
    LinkedList<ParkingLocations.parkingType> allowedParkingTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create fake starting messages
        messages = new LinkedList<>();
        messages.add(new Message("There is a lot of parking left at the lot on the corner of Springfield and Gregory."));

        // Create linked list for parking locations
        allParkingLocations = new LinkedList<>();
        // Add fake parking locations
        allParkingLocations.add(new ParkingLocations("The Union", 40.110221, -88.226247,
                ParkingLocations.carSize.SMALL, ParkingLocations.parkingType.STREET));
        allParkingLocations.add(new ParkingLocations("Meters on Wright St", 40.1113254,
                -88.228872, ParkingLocations.carSize.LARGE, ParkingLocations.parkingType.STREET));
        allParkingLocations.add(new ParkingLocations("Lot by Walgreens", 40.110056, -88.232529,
                ParkingLocations.carSize.EXTRA_LARGE, ParkingLocations.parkingType.FREE));
        allParkingLocations.add(new ParkingLocations("Grainger Library", 40.112565, -88.226900,
                ParkingLocations.carSize.LARGE, ParkingLocations.parkingType.FREE));

        // Create list for storing selected parking locations
        selectedParkingLocations = new LinkedList<>();

        // Instantiate the last search term
        lastSearch = "";

        // Initialize overlay tracking variables (start with none of the variables having been seen)
        parkingSeen=false;
        mapOverlaySeen=false;
        homeOverlaySeen=false;

        // Set the default car size (filter variable)
        this.currentCarSize = ParkingLocations.carSize.LARGE;

        // Create a linked list for the allowed parking types (filter variables) and add defaults
        this.allowedParkingTypes = new LinkedList<>();
        this.allowedParkingTypes.add(ParkingLocations.parkingType.FREE);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.PAID);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.STREET);
        this.allowedParkingTypes.add(ParkingLocations.parkingType.EVENT);

        // Setup navigation drawer
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Start the home fragment (go to the home page initially)
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();

        // Set the action bar title
        getSupportActionBar().setTitle("Parking Pterodactyl");

        // Setup listeners for the buttons in the navigation drawer
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
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
