package com.example.clinica_dental.Doctores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;

import java.util.ArrayList;
import java.util.List;

public class Doctores_Ver extends AppCompatActivity {
    Database db;
    List<Doctor> doctorList = new ArrayList<>();
    ListView lv;
    Adaptador_Doctores arrayDoctor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctores);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        doctorList.addAll(db.doctorDao().ObtenerTodo());
        lv = findViewById(R.id.listView); //Obtenemos el ListView
        arrayDoctor = new Adaptador_Doctores(doctorList, this); //Lamamoos al constructor del adaptador
        lv.setAdapter(arrayDoctor); //Cargamos el listView con el adapter
        //Agregamos el evento onClick al ListView
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Doctor doctor = doctorList.get(i); //Obtenemos el doctor que ha sido cliqueado
                Intent intent = new Intent(Doctores_Ver.this, Informacion_Doctores_Activity.class);
                intent.putExtra("doctor", doctor);
                startActivityForResult(intent, 1234);
            }
        });
    }
}
