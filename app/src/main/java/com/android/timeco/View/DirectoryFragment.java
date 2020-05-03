package com.android.timeco.View;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.timeco.R;
import com.android.timeco.ViewModel.DirectoryViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class DirectoryFragment extends Fragment{

    /*private DirectoryViewModel directoryViewModel;
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;


    public static DirectoryFragment newInstance() {
        return new DirectoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.directory_fragment, container, false);

        directoryViewModel = ViewModelProviders.of(this).get(DirectoryViewModel.class);

        mStorage = FirebaseStorage.getInstance().getReference();

        final EditText nombre = root.findViewById(R.id.NombreeditText);
        final EditText mail = root.findViewById(R.id.MaileditText);
        final ImageView imagen = root.findViewById(R.id.FotoimageView);
        final Button galeria = root.findViewById(R.id.Gallerybutton);
        final Button camera = root.findViewById(R.id.Camerabutton);
        final Button enviar = root.findViewById(R.id.bt_add);
        final Button atras = root.findViewById(R.id.bt_back);

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cargar_imagen_galeria();

            }
        });

       camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                take_photo();

            }
        });

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString() != null && mail.getText().toString() != null) {
                    //directoryViewModel.WriteOnFirebase(nombre.getText().toString(), mail.getText().toString());


                } else {
                    Toast.makeText(getContext(), "Campo nombre y/o mail sin rellenar", Toast.LENGTH_LONG).show();

                }
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new PhotoFragment()).commit();

            }
        });
        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });

        return root;

    }

   private void take_photo() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 20);

    }

    private void cargar_imagen_galeria() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = null;

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            Uri uri;
            uri = data.getData();


            try {

              /*bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);*/

               /* StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Foto subida correctamente", Toast.LENGTH_SHORT).show();
                    }
                });*/

                /*filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       StorageReference descargar = taskSnapshot.getStorage();

                       Glide.with(DirectoryFragment.this)
                                .load(descargar)
                                .fitCenter()
                                .centerCrop()
                                .into(imagen);
                    }
                });*/


           /* }catch (Exception e){
                e.printStackTrace();
            }
        } else if (requestCode == 20 && resultCode == RESULT_OK){

            bitmap = (Bitmap) data.getExtras().get("data");

        }

    }*/

}
