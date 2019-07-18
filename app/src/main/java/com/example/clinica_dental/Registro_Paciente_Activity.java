package com.example.clinica_dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.ArrayList;
import java.util.List;

public class Registro_Paciente_Activity extends AppCompatActivity {

    Database db;
    EditText nombre, apellido, telefono, usuario, pwd, pwd2;
    List<Paciente> pacienteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__paciente_);

        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        pacienteList.addAll(db.pacienteDao().ObtenerTodo());
        CargarEditText();
    }

    public void onClickRegistrarse(View v){
        String contra1 = pwd.getText().toString();
        String contra2 = pwd2.getText().toString();
        if(nombre.getText().length()==0)
            nombre.setError("Debe llenar este campo");
        else if(apellido.getText().length()==0)
            apellido.setError("Debe llenar este campo");
        else if(telefono.getText().length()==0)
            telefono.setError("Debe llenar este campo");
        else if(usuario.getText().length()==0)
            usuario.setError("Debe llenar este campo");
        else if(pwd.getText().length()==0)
            pwd.setError("Debe llenar este campo");
        else if(pwd2.getText().length()==0)
            pwd2.setError("Debe llenar este campo");
        else if(!contra1.equals(contra2)){
            Toast.makeText(this, "Las contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
        }
        else{
            Paciente paciente = new Paciente();
            paciente.setNombres(nombre.getText().toString());
            paciente.setApellidos(apellido.getText().toString());
            paciente.setUser_name(usuario.getText().toString());
            paciente.setTelfono(telefono.getText().toString());
            paciente.setPassword(pwd.getText().toString());
           if(pacienteList.size()!=0){
                int verificacion = Verificar_Nombre_Usuario();
                if(verificacion==0){
                    usuario.setError("Nombre de usuario ya existe");
                }
                else{
                    AgregarPaciente(paciente);
                }
           }
           else
                AgregarPaciente(paciente);
        }
    }

    public void CargarEditText(){
        nombre = findViewById(R.id.et_nombres);
        apellido = findViewById(R.id.et_apellidos);
        telefono = findViewById(R.id.et_telefono);
        usuario = findViewById(R.id.et_nombre_usuario);
        pwd = findViewById(R.id.et_contraseña);
        pwd2 = findViewById(R.id.et_repita_contraseña);
    }

    public void AgregarPaciente(Paciente paciente){
        Intent intent = new Intent();
        intent.putExtra("paciente", paciente);
        Toast.makeText(this, "Paciente registrado correctamente", Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK, intent);
        finish();
    }

    public int Verificar_Nombre_Usuario(){ //Verifica que un paciente no se haya registrado con ese nombre de usuario anteriormente
        int variable = 1;
        Paciente paciente = db.pacienteDao().ObtenerPorUser_Name(usuario.getText().toString());
        if(paciente != null){
            variable = 0;
        }
        return variable;
    }

}
