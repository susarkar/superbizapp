package com.ss.app.superbiz.model;

/**
 * Created by Susanta on 29-04-2017.
 */

public class ResponseData {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
