package edu.illinois.cs465.parkingpterodactyl;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Parking Pterodactyl");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ImageButton searchButton = (ImageButton) getActivity().findViewById(R.id.main_search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Hide the keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                ((MainActivity)getActivity()).lastSearch = ((EditText)getActivity().findViewById(R.id.search_text)).getText().toString();

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new GMapFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

}
