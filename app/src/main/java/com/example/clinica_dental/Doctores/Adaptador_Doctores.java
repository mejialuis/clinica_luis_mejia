package com.example.clinica_dental.Doctores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clinica_dental.R;
import com.example.clinica_dental.TablasDB.Doctor;

import java.util.ArrayList;
import java.util.List;

public class Adaptador_Doctores extends BaseAdapter {

    private List<Doctor> doctorList;
    private Context context;

    public Adaptador_Doctores(List<Doctor> doctorList, Context context){
        this.doctorList = doctorList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return doctorList.size();
    }

    @Override
    public Object getItem(int i) {
        return doctorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Doctor doctor = (Doctor) getItem(i);
        view = (View) LayoutInflater.from(context).inflate(R.layout.item_doctores, null);
        TextView nombre = view.findViewById(R.id.tv_nombre_doctor);
        TextView apellido = view.findViewById(R.id.tv_apellido_doctor);
        nombre.setText(doctor.getNombres());
        apellido.setText(doctor.getApellidos());
        return view;
    }
}
