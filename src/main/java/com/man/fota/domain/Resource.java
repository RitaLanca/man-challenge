package com.man.fota.domain;

public class Resource<T> {

    private String description;

    private T body;

    public Resource(String description, T body) {
        this.description = description;
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }


}
