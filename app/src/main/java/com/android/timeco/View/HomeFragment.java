package com.android.timeco.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.timeco.R;
import com.android.timeco.ViewModel.HomeViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel = new HomeViewModel();
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    /**
     * Elements of Fragment
     */

    private Button btnBack;
    private Button btnReg;

    private EditText et_start_hours;
    private EditText et_start_minuts;
    private EditText et_end_hours;
    private EditText et_end_minuts;
    private EditText et_rest_hours;
    private EditText et_rest_minuts;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Method to get interactions and throw information to View Model
     *
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saveInstanceState
     * @return View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_home, container, false);

        et_start_hours = ui_layout.findViewById(R.id.etxt_start_h);
        et_start_minuts = ui_layout.findViewById(R.id.etxt_start_m);
        et_end_hours = ui_layout.findViewById(R.id.etxt_end_h);
        et_end_minuts = ui_layout.findViewById(R.id.etxt_end_m);
        et_rest_hours = ui_layout.findViewById(R.id.etxt_pause_h);
        et_rest_minuts = ui_layout.findViewById(R.id.etxt_pause_m);

        btnBack = ui_layout.findViewById(R.id.btnBack);
        btnReg = ui_layout.findViewById(R.id.button_reg);


        //ActionListener of button Worklogs to go work logs fragment
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });

        resetInputs();

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.inputWorklog(et_start_hours.getText().toString(), et_start_minuts.getText().toString(),
                        et_end_hours.getText().toString(), et_end_minuts .getText().toString(),
                        et_rest_hours .getText().toString(), et_rest_minuts.getText().toString());

                resetInputs();
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });

        return ui_layout;
    }

    private void resetInputs(){
        et_start_hours.setText("0");
        et_start_minuts.setText("0");
        et_end_hours.setText("0");
        et_end_minuts.setText("0");
        et_rest_hours.setText("0");
        et_rest_minuts.setText("0");
    }
}