package com.example.mynotepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotepadAdapter extends RecyclerView.Adapter<NotepadAdapter.VH> {

    private Context context;
    List<Notes> list;

    public NotepadAdapter(MainActivity context, List<Notes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.cell_layout,parent,false);
        return (new VH(view));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        final Notes note=list.get(position);
        holder.mTvtitle.setText(note.getTitle().toUpperCase());
        holder.mTvcontent.setText(note.getContent());
        holder.mTvdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onDelete(note);
            }
        });

        holder.mBtnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUpdate(note);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class VH extends RecyclerView.ViewHolder {
        private TextView mTvtitle,mTvcontent,mTvdelete;
        Button mBtnupdate;
        public VH(@NonNull View itemView) {
            super(itemView);
            mTvtitle=itemView.findViewById(R.id.tv_title);
            mTvcontent=itemView.findViewById(R.id.tv_content);
            mTvdelete=itemView.findViewById(R.id.tv_delete);
            mBtnupdate=itemView.findViewById(R.id.btn_update);
        }
    }

    OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onUpdate(Notes note);
        void onDelete(Notes note);
    }

}
