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

    /*private MutableLiveData<String> mText;

    public DirectoryViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void WriteOnFirebase(String position, String nombre, String mail){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("datos").push();
        employe_reference.setValue(new Employes(position, nombre, mail));
        db.child("timeco directory").child("empleados").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("empleados")
                .child("datos").setValue(employe_reference.getKey());

            }*/
}
