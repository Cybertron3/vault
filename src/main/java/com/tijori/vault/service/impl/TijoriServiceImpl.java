package com.tijori.vault.service.impl;

import com.tijori.vault.repository.TijoriRepository;
import com.tijori.vault.service.HashingService;
import com.tijori.vault.service.TijoriService;
import com.tijori.vault.TijoriEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
public class TijoriServiceImpl implements TijoriService {



    @Autowired
    TijoriRepository tijoriRepository;

    @Autowired HashingService hashingService;

    @Override
    public Boolean existsByUserId(String userId) {
        return tijoriRepository.existsByUserId(userId);
    }

    @Override
    public Boolean isValidPassword(String password, String userId) {
        Optional<TijoriEntity> tijoriEntityOptional = tijoriRepository.findByUserId(userId);
        if(tijoriEntityOptional.isEmpty()){
            return null;
        }

        String resultedHash = hashingService.getHashValue(password, tijoriEntityOptional.get().getSalt());
        if(resultedHash.equals(tijoriEntityOptional.get().getKey()))
            return Boolean.TRUE;

        return Boolean.FALSE;
    }

    @Override
    public void createProfile(String password, String userId) {
        TijoriEntity tijoriEntity = new TijoriEntity();
        tijoriEntity.setUserId(userId);
        hashingService.setSaltAndKeyValues(tijoriEntity, password);
        tijoriRepository.save(tijoriEntity);
        log.info("Profile created.");
    }

    @Override
    @Transactional
    public void updatePassword(String currentPassword, String newPassword, String userId) {
        if(Boolean.FALSE.equals(isValidPassword(currentPassword, userId))){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect password");
        }
        //Since its a valid password, means entity exists
        Optional<TijoriEntity> tijoriEntityOptional = tijoriRepository.findByUserId(userId);
        TijoriEntity tijori = tijoriEntityOptional.get();

        hashingService.setSaltAndKeyValues(tijori, newPassword);
        tijoriRepository.save(tijori);
        log.info("Credentials updated.");
    }

    @Override
    public Boolean usernameValidator(String userName) {
        if(userName.isEmpty() || userName.length() < 5 || userName.length() < 15 ||
                !userName.chars().allMatch(Character::isLetterOrDigit)){
            return false;
        }
        return true;
    }
}
