package com.example.mynotepad;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import androidx.cardview.widget.CardView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Context context;
    private List<Notes> list;

    public Adapter(Context context, List<Notes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Notes getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CardView view1=new CardView(context);
        view1.setLayoutParams(new GridView.LayoutParams(70, 70));
        return view1;    }
}
