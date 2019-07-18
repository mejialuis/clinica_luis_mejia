package com.example.clinica_dental;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.Doctores.Agregar_Doctor_Activity;
import com.example.clinica_dental.Doctores.Doctores_Ver;
import com.example.clinica_dental.TablasDB.Especialidad;

public class Administrador_Activity extends AppCompatActivity {
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_);
        db= Room.databaseBuilder(getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_especialidades, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.mn_especialidades){ //Si da clic en añadir especialidades
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Administrador_Activity.this); //Creamos un alertDialog donde va a introducir la especialidad
            alertDialog.setTitle("Especialidades");
            final EditText especialiad = new EditText(Administrador_Activity.this);
            alertDialog.setView(especialiad); //Le agregamos al alertDialos un editText
            alertDialog.setNegativeButton("Cancelar", null); //Le añadimos el boton de cancelar
            alertDialog.setPositiveButton("Añadir", new DialogInterface.OnClickListener() { //Le añadimos el boton de agregar y le asociamos un evento onClick
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if(especialiad.length() == 0)
                        especialiad.setError("Debe llenar este campo");
                    else{
                        Especialidad esp = new Especialidad();
                        esp.setNombre_especialidad(especialiad.getText().toString());
                        long id = db.especialidadDao().Insertar(esp);
                        esp.setId_especialidad((int)id);
                        Toast.makeText(Administrador_Activity.this, "Especialidad Añadida correctamente", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            AlertDialog dialog = alertDialog.create();
            dialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickVerDoctores(View v) {
        Intent intent = new Intent(Administrador_Activity.this, Doctores_Ver.class);
        startActivity(intent);
    }

    public void onClickAgregarDoctor(View v){
        Intent intent = new Intent(Administrador_Activity.this, Agregar_Doctor_Activity.class);
        startActivityForResult(intent, 1234);//RequestCode para agregar doctor
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onClickInformacion(View v){

    }

    public void onClickVerCitas(View v){

    }
}
