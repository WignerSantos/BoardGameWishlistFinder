package com.wigner.BoardGameWishlistFinder.model;

public class Person {

    private int personId;

    private String name;

    private String email;

    private String confirmEmail;

    private String password;

    private String confirmPassword;

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
