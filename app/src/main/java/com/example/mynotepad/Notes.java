package com.example.mynotepad;

public class Notes {
    public static final String TABLE_NAME="NOTES";
    public static final String COLUMN_ID="id";
    public static final String TITLE="title";
    public static final String CONTENT="content";
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+
            COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            TITLE+" TEXT,"+CONTENT+" TEXT);";

    private int id;
    private  String title,content;

    public Notes() {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
