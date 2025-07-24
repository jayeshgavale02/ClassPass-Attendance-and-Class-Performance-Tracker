package com.classpass.service;

import com.classpass.dao.AttendanceDao;
import com.classpass.entity.Attendance;
import com.classpass.entity.Student;
import com.classpass.entity.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AttendanceService {

    @Autowired
    private SessionFactory sessionFactory;

    public Map<String, Object> saveAttendance(AttendanceDao dto) {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Student student = session.get(Student.class, dto.getStudentId());
            Subject subject = session.get(Subject.class, dto.getSubjectId());

            if (student == null || subject == null) {
                response.put("status", "error");
                response.put("message", "Student or Subject not found");
                return response;
            }

            Attendance attendance = new Attendance();
            attendance.setDate(dto.getDate());
            attendance.setPresent(dto.isPresent());
            attendance.setStudent(student);
            attendance.setSubject(subject);

            session.save(attendance);
            tx.commit();

            response.put("status", "success");
            response.put("message", "Attendance saved successfully");
        } catch (Exception e) {
            tx.rollback();
            response.put("status", "error");
            response.put("message", "Failed to save attendance: " + e.getMessage());
        } finally {
            session.close();
        }
        return response;
    }

    public Map<String, Object> getAllAttendance() {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();

        try {
            List<Attendance> attendanceList = session.createQuery("FROM Attendance", Attendance.class).list();
            response.put("status", "success");
            response.put("data", new ArrayList<>(attendanceList));
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch attendance: " + e.getMessage());
        } finally {
            session.close();
        }

        return response;
    }

    public Map<String, Object> getAttendanceByStudent(Long studentId) {
        Map<String, Object> response = new HashMap<>();
        Session session = sessionFactory.openSession();

        try {
            List<Attendance> list = session
                    .createQuery("FROM Attendance WHERE student.id = :studentId", Attendance.class)
                    .setParameter("studentId", studentId)
                    .list();

            response.put("status", "success");
            response.put("data", new ArrayList<>(list));
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to fetch attendance by student: " + e.getMessage());
        } finally {
            session.close();
        }

        return response;
    }
}
