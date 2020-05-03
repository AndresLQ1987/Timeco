package com.android.timeco.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.timeco.Model.Employes;
import com.android.timeco.R;
import com.android.timeco.ViewModel.StaffSelectionViewModel;

public class DetalleContactoFragment extends Fragment {

    private StaffSelectionViewModel staffSelectionViewModel;
    private TextView nombre, mail, departamento;

    public DetalleContactoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        staffSelectionViewModel = new ViewModelProvider(requireActivity()).get(StaffSelectionViewModel.class);
        View view = inflater.inflate(R.layout.fragment_detalle_contacto, container, false);

        nombre = view.findViewById(R.id.tv_nom);
        mail = view.findViewById(R.id.tv_correo);
        departamento = view.findViewById(R.id.tv_cargo);

        cargar_datos_empleado(staffSelectionViewModel.getEmployesAtPosition(getArguments().getInt("POSICION")));

        return view;
    }

    public void cargar_datos_empleado(Employes employes){

        nombre.setText(employes.getNombre());
        mail.setText(employes.getMail());
        departamento.setText(employes.getPosition());

    }
}
