package Model.common;

import java.io.Serializable;

public class Account implements Serializable {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String profilePhotoPath;
    //help fields:
    private String fathersBirthYear;
    private String mothersBirthYear;
    private String favoriteColor;

    public Account(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        try {
            return this.username.equals(((Account) obj).getUsername());
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFavoriteColor() {
        return favoriteColor;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public Account authenticate(String username, String password) {
        if (this.username.equals(username) && this.password.equals(password))
            return this;
        return null;
    }

    public String getFathersBirthYear() {
        return fathersBirthYear;
    }

    public void setFathersBirthYear(String fathersBirthYear) {
        this.fathersBirthYear = fathersBirthYear;
    }

    public String getMothersBirthYear() {
        return mothersBirthYear;
    }

    public void setMothersBirthYear(String mothersBirthYear) {
        this.mothersBirthYear = mothersBirthYear;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }

    public void setProfilePhotoPath(String profilePhotoPath) {
        this.profilePhotoPath = profilePhotoPath;
    }
}
