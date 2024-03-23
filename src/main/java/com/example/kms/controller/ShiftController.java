package com.example.kms.controller;

import com.example.kms.entity.Shift;
import com.example.kms.service.ShiftService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShiftController {
    private final ShiftService service;

    @Operation(summary = "Get all shifts", description = "Returns shifts data")
    @GetMapping("/shifts")
    public ResponseEntity<List<Shift>> getAllShifts() {
        var shifts = service.getAllShifts();
        if (shifts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shifts, HttpStatus.OK);
    }
    @Operation(summary = "Shift creation", description = "Returns shift data after successful creation")
    @PostMapping("/shifts")
    public ResponseEntity<Shift> createShift(Integer userId, Integer watchId) {
        return new ResponseEntity<>(service.createShift(userId, watchId), HttpStatus.CREATED);
    }

    @Operation(summary = "Get shift by id", description = "Returns shift data")
    @GetMapping("/shifts/{id}")
    public ResponseEntity<Shift> getShiftById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(service.getShiftById(id), HttpStatus.OK);
    }

}
