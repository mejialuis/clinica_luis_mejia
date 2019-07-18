package com.example.clinica_dental.Doctores;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clinica_dental.MapsActivity;
import com.example.clinica_dental.Pacientes_Activity;
import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

public class Principal_Doctor_Activity extends AppCompatActivity {

    Doctor doctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal__doctor_);
        Bundle bundle = getIntent().getExtras();
        doctor = (Doctor) bundle.getSerializable("doctor");
    }

    public void onClickVerCitas(View view){
        Intent intent = new Intent(Principal_Doctor_Activity.this, Consulta_po_Doctor_Activity.class);
        intent.putExtra("doctor", doctor);
        startActivityForResult(intent, 3333);
    }

    public void onClickEditarDatos(View view){
        Intent intent = new Intent(Principal_Doctor_Activity.this, Editar_Doctor_Activity.class);
        intent.putExtra("doctor", doctor);
        startActivityForResult(intent, 1234);
    }

    public void onClickVerDatos(View view){
        Intent intent = new Intent(Principal_Doctor_Activity.this, Editar_Doctor_Activity.class);
        intent.putExtra("doctor", doctor);
        intent.putExtra("valor", 1);
        startActivityForResult(intent, 1234);
    }

    public void onClickUbicacion(View view){
        Intent intent = new Intent(Principal_Doctor_Activity.this, MapsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1234 && resultCode!=0){
            doctor = (Doctor) data.getExtras().getSerializable("doctor");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
