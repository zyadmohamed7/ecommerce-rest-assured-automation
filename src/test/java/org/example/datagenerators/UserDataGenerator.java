package org.example.datagenerators;

import com.github.javafaker.Faker;
import org.example.pojos.UserCredentialsPojo;

public class UserDataGenerator {
    private static final Faker faker = new Faker();


    public static UserCredentialsPojo generateRandomUser() {
        return new UserCredentialsPojo(
                faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                false // Regular user
        );
    }

    public static UserCredentialsPojo generateAdminUser() {
        return new UserCredentialsPojo(
                "admin_" + faker.internet().emailAddress(),
                faker.internet().password(8, 16),
                true // Admin user
        );
    }

    public static UserCredentialsPojo generateUserWithUsername(String username) {
        return new UserCredentialsPojo(
                username,
                faker.internet().password(8, 16),
                false
        );
    }


    public static UserCredentialsPojo generateUserWithWeakPassword() {
        return new UserCredentialsPojo(
                faker.internet().emailAddress(),
                "123", // Weak password
                false
        );
    }


    public static UserCredentialsPojo generateUserWithInvalidEmail() {
        return new UserCredentialsPojo(
                "invalid-email", // No @ symbol
                faker.internet().password(8, 16),
                false
        );
    }
}