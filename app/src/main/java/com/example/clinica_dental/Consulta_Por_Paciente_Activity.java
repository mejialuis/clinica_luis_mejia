package com.example.clinica_dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ListView;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.List;

public class Consulta_Por_Paciente_Activity extends AppCompatActivity {

    List<Consultas> consultasList;
    Adaptador_Consultas_Paciente adaptador;
    ListView lv;
    Paciente paciente;
    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta__por__paciente_);
        db = Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        paciente = (Paciente) bundle.getSerializable("paciente"); //Obtenemos los datos del activity
        consultasList = db.consultaDao().Consultas_Por_Paciente(paciente.getId_paciente());
        adaptador = new Adaptador_Consultas_Paciente(consultasList, this);
        lv = findViewById(R.id.lv_consulta);
        lv.setAdapter(adaptador);
    }
}
