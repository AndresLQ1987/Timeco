package com.android.timeco.View;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.timeco.Model.Employes;
import com.android.timeco.R;
import com.android.timeco.ViewModel.StaffSelectionViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffSelectionFragment extends Fragment {

    private StaffSelectionViewModel staffSelectionViewModel;
    private RecyclerView recyclerView;
    private AdapterEmpleados miAdapter;
    private ArrayList<Employes> lista_empleados = new ArrayList<>();
    private Button añadir;
    private Button atras;
    private Button info;
    //private Context ctx;


    public StaffSelectionFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Spinner spinner;

        View root = inflater.inflate(R.layout.fragment_staff_selection, container, false);

        //staffSelectionViewModel = ViewModelProviders.of(this).get(StaffSelectionViewModel.class);
        staffSelectionViewModel = new ViewModelProvider(requireActivity()).get(StaffSelectionViewModel.class);

        recyclerView = root.findViewById(R.id. recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        miAdapter = new AdapterEmpleados();
        recyclerView.setAdapter(miAdapter);
        añadir = root.findViewById(R.id.bt_add);
        atras = root.findViewById(R.id.bt_back);
        info = root.findViewById(R.id.btn_info);

        spinner = root.findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.departamentos_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Listener que comunica con el ViewHolder
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                staffSelectionViewModel.getEmpleadosDepartamento(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        añadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new StaffFragment()).commit();
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });

        staffSelectionViewModel.getListaEmpleadosDept().observe(getViewLifecycleOwner(), new Observer<ArrayList<Employes>>() {
            @Override
            public void onChanged(ArrayList<Employes> employes) {

                //Modificar ArrayList del Recycler Adapter
            }
        });

        return root;
    }

    public class StaffViewHolder extends RecyclerView.ViewHolder{

        private TextView nombre;


        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.tv_nombre);

            //Listener sobre el ítem pulsado
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("POSICION", getAdapterPosition());
                    Navigation.findNavController(v).navigate(R.id.detalleContactoFragment, bundle);
                }
            });

        }

        public void rellenarEmpleado(Employes employes){

            nombre.setText(employes.getNombre());
        }
    }

    public class AdapterEmpleados extends RecyclerView.Adapter<StaffViewHolder>{

        @NonNull
        @Override
        public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            return new StaffViewHolder(getLayoutInflater().inflate(R.layout.staff_viewholder, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {

            holder.rellenarEmpleado(lista_empleados.get(position));
        }

        @Override
        public int getItemCount() {

            return lista_empleados.size();
        }
    }

}