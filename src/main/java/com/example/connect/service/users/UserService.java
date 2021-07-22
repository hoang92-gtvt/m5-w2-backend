package com.example.connect.service.users;

import com.example.connect.model.User;
import com.example.connect.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{
    @Autowired
    IUserRepository userRepository;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public String save(User object) {
        userRepository.save(object);
        return "Save or Update sucessfull";

    }

    @Override
    public String delete(Long id) {
        userRepository.deleteById(id);
        return "Object is deleted";
    }
}
