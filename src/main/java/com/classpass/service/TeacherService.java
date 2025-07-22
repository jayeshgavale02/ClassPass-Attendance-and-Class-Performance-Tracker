package com.classpass.service;

import com.classpass.dao.TeacherDao;
import com.classpass.entity.Teacher;
import com.classpass.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    private SessionFactory sessionFactory;

    public Map<String, Object> registerTeacher(TeacherDao dto) {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            User user = session.get(User.class, dto.getUserId());
            if (user == null) {
                response.put("status", "error");
                response.put("errorCode", "USER_NOT_FOUND");
                tx.rollback();
                return response;
            }

            Teacher teacher = new Teacher();
            teacher.setUser(user);
            teacher.setFullName(dto.getFullName());
            teacher.setDepartment(dto.getDepartment());

            session.save(teacher);
            tx.commit();

            response.put("status", "success");
            response.put("message", "Teacher registered successfully");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            response.put("status", "error");
            response.put("errorCode", "TEACHER_REGISTRATION_FAILED");
            response.put("message", e.getMessage().toUpperCase());
        } finally {
            session.close();
        }

        return response;
    }

    public Teacher getTeacherByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM Teacher WHERE user.id = :userId";
        Teacher teacher = (Teacher) session.createQuery(hql)
                .setParameter("userId", userId)
                .uniqueResult();

        if (teacher != null) {
            teacher.getSubjects().size();
            teacher.getRemarks().size();
        }

        tx.commit();
        session.close();
        return teacher;
    }
}
