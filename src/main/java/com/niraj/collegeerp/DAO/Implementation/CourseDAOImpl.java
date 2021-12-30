package com.niraj.collegeerp.DAO.Implementation;

import com.niraj.collegeerp.DAO.CourseDAO;
import com.niraj.collegeerp.bean.Courses;
import com.niraj.collegeerp.utility.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean registerCourse(Courses course) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Courses> getCourses() {
        Session session = HibernateSessionUtil.getSession();
        List<Courses> courses = new ArrayList<>();
        try {
            for (final Object course : session.createQuery("from Courses ").list()) {
                courses.add((Courses) course);
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return courses;
    }

    @Override
    public Courses getCourseByID(Integer id) {
        try (Session session = HibernateSessionUtil.getSession()) {
            return session.get(Courses.class, id);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}