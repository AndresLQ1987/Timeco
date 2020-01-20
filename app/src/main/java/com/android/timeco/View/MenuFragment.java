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
public class MenuFragment extends Fragment {

    Button registroBtn;
    Button visualizarBtn;
    Button escribirBtn;
    Button verBtn;
    Button gestionBtn;
    Button configBtn;
    Button exitBtn;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_menu, container, false);

        registroBtn = ui_layout.findViewById(R.id.btn_registro);
        visualizarBtn = ui_layout.findViewById(R.id.btn_visualizar);
        escribirBtn = ui_layout.findViewById(R.id.btn_escribir);
        verBtn = ui_layout.findViewById(R.id.btn_ver_incidencias);
        gestionBtn = ui_layout.findViewById(R.id.btn_gestion);
        configBtn = ui_layout.findViewById(R.id.btn_config);
        exitBtn = ui_layout.findViewById(R.id.btn_exit);

        registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new HomeFragment()).commit();
            }
        });

        visualizarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new WorklogsFragment()).commit();
            }
        });

        escribirBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new WriteIncident()).commit();
            }
        });

        verBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        //Falta por crear el fragment de ver incidencias!!!
                        R.id.MainActivity, new WorklogsFragment()).commit();
            }
        });

        gestionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new StaffFragment()).commit();
            }
        });

        configBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new OptionsFragment()).commit();
            }
        });

        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new LoginFragment()).commit();
            }
        });

        return ui_layout;
    }

}
