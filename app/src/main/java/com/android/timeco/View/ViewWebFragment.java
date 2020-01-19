package com.android.timeco.View;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.android.timeco.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ViewWebFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    /**
     * Elements of Fragment
     */

    private WebView web;

    public ViewWebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewWebFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewWebFragment newInstance(String param1, String param2) {
        ViewWebFragment fragment = new ViewWebFragment();
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
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState saveInstanceState
     * @return View
     */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View ui_layout = inflater.inflate(R.layout.fragment_view_web, container, false);

        web = ui_layout.findViewById(R.id.web);

       // url_austria = austria.getText().toString();

        //MiHilo hilo = new MiHilo();
       // hilo.execute("https://agora.xtec.cat/insjoandaustria/");

       /* btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task1().execute(txtUsuario.getText().toString());
            }
        });*/

//}


   /* public class MiHilo extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection;
            URL url;
            connection = null;
            String result;
            result ="";

            try{

                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();

                int data = inputStream.read();

                while(data != -1){
                    result += (char) data;
                    data = inputStream.read();
                }

            }catch (Exception e){

                e.printStackTrace();

            }

            Log.i("RESULT", result);

            return result;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);

           /* try {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");

                Log.i("WEATHER", jsonArray.toString());

                for(int i=0; i<jsonArray.length(); i++){

                    JSONObject jsonitem = jsonArray.getJSONObject(i);
                    tiempo.setText(jsonitem.getString("description"));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }*/


       // }
   // }

        return ui_layout;
    }
}
