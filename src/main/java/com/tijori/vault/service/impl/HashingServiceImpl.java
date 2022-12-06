package com.tijori.vault.service.impl;

import com.google.common.hash.Hashing;
import com.tijori.vault.service.HashingService;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;

import com.tijori.vault.TijoriEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HashingServiceImpl implements HashingService {
    @Override
    public String getHashValue(String key, String salt) {
        try {

            String saltedPassword = key + salt;
            String hashedKey = Hashing.sha256().hashString(saltedPassword, StandardCharsets.UTF_8).toString();
            return hashedKey;
        }catch (Exception e){
            log.info("Unexpected error occurred, error: {}", e);
            return null;
        }
    }

    @Override
    public void setSaltAndKeyValues(TijoriEntity entity, String password) {
        String salt = generateSalt();
        entity.setSalt(salt);
        entity.setKey(getHashValue(password, salt));
    }

    private String generateSalt(){
        String generatedSalt = UUID.randomUUID().toString();
        return generatedSalt;
    }

}
