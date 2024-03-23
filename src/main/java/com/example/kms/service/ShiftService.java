package com.example.kms.service;

import com.example.kms.entity.Shift;
import com.example.kms.entity.User;
import com.example.kms.entity.Watch;
import com.example.kms.repository.ShiftRepository;
import com.example.kms.repository.UserRepository;
import com.example.kms.repository.WatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftService {
    private final ShiftRepository shiftRepository;
    private final UserRepository userRepository;
    private final WatchRepository watchRepository;

    public Shift createShift(Integer userId, Integer watchId) {
        User watchman = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Not found user with id = " + userId));
        Watch watch = watchRepository.findById(watchId)
                .orElseThrow(()-> new RuntimeException("Not found watch with id = " + watchId));
        return shiftRepository.save(new Shift(watchman, watch, new Timestamp(System.currentTimeMillis())));
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }

    public Shift getShiftById(Integer id){
        return shiftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found Shift with id = " + id));
    }

}
