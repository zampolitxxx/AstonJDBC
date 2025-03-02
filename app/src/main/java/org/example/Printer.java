package org.example;

import org.example.model.User;

import java.util.List;

public final class Printer {
    public static void printUsers(List<User> users) {
        for (User user : users) {
            printUser(user);
        }
    }
    public static void printUser(User user) {
        System.out.println("user is: " + user.getUsername() + ", phone number is: " + user.getPhone());
        System.out.println("\n" + "-".repeat(20));
    }
}
