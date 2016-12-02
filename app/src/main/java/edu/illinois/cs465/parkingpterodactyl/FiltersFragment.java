package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;


public class FiltersFragment extends Fragment {

    // TODO(Zach) - Add listeners for car size

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

    private void setCheckboxListeners() {
        CheckBox freeBox = (CheckBox) getActivity().findViewById(R.id.free);
        CheckBox paidBox = (CheckBox) getActivity().findViewById(R.id.paid);
        CheckBox streetBox = (CheckBox) getActivity().findViewById(R.id.meter);
        CheckBox eventBox = (CheckBox) getActivity().findViewById(R.id.special_event);

        freeBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!((MainActivity)getActivity()).allowedParkingTypes.contains(ParkingLocations.parkingType.FREE))
                    ((MainActivity)getActivity()).allowedParkingTypes.add(ParkingLocations.parkingType.FREE);
                } else {
                    ((MainActivity) getActivity()).allowedParkingTypes.remove(ParkingLocations.parkingType.FREE);
                }
            }
        });

        paidBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!((MainActivity)getActivity()).allowedParkingTypes.contains(ParkingLocations.parkingType.PAID))
                        ((MainActivity)getActivity()).allowedParkingTypes.add(ParkingLocations.parkingType.PAID);
                } else {
                    ((MainActivity) getActivity()).allowedParkingTypes.remove(ParkingLocations.parkingType.PAID);
                }
            }
        });

        streetBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!((MainActivity)getActivity()).allowedParkingTypes.contains(ParkingLocations.parkingType.STREET))
                        ((MainActivity)getActivity()).allowedParkingTypes.add(ParkingLocations.parkingType.STREET);
                } else {
                    ((MainActivity) getActivity()).allowedParkingTypes.remove(ParkingLocations.parkingType.STREET);
                }
            }
        });

        eventBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!((MainActivity)getActivity()).allowedParkingTypes.contains(ParkingLocations.parkingType.EVENT))
                        ((MainActivity)getActivity()).allowedParkingTypes.add(ParkingLocations.parkingType.EVENT);
                } else {
                    ((MainActivity) getActivity()).allowedParkingTypes.remove(ParkingLocations.parkingType.EVENT);
                }
            }
        });
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
        setCheckboxListeners();
        ImageButton down_arrow = (ImageButton) getActivity().findViewById(R.id.down_arrow);
        TextView locationName = (TextView)getActivity().findViewById(R.id.location_name_1);
        locationName.setText(((MainActivity)getActivity()).lastSearch);
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
