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
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.LinkedList;

public class GMapFragment extends Fragment implements OnMapReadyCallback {
    private static View view;
    private GoogleMap gmap;

    public GMapFragment() {
        // Required empty constructor
    }

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

            gmap.addMarker(opts);
        }

        MarkerOptions current_loc = new MarkerOptions().position(new LatLng(40.110451, -88.229364));
        current_loc.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        gmap.addMarker(current_loc);


        //addCustomMarkers(R.drawable.pin3, 40.109700, -88.230400);

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

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Help Overlay Code
        final View topLevelLayout = getActivity().findViewById(R.id.overlaymapfragment);
        if (!((MainActivity)getActivity()).mapOverlaySeen) {
            topLevelLayout.setVisibility(View.VISIBLE);
            ((MainActivity)getActivity()).mapOverlaySeen = true;
        } else {
            topLevelLayout.setVisibility(View.INVISIBLE);
        }
        topLevelLayout.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                topLevelLayout.setVisibility(View.INVISIBLE);
                return false;
            }

        });
        MapFragment fragment = (MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

        Button filter = (Button) getActivity().findViewById(R.id.filter);
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container, new FiltersFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ImageButton up_arrow = (ImageButton) getActivity().findViewById(R.id.up_arrow);
        up_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new FiltersFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

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

        TextView locationName = (TextView)getActivity().findViewById(R.id.location_name);
        locationName.setText(((MainActivity)getActivity()).lastSearch);
    }

    //this function adds a new marker on the map
    public void addCustomMarkers(int resource, double lat, double lon) {
        LatLng pin = new LatLng(lat,lon);
        gmap.addMarker(new MarkerOptions()
                .position(pin)
                /*.title("Marker in sydney")*/
                .icon(BitmapDescriptorFactory.fromResource(resource)));
    }

    //this function adds a new circle
    public void addCustomCircle(LatLng pin, int color1, int color2) {
        gmap.addCircle(new CircleOptions()
                .center(pin)
                .radius(10)
                .strokeColor(color1)
                .fillColor(color2));
    }
 }
