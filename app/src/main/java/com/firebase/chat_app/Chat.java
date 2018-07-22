package com.firebase.chat_app;

public class Chat {

    public String message;
    public String id;
    public String keyStr;
    public Integer key;

    public Chat() {
    }

    public Chat(String message, String id, int key) {
        this.message = message;
        this.id = id;
        this.key = key;
    }


    public Chat(String message, String id, String keyStr) {
        this.message = message;
        this.id = id;
        this.keyStr = keyStr;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKeyStr() {
        return keyStr;
    }

    public void setKeyStr(String keyStr) {
        this.keyStr = keyStr;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }
}
