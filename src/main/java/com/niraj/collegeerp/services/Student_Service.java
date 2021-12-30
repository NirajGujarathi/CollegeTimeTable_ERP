package com.niraj.collegeerp.services;

import com.niraj.collegeerp.DAO.Implementation.StudentDAOImpl;
import com.niraj.collegeerp.DAO.StudentDAO;
import com.niraj.collegeerp.bean.Students;
import com.niraj.collegeerp.bean.TimeTable;

import java.util.List;

public class Student_Service {
    StudentDAO studentDao = new StudentDAOImpl();
    public Students verifyroll_number(Students student){
        return studentDao.roll_numberVerify(student);
    }

    public boolean registerStudent(Students student){
        return studentDao.registerStudent(student);
    }

    public List<TimeTable> getCourse(Students student){return studentDao.getCourse(student);}
}
