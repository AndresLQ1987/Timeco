package com.android.timeco.View;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.timeco.R;


public class PhotoFragment extends Fragment {

    public PhotoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_photo, container, false);

        final ImageView imagen = ui_layout.findViewById(R.id.FotoimageView);
        final Button atras = ui_layout.findViewById(R.id.bt_back);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });



        return ui_layout;
    }
}
