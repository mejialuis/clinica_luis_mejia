package com.example.clinica_dental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Agendar_Cita_Activity extends AppCompatActivity {

    Paciente paciente;
    TextView nombre;
    Spinner spinner;
    EditText fecha, hora, tratamiento;
    List<String> stringList = new ArrayList<>();
    ArrayAdapter<String> adapter; //Adaptador para el spinner
    Database db;
    List<Doctor> doctorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar__cita_);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        doctorList = db.doctorDao().ObtenerTodo();
        paciente = (Paciente) bundle.getSerializable("paciente"); //Obtenemos los datos del activity anterior
        spinner = findViewById(R.id.sp_doctor);
        nombre = findViewById(R.id.tc_paciente);
        nombre.setText(paciente.getNombres() + " " + paciente.getApellidos());
        cargarDoctores(); //Método que agregara los doctores a una lista para ser mostrada en el spinner
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(adapter); //Asignamos el adaptador al spinner
        CargarEditText();

    }

    public void cargarDoctores(){
        for(int i = 0; i<doctorList.size(); i++){ //Recorremos la lista de todos los doctores que estan en la BD
            stringList.add(doctorList.get(i).getNombres() + " " + doctorList.get(i).getApellidos());
        }
    }

    public void CargarEditText(){
        fecha = findViewById(R.id.et_fecha);
        hora = findViewById(R.id.et_hora);
        tratamiento = findViewById(R.id.et_motivo_consulta);
    }

    public void onClickAnular(View view){
        finish();
    }

    public void onClickAgregarCita(View view){
        if(fecha.length() == 0)
            fecha.setError("Debe llenar este campo");
        else if(hora.length() == 0)
            hora.setError("Debe llenear este campo");

        else if(tratamiento.length() == 0)
            tratamiento.setError("Debe llenar este campo");
        else{
            Consultas consultas = new Consultas();
            consultas.setFecha_consulta(fecha.getText().toString());
            consultas.setHora_consulta(hora.getText().toString());
            consultas.setTratamiento(tratamiento.getText().toString());
            consultas.setId_Paciente(paciente.getId_paciente());
            String aux = spinner.getSelectedItem().toString();
            String[] aux2 = aux.split(" ");
            Doctor doctor = db.doctorDao().ObtenerPorNombre(aux2[0]);
            consultas.setId_Doctor(doctor.getId_doctor());
            long id = db.consultaDao().Insertar(consultas);
            consultas.setId_consulta((int)id);
            Toast.makeText(this, "Consulta agregada correctamente", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
