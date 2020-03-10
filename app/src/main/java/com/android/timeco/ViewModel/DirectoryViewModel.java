package com.android.timeco.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DirectoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DirectoryViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void WriteOnFirebase(String msg){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("timeco directory").child("empleados").push();
        msg_reference.setValue(new Message("usuario inventado",
                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
        db.child("timeco directory").child("empleados").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("empleados")
                .setValue(msg_reference.getKey());

    }
}
