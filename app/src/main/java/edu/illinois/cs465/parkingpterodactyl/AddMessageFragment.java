package edu.illinois.cs465.parkingpterodactyl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class AddMessageFragment extends Fragment {

    public AddMessageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_add_message, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("New Post");
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button button = (Button) getActivity().findViewById(R.id.post_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                InputMethodManager inputManager = (InputMethodManager)
                        getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);

                String messageText = ((EditText)getActivity().findViewById(R.id.message_text_entry)).getText().toString();
                ((MainActivity)getActivity()).messages.addFirst(new Message(messageText));

                inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_container,  new MessageBoardFragment());
                transaction.commit();
            }
        });
    }
}
