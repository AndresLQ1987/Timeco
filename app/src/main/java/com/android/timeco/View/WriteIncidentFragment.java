package com.android.timeco.View;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.timeco.R;
import com.android.timeco.ViewModel.WriteIncidentViewModel;

public class WriteIncidentFragment extends Fragment {

    private WriteIncidentViewModel writeIncidentViewModel;

    public static WriteIncidentFragment newInstance() {
        return new WriteIncidentFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_write_incident, container, false);

        final EditText writeIncidents = root.findViewById(R.id.etxIncidencias);
        final Button send = root.findViewById(R.id.btnEnviar);
        //final Button back = root.findViewById(R.id.btnHome);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (writeIncidents.getText().toString() != null) {
                    writeIncidentViewModel.WriteOnFirebase(writeIncidents.getText().toString());
                }
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        writeIncidentViewModel = ViewModelProviders.of(this).get(WriteIncidentViewModel.class);
        // TODO: Use the ViewModel
    }

}
