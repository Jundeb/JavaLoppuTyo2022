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

    // tehdään listoista public jotta niitä voidaan käyttää controllereissa
    public List<Course> courses = new ArrayList<>();
    public List<Student> students = new ArrayList<>();

    public StudentAndCourseService() {
        courses = myFileService.readCoursesFromFile();
        students = myFileService.readStudentsFromFile();
    }

    // adds new course to courses list
    // and saves courses to a file
    public String addCourse(Course course) {
            courses.add(course);
            myFileService.writeCoursesToFile(courses);
            return "Course created successfully";
    }

    // adds new student to students list
    // and saves students to a file
    public String addStudent(Student student) {
        //checks if email already exists
        if(students.stream().anyMatch(o -> o.getEmail().equals(student.getEmail()))){
            return "This email is already in use";
        } 
        //checks if email already exists
        else if(students.stream().anyMatch(o -> o.getName().equals(student.getName()))){
            return "This name is already in use";
        }
        //if both tests pass create new student
        else {
            students.add(student);
            myFileService.writeStudentsToFile(students);
            return "Student created successfully";
        }
    }

    // if student and course exists, adds student to a course
    // and saves courses to a file
    public String addStudentToCourse(String studentId, String courseId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                for (Course course : courses) {
                    if (course.getCourseId().equals(courseId)) {
                        // if student is not in the course add it to the course
                        if (!course.getParticipants().stream().anyMatch(o -> o.getStudentId().equals(studentId))) {
                            course.addParticipants(student);
                            myFileService.writeCoursesToFile(courses);
                            return "Student added to course successfully";
                        }
                        // if student is already in course
                        return "Student already in course";
                    }
                }
                // if course is not found
                return "Course not found";
            }
        }
        //if stundet is not found
        return "Student not found";
    }

}
