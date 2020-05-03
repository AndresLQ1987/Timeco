package com.android.timeco.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.android.timeco.Data.Repository;
import com.android.timeco.Model.Employes;

import java.util.ArrayList;

public class StaffSelectionViewModel extends ViewModel {

    Repository repository;
    ArrayList<Employes> lista_empleados_dept;
    MutableLiveData<ArrayList<Employes>> liveData_lista_empleados;

    public StaffSelectionViewModel(){

        repository = Repository.getRepository();
        lista_empleados_dept = new ArrayList<>();
        liveData_lista_empleados = new MutableLiveData<>();
    }

    public void getEmpleadosDepartamento(String departamento){

        repository.getEmpleadosDepartamento(departamento);
        repository.getListaEmpleadosDept().observeForever(new Observer<ArrayList<Employes>>() {
            @Override
            public void onChanged(ArrayList<Employes> employes) {
                lista_empleados_dept = employes;
                liveData_lista_empleados.postValue(lista_empleados_dept);
            }
        });
    }

    public LiveData<ArrayList<Employes>> getListaEmpleadosDept(){
        return liveData_lista_empleados;
    }

    public Employes getEmployesAtPosition(int posicion){

        return lista_empleados_dept.get(posicion);

    }

}
