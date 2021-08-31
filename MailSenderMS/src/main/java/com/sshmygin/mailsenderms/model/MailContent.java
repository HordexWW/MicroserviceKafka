package com.sshmygin.mailsenderms.model;

public enum MailContent {
    CONFIRMATION_EMAIL("Hi! Please, confirm your email by following the link < Fake link :) > \n" +
            "Activation code:");

    public final String content;

    MailContent(String content) {
        this.content = content;
    }

}
