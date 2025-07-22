package com.classpass.service;

import com.classpass.dao.UserDao;
import com.classpass.entity.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;

    public Map<String, Object> saveUser(UserDao dto) {
        Map<String, Object> response = new HashMap<>();

        if (dto.getUsername() == null || dto.getUsername().isEmpty() ||
            dto.getPassword() == null || dto.getPassword().isEmpty() ||
            dto.getRole() == null) {
            response.put("status", "error");
            response.put("message", "Username, password, and role are required.");
            return response;
        }

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            User user = new User();
            user.setUsername(dto.getUsername());
            user.setPassword(dto.getPassword());
            user.setRole(dto.getRole());

            session.save(user);
            tx.commit();

            response.put("status", "success");
            response.put("message", "User registered successfully");
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            response.put("status", "error");
            response.put("message", "USER ALREADY EXISTS");
        } finally {
            session.close();
        }

        return response;
    }

    public Map<String, Object> loginUser(UserDao dto) {
        Map<String, Object> response = new HashMap<>();

        if (dto.getUsername() == null || dto.getUsername().isEmpty() ||
            dto.getPassword() == null || dto.getPassword().isEmpty()) {
            response.put("status", "error");
            response.put("message", "Username and password are required.");
            return response;
        }

        Session session = sessionFactory.openSession();

        String hql = "FROM User WHERE username = :username AND password = :password";
        User user = (User) session.createQuery(hql)
                .setParameter("username", dto.getUsername())
                .setParameter("password", dto.getPassword())
                .uniqueResult();

        session.close();

        if (user != null) {
            response.put("status", "success");
            response.put("message", "Login successful");
            response.put("role", user.getRole());
        } else {
            response.put("status", "error");
            response.put("message", "Invalid username or password");
        }

        return response;
    }
}
