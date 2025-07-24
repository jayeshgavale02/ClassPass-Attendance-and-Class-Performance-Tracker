package com.classpass.controller;

import com.classpass.dao.ParentDao;
import com.classpass.entity.Parent;
import com.classpass.service.ParentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;

    @PostMapping("/register")
    public Map<String, Object> registerParent(@RequestBody ParentDao dto) {
        Map<String, Object> response = new HashMap<>();
        String result = parentService.saveParent(dto);

        if (result.contains("successfully")) {
            response.put("status", "success");
            response.put("message", result);
        } else {
            response.put("status", "error");
            response.put("errorCode", "PARENT_REGISTRATION_FAILED");
            response.put("message", result);
        }
        return response;
    }

    @GetMapping("/{userId}")
    public Parent getParent(@PathVariable Long userId) {
        return parentService.getParentByUserId(userId);
    }
}
