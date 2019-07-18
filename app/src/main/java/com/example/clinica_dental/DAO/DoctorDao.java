package com.example.clinica_dental.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.List;

@Dao
public interface DoctorDao {

    @Insert
    long Insertar(Doctor doctor);

    @Delete
    void Eliminar(Doctor doctor);

    @Update
    void Update(Doctor doctor);

    @Query("SELECT * FROM DoctorTB")
    List<Doctor> ObtenerTodo();

    @Query("SELECT * FROM DoctorTB where user_name=:user_name")
    Doctor ObtenerPorUser_Name(String user_name);

    @Query("SELECT * FROM DoctorTB where nombres=:nombre")
    Doctor ObtenerPorNombre(String nombre);

    @Query("SELECT * FROM DoctorTB where id_doctor=:id")
    Doctor ObtenerPorID(int id);
}
