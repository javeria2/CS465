package edu.illinois.cs465.parkingpterodactyl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Adapter class for the list of selected parking locations
 */

public class ParkingListAdapter extends BaseAdapter implements ListAdapter {
    private LinkedList<ParkingLocations> parkingLocations = new LinkedList<>();
    private Context context;


    public ParkingListAdapter(LinkedList<ParkingLocations> list, Context context) {
        this.parkingLocations = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return parkingLocations.size();
    }

    @Override
    public Object getItem(int pos) {
        return parkingLocations.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.parking_list_item_layout, null);
        }

        // Set the item text to the name of the parking location
        TextView listItemText = (TextView)view.findViewById(R.id.list_item_string);
        listItemText.setText(parkingLocations.get(position).getNameOfLoc());

        // Add onclick listener for the delete button that deletes the current parking location
        Button deleteBtn = (Button)view.findViewById(R.id.delete_btn);
        deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                parkingLocations.remove(position);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}