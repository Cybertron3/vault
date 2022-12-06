package com.tijori.vault.dto;

import lombok.Data;

@Data
public class CreateProfileRequestDto {
    String userId;
    String password;
}
