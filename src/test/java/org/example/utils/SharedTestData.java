package org.example.utils;


//centralized storage for test data shared across all test classes
public class SharedTestData {

    // tokens for both admin and customer
    private static String adminToken;
    private static String customerToken;

    // user credentials for login in
    private static String customerUsername;

    // create the ID for rest of test to relay on
    private static String createdItemId;
    private static String createdOrderId;

    // backup ID in case of failing of the main item_id
    private static final String BACKUP_ITEM_ID = "1770891560866";

    // methods for tokens
    public static void setAdminToken(String token) {
        adminToken = token;
    }

    public static String getAdminToken() {
        return adminToken;
    }

    public static void setCustomerToken(String token) {
        customerToken = token;
    }

    public static String getCustomerToken() {
        return customerToken;
    }

    // methods for usernames
    public static void setCustomerUsername(String username) {
        customerUsername = username;
    }

    public static String getCustomerUsername() {
        return customerUsername;
    }

    // methods for item_id
    public static void setCreatedItemId(String id) {
        createdItemId = id;
    }

    public static String getCreatedItemId() {
        return createdItemId != null ? createdItemId : BACKUP_ITEM_ID;
    }

    // methods for orders
    public static void setCreatedOrderId(String id) {
        createdOrderId = id;
    }

    public static String getCreatedOrderId() {
        return createdOrderId;
    }

    // method for the backup_id
    public static String getBackupItemId() {
        return BACKUP_ITEM_ID;
    }
}