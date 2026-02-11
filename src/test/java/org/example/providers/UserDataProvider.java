package org.example.providers;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.utils.JsonUtils;
import org.example.pojos.UserCredentialsPojo;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;

public class UserDataProvider {

    @DataProvider(name = "users")
    public static Object[][] getUsers() throws IOException {

        List<UserCredentialsPojo> users = JsonUtils.readJsonFile("users.json", new TypeReference<List<UserCredentialsPojo>>() {
        });

        Object[][] data = new Object[users.size()][1];
        for (int i = 0; i < users.size(); i++) {
            data[i][0] = users.get(i);
        }

        return data;
    }
}


    /*
    @DataProvider(name = "regularUsers")
    public static Object[][] getRegularUsers() throws IOException {
        List<UserCredentialsPojo> users = JsonUtils.readJsonFile(
                "users.json",
                new TypeReference<List<UserCredentialsPojo>>() {
                }
        );


        // not admin
        List<UserCredentialsPojo> regularUsers = users.stream()
         //       .filter(user -> !user.isAdmin())
          //      .collect(Collectors.toList());

        Object[][] data = new Object[regularUsers.size()][1];
        for (int i = 0; i < regularUsers.size(); i++) {
            data[i][0] = regularUsers.get(i);
        }

        return data;
    }

    @DataProvider(name = "adminUsers")
    public static Object[][] getAdminUsers() throws IOException {
        List<UserCredentialsPojo> users = JsonUtils.readJsonFile(
                "users.json",
                new TypeReference<List<UserCredentialsPojo>>() {}
        );

        // admin only
        List<UserCredentialsPojo> adminUsers = users.stream()
                .filter(UserCredentialsPojo::isAdmin)
                .collect(Collectors.toList());

        Object[][] data = new Object[adminUsers.size()][1];
        for (int i = 0; i < adminUsers.size(); i++) {
            data[i][0] = adminUsers.get(i);
        }

        return data;
    }

*/

