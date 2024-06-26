package com.example.kms.service;

import com.example.kms.entity.User;
import com.example.kms.form.RegForm;
import com.example.kms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());

    }

    public User updateUser(RegForm form, Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id " + id + " not found"));
        user.setPassword(form.getUsername());
        user.setPassword(form.getPassword());
        //user.setSalt(form.getSalt()); // todo
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Integer id) { // todo убрать пароли из зоны видимости!!!
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + id));
    }
}
