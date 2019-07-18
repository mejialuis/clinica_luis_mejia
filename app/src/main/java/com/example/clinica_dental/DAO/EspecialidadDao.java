package com.example.clinica_dental.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Especialidad;

import java.util.List;

@Dao
public interface EspecialidadDao {

    @Insert
    long Insertar(Especialidad especialidad);

    @Delete
    void Eliminar(Especialidad especialidad);

    @Query("SELECT * FROM EspecialidadTB")
    List<Especialidad> ObtenerTodo();

    @Query("SELECT * FROM EspecialidadTB where nombre_especialidad=:nombre")
    Especialidad ObtenerporNombre(String nombre);

    @Query("SELECT * FROM EspecialidadTB where id_especialidad=:id")
    Especialidad ObtenerPorId(int id);

}
