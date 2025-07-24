package com.classpass.controller;

import com.classpass.dao.AttendanceDao;
import com.classpass.entity.Attendance;
import com.classpass.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/save")
    public Map<String, Object> saveAttendance(@RequestBody AttendanceDao dto) {
        return attendanceService.saveAttendance(dto);
    }

    @GetMapping("/all")
    public Map<String, Object> getAllAttendance() {
        return attendanceService.getAllAttendance();
    }

    @GetMapping("/student/{id}")
    public Map<String, Object> getByStudent(@PathVariable Long id) {
        return attendanceService.getAttendanceByStudent(id);
    }
}
