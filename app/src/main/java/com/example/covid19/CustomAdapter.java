package com.example.covid19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Listview_covid> listviews;

    public CustomAdapter(Context context, ArrayList<Listview_covid> listviews) {
        this.context = context;
        this.listviews = listviews;
    }
    @Override
    public int getCount() {
        return listviews.size();
    }

    @Override
    public Object getItem(int i) {
        return listviews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Listview_covid listview_value = listviews.get(i);

        view= layoutInflater.inflate(R.layout.activity_listview,null);
        TextView txt_thanhpho = view.findViewById(R.id.thanhpho);
        TextView txt_tong_lv=view.findViewById(R.id.case_lv);
        TextView txt_24g_lv=view.findViewById(R.id.recovered_lv);
        TextView txt_tuvong_lv=view.findViewById(R.id.Deaths_lv);

        txt_thanhpho.setText(listview_value.Tinh);
        txt_tong_lv.setText(listview_value.Tongsoca);
        txt_24g_lv.setText(listview_value.sau24gio);
        txt_tuvong_lv.setText(listview_value.tuvong);

        return view;
    }
}
