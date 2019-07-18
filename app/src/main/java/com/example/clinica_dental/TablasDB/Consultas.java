package com.example.clinica_dental.TablasDB;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "ConsultasTB")
public class Consultas {

    @PrimaryKey(autoGenerate = true)
    private int id_consulta;

    @ForeignKey(entity = Paciente.class, parentColumns = "id_Paciente", childColumns = "id_paciente",
    onDelete = CASCADE,
    onUpdate = RESTRICT)
    private int id_Paciente;

    @ForeignKey(entity = Doctor.class, parentColumns = "id_Doctor", childColumns = "id_doctor",
    onDelete = CASCADE,
    onUpdate = RESTRICT)
    private int id_Doctor;

    private String fecha_consulta;
    private String tratamiento;
    private String hora_consulta;

    public Consultas(int id_consulta, int id_Paciente, int id_Doctor, String fecha_consulta, String tratamiento, String hora_consulta) {
        this.id_consulta = id_consulta;
        this.id_Paciente = id_Paciente;
        this.id_Doctor = id_Doctor;
        this.fecha_consulta = fecha_consulta;
        this.tratamiento = tratamiento;
        this.hora_consulta = hora_consulta;
    }

    public Consultas(){

    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_Paciente() {
        return id_Paciente;
    }

    public void setId_Paciente(int id_Paciente) {
        this.id_Paciente = id_Paciente;
    }

    public int getId_Doctor() {
        return id_Doctor;
    }

    public void setId_Doctor(int id_Doctor) {
        this.id_Doctor = id_Doctor;
    }

    public String getFecha_consulta() {
        return fecha_consulta;
    }

    public void setFecha_consulta(String fecha_consulta) {
        this.fecha_consulta = fecha_consulta;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getHora_consulta() {
        return hora_consulta;
    }

    public void setHora_consulta(String hora_consulta) {
        this.hora_consulta = hora_consulta;
    }
}
