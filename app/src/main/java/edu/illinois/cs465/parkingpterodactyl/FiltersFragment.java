package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


public class FiltersFragment extends Fragment {

    // TODO(Zach) - Assign the checkbox values (and eventually the car size selection) based off of the variables in the main activity
    // TODO(Zach) - When this fragment is navigated away from the list of spots to display should be updated based on the filters

    public FiltersFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filters, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
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
