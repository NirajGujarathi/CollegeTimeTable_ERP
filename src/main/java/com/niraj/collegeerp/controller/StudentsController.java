package com.niraj.collegeerp.controller;



import com.niraj.collegeerp.bean.Courses;
import com.niraj.collegeerp.bean.Students;
import com.niraj.collegeerp.bean.TimeTable;
import com.niraj.collegeerp.services.Course_Service;
import com.niraj.collegeerp.services.Student_Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Path("students")
public class StudentsController {
    Student_Service studentService = new Student_Service();
    Course_Service courseService =  new Course_Service();
    @POST
    @Path("/register")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerStudent(Students student) throws URISyntaxException {
        System.out.println();
        List<Courses> courses = new ArrayList<>();
        Courses course1 = courseService.getCourseByID(student.getCourses().get(0).getCourse_id());
        Courses course2 = courseService.getCourseByID(student.getCourses().get(1).getCourse_id());

        if(course1!=null && course2 !=null){
            courses.add(course1);
            courses.add(course2);
            student.setCourses(courses);
            if(studentService.registerStudent(student)){
                return Response.ok().build();
            }else{
                return Response.status(203).build();
            }
        }
        return Response.status(203).build();

    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginStudent(Students student) throws URISyntaxException {
        Students result = studentService.verifyroll_number(student);

        if(result == null){
            return Response.noContent().build();
        }

        return Response.ok().entity(result).build();
    }


    @POST
    @Path("/courseid")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response studentcourse(Students student) throws URISyntaxException {

        List<TimeTable> courseslist = studentService.getCourse(student);
        System.out.println(courseslist.get(0).getRoom());
        System.out.println(courseslist.get(0).getFaculty_name());

        if(courseslist.size()!=0)
        {
            return Response.ok().entity(courseslist).build();
        }
        else{
            return Response.status(203).build();
        }
    }
}
