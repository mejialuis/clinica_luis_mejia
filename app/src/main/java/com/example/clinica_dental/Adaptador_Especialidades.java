package com.example.clinica_dental;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.clinica_dental.TablasDB.Especialidad;

import java.util.ArrayList;
import java.util.List;

public class Adaptador_Especialidades extends BaseAdapter {

    private List<String> especialidadList = new ArrayList<>();
    private Context context;

    public Adaptador_Especialidades(List<String> especialidadList, Context context){
        this.especialidadList = especialidadList;
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
        String st = (String) getItem(i);
        view = (View) LayoutInflater.from(context).inflate(R.layout.item_especialidades, null);
        TextView esp = view.findViewById(R.id.tv_especialidad);
        ImageView borrar = view.findViewById(R.id.img_borrar);
        esp.setText(especialidadList.get(i));
        return view;
    }
}
