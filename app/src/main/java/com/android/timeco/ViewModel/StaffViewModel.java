package com.android.timeco.ViewModel;

import android.widget.EditText;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.timeco.MainActivity;
import com.android.timeco.Model.Employes;
import com.android.timeco.Model.User;
import com.android.timeco.R;
import com.android.timeco.View.StaffFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Class StaffViewModel have methods to process the information gets in view staff
 */
public class StaffViewModel extends ViewModel {
    //TODO Methods to process staff add or remove workers, edit, etc.

    private MutableLiveData<String> mText;

    public StaffViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public static void WriteOnFirebase(String position, String nombre, String mail, String urlfoto){

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

        if (position == "Comercial") {
            DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("Comercial").push();
            employe_reference.setValue(new Employes(nombre, mail, urlfoto));
            //Pongo esta parte de código comentado porque creo que no es necesario en este caso
           /* db.child("timeco directory").child("empleados").child("pVBEOgINbVOGdkne7Sjhg3ycW6h2").child("empleados")
                    .child("datos").setValue(employe_reference.getKey());*/

        } else if (position == "Administración") {
            DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("Administración").push();
            employe_reference.setValue(new Employes(nombre, mail, urlfoto));

        } else if (position == "RRHH") {
            DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("RRHH").push();
            employe_reference.setValue(new Employes(nombre, mail, urlfoto));

        } else if (position == "Técnicos") {
            DatabaseReference employe_reference = db.child("timeco directory").child("empleados").child("Técnicos").push();
            employe_reference.setValue(new Employes(nombre, mail, urlfoto));

        }

    }

    public boolean enterUser(String name, String mail, String position, String username,
            String password){
        try{
            User user = new User(username, password, "", 2);
            MainActivity.accessData.saveUser(user);
            MainActivity.accessData.insertInPostgres(user.getId(),user.getUsername(),user.getPassword(),
                    user.getFullname(),user.getRol());

            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
}
