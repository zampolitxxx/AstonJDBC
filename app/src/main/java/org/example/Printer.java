package org.example;

import java.util.Set;

public final class Printer {
    public static void printUsers(Set<User> users) {
        for (User user : users) {
            System.out.println(user.getUsername() + ", number is: " + user.getPhone());
        }
    }
}
