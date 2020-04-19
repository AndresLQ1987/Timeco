package com.android.timeco.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Employes;
import com.android.timeco.Model.Message;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DirectoryViewModel extends ViewModel {

   /* private MutableLiveData<ArrayList<String>> mText;

    public DirectoryViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<ArrayList<String>> getText() {
        return mText;
    }

    public void WriteOnFirebase(String nombre, String mail){

        final ArrayList<String> datos = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        db.child("timeco directory").child("empleados").addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot i : dataSnapshot.getChildren()) {
                    Employes data = i.getValue(Employes.class);
                    datos.add(data.getNombre());
                }
                mText.postValue(datos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("datos").push();
        employe_reference.setValue(new Employes(nombre, mail));
        db.child("timeco directory").child("empleados").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("empleados")
                .child("datos").setValue(employe_reference.getKey());

    }*/
}
