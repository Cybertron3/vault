package com.tijori.vault.service;

public interface TijoriService {
    Boolean existsByUserId(String userId);

    Boolean isValidPassword(String password, String userId);

    void createProfile(String password, String userId);

    void updatePassword(String currentPassword, String newPassword, String userId);

    Boolean usernameValidator(String userName);
}
