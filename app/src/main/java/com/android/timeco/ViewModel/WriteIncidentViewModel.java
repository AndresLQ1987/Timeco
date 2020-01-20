package com.android.timeco.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.timeco.Model.Message;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class WriteIncidentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WriteIncidentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void WriteOnFirebase(String msg){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference msg_reference = db.child("dam2m8enviomensajes").child("mensajes").push();
        msg_reference.setValue(new Message("usuario inventado",
                "pVBEOgINbVOGdkne7Sjhg3ycW6h2", msg));
        db.child("dam2m8enviomensajes").child("usuarios").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("mensaje")
                .setValue(msg_reference.getKey());

    }
}
