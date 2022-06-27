package com.example.habittracker;

public class MyApplication {
    private static final MyApplication my_app_instance = new MyApplication();
    private User user = new User();

    public static MyApplication get_instance() {
        return my_app_instance;
    }
    public User get_user() {
        return user;
    }
    public void set_user(User user) {
        this.user = user;
    }
}
