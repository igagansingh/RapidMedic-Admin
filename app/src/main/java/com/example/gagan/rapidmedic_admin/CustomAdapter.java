package com.example.gagan.rapidmedic_admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 02-Apr-17.
 */

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList dataCred;
    ArrayList age;
    ArrayList gender;
    ArrayList temp;
    ArrayList heart;

    public CustomAdapter(Context context, ArrayList<String> dataCred,ArrayList<String> age,ArrayList<String> gender,ArrayList<String> temp,ArrayList<String> heart){
        this.context=context;
        this.dataCred=dataCred;
        this.age=age;
        this.gender=gender;
        this.temp=temp;
        this.heart=heart;
    }
    @Override
    public int getCount() {
        return dataCred.size();
    }

    @Override
    public Object getItem(int i) {
        return dataCred.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.list_row,null);
        TextView name= (TextView) view.findViewById(R.id.name);
        TextView ageview= (TextView) view.findViewById(R.id.age);
        TextView genderview= (TextView) view.findViewById(R.id.gender);
        TextView tempview= (TextView) view.findViewById(R.id.temp);
        TextView heartview= (TextView) view.findViewById(R.id.heartRate);
        name.setText("Name: "+dataCred.get(0)+"");
        ageview.setText("Age: "+age.get(0)+"");
        genderview.setText("Gender: "+gender.get(0));
        tempview.setText("Body Temperature: "+temp.get(0));
        heartview.setText("Heart Rate: "+heart.get(0));
        return view;
    }
}
