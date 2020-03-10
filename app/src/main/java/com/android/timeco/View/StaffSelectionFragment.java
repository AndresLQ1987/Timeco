package com.android.timeco.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.android.timeco.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffSelectionFragment extends Fragment {

    private Button dirbutton;
    private Button emplebutton;


    public StaffSelectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ui_layout = inflater.inflate(R.layout.fragment_staff_selection, container, false);

        dirbutton = ui_layout.findViewById(R.id.Directorybutton);
        emplebutton = ui_layout.findViewById(R.id.Empleadobutton);

        dirbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new DirectoryFragment()).commit();
            }
        });

        emplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new StaffFragment()).commit();
            }
        });

        return ui_layout;
    }

}
