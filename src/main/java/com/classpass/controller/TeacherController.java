package com.classpass.controller;

import com.classpass.dao.TeacherDao;
import com.classpass.entity.Teacher;
import com.classpass.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/register")
    public Map<String, Object> registerTeacher(@RequestBody TeacherDao teacherDto) {
        return teacherService.registerTeacher(teacherDto);
    }

    @GetMapping("/getByUser/{userId}")
    public Teacher getTeacher(@PathVariable Long userId) {
        return teacherService.getTeacherByUserId(userId);
    }
}
