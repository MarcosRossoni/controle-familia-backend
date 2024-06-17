package com.controller.session;

public class SessionHolder {

    private static final InheritableThreadLocal<String> USER_TOKEN = new InheritableThreadLocal<>();
}
