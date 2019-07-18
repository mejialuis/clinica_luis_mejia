package com.example.clinica_dental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.Doctores.Principal_Doctor_Activity;
import com.example.clinica_dental.TablasDB.Admin;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    Database db;
    Admin admin;
    String pwd, name;
    EditText user_name, password;
    Button btn_registrarse;
    int tipo_usuario = 0;
    List<Admin> adminList = new ArrayList<>();
    List<Paciente> pacienteList = new ArrayList<>();
    List<Doctor> doctorList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Bundle bundle = getIntent().getExtras();
        tipo_usuario = bundle.getInt("Usuario");
        user_name = findViewById(R.id.et_telefono);
        btn_registrarse = findViewById(R.id.btn_registro);
        password = findViewById(R.id.et_password);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        pacienteList.addAll(db.pacienteDao().ObtenerTodo());
        doctorList.addAll(db.doctorDao().ObtenerTodo());
        adminList.addAll(db.adminDao().ObtenerTodo());
        OcultarBoton();
    }

    public void onClickIngresar(View v){
        ValidarUsuario();
    }

    public void onClickNuevoPaciente(View v){
        user_name.setText("");
        password.setText("");
        Intent intent = new Intent(this, Registro_Paciente_Activity.class);
        startActivityForResult(intent, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==2000 && resultCode!=0){
            Paciente paciente = (Paciente) data.getExtras().getSerializable("paciente");
            try{
                long id = db.pacienteDao().Insertar(paciente);
                paciente.setId_paciente((int)id);
                pacienteList.add(paciente);
                Toast.makeText(this, "100%", Toast.LENGTH_SHORT).show();
            }
            catch (SQLiteConstraintException e){
                Toast.makeText(this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void ValidarUsuario(){
        if(user_name.getText().length() == 0){
           user_name.setError("Debe llenar este campo");
        }
        else if(password.getText().length()==0)
            password.setError("Debe llenar este campo");
        else{
            pwd = password.getText().toString();
            name = user_name.getText().toString();
            if(tipo_usuario == 1){
                EsUnAdmin();
            }
            else if(tipo_usuario == 2){
                EsUnDoctor();
            }
            else if(tipo_usuario == 3){
                EsUnPaciente();
            }
        }
    }

    public void OcultarBoton(){
        btn_registrarse.setVisibility(View.INVISIBLE);
        if(tipo_usuario == 3){
            btn_registrarse.setVisibility(View.VISIBLE);
        }
    }

    public void EsUnAdmin(){
       for(int i = 0; i<adminList.size(); i++){
            if(name.equalsIgnoreCase(adminList.get(i).getUser_name())){
                if(pwd.equalsIgnoreCase(adminList.get(i).getPassword())){
                    Intent intent = new Intent(this, Administrador_Activity.class);
                    startActivity(intent);
                    break;
                }
                else
                    Toast.makeText(this, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Nombre de usuario incorrecto", Toast.LENGTH_SHORT).show();
        }
        user_name.setText("");
        password.setText("");

    }

    public void EsUnDoctor(){
        Doctor doctor = db.doctorDao().ObtenerPorUser_Name(user_name.getText().toString());
        if(doctor == null){
            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
        else{
            pwd = password.getText().toString();
            if(pwd.equalsIgnoreCase(doctor.getPassword())){
                Intent intent = new Intent(this, Principal_Doctor_Activity.class);
                intent.putExtra("doctor", doctor);
                startActivityForResult(intent, 4545);
                user_name.setText("");
                password.setText("");
            }
            else{
                Toast.makeText(this, "Contraseña Incorrecta ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void EsUnPaciente(){
        Paciente paciente = db.pacienteDao().ObtenerPorUser_Name(user_name.getText().toString());
        if(paciente == null){
            Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT).show();
        }
        else{
            pwd = password.getText().toString();
            if(pwd.equalsIgnoreCase(paciente.getPassword())){
                Intent intent = new Intent(this, Pacientes_Activity.class);
                intent.putExtra("paciente", paciente);
                startActivityForResult(intent, 1234);
                user_name.setText("");
                password.setText("");
            }
            else{
                Toast.makeText(this, "Contraseña Incorrecta ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
