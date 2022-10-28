package com.junnukyro.lopputyo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junnukyro.lopputyo.data.Student;
import com.junnukyro.lopputyo.data.Course;

@Service
public class StudentAndCourseService {
    
    @Autowired
    FileService myFileService = new FileService();
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();


    public StudentAndCourseService() {
        try{
            courses = myFileService.readCoursesFromFile();
            students = myFileService.readStudentsFromFile();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //adds new course to courses list
    //and saves courses to a file
    public void addCourse(Course course){
        courses.add(course);
        myFileService.writeCoursesToFile(courses);
    }

    //adds new student to students list
    //and saves students to a file
    public void addStudent(Student student){
        students.add(student);
        try {
            myFileService.writeStudentsToFile(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //returns all courses
    public List<Course> getCourses(){
        return new ArrayList<>(courses);
    }

    //returns all students
    public List<Student> getStudents(){
        return new ArrayList<>(students);
    }

    //if student and course exists, adds student to a course
    //and saves courses to a file
    public void addStudentToCourse(String studentId,String courseId){
        for(Student student : students){
            if(student.getStudentId().equals(studentId)){
                for(Course course : courses){
                    if(course.getCourseId().equals(courseId)){
                        course.addParticipants(student);
                        try {
                            myFileService.writeCoursesToFile(courses);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
