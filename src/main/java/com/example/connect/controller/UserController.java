package com.example.connect.controller;

import com.example.connect.model.User;
import com.example.connect.service.users.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList = (List<User>) userService.findAll();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/user/detail/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        Optional<User> user = userService.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        String message= "Not found Object";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/create")
    public ResponseEntity<?> saveUser(@Validated @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String message="active is fail";
            return new ResponseEntity<>( HttpStatus.NOT_MODIFIED);
        }
        String message = userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }

    @PutMapping("/user/edit/{id}")
    public ResponseEntity<?> editUser(@Validated @RequestBody User user, @PathVariable Long id , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String message="active is fail";
            return new ResponseEntity<>( HttpStatus.NOT_MODIFIED);
        }
        user.setId(id);
        String message = userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        String message= userService.delete(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }


}
