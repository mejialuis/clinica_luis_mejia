package com.example.clinica_dental.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clinica_dental.TablasDB.Paciente;

import java.util.List;

@Dao
public interface PacienteDao {

    @Insert
    long Insertar(Paciente paciente);

    @Update
    void Update(Paciente paciente);

    @Query("SELECT * FROM PacienteTB")
    List<Paciente> ObtenerTodo();

    @Query("SELECT * FROM PacienteTB where user_name=:user_name")
    Paciente ObtenerPorUser_Name(String user_name);

    @Query("SELECT * FROM PacienteTB where id_paciente=:id")
    Paciente ObtenerPorID(int id);
}
