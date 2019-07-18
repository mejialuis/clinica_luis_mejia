package com.example.clinica_dental.Doctores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.clinica_dental.Adaptador_Especialidades;
import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;

import java.util.ArrayList;
import java.util.List;

public class Agregar_Doctor_Activity extends AppCompatActivity {

    Database db;
    Spinner spinner;
    ListView lv;
    ArrayAdapter<String> adapter;
    Adaptador_Especialidades adaptador_especialidades;
    List<String> stringList = new ArrayList<>();
    List<Especialidad> especialidadList = new ArrayList<>();
    List<String> especialidadAdapter = new ArrayList<>();
    EditText nombre, apellido, usuario, telefono, contraseña;
    List<Doctor> doctorList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar__doctor_);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        especialidadList = db.especialidadDao().ObtenerTodo();
        doctorList = db.doctorDao().ObtenerTodo();
        cargarString();
        spinner = findViewById(R.id.sp_especialiades);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(adapter);
        lv = findViewById(R.id.lv_especialidades);
        cargarEditText();
    }

    public void cargarString(){
        for (int i = 0; i < especialidadList.size(); i++){
            stringList.add(especialidadList.get(i).getNombre_especialidad());
        }
    }

    //Método que se invoca cuando se da clic en agregar
    public void onClickAgregar(View view){
        if(nombre.getText().length() == 0)
            nombre.setError("Debe llenar este campo");
        else if(apellido.getText().length()==0)
            apellido.setError("Debe llenar este campo");
        else if(telefono.getText().length() == 0)
            telefono.setError("Debe llenar este campo");
        else if(usuario.getText().length()==0)
            usuario.setError("Debe llenar este campo");
        else if(contraseña.getText().length()==0)
            contraseña.setError("Debe llenar este campo");
        else{
            Doctor doctor = new Doctor();
            doctor.setNombres(nombre.getText().toString());
            doctor.setApellidos(apellido.getText().toString());
            doctor.setUser_name(usuario.getText().toString());
            doctor.setTelfono(telefono.getText().toString());
            doctor.setPassword(contraseña.getText().toString());
            if(doctorList.size()!=0){
                int verificacion = Verificar_Nombre_Usuario();
                if(verificacion==0){
                    usuario.setError("Nombre de usuario ya existe");
                }
                else{
                    AgregarDoctor(doctor);
                }
            }
            else
                AgregarDoctor(doctor);
        }
    }

    //Método que se invoca cuando se da clic en cancelar
    public void onClickCancelar(View view){
        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show();
    }

    //Método que se invoca cuando se da clic en añadir especialidad
    public void onClickAñadir(View view){
        especialidadAdapter.add(spinner.getSelectedItem().toString());
        adaptador_especialidades = new Adaptador_Especialidades(especialidadAdapter, this);
        lv.setAdapter(adaptador_especialidades);
    }

    public void cargarEditText(){
        nombre = findViewById(R.id.et_nombre);
        apellido = findViewById(R.id.et_apellido2);
        telefono = findViewById(R.id.et_telefono);
        usuario = findViewById(R.id.et_nombre_usuario);
        contraseña = findViewById(R.id.et_password);
    }

    public void AgregarDoctor(Doctor doctor){
        long id = db.doctorDao().Insertar(doctor);
        Especialidad esp = new Especialidad();
        DoctorhasEspecialidad doctorhasEspecialidad = new DoctorhasEspecialidad();
        for(int i = 0; i<especialidadAdapter.size(); i++){
            esp = db.especialidadDao().ObtenerporNombre(especialidadAdapter.get(i));
            doctorhasEspecialidad.setId_Doctor((int) id);
            doctorhasEspecialidad.setId_Especialidad(esp.getId_especialidad());
            db.tieneEspecialidadDao().Insertar(doctorhasEspecialidad);
        }
        Toast.makeText(this, "Doctor agregado correctamente", Toast.LENGTH_SHORT).show();
        finish();
    }

    public int Verificar_Nombre_Usuario(){ //Verifica que un paciente no se haya registrado con ese nombre de usuario anteriormente
        int variable = 1;
        Doctor doctor = db.doctorDao().ObtenerPorUser_Name(usuario.getText().toString());
        if(doctor != null){
            variable = 0;
        }
        return variable;
    }

}
