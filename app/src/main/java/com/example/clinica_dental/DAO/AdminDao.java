package com.example.clinica_dental.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.clinica_dental.TablasDB.Admin;

import java.util.List;

@Dao
public interface AdminDao {

    @Insert
    long Insertar(Admin admin);

    @Query("Select *FROM AdminTB where user_name=:user_name & password=:password")
    Admin ObtenerPorID(String user_name, String password);

    @Query("SELECT * FROM AdminTB")
    List<Admin> ObtenerTodo();
}
