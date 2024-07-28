package com.example.qldv.model;

public class Note {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String content;

    public Note() {
    }

    public Note(String content, String id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
