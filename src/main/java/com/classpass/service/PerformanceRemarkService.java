package com.classpass.service;

import com.classpass.dao.PerformanceRemarkDao;
import com.classpass.entity.PerformanceRemark;
import com.classpass.entity.Student;
import com.classpass.entity.Subject;
import com.classpass.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PerformanceRemarkService {

    @Autowired
    private SessionFactory sessionFactory;

    public Map<String, Object> saveRemark(PerformanceRemarkDao dto) {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, dto.getStudentId());
            Subject subject = session.get(Subject.class, dto.getSubjectId());
            Teacher teacher = session.get(Teacher.class, dto.getTeacherId());

            if (student == null || subject == null || teacher == null) {
                response.put("status", "error");
                response.put("message", "Student, Subject or Teacher not found");
                return response;
            }

            PerformanceRemark remark = new PerformanceRemark();
            remark.setRemark(dto.getRemark());
            remark.setStudent(student);
            remark.setSubject(subject);
            remark.setTeacher(teacher);

            session.save(remark);
            tx.commit();

            response.put("status", "success");
            response.put("message", "Remark saved successfully");
        } catch (Exception e) {
            tx.rollback();
            response.put("status", "error");
            response.put("message", "Failed to save remark: " + e.getMessage());
        } finally {
            session.close();
        }

        return response;
    }

    public Map<String, Object> getAllRemarks() {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();

        try {
            List<PerformanceRemark> list = session
                    .createQuery("FROM PerformanceRemark", PerformanceRemark.class)
                    .list();

            response.put("status", "success");
            response.put("data", new ArrayList<>(list));
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch remarks: " + e.getMessage());
        } finally {
            session.close();
        }

        return response;
    }

    public Map<String, Object> getRemarksByStudent(Long studentId) {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();

        try {
            List<PerformanceRemark> list = session
                    .createQuery("FROM PerformanceRemark WHERE student.id = :studentId", PerformanceRemark.class)
                    .setParameter("studentId", studentId)
                    .list();

            response.put("status", "success");
            response.put("data", new ArrayList<>(list));
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch remarks for student: " + e.getMessage());
        } finally {
            session.close();
        }

        return response;
    }
}
