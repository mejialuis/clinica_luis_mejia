package com.example.clinica_dental.TablasDB;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import static androidx.room.ForeignKey.CASCADE;
import static androidx.room.ForeignKey.RESTRICT;

@Entity(tableName = "TieneEspecialidadTB")
public class DoctorhasEspecialidad implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id_doctor_has_especialidad;

    @ForeignKey(entity = Doctor.class, parentColumns = "id_Doctor", childColumns = "id_doctor",
    onDelete = CASCADE,
    onUpdate = RESTRICT)
    private int id_Doctor;

    @ForeignKey(entity = Especialidad.class, parentColumns = "id_Especialidad", childColumns = "id_especialidad",
    onDelete = CASCADE,
    onUpdate = RESTRICT)
    private int id_Especialidad;

    public DoctorhasEspecialidad(){

    }

    public DoctorhasEspecialidad(int id_doctor_has_especialidad, int id_Doctor, int id_Especialidad) {
        this.id_doctor_has_especialidad = id_doctor_has_especialidad;
        this.id_Doctor = id_Doctor;
        this.id_Especialidad = id_Especialidad;
    }

    public int getId_doctor_has_especialidad() {
        return id_doctor_has_especialidad;
    }

    public void setId_doctor_has_especialidad(int id_doctor_has_especialidad) {
        this.id_doctor_has_especialidad = id_doctor_has_especialidad;
    }

    public int getId_Doctor() {
        return id_Doctor;
    }

    public void setId_Doctor(int id_Doctor) {
        this.id_Doctor = id_Doctor;
    }

    public int getId_Especialidad() {
        return id_Especialidad;
    }

    public void setId_Especialidad(int id_Especialidad) {
        this.id_Especialidad = id_Especialidad;
    }
}
