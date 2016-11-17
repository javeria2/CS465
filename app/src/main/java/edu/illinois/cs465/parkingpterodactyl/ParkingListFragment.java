package edu.illinois.cs465.parkingpterodactyl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


public class ParkingListFragment extends Fragment {

    public ParkingListFragment() {
        //required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //set title and names of selected parking locations
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Parking Selections");

        TextView selection1 = (TextView) getActivity().findViewById(R.id.selectionOne);
        selection1.setText(((MainActivity) getActivity()).parkingList.get(0).getNameOfLoc());

        TextView selection2 = (TextView) getActivity().findViewById(R.id.selectionTwo);
        selection2.setText(((MainActivity) getActivity()).parkingList.get(1).getNameOfLoc());

        TextView selection3 = (TextView) getActivity().findViewById(R.id.selectionThree);
        selection3.setText(((MainActivity) getActivity()).parkingList.get(2).getNameOfLoc());
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton navigateButton = (ImageButton) getActivity().findViewById(R.id.navigate);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse(((MainActivity) getActivity()).parkingList.get(0).getNameForGoogleMaps());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //create occupied button??
            }

            /*ImageButton occupiedButton = getActivity().findViewById(R.id.occupied);
            occupiedButton.setOnClickListener(new View.OnClickListener());
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse(((MainActivity)getActivity()).parkingList.get(1).getNameForGoogleMaps());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //recreate occupiedButton2
            }*/

            //ImageButton occupiedButton2
            //setOnClickListener for Occupied Button2
            //on image button click

            /*public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse(((MainActivity)getActivity()).parkingList.get(2).getNameForGoogleMaps());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //Occupied
                //Navigate them back to the map
            }*/

        });


    }

}