package com.classpass.service;

import com.classpass.dao.ParentDao;
import com.classpass.entity.Parent;
import com.classpass.entity.Student;
import com.classpass.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParentService {

    @Autowired
    private SessionFactory sessionFactory;

    public String saveParent(ParentDao dto) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            User user = session.get(User.class, dto.getUserId());
            Student child = session.get(Student.class, dto.getStudentId());

            if (user == null || child == null) {
                return "User or Student not found.";
            }

            Parent parent = new Parent();
            parent.setUser(user);
            parent.setChild(child);
            parent.setName(dto.getName());
            parent.setPhone(dto.getPhone());

            session.save(parent);
            tx.commit();
            return "Parent saved successfully.";
        } catch (Exception e) {
            tx.rollback();
            return "Error: " + e.getMessage();
        } finally {
            session.close();
        }
    }

    public Parent getParentByUserId(Long userId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "FROM Parent WHERE user.id = :userId";
        Parent parent = (Parent) session.createQuery(hql)
                .setParameter("userId", userId)
                .uniqueResult();

        if (parent != null && parent.getChild() != null) {
            // Force lazy collections to load before session closes
            parent.getChild().getAttendanceList().size();
            parent.getChild().getRemarks().size();
        }

        tx.commit();
        session.close();
        return parent;
    }

}
