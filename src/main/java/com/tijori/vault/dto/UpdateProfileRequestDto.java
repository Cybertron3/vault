package com.tijori.vault.dto;

import lombok.Data;

@Data
public class UpdateProfileRequestDto {
    String userId;
    String currentPassword;
    String newPassword;
}
