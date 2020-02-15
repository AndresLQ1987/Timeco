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

import java.util.ArrayList;

public class IncidentsViewViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String>> listaMensajes;

    public IncidentsViewViewModel() {
        listaMensajes = new MutableLiveData<>();
    }

    public LiveData<ArrayList<String>> getlistaMensajes() { return listaMensajes; }

    public void readlistaMensajes() {

        final ArrayList<String> incidentList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco incidents").child("mensajes").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
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
}