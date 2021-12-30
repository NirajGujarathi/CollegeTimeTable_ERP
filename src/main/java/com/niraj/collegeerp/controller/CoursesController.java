package com.niraj.collegeerp.controller;

import com.niraj.collegeerp.bean.Courses;
import com.niraj.collegeerp.services.Course_Service;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.List;

@Path("courses")
public class CoursesController {

    Course_Service courseService = new Course_Service();

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCourses() {
        List<Courses> courses = courseService.getCourses();
        return Response.ok().entity(courses).build();
    }


    @POST
    @Path("/register")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.TEXT_PLAIN)
    public Response registerCourse(@FormDataParam("name") String name,
                                   @FormDataParam("description") String specialization,
                                   @FormDataParam("credits") Integer credits,
                                   @FormDataParam("term") Integer term) throws URISyntaxException {
        Courses course =  new Courses(name, specialization, credits,term);

            if(courseService.registerCourse(course)){
            return Response.ok().build();
        }
        return Response.status(203).build();

    }
}
