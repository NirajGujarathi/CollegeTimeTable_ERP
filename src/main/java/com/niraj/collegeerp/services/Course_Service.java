package com.niraj.collegeerp.services;

import com.niraj.collegeerp.DAO.CourseDAO;
import com.niraj.collegeerp.DAO.Implementation.CourseDAOImpl;

import com.niraj.collegeerp.bean.Courses;

import java.util.List;

public class Course_Service {
    CourseDAO courseDao = new CourseDAOImpl();
    public boolean registerCourse(Courses course){
        return courseDao.registerCourse(course);
    }

    public List<Courses> getCourses (){
        return courseDao.getCourses();
    }

    public Courses getCourseByID(Integer id){
        return courseDao.getCourseByID(id);
    }
}
