package com.junnukyro.lopputyo.data;
import java.io.Serializable;

public class Student implements Serializable {
    private String name;
    private int age;
    private String studentId;
    private String groupId;
    private String email;
    private String phonenumber;


    public Student(String name, int age, String studentId, String groupId, String email, String phonenumber) {
        this.name = name;
        this.age = age;
        this.studentId = studentId;
        this.groupId = groupId;
        this.email = email;
        this.phonenumber = phonenumber;
    }


    public Student() {
        this("", 0, "", "", "", "");
    }
    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}

    
