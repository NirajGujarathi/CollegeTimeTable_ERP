package com.niraj.collegeerp.DAO.Implementation;

import com.niraj.collegeerp.DAO.StudentDAO;
import com.niraj.collegeerp.bean.Students;
import com.niraj.collegeerp.bean.TimeTable;
import com.niraj.collegeerp.utility.HibernateSessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;


public class StudentDAOImpl implements StudentDAO {

    @Override
    public Students roll_numberVerify(Students student) {
        try (Session session = HibernateSessionUtil.getSession()) {
            session.setProperty("id",student.getRoll_number());
            Query query = session.createQuery("from Students where roll_number=:roll_number");
            query.setParameter("roll_number", student.getRoll_number());

            for(final Object fetch: query.list())
            {
                return (Students) fetch;
            }

        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }

    @Override
    public boolean registerStudent(Students student) {
        try (Session session = HibernateSessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }
    @Override
    public List<TimeTable> getCourse(Students student){
        try (Session session = HibernateSessionUtil.getSession()) {
            Query query = session.createQuery("from Students where roll_number=:roll_number");
            query.setParameter("roll_number", student.getRoll_number());
// only 2 course can be registered
            for (final Object fetch : query.list()) {
                Students obj = (Students) fetch;
                int c1 = obj.getCourses().get(0).getCourse_id();


                Query query1 = session.createQuery("from TimeTable where course_id=:c1");
                query1.setParameter("c1", c1);
                List<TimeTable> ret = new ArrayList<>();
                for (final Object fetch1 : query1.list()) {
                    TimeTable obj1 = (TimeTable) fetch1;
                    ret.add(obj1);
                    System.out.println(obj1.getFaculty_name());
                    System.out.println(obj1.getRoom());
                    System.out.println(obj1.getCourse_name());
                }

                int c2 = obj.getCourses().get(1).getCourse_id();

                Query query2 = session.createQuery("from TimeTable where course_id=:c2");
                query2.setParameter("c2", c2);

                for (final Object fetch1 : query2.list()) {
                    TimeTable obj2 = (TimeTable) fetch1;
                    ret.add(obj2);
                    System.out.println(obj2.getFaculty_name());
                    System.out.println(obj2.getRoom());
                    System.out.println(obj2.getCourse_name());
                }
                return ret;
            }
        }catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return null;
        }
        return null;
    }
}