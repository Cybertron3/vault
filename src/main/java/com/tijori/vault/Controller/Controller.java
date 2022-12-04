package com.tijori.vault.Controller;

import com.tijori.vault.Repository.TijoriRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vault")
public class Controller {

    @Autowired
    TijoriRepository tijoriRepository;

    @GetMapping
    ResponseEntity<Boolean> testing(){
        return ResponseEntity.ok(true);
    }

    @GetMapping("/verify/{userId}")
    ResponseEntity<Boolean> isValidPassword(@RequestParam String password, @PathVariable String userId){

        if(!tijoriRepository.existsByUserId(userId)){
            return  new ResponseEntity<>(HttpStatus.BAD_REQUEST) ;
        }

        String storedHashedKey = tijoriRepository.getHashedkeyForUserId(userId);

        if(password.equals(storedHashedKey)){
            return ResponseEntity.ok(Boolean.TRUE);
        }else {
            return ResponseEntity.ok(Boolean.FALSE);
        }

    }
}
