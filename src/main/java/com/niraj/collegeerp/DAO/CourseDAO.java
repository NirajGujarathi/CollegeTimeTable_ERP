package com.niraj.collegeerp.DAO;

import com.niraj.collegeerp.bean.Courses;

import java.util.List;

public interface CourseDAO {

    boolean registerCourse(Courses course);
    List<Courses> getCourses();
    Courses getCourseByID(Integer id);
}
