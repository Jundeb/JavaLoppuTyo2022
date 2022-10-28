package com.junnukyro.lopputyo.controller;

import com.junnukyro.lopputyo.data.Course;
import com.junnukyro.lopputyo.data.Student;
import com.junnukyro.lopputyo.service.StudentAndCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class StudentAndCourseController {
    
    @Autowired
    StudentAndCourseService service;

    @GetMapping("courses")
    public List<Course> getCourses(){
        return service.courses;
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        return service.students;
    }

    @PostMapping("addcourse")
    public String addCourse(@RequestBody Course course){
        service.addCourse(course);
        return "Course created";
    }

    @PostMapping("addstudent")
    public String addStudent(@RequestBody Student student){
        return service.addStudent(student);
    }

    @PostMapping("addstudenttocourse")
    public String addStudentToCourse(@RequestBody Map<String, Object> jsonMapping){
        //tries to find attribute values for "studentId" and "courseId" from JSON
        String studentId = jsonMapping.get("studentId").toString();
        String courseId = jsonMapping.get("courseId").toString();
        if(studentId == "" || courseId == ""){
            return "StudentId and CourseId required";
        }
        return service.addStudentToCourse(studentId, courseId);
    }
}
