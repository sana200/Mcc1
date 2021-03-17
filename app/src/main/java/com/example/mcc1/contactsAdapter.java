package com.example.mcc1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class contactsAdapter extends BaseAdapter {
    ArrayList<contacts> contacts =new ArrayList<>();
    Activity activity;
    private TextView TV1;
    private TextView TV2;
    private TextView TV3;

    public contactsAdapter(ArrayList<contacts> contacts, Activity activity) {
        this.contacts = contacts;
        this.activity = activity;


    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(activity).inflate(R.layout.activity_main, null);
        TV1 = v.findViewById(R.id.TV1);
        TV2 = v.findViewById(R.id.TV2);
        TV3 = v.findViewById(R.id.TV3);

        TV1.setText(contacts.get(i).getName());
        TV2.setText(contacts.get(i).getNumber());
        TV3.setText(contacts.get(i).getAddress());
        return v;

    }
}
