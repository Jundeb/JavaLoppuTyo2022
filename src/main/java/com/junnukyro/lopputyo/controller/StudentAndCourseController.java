package com.junnukyro.lopputyo.controller;

import com.junnukyro.lopputyo.data.Course;
import com.junnukyro.lopputyo.data.Student;
import com.junnukyro.lopputyo.service.StudentAndCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentAndCourseController {
    
    @Autowired
    StudentAndCourseService service;

    @GetMapping("courses")
    public List<Course> getCourses(){
        return service.getCourses();
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        return service.getStudents();
    }

    @PostMapping("addcourse")
    public String addCourse(@RequestBody Course course){
        service.addCourse(course);
        return "";
    }

    @PostMapping("addstudent")
    public String addStudent(@RequestBody Student student){
        service.addStudent(student);
        return "";
    }

    @PostMapping("addstudenttocourse")
    public void addStudentToCourse(@RequestParam String studentId,@RequestParam String courseId){
        service.addStudentToCourse(studentId, courseId);
    }
}
