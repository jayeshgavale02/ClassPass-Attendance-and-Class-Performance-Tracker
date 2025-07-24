package com.classpass.controller;

import com.classpass.dao.SubjectDao;
import com.classpass.entity.Subject;
import com.classpass.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping("/save")
    public Map<String, Object> saveSubject(@RequestBody SubjectDao dto) {
        Map<String, Object> response = new HashMap<>();
        String result = subjectService.saveSubject(dto);

        if (result.contains("successfully")) {
            response.put("status", "success");
            response.put("message", result);
        } else {
            response.put("status", "error");
            response.put("errorCode", "SUBJECT_CREATION_FAILED");
            response.put("message", result);
        }
        return response;
    }

    @GetMapping("/all")
    public List<Subject> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Long id) {
        return subjectService.getSubjectById(id);
    }
}
