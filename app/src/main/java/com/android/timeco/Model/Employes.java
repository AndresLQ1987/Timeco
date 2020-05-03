package com.android.timeco.Model;

import org.json.JSONException;
import org.json.JSONObject;

public class Employes {

    private String nombre;
    private String mail;
    private String position;
    private String urlfoto;

    public Employes(String nombre, String mail, String position, String urlfoto) {
        this.nombre = nombre;
        this.mail = mail;
        this.position = position;
        this.urlfoto = urlfoto;
    }

    public Employes(String nombre, String mail, String urlfoto) {
        this.nombre = nombre;
        this.mail = mail;
        this.urlfoto = urlfoto;
    }

    public Employes(){

    }

    //Constructor para JSONObject
    public Employes(JSONObject jsonObject){
        try {
            nombre = jsonObject.getString("nombre");
            mail = jsonObject.getString("mail");
            position = jsonObject.getString("position");
            urlfoto = jsonObject.getString("urlfoto");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getUrlfoto() { return urlfoto; }

    public void setUrlfoto(String urlfoto) { this.urlfoto = urlfoto; }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPosition() { return position; }

    public void setPosition(String position) {
        this.position = position;
    }

}
