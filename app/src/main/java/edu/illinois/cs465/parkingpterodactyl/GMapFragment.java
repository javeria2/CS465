package edu.illinois.cs465.parkingpterodactyl;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GMapFragment extends Fragment implements OnMapReadyCallback {
    private static View view;
    private GoogleMap gmap;

    public GMapFragment() {
        // Required empty constructor
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

        addCustomMarkers(R.drawable.pin3, 40.109700, -88.230400);
        addCustomMarkers(R.drawable.pin3, 40.111200, -88.232050);
        addCustomMarkers(R.drawable.pin7, 40.110790, -88.229032);

        LatLng pin = new LatLng(40.109700, -88.230400);
        LatLng pin1 = new LatLng(40.110790, -88.229032);

        addCustomCircle(pin, Color.BLACK, Color.GREEN);
        addCustomCircle(pin1, Color.BLACK, Color.RED);


        gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(pin, 17));
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

    public View onCreateVew(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MapFragment fragment = (MapFragment)getActivity().getFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

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
        Circle circle = gmap.addCircle(new CircleOptions()
                .center(pin)
                .radius(10)
                .strokeColor(color1)
                .fillColor(color2));
    }
 }
