package org.ruslan.springbootbasic.model;


public class Message {

    private final String text;
    private final Boolean check;

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
