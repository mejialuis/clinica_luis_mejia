package com.example.clinica_dental.Doctores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clinica_dental.Adaptador_Consultas_Paciente;
import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Doctor;

import java.util.List;

public class Consulta_po_Doctor_Activity extends AppCompatActivity {

    List<Consultas> consultasList;
    Doctor doctor;
    Adaptador_Consultas_Paciente adaptador;
    ListView lv;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_po__doctor_);

        db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");
        consultasList = db.consultaDao().Consultas_Por_Doctor(doctor.getId_doctor());
        Toast.makeText(this, ""+consultasList.size(), Toast.LENGTH_SHORT).show();
        adaptador = new Adaptador_Consultas_Paciente(consultasList, this);
        lv = findViewById(R.id.lv_consulta);
        lv.setAdapter(adaptador);
    }
}
