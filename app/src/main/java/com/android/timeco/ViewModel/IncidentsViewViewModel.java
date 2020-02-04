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

    private MutableLiveData<Message> mMsg;
    private MutableLiveData<String> msgID;
    private MutableLiveData<ArrayList<Message>> listaMensajes;

    public IncidentsViewViewModel() {
        mMsg = new MutableLiveData<>();
        msgID = new MutableLiveData<>();
        listaMensajes = new MutableLiveData<>();

    }

    public LiveData<Message> getMsg() {
        return mMsg;
    }

    public LiveData<String> getMsgID() {
        return msgID;
    }

    //public LiveData<Message> getListaMensajes() { return listaMensajes; }

    public void readMsgID(String uid){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco incidents").child("usuarios").child(uid).child("mensaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                msgID.postValue(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void readMsg(String msgID){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco incidents").child("mensajes").child(msgID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mMsg.postValue(dataSnapshot.getValue(Message.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    //Metodo inventado para probar RecyclerView con un ArrayList manual
    public ArrayList<String> getListadoRecycler(){
        final ArrayList<String> incidentList = new ArrayList<>();

        incidentList.add("Incidencia 1");
        incidentList.add("Incidencia 2");
        incidentList.add("Incidencia 3");
        incidentList.add("Incidencia 4");
        incidentList.add("Incidencia 5");

/*        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco incidents").child("mensajes").child(msgID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    incidentList.add(postSnapshot.getValue(Message.class).getMensaje());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        return incidentList;
    }
}
