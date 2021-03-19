package org.example.tomasBarauskas.model.user;

public class User {
    private String userId;
    private String password;
    private String name;
    private String surname;
    private UserRole role;

    public User(String userId, String password, String name, String surname) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = UserRole.REGULAR_ROLE;
    }

    @Override
    public String toString() {
        return "Vartotojas - ID | Vardas | Pavarde" + '\n' +
                userId + " | " + name + " | " + surname + '\n';
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
