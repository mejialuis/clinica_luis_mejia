package com.example.clinica_dental.Doctores;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clinica_dental.Adaptador_Especialidad_por_Doctor;
import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.Editar_Paciente_Activity;
import com.example.clinica_dental.POJOS.DoctorTieneEspecialidad;
import com.example.clinica_dental.Pacientes_Activity;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Informacion_Doctores_Activity extends AppCompatActivity {
    Database db;
    ListView lv;
    Doctor doctor;
    TextView nombre, apellido, telefono, usuario;
    Adaptador_Especialidad_por_Doctor adapter;
    List<DoctorTieneEspecialidad> doctorTieneEspecialidadList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion__doctores_);
        Bundle bundle = getIntent().getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        CargarDatos(); //Cargamos los datos del doctor
        doctorTieneEspecialidadList = db.tieneEspecialidadDao().ESPECIALIDAD_LIST(doctor.getId_doctor());

        lv = findViewById(R.id.lv_especialidades); //Obtenemos el ListView
        adapter = new Adaptador_Especialidad_por_Doctor(doctorTieneEspecialidadList, this);
        lv.setAdapter(adapter); //Cargamos el listView con el adapter

    }

    public void CargarDatos(){
        nombre = findViewById(R.id.tv_nombre_doctor);
        apellido = findViewById(R.id.tv_apellido_doctor);
        telefono = findViewById(R.id.tv_telefono);
        usuario = findViewById(R.id.tv_usuario);
        nombre.setText(doctor.getNombres());
        apellido.setText(doctor.getApellidos());
        telefono.setText(doctor.getTelfono());
        usuario.setText(doctor.getUser_name());
    }

    public void onClickEditar(View view){
        Intent intent = new Intent(Informacion_Doctores_Activity.this, Editar_Doctores_Activity.class);
        intent.putExtra("doctor", doctor);
        startActivityForResult(intent, 3333);
    }

    public void onClickCerrar(View view){
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 3333 && resultCode != 0){
            doctor = (Doctor) data.getExtras().getSerializable("doctor");
            CargarDatos();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
