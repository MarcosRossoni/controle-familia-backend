package com.controller;

public class SessionHolder {

    private static final InheritableThreadLocal<String> USER_TOKEN = new InheritableThreadLocal<>();

    public static String getUserToken() {
        return USER_TOKEN.get();
    }

    public static void setUserToken(String userToken) {
        USER_TOKEN.set(userToken);
    }
}
