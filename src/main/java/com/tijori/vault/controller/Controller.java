package com.tijori.vault.controller;

import com.tijori.vault.dto.CreateProfileRequestDto;
import com.tijori.vault.dto.UpdateProfileRequestDto;
import com.tijori.vault.service.TijoriService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vault/profile")
@Slf4j
public class Controller {

    @Autowired
    TijoriService tijoriService;

    @GetMapping("/verify/{userId}")
    ResponseEntity<Boolean> isValidPassword(@RequestParam String password, @PathVariable String userId){

        if(!tijoriService.existsByUserId(userId)){
            log.error("Incorrect UserId.");
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }

        return ResponseEntity.ok(tijoriService.isValidPassword(password, userId));
    }

    @PostMapping("/create")
    ResponseEntity<String> createProfile(@RequestBody CreateProfileRequestDto requestDto){
        if(tijoriService.existsByUserId(requestDto.getUserId())){
            log.info("User already exists.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!tijoriService.usernameValidator(requestDto.getUserId())){
            log.info("incorrect format for username");
            return new ResponseEntity<>("incorrect username format", HttpStatus.BAD_REQUEST);
        }

        tijoriService.createProfile(requestDto.getPassword(), requestDto.getUserId());
        return new ResponseEntity<>("Profile Created.", HttpStatus.ACCEPTED);
    }

    @PostMapping("/update")
    ResponseEntity<String> updateProfile(@RequestBody UpdateProfileRequestDto requestDto){
        if(!tijoriService.existsByUserId(requestDto.getUserId())){
            log.error("User does not exist, so update not possible");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        tijoriService.updatePassword(requestDto.getCurrentPassword(), requestDto.getNewPassword(), requestDto.getUserId());
        return new ResponseEntity<>("Credentials Updated." ,HttpStatus.ACCEPTED);
    }

}
