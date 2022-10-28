package com.junnukyro.lopputyo.data;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable{
    private String courseName;
    private String courseId;
    private String teacher;
    private String classRoom;

    private List<Student> participants;

    public Course(String courseName, String courseId, String teacher, String classRoom) {
        this.courseName = courseName;
        this.courseId = courseId;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.participants = new ArrayList<>();
    }

    public Course() {
        this("", "", "", "");
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return this.classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public List<Student> getParticipants() {
        return this.participants;
    }

    public void addParticipants(Student student){
        this.participants.add(student);
    }
}
