package com.example.clinica_dental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.clinica_dental.POJOS.DoctorTieneEspecialidad;
import com.example.clinica_dental.TablasDB.Doctor;
import com.example.clinica_dental.TablasDB.DoctorhasEspecialidad;
import com.example.clinica_dental.TablasDB.Especialidad;

import java.util.List;

public class Adaptador_Especialidad_por_Doctor extends BaseAdapter {

    private List<DoctorTieneEspecialidad> especialidadList;
    private Context context;

    public Adaptador_Especialidad_por_Doctor(List<DoctorTieneEspecialidad> especialidadList, Context context){
        this.especialidadList= especialidadList;
        this.context = context;
    }
    @Override
    public int getCount() {
        return especialidadList.size();
    }

    @Override
    public Object getItem(int i) {
        return especialidadList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        DoctorTieneEspecialidad doctorTieneEspecialidad = (DoctorTieneEspecialidad) getItem(i);
        view = (View) LayoutInflater.from(context).inflate(R.layout.item_especialida_de_un_doctor, null);
        TextView especialidad = view.findViewById(R.id.tv_especialidad_doctor);
        especialidad.setText(doctorTieneEspecialidad.getEspecialidadList().get(i).getNombre_especialidad());
        return view;
    }
}
