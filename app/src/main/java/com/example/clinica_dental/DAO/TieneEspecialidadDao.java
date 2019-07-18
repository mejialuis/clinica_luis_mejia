package com.example.clinica_dental.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinica_dental.POJOS.DoctorTieneEspecialidad;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;

import java.util.List;

@Dao
public interface TieneEspecialidadDao {

    @Insert
    long Insertar(DoctorhasEspecialidad doctorhasEspecialidad);

    @Delete
    void Borrar(Especialidad especialidad);

    @Query("SELECT * FROM TieneEspecialidadTB where id_Doctor=:id_doctor")
    List<DoctorTieneEspecialidad> ESPECIALIDAD_LIST(int id_doctor);

    @Query("SELECT *FROM TieneespecialidadTB")
    List<DoctorhasEspecialidad> ObtenerTodo();
}
