package com.classpass.service;

import com.classpass.dao.StudentDao;
import com.classpass.entity.Student;
import com.classpass.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    @Autowired
    private SessionFactory sessionFactory;

    public Map<String, Object> registerStudent(StudentDao dto) {
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

            Student student = new Student();
            student.setUser(user);
            student.setFullName(dto.getFullName());
            student.setClassName(dto.getClassName());

            session.save(student);
            tx.commit();

            response.put("status", "success");
            response.put("message", "Student registered successfully");

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            response.put("status", "error");
            response.put("errorCode", "STUDENT_REGISTRATION_FAILED");
            response.put("message", e.getMessage().toUpperCase());
        } finally {
            session.close();
        }

        return response;
    }

    public Student getStudentByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM Student WHERE user.id = :userId";
        Student student = (Student) session.createQuery(hql)
                .setParameter("userId", userId)
                .uniqueResult();

        if (student != null) {
            student.getAttendanceList().size();
            student.getRemarks().size();
        }
        

        tx.commit();
        session.close();
        return student;
    }
}
