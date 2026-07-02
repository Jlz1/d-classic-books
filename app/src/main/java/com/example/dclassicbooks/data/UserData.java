package com.example.dclassicbooks.data;

/**
 * Holds the username of the currently logged-in user for the app session.
 */
public class UserData {
    private static String currentUsername;

    public static void setCurrentUsername(String username) {
        currentUsername = username;
    }

    public static String getCurrentUsername() {
        return currentUsername;
    }
}

