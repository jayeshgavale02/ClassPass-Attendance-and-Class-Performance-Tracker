package com.classpass.controller;

import com.classpass.dao.PerformanceRemarkDao;
import com.classpass.service.PerformanceRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/remarks")
public class PerformanceRemarkController {

    @Autowired
    private PerformanceRemarkService remarkService;

    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody PerformanceRemarkDao dto) {
        return remarkService.saveRemark(dto);
    }

    @GetMapping("/all")
    public Map<String, Object> getAll() {
        return remarkService.getAllRemarks();
    }

    @GetMapping("/student/{id}")
    public Map<String, Object> getByStudent(@PathVariable Long id) {
        return remarkService.getRemarksByStudent(id);
    }
}
