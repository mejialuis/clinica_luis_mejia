package com.example.clinica_dental.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Doctor;

import java.util.List;

@Dao
public interface ConsultaDao {
    @Insert
    long Insertar(Consultas consultas);

    @Delete
    void Eliminar(Consultas consultas);

    @Query("SELECT * FROM ConsultasTB")
    List<Consultas> ObtenerTodo();

    @Query("SELECT * FROM ConsultasTB where id_doctor=:id_doctor")
    List<Consultas> Consultas_Por_Doctor(int id_doctor);

    @Query("SELECT * FROM ConsultasTB where id_Paciente=:id_paciente")
    List<Consultas> Consultas_Por_Paciente(int id_paciente);
}
