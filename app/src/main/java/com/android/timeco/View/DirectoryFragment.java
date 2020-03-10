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

import com.android.timeco.R;
import com.android.timeco.ViewModel.DirectoryViewModel;

public class DirectoryFragment extends Fragment {

    private DirectoryViewModel directoryViewModel;
    ImageView imagen;


    public static DirectoryFragment newInstance() {
        return new DirectoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        directoryViewModel = ViewModelProviders.of(this).get(DirectoryViewModel.class);
        View root = inflater.inflate(R.layout.directory_fragment, container, false);

        final EditText nombre = root.findViewById(R.id.NombreeditText);
        final EditText mail = root.findViewById(R.id.MaileditText);
        final ImageView imagen = root.findViewById(R.id.FotoimageView);
        final Button galeria = root.findViewById(R.id.Gallerybutton);
        final Button camera = root.findViewById(R.id.Camerabutton);
        final Button enviar = root.findViewById(R.id.bt_send);
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
                    directoryViewModel.WriteOnFirebase(nombre.getText().toString());
                }
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

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 10);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = null;

        if(requestCode == 10 && resultCode == RESULT_OK){

            Uri uri;
            uri = data.getData();

            try {

                bitmap = MediaStore.Images.Media
                        .getBitmap(getContentResolver(), uri);


            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (requestCode == 20 && resultCode == RESULT_OK){

            bitmap = (Bitmap) data.getExtras().get("data");

        }

        if(bitmap != null){
            imagen.setImageBitmap(bitmap);
        }


    }
}
