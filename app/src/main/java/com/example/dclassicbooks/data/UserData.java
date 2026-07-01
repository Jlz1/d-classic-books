package com.example.dclassicbooks.data;

import java.util.HashMap;
import java.util.Map;

public class UserData {
    private static final Map<String, String> users = new HashMap<>();

    static {
        // Add admin credentials
        users.put("admin", "admin");
    }

    /**
     * Validate user credentials
     * 
     * @param username Username to validate
     * @param password Password to validate
     * @return true if credentials are valid, false otherwise
     */
    public static boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    /**
     * Check if user exists
     * 
     * @param username Username to check
     * @return true if user exists, false otherwise
     */
    public static boolean userExists(String username) {
        return users.containsKey(username);
    }

    /**
     * Get all users (for admin purposes)
     * 
     * @return Map of all users
     */
    public static Map<String, String> getAllUsers() {
        return new HashMap<>(users);
    }
}
