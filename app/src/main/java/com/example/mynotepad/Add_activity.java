package com.example.mynotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_activity extends AppCompatActivity {
    DbHelper helper;
    private EditText mEttitle,mEtcontent;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        String intentType=getIntent().getStringExtra("EXTRA_TYPE");

        btn=findViewById(R.id.btn_save);
        mEttitle=findViewById(R.id.et_title);
        mEtcontent=findViewById(R.id.et_content);

        if(intentType.equals("ADD")) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String title=mEttitle.getText().toString().trim();
                    String content=mEtcontent.getText().toString().trim();
                    saveToDb(title,content);
                }
            });
        }else if(intentType.equals("UPDATE")) {
            final Notes note=getIntent().getParcelableExtra("NOTES");
            mEttitle.setText(note.getTitle());
            mEtcontent.setText(note.getContent());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    helper=new DbHelper(Add_activity.this);
                    Notes note1=new Notes();
                    String title=mEttitle.getText().toString().trim();
                    String content=mEtcontent.getText().toString().trim();
                    note1.setTitle(title);
                    note1.setContent(content);
                    helper.updateNote(note1);
                    Intent intent=new Intent();
                    setResult(RESULT_OK,intent);
                    finish();
                }
            });
        }


    }
    private void saveToDb(String title,String content) {

        helper=new DbHelper(Add_activity.this);
        helper.insertNote(title,content);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
