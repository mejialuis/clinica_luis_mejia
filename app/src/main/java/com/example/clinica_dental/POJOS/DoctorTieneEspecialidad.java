package com.example.clinica_dental.POJOS;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DoctorTieneEspecialidad implements Serializable {

    @Embedded
    private DoctorhasEspecialidad doctorhasEspecialidad;

    @Relation(entity = Especialidad.class,
    parentColumn = "id_Especialidad",
    entityColumn = "id_especialidad")
    List<Especialidad> especialidadList = new ArrayList<>();

    public DoctorhasEspecialidad getDoctorhasEspecialidad() {
        return doctorhasEspecialidad;
    }

    public void setDoctorhasEspecialidad(DoctorhasEspecialidad doctorhasEspecialidad) {
        this.doctorhasEspecialidad = doctorhasEspecialidad;
    }

    public List<Especialidad> getEspecialidadList() {
        return especialidadList;
    }

    public void setEspecialidadList(List<Especialidad> especialidadList) {
        this.especialidadList = especialidadList;
    }
}
