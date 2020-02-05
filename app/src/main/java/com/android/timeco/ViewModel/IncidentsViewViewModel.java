package com.android.timeco.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IncidentsViewViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> listaMensajes;
    //private MutableLiveData<ArrayList<String>> listaMensajes;

    public IncidentsViewViewModel() {

        listaMensajes = new MutableLiveData<>();

    }

    public LiveData<ArrayList<String>> getlistaMensajes() { return listaMensajes; }


    //public LiveData<ArrayList<String>> getListaMensajes() { return listaMensajes; }

    public void readlistaMensajes(){

        final ArrayList<String> incidentList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco incidents").child("mensajes").addValueEventListener(new ValueEventListener()
            {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot i : dataSnapshot.getChildren()){

                    //String mensaje = i.child("mensaje").getValue(String.class);
                    Message msg = i.getValue(Message.class);
                    incidentList.add(msg.getMensaje());
                }

                listaMensajes.postValue(incidentList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    //Metodo inventado para probar RecyclerView con un ArrayList manual

   /* public ArrayList<String> getListadoRecycler(){
        final ArrayList<String> incidentList = new ArrayList<>();

        incidentList.add("Incidencia 1");
        incidentList.add("Incidencia 2");
        incidentList.add("Incidencia 3");
        incidentList.add("Incidencia 4");
        incidentList.add("Incidencia 5");

        return incidentList;
    }*/

  //Método para probar el RecyclerView con un ArrayList manual haciendo uso del LiveData

  /*public void listaMensajes() {

      ArrayList<String> incidentList = new ArrayList<>();

      incidentList.add("Incidencia 1");
      incidentList.add("Incidencia 2");
      incidentList.add("Incidencia 3");
      incidentList.add("Incidencia 4");
      incidentList.add("Incidencia 5");

      listaMensajes.postValue(incidentList);



  }*/

}
