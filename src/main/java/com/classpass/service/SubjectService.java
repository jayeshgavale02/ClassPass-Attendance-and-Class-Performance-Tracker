package com.classpass.service;

import com.classpass.dao.SubjectDao;
import com.classpass.entity.Subject;
import com.classpass.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SessionFactory sessionFactory;

    public String saveSubject(SubjectDao dto) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String response;

        try {
            Teacher teacher = session.get(Teacher.class, dto.getTeacherId());
            if (teacher == null) {
                tx.rollback();
                return "Teacher not found with ID: " + dto.getTeacherId();
            }

            Subject subject = new Subject();
            subject.setName(dto.getName());
            subject.setClassName(dto.getClassName());
            subject.setTeacher(teacher);

            session.save(subject);
            tx.commit();
            response = "Subject saved successfully";
        } catch (Exception e) {
            tx.rollback();
            response = "Failed to save subject: " + e.getMessage();
        } finally {
            session.close();
        }

        return response;
    }

    public List<Subject> getAllSubjects() {
        Session session = sessionFactory.openSession();
        List<Subject> list = session.createQuery("FROM Subject", Subject.class).list();
        for (Subject subject : list) {
            subject.getTeacher().getFullName(); // load teacher
        }
        session.close();
        return list;
    }

    public Subject getSubjectById(Long id) {
        Session session = sessionFactory.openSession();
        Subject subject = session.get(Subject.class, id);
        if (subject != null && subject.getTeacher() != null) {
            subject.getTeacher().getFullName(); // force lazy load
        }
        session.close();
        return subject;
    }
}
