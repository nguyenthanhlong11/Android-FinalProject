package com.lmhvteam.lmhv_foodordering;

public class Notify {
    private String documentId, content, time;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Notify(String documentId, String content, String time) {
        this.documentId = documentId;
        this.content = content;
        this.time = time;
    }
}
