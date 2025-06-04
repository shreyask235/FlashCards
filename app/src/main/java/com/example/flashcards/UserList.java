package com.example.flashcards;

import java.util.ArrayList;

public class UserList {
    private static final UserList instance = new UserList(); // Singleton instance
    private final ArrayList<User> users = new ArrayList<>();

    private UserList() {}

    public static UserList getInstance() {
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
