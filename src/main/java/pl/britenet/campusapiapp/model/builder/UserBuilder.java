package pl.britenet.campusapiapp.model.builder;

import pl.britenet.campusapiapp.model.User;

public class UserBuilder {

    private final User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder setId(int id) {
        this.user.setId(id);
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.user.setLogin(login);
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.user.setPassword(password);
        return this;
    }

    public UserBuilder setName(String name) {
        this.user.setName(name);
        return this;
    }

    public UserBuilder setSurname(String surname) {
        this.user.setSurname(surname);
        return this;
    }

    public UserBuilder setCity(String city) {
        this.user.setCity(city);
        return this;
    }

    public UserBuilder setStreet(String street) {
        this.user.setStreet(street);
        return this;
    }

    public UserBuilder setCountry(String country) {
        this.user.setCountry(country);
        return this;
    }

    public UserBuilder setZipCode(String zipCode) {
        this.user.setZipCode(zipCode);
        return this;
    }

    public UserBuilder setProfilePicturePath(String profilePicturePath) {
        this.user.setProfilePicturePath(profilePicturePath);
        return this;
    }

    public User getUser() {
        return user;
    }
}
