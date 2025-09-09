package com.Lost.Found.Lost.Found.Controller;

import com.Lost.Found.Lost.Found.DTO.AppUserDTO;
import com.Lost.Found.Lost.Found.Service.AppUserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(appUserService.getUserById(id) , HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> getAllUser(){
        return new ResponseEntity<>(appUserService.getAllUsers() , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@RequestBody AppUserDTO appUserDTO , @PathVariable Long id){
        return new ResponseEntity<>(appUserService.updateUser(appUserDTO , id) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        new ResponseEntity<>(appUserService.deleteUser(id) , HttpStatus.OK);
        return ResponseEntity.noContent().build();
    }

}
