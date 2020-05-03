package com.android.timeco.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.timeco.R;
import com.android.timeco.ViewModel.StaffViewModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class StaffFragment extends Fragment {

    private StaffViewModel mViewModel = new StaffViewModel();
    private StorageReference mStorage;
    private static final int GALLERY_INTENT = 1;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    //Elements of the fragment;
    private EditText et_name;
    private EditText et_mail;
    private EditText et_position;
    private EditText et_username;
    private EditText et_password;
    //private Selector? role;
    //private Selector? mode?;
    private Button bt_confirm;
    private Button bt_back;
    private Button btn_info;


    public StaffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StaffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StaffFragment newInstance(String param1, String param2) {
        StaffFragment fragment = new StaffFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Method to get interactions and throw information to View Model
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_staff, container, false);

        et_name = ui_layout.findViewById(R.id.txt_Staff_Name);
        et_mail = ui_layout.findViewById(R.id.txt_Staff_Surname);
        et_position = ui_layout.findViewById(R.id.txt_Staff_Position);
        et_username = ui_layout.findViewById(R.id.txt_Staff_Username);
        et_password = ui_layout.findViewById(R.id.txt_Staff_Password);
        final Button galeria = ui_layout.findViewById(R.id.Gallerybutton);
        final Button camera = ui_layout.findViewById(R.id.Camerabutton);
        //TODO Rename id button to btn_confirm
        bt_confirm = ui_layout.findViewById(R.id.bt_add);
        bt_back = ui_layout.findViewById(R.id.bt_back);
        btn_info = ui_layout.findViewById(R.id.btn_info);

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

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mViewModel.enterUser(et_name.getText().toString(),
                        et_mail.getText().toString(),
                        et_position.getText().toString(),
                        et_username.getText().toString(),
                        et_password.getText().toString())) {
                    Toast.makeText(getContext(), "Usuario añadido correctamente", Toast.LENGTH_SHORT).show();
                    StaffViewModel.WriteOnFirebase(et_position.getText().toString(),
                            et_name.getText().toString(), et_mail.getText().toString(), /*FALTA RECOGER URL IMAGEN CARGADA*/);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(
                            R.id.MainActivity, new MenuFragment()).commit();
                }
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(
                        R.id.MainActivity, new MenuFragment()).commit();
            }
        });

        //TODO get the information in fragment and sent to ViewModel StaffViewModel

        return ui_layout;
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

                StorageReference filePath = mStorage.child("fotos").child(uri.getLastPathSegment());
                filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "Foto subida correctamente", Toast.LENGTH_SHORT).show();
                    }
                });

            }catch (Exception e){
                e.printStackTrace();
            }
        } else if (requestCode == 20 && resultCode == RESULT_OK){

            bitmap = (Bitmap) data.getExtras().get("data");

        }

    }
}
