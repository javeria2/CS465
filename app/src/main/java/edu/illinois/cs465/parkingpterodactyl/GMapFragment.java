package edu.illinois.cs465.parkingpterodactyl;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class GMapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap gmap;

    public GMapFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;

        addCustomMarkers(R.drawable.pin4, 40.1104619, -88.230041);
        addCustomMarkers(R.drawable.pin2, 40.110109, -88.2303623);
        addCustomMarkers(R.drawable.pin3, 40.110363, -88.230620);
        addCustomMarkers(R.drawable.pin5, 40.110072, -88.231741);
        addCustomMarkers(R.drawable.pin1, 40.109871, -88.228367);
        addCustomMarkers(R.drawable.pin6, 40.110790, -88.229032);
        addCustomMarkers(R.drawable.pin7, 40.110634, -88.230207);

        LatLng pin = new LatLng(40.1104619, -88.230041);

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
    }

    //this function adds a new marker on the map
    public void addCustomMarkers(int resource, double lat, double lon) {
        LatLng pin = new LatLng(lat,lon);
        gmap.addMarker(new MarkerOptions()
                .position(pin)
                .title("Marker in sydney")
                .icon(BitmapDescriptorFactory.fromResource(resource)));
    }

}
