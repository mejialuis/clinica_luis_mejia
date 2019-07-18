package com.example.clinica_dental.TablasDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "DoctorTB")
public class Doctor implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id_doctor;
    private String nombres;
    private String apellidos;
    private String user_name;
    private String password;
    private String telfono;

    public Doctor(int id_doctor, String nombres, String apellidos, String user_name, String password, String telfono) {
        this.id_doctor = id_doctor;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.user_name = user_name;
        this.password = password;
        this.telfono = telfono;
    }

    public Doctor(){

    }

    public int getId_doctor() {
        return id_doctor;
    }

    public void setId_doctor(int id_doctor) {
        this.id_doctor = id_doctor;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public String getTelfono() {
        return telfono;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }
}
