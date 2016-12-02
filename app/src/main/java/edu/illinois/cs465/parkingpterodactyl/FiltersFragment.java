package edu.illinois.cs465.parkingpterodactyl;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;


public class FiltersFragment extends Fragment {
    ParkingLocations.carSize carSize = ((MainActivity)getActivity()).currentCarSize;

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

        setCurrentCarSize();
    }

    private void setCurrentCarSize() {
        //fetch all buttons for car sizes
        final Button hb = (Button)getActivity().findViewById(R.id.hb_bt);
        final Button sedan = (Button) getActivity().findViewById(R.id.sedan_bt);
        final Button suv = (Button) getActivity().findViewById(R.id.suv_bt);
        final Button truck = (Button) getActivity().findViewById(R.id.truck_bt);

        //fetch the img buttons
        final ImageView hb_img = (ImageView) getActivity().findViewById(R.id.imageView1);
        final ImageView sedan_img = (ImageView) getActivity().findViewById(R.id.imageView2);
        final ImageView suv_img = (ImageView) getActivity().findViewById(R.id.imageView3);
        final ImageView truck_img = (ImageView) getActivity().findViewById(R.id.imageView4);

        if(carSize == ParkingLocations.carSize.SMALL){
            hb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            hb.setTextColor(Color.WHITE);
            hb_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if(carSize == ParkingLocations.carSize.MEDIUM) {
            sedan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            sedan.setTextColor(Color.WHITE);
            sedan_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if(carSize == ParkingLocations.carSize.LARGE){
            suv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            suv.setTextColor(Color.WHITE);
            suv_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else if (carSize == ParkingLocations.carSize.EXTRA_LARGE) {
            truck.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            truck.setTextColor(Color.WHITE);
            truck_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
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
        setButtonBackground();
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

    private void setButtonBackground() {
        //fetch all buttons for car sizes
        final Button hb = (Button)getActivity().findViewById(R.id.hb_bt);
        final Button sedan = (Button) getActivity().findViewById(R.id.sedan_bt);
        final Button suv = (Button) getActivity().findViewById(R.id.suv_bt);
        final Button truck = (Button) getActivity().findViewById(R.id.truck_bt);

        //fetch the img buttons
        final ImageView hb_img = (ImageView) getActivity().findViewById(R.id.imageView1);
        final ImageView sedan_img = (ImageView) getActivity().findViewById(R.id.imageView2);
        final ImageView suv_img = (ImageView) getActivity().findViewById(R.id.imageView3);
        final ImageView truck_img = (ImageView) getActivity().findViewById(R.id.imageView4);


        //add click listener on each
        hb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                carSize = ParkingLocations.carSize.SMALL;

                hb.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                hb.setTextColor(Color.WHITE);
                suv.setBackgroundColor(Color.TRANSPARENT);
                suv.setTextColor(Color.BLACK);
                sedan.setBackgroundColor(Color.TRANSPARENT);
                sedan.setTextColor(Color.BLACK);
                truck.setBackgroundColor(Color.TRANSPARENT);
                truck.setTextColor(Color.BLACK);

                hb_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sedan_img.setBackgroundColor(Color.TRANSPARENT);
                suv_img.setBackgroundColor(Color.TRANSPARENT);
                truck_img.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        sedan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                carSize = ParkingLocations.carSize.MEDIUM;

                sedan.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sedan.setTextColor(Color.WHITE);
                suv.setBackgroundColor(Color.TRANSPARENT);
                suv.setTextColor(Color.BLACK);
                truck.setBackgroundColor(Color.TRANSPARENT);
                truck.setTextColor(Color.BLACK);
                hb.setBackgroundColor(Color.TRANSPARENT);
                hb.setTextColor(Color.BLACK);

                sedan_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                hb_img.setBackgroundColor(Color.TRANSPARENT);
                suv_img.setBackgroundColor(Color.TRANSPARENT);
                truck_img.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        suv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                carSize = ParkingLocations.carSize.LARGE;

                suv.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                suv.setTextColor(Color.WHITE);
                truck.setBackgroundColor(Color.TRANSPARENT);
                truck.setTextColor(Color.BLACK);
                sedan.setBackgroundColor(Color.TRANSPARENT);
                sedan.setTextColor(Color.BLACK);
                hb.setBackgroundColor(Color.TRANSPARENT);
                hb.setTextColor(Color.BLACK);

                suv_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sedan_img.setBackgroundColor(Color.TRANSPARENT);
                hb_img.setBackgroundColor(Color.TRANSPARENT);
                truck_img.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        truck.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                carSize = ParkingLocations.carSize.EXTRA_LARGE;

                truck.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                truck.setTextColor(Color.WHITE);
                suv.setBackgroundColor(Color.TRANSPARENT);
                suv.setTextColor(Color.BLACK);
                sedan.setBackgroundColor(Color.TRANSPARENT);
                sedan.setTextColor(Color.BLACK);
                hb.setBackgroundColor(Color.TRANSPARENT);
                hb.setTextColor(Color.BLACK);

                truck_img.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                sedan_img.setBackgroundColor(Color.TRANSPARENT);
                suv_img.setBackgroundColor(Color.TRANSPARENT);
                hb_img.setBackgroundColor(Color.TRANSPARENT);
            }
        });
    }
}
