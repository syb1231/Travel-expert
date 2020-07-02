package com.example.login;

public class BoardItem {

    String id;
    String title;
    int imageSrc;
    String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title=title;
    }

    public int getImageSrc() { return imageSrc; }

    public void setImageSrc(int imageSrc) {
        this.imageSrc=imageSrc;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body=body;
    }
}
