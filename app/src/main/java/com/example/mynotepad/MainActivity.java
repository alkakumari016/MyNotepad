package com.example.mynotepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.animation.GridLayoutAnimationController;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NotepadAdapter.OnItemClickListener {
    private DbHelper helper;
    private RecyclerView mRv;
    private EditText mEtnew;
    private TextView mTvnoItem;
    private GridView mGrv;
    private FloatingActionButton mFab;
    private static int REQUEST_ADD=100,REQUEST_UPDATE=200;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_ADD && resultCode ==RESULT_OK) {
            assert data != null;
            setupAdapter();

        }
        if(requestCode==REQUEST_UPDATE && resultCode==RESULT_OK) {
            assert data!=null;
            setupAdapter();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvnoItem=findViewById(R.id.tv_noItem);

        mFab=findViewById(R.id.fab);

//        mEtnew=findViewById(R.id.et_enternew);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Add_activity.class);
                intent.putExtra("EXTRA_TYPE","ADD");
                startActivityForResult(intent,REQUEST_ADD);
            }
        });
        mRv=findViewById(R.id.rv);
        mRv.setLayoutManager(new GridLayoutManager(this,2));


        setupAdapter();


    }

    private void setupAdapter() {
        List<Notes> list;
        helper = new DbHelper(this);
        list = helper.getNotes();

        if (list.size() > 0){
            mTvnoItem.setVisibility(View.GONE);
            mRv.setVisibility(View.VISIBLE);
            NotepadAdapter adapter = new NotepadAdapter(MainActivity.this, list);
            adapter.setListener(this);
            mRv.setAdapter(adapter);

        }else {
            mTvnoItem.setVisibility(View.VISIBLE);
            mRv.setVisibility(View.GONE);
        }


    }

//    private void setupAdapter() {
//        List<Notes> list;
//        helper = new DbHelper(this);
//        list = helper.getNotes();
//
//        if (list.size() > 0){
//            mTvnoItem.setVisibility(View.GONE);
//            mGrv.setVisibility(View.VISIBLE);
//            NotepadAdapter adapter = new NotepadAdapter(MainActivity.this, list);
//            adapter.setListener(this);
//            mGrv.setAdapter((ListAdapter)adapter);
//
//        }else {
//            mTvnoItem.setVisibility(View.VISIBLE);
//            mGrv.setVisibility(View.GONE);
//        }
//
//
//    }


    @Override
    public void onUpdate(Notes note) {
        Intent updateIntent=new Intent(MainActivity.this,Add_activity.class);
        updateIntent.putExtra("EXTRA_TYPE","UPDATE");
        updateIntent.putExtra("NOTES", (Parcelable) note);
        startActivityForResult(updateIntent,REQUEST_UPDATE);
    }

    public void onDelete(Notes note) {
        helper = new DbHelper(this);
        helper.deleteNote(note);
        setupAdapter();
    }


}
