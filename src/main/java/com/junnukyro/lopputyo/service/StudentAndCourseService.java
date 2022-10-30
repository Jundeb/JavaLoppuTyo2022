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

    // when lists are public they can be used in controller directly
    public List<Course> courses = new ArrayList<>();
    public List<Student> students = new ArrayList<>();

    public StudentAndCourseService() {
        // gets objects from files
        courses = myFileService.readCoursesFromFile();
        students = myFileService.readStudentsFromFile();
    }

    // adds new course to courses list
    // and saves courses to a file
    public String addCourse(Course course) {

        // if required information is given, create new course
        if (course.getCourseName() == "" || course.getClassRoom() == "" || course.getTeacher() == "") {
            return "Course name, teacher and classroom required.";
        } else {
            courses.add(course);
            myFileService.writeCoursesToFile(courses);
            return "Course created successfully";
        }
    }

    // adds new student to students list
    // and saves students to a file
    public String addStudent(Student student) {

        // if required information is given, create new student
        if (student.getName() == "" || student.getAge() == 0 || student.getGroupId() == "" || student.getEmail() == ""
                || student.getPhonenumber() == "") {
            return "Student name, age, groupId, email and phonenumber required.";
        } else {
            // checks if email already exists
            if (students.stream().anyMatch(o -> o.getEmail().equals(student.getEmail()))) {
                return "This email is already in use";
            }
            // checks if email already exists
            else if (students.stream().anyMatch(o -> o.getName().equals(student.getName()))) {
                return "This name is already in use";
            } else {
                students.add(student);
                myFileService.writeStudentsToFile(students);
                return "Student created successfully";
            }
        }
    }

    // if student and course exists, adds student to a course
    // and saves courses list to file
    public String addStudentToCourse(String studentId, String courseId) {

        // if either studentId or courseId is empty return text below
        if (studentId == "" || courseId == "") {
            return "StudentId and CourseId required";
        } else {
            for (Student student : students) {
                if (student.getStudentId().equals(studentId)) {
                    for (Course course : courses) {
                        if (course.getCourseId().equals(courseId)) {
                            // if student is not in the course add it to course
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
            // if student is not found
            return "Student not found";
        }
    }

}
