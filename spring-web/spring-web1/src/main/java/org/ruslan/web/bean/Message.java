package org.ruslan.web.bean;

public class Message {

    private String text;
    private Boolean check;

    public Message() {
    }

    public Message(String text, Boolean check) {
        this.text = text;
        this.check = check;
    }

    @Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", check=" + check +
                '}';
    }
}
