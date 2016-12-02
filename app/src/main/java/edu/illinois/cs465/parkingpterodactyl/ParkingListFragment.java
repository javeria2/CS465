package edu.illinois.cs465.parkingpterodactyl;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;


public class ParkingListFragment extends Fragment {

    private static final int notificationid = 0;

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
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        ParkingListAdapter adapter = new ParkingListAdapter(((MainActivity) getActivity()).selectedParkingLocations, getActivity());

        ListView listView = (ListView)getActivity().findViewById(R.id.parking_list_items);
        listView.setAdapter(adapter);


        ImageButton navigateButton = (ImageButton) getActivity().findViewById(R.id.navigate);
        //Help Overlay Code
        final View topLevelLayout = getActivity().findViewById(R.id.overlayparkinglocations);
        if (!((MainActivity)getActivity()).parkingSeen) {
            topLevelLayout.setVisibility(View.VISIBLE);
            ((MainActivity)getActivity()).parkingSeen = true;
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
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).selectedParkingLocations.size() > 0) {
                    Uri gmmIntentUri = Uri.parse(((MainActivity) getActivity()).selectedParkingLocations.get(0).getNameForGoogleMaps());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);

                    // Create occupied button
                    if (((MainActivity) getActivity()).selectedParkingLocations.size() > 1) {
                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(getActivity())
                                        .setSmallIcon(R.drawable.launcher_icon)
                                        .setContentTitle("Spot Occupied")
                                        .setContentText("Navigate to next spot.");

                        Uri secondGmmIntentUri = Uri.parse(((MainActivity) getActivity()).selectedParkingLocations.get(1).getNameForGoogleMaps());
                        Intent secondMapIntent = new Intent(Intent.ACTION_VIEW, secondGmmIntentUri);
                        secondMapIntent.setPackage("com.google.android.apps.maps");
                        PendingIntent secondIntentPending = PendingIntent.getActivity(getActivity(),
                                0, secondMapIntent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                        mBuilder.setContentIntent(secondIntentPending);

                        NotificationManager mNotificationManager =
                                (NotificationManager) getActivity().getSystemService(getActivity().NOTIFICATION_SERVICE);
                        mNotificationManager.notify(notificationid, mBuilder.build());
                    }
                }
            }

            /*ImageButton occupiedButton = getActivity().findViewById(R.id.occupied);
            occupiedButton.setOnClickListener(new View.OnClickListener());
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse(((MainActivity)getActivity()).selectedParkingLocations.get(1).getNameForGoogleMaps());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //recreate occupiedButton2
            }*/

            //ImageButton occupiedButton2
            //setOnClickListener for Occupied Button2
            //on image button click

            /*public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse(((MainActivity)getActivity()).selectedParkingLocations.get(2).getNameForGoogleMaps());
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
                //Occupied
                //Navigate them back to the map
            }*/

        });


    }

}