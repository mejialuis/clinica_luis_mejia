package com.example.clinica_dental.TablasDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "AdminTB")
public class Admin {

    @PrimaryKey(autoGenerate = true)
    private int id_admin;
    private String nombre;
    private String apellido;
    private String user_name;
    private String password;

    public Admin(int id_admin, String nombre, String apellido, String user_name, String password) {
        this.id_admin = id_admin;
        this.nombre = nombre;
        this.apellido = apellido;
        this.user_name = user_name;
        this.password = password;
    }

    public Admin(){

    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
