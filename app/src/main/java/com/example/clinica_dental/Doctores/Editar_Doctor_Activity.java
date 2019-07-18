package com.example.clinica_dental.Doctores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

public class Editar_Doctor_Activity extends AppCompatActivity {

    Database db;
    TextView nombre, apellido, telefono, usuario, contraseña;
    Doctor doctor;
    Button editar, cerrar;
    int auxiliar = 0; //Variable que indica si se va a visualizar o a editar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__doctor_);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");
        editar = findViewById(R.id.btn_actualizar);
        cerrar = findViewById(R.id.btn_cancelar);
        auxiliar = bundle.getInt("valor");
        if(auxiliar==1) {
            SoloVer();
        }
        CargarDatos();
    }

    public void onClickCancelar(View view){
        finish();
    }

    public void onClickEditar(View view){
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
            Intent intent = new Intent();
            intent.putExtra("doctor", doctor);
            Toast.makeText(this, "Doctor actualizado correctamente", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, intent);
            finish();
        }
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

    public void SoloVer(){
        editar.setVisibility(View.INVISIBLE);
        cerrar.setVisibility(View.INVISIBLE);
    }
}
