package com.example.clinica_dental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.clinica_dental.TablasDB.Paciente;

public class Pacientes_Activity extends AppCompatActivity {

    Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pacientes_);
        Bundle bundle= getIntent().getExtras();
        paciente = (Paciente) bundle.getSerializable("paciente");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_paciente, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.mn_perfil){
            Intent intent = new Intent(Pacientes_Activity.this, Editar_Paciente_Activity.class);
            intent.putExtra("paciente", paciente);
            startActivityForResult(intent, 3333);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickInformacion(View v)
    {
        Toast.makeText(this, "App Clinica Dental", Toast.LENGTH_SHORT).show();
    }

    public void onClickUbicacion(View v){
        Intent intent = new Intent(Pacientes_Activity.this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickVerCitas(View view){
        Intent intent = new Intent(Pacientes_Activity.this, Consulta_Por_Paciente_Activity.class);
        intent.putExtra("paciente", paciente);
        startActivityForResult(intent, 5555);
    }

    public void onClickAgregarCita(View v){
        Intent intent = new Intent(Pacientes_Activity.this, Agendar_Cita_Activity.class);
        intent.putExtra("paciente", paciente);
        startActivityForResult(intent, 1234);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 3333 && resultCode != 0){
            paciente = (Paciente) data.getExtras().getSerializable("paciente");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
