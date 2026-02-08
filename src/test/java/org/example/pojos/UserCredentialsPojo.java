package org.example.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentialsPojo {

    private String username;
    private String password;
    //@JsonProperty("isAdmin")
    //private boolean isAdmin;

    public UserCredentialsPojo() {
    }

    public UserCredentialsPojo(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        //this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /*
   public boolean isAdmin() {
    //  return isAdmin;
    }

    public void setAdmin(boolean admin) {
    //   isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserCredentialsPojo{" + "username='" + username + '\'' + ", isAdmin=" + isAdmin + '}';
    }



     */
}