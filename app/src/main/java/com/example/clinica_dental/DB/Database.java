package com.example.clinica_dental.DB;

import android.graphics.Paint;

import androidx.room.RoomDatabase;

import com.example.clinica_dental.DAO.AdminDao;
import com.example.clinica_dental.DAO.ConsultaDao;
import com.example.clinica_dental.DAO.DoctorDao;
import com.example.clinica_dental.DAO.EspecialidadDao;
import com.example.clinica_dental.DAO.PacienteDao;
import com.example.clinica_dental.DAO.TieneEspecialidadDao;
import com.example.clinica_dental.TablasDB.Admin;
import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;
import com.example.clinica_dental.TablasDB.Paciente;

@androidx.room.Database(entities = {Admin.class, Paciente.class, Doctor.class, Especialidad.class,
        DoctorhasEspecialidad.class, Consultas.class}, version = 1)
public abstract class Database extends RoomDatabase {
    public abstract AdminDao adminDao();
    public abstract PacienteDao pacienteDao();
    public abstract DoctorDao doctorDao();
    public abstract EspecialidadDao especialidadDao();
    public abstract TieneEspecialidadDao tieneEspecialidadDao();
    public abstract ConsultaDao consultaDao();
}
