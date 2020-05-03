package com.android.timeco.Data;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.timeco.Model.Employes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class Repository {

    private static Repository repository;
    private Context context;
    private MutableLiveData<ArrayList<Employes>> lista_empleados_dept;

    private Repository(Context context){

        this.context = context;
        lista_empleados_dept = new MutableLiveData<>();
    }

    public static Repository get(Context context){

        if(repository == null){

            repository = new Repository(context);
        }
        return repository;
    }

    public static Repository getRepository(){ return repository; }

    public void getEmpleadosDepartamento(String departamento) {

        // Lanzar thread de consulta JSON API
        MiHilo thread = new MiHilo();
        thread.execute("https://timeco-3fafd.firebaseio.com/timeco%20directory" + departamento + ".json");

    }

    public LiveData<ArrayList<Employes>> getListaEmpleadosDept(){
        return lista_empleados_dept;
    }

    public class MiHilo extends AsyncTask<String,Void,String> {

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

            ArrayList<Employes> lista_empleados = new ArrayList<>();

            //JSON format

            try {
                JSONArray jsonArray = new JSONArray(data);
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject;
                    jsonObject = jsonArray.getJSONObject(i);

                    //Traspasar el formato JSON a mi modelo
                    Employes employes = new Employes(jsonObject);
                    lista_empleados.add(employes);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            lista_empleados_dept.postValue(lista_empleados);

        }

    }

}
