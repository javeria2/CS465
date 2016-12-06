package edu.illinois.cs465.parkingpterodactyl;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class GMapFragment extends Fragment implements GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback {
    private static View view;
    private GoogleMap gmap;

    public GMapFragment() {
        // Required empty constructor
    }

    // Filter the list of parking locations based on the stored filter parameters. Returns the
    // list of the parking spots meeting the filter criteria.
    private LinkedList<ParkingLocations> filterLocations(LinkedList<ParkingLocations> allLocations) {
        ParkingLocations.carSize size = ((MainActivity)getActivity()).currentCarSize;
        LinkedList<ParkingLocations.parkingType> types = ((MainActivity)getActivity()).allowedParkingTypes;

        LinkedList<ParkingLocations> validLocations = new LinkedList<>();

        for (ParkingLocations loc : allLocations) {
            if (loc.spotBigEnough(size) && types.contains(loc.getType())) {
                validLocations.add(loc);
            }
        }

        return validLocations;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null)
                parent.removeView(view);
        }
        try {
            view = inflater.inflate(R.layout.fragment_map, container, false);
        } catch (InflateException e) {
            /* map is already there, just return view as it is */
        }
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.clear();
        gmap.setOnInfoWindowClickListener(this);

        // Add all of the parking locations that meet the filter criteria.
        for (ParkingLocations loc : filterLocations(((MainActivity)getActivity()).allParkingLocations)) {
            MarkerOptions opts = new MarkerOptions().position(loc.getLatLng());
            // TODO(Zach) - Replace 'x' with the actual distance
            opts.snippet("Distance to Destination " + "x" + " mile(s)");

            switch(loc.getType()) {
                case FREE:
                    opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    opts.title("Free Parking");
                    break;
                case PAID:
                    opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                    opts.title("Paid Parking");
                    break;
                case STREET:
                    opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                    opts.title("Street Parking");
                    break;
                case EVENT:
                    opts.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                    opts.title("Event Parking");
                    break;
            }

            Marker newMarker = gmap.addMarker(opts);
            newMarker.setTag(loc);
        }

        // Add a red marker for the location that was searched for
        MarkerOptions current_loc = new MarkerOptions().position(new LatLng(40.110451, -88.229364));
        current_loc.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        gmap.addMarker(current_loc);

        // Center the map on the red pin
        LatLng zoomlatlng = new LatLng(40.110451, -88.229364);

        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(zoomlatlng, 16));
        if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gmap.setMyLocationEnabled(true);

    }

    // Overwrite the functionality of the onclick listener for the info windows for the pins so that
    // the parking location is added to the list of selected locations
    @Override
    public void onInfoWindowClick(Marker marker) {
        ParkingLocations loc = (ParkingLocations) marker.getTag();
        if (!((MainActivity)getActivity()).selectedParkingLocations.contains(loc)) {
            ((MainActivity)getActivity()).selectedParkingLocations.add(loc);
        }
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Button listener for dismissing overlay
        Button mapOverlayDoneButton = (Button) getActivity().findViewById(R.id.mapOverlayDone);
        mapOverlayDoneButton.setVisibility(View.VISIBLE);
        mapOverlayDoneButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                RelativeLayout homeOverlay = (RelativeLayout) getActivity().findViewById(R.id.map_help_layout);
                homeOverlay.setVisibility(View.INVISIBLE);
                ((MainActivity)getActivity()).mapOverlaySeen = true;
                return false;
            }

        });

        MapFragment fragment = (MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        // Button handler for the up arrow that opens the filter page
        ImageButton up_arrow = (ImageButton) getActivity().findViewById(R.id.up_arrow);
        up_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new FiltersFragment());
                transaction.commit();
            }
        });

        // Button handler for the filter text that opens the filter page
        Button filterButton = (Button) getActivity().findViewById(R.id.filter);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new FiltersFragment());
                transaction.commit();
            }
        });

        // Button handler for the button that takes you to the next page (the list of spots)
        Button goToListButton = (Button) getActivity().findViewById(R.id.goToList);
        goToListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, new ParkingListFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        // Set the search location in the bottom left corner
        TextView locationName = (TextView)getActivity().findViewById(R.id.location_name);
        locationName.setText(((MainActivity)getActivity()).lastSearch);

        // Hide the overlay if it has been seen
        if (((MainActivity)getActivity()).mapOverlaySeen) {
            RelativeLayout homeOverlay = (RelativeLayout) getActivity().findViewById(R.id.map_help_layout);
            homeOverlay.setVisibility(View.INVISIBLE);
        }
    }
 }
