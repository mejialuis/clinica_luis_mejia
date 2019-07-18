package com.example.clinica_dental.Doctores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

public class Editar_Doctores_Activity extends AppCompatActivity {

    Doctor doctor;
    Database db;
    TextView nombre, apellido, telefono, usuario, contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__doctores_);

        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");
        CargarDatos();
    }

    public void CargarDatos(){
        nombre = findViewById(R.id.et_nombres);
        apellido = findViewById(R.id.et_apellidos);
        telefono = findViewById(R.id.et_telefono);
        usuario = findViewById(R.id.et_nombre_usuario);
        contraseña = findViewById(R.id.et_contraseña);
        nombre.setText(doctor.getNombres());
        apellido.setText(doctor.getApellidos());
        usuario.setText(doctor.getUser_name());
        telefono.setText(doctor.getTelfono());
        contraseña.setText(doctor.getPassword());
    }

    public void onClickEditarDoctor(View view){

        if(nombre.getText().length()==0)
            nombre.setError("Debe llenar este campo");
        else if(apellido.getText().length()==0)
            apellido.setError("Debe llenar este campo");
        else if(telefono.getText().length()==0)
            telefono.setError("Debe llenar este campo");
        else if(usuario.getText().length()==0)
            usuario.setError("Debe llenar este campo");
        else if(contraseña.getText().length()==0)
            contraseña.setError("Debe llenar este campo");
        else{
            doctor.setNombres(nombre.getText().toString());
            doctor.setApellidos(apellido.getText().toString());
            doctor.setTelfono(telefono.getText().toString());
            doctor.setUser_name(usuario.getText().toString());
            doctor.setPassword(contraseña.getText().toString());
            db.doctorDao().Update(doctor);
            Toast.makeText(this, "Doctor actualizado con éxito", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("doctor", doctor);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onClickCancelarDoctor(View view){
        finish();
    }
}
