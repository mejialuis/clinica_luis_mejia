package com.example.clinica_dental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.room.Room;

import com.example.clinica_dental.DB.Database;
import com.example.clinica_dental.TablasDB.Consultas;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.Paciente;

import java.util.List;

public class Adaptador_Consultas_Paciente extends BaseAdapter {

    private List<Consultas> consultasList;
    private Context context;

    public Adaptador_Consultas_Paciente(List<Consultas> consultasList, Context context){
        this.consultasList = consultasList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return consultasList.size();
    }

    @Override
    public Object getItem(int i) {
        return consultasList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Database db = Room.databaseBuilder(context.getApplicationContext(),
                Database.class, "Prestamo").allowMainThreadQueries().build();
        Consultas consultas = (Consultas) getItem(i);
        view = (View) LayoutInflater.from(context).inflate(R.layout.item_consulta, null);
        TextView doctor = view.findViewById(R.id.tv_doctor_consulta);
        TextView motivo = view.findViewById(R.id.tv_motivo);
        TextView fecha = view.findViewById(R.id.tv_fecha_consulta);
        TextView hora = view.findViewById(R.id.tv_hora_consulta);
        TextView paci = view.findViewById(R.id.tv_paciente_consulta);
        Doctor doctorado = db.doctorDao().ObtenerPorID(consultas.getId_Doctor());
        Paciente paciente = db.pacienteDao().ObtenerPorID(consultas.getId_Paciente());
        paci.setText(paciente.getNombres() + " " + paciente.getApellidos());
        doctor.setText(doctorado.getNombres() + " " + doctorado.getApellidos());
        motivo.setText(consultas.getTratamiento());
        fecha.setText(consultas.getFecha_consulta());
        hora.setText(consultas.getHora_consulta());
        return view;
    }
}
