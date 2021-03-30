package org.example.tomasBarauskas.model.user;

import org.example.tomasBarauskas.model.parking.Nameable;

public enum UserRole implements Nameable {
    REGULAR_ROLE("Regular user"),
    MANAGER_ROLE("Manager user");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
