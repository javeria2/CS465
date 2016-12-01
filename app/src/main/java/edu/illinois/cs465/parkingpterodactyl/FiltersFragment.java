package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;

import java.util.LinkedList;


public class FiltersFragment extends Fragment {

    // TODO(Zach) - When this fragment is navigated away from the list of spots to display should be updated based on the filters

    public FiltersFragment() {
        // Required empty constructor
    }

    // TODO(Zach) - set the car size too
    private void setFilterOptions() {
        LinkedList<ParkingLocations.parkingType> types = ((MainActivity)getActivity()).allowedParkingTypes;
        CheckBox freeBox = (CheckBox) getActivity().findViewById(R.id.free);
        CheckBox paidBox = (CheckBox) getActivity().findViewById(R.id.paid);
        CheckBox streetBox = (CheckBox) getActivity().findViewById(R.id.meter);
        CheckBox eventBox = (CheckBox) getActivity().findViewById(R.id.special_event);

        if (types.contains(ParkingLocations.parkingType.FREE)) {
            freeBox.setChecked(true);
        } else {
            freeBox.setChecked(false);
        }

        if (types.contains(ParkingLocations.parkingType.PAID)) {
            paidBox.setChecked(true);
        } else {
            paidBox.setChecked(false);
        }

        if (types.contains(ParkingLocations.parkingType.STREET)) {
            streetBox.setChecked(true);
        } else {
            streetBox.setChecked(false);
        }

        if (types.contains(ParkingLocations.parkingType.EVENT)) {
            eventBox.setChecked(true);
        } else {
            eventBox.setChecked(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        setFilterOptions();
        ImageButton down_arrow = (ImageButton) getActivity().findViewById(R.id.down_arrow);
        down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new GMapFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }

}
