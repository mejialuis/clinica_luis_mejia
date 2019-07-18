package com.example.clinica_dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.TablasDB.Paciente;

public class Editar_Paciente_Activity extends AppCompatActivity {

    Paciente paciente;
    Database db;
    TextView nombre, apellido, telefono, usuario, contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar__paciente_);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        paciente = (Paciente) bundle.getSerializable("paciente");
        CargarDatos();
    }

    public void CargarDatos(){
        nombre = findViewById(R.id.et_nombres);
        apellido = findViewById(R.id.et_apellidos);
        telefono = findViewById(R.id.et_telefono);
        usuario = findViewById(R.id.et_nombre_usuario);
        contraseña = findViewById(R.id.et_contraseña);
        nombre.setText(paciente.getNombres());
        apellido.setText(paciente.getApellidos());
        usuario.setText(paciente.getUser_name());
        telefono.setText(paciente.getTelfono());
        contraseña.setText(paciente.getPassword());
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
            paciente.setNombres(nombre.getText().toString());
            paciente.setApellidos(apellido.getText().toString());
            paciente.setTelfono(telefono.getText().toString());
            paciente.setUser_name(usuario.getText().toString());
            paciente.setPassword(contraseña.getText().toString());
            db.pacienteDao().Update(paciente);
            Intent intent = new Intent();
            intent.putExtra("paciente", paciente);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onClickCancelar(View view){
        finish();
    }
}
