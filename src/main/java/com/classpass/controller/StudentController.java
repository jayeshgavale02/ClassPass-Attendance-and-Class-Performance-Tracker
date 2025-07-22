package com.classpass.controller;

import com.classpass.dao.StudentDao;
import com.classpass.entity.Student;
import com.classpass.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    public Map<String, Object> registerStudent(@RequestBody StudentDao studentDto) {
        return studentService.registerStudent(studentDto);
    }

    @GetMapping("/getByUser/{userId}")
    public Student getStudent(@PathVariable Long userId) {
        return studentService.getStudentByUserId(userId);
    }
}
