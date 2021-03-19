package org.example.tomasBarauskas.model.user;

public class ManagerUser extends User {
    public ManagerUser(String userId, String password, String name, String surname) {
        super(userId, password, name, surname);
        setRole(UserRole.MANAGER_ROLE);
    }

    @Override
    public String toString() {
        return "Manager " + super.toString();
    }
}
