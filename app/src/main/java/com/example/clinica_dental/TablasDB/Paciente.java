package com.example.clinica_dental.TablasDB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "PacienteTB")
public class Paciente implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_paciente;
    private String nombres;
    private String apellidos;
    private String user_name;
    private String password;
    private String telfono;

    public Paciente(int id_paciente, String nombres, String apellidos, String user_name, String password, String telfono) {
        this.id_paciente = id_paciente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.user_name = user_name;
        this.password = password;
        this.telfono = telfono;
    }

    public Paciente(){

    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
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
