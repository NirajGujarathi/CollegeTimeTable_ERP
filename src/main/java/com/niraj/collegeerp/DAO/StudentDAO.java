package com.niraj.collegeerp.DAO;

import com.niraj.collegeerp.bean.Students;
import com.niraj.collegeerp.bean.TimeTable;

import java.util.List;

public interface StudentDAO {

    Students roll_numberVerify(Students student);
    boolean registerStudent(Students student);
    List<TimeTable> getCourse(Students student);
}
