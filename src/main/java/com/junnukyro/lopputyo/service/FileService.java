package com.junnukyro.lopputyo.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.junnukyro.lopputyo.data.Student;
import com.junnukyro.lopputyo.data.Course;

@Service
public class FileService {
    public void writeCoursesToFile(List<Course> courses){
        try {
            //Serializes list courses to projects root folder
            FileOutputStream fos = new FileOutputStream("courses");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(courses);
            oos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeStudentsToFile(List<Student> students){
        try {
            //Serializes list students to projects root folder
            FileOutputStream fos = new FileOutputStream("students");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(students);
            oos.close();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Course> readCoursesFromFile(){
        try {
            List<Course> courses = new ArrayList<>();
            File file = new File("courses");
            if(file.isFile()){
            //Deserializes list of objects from folder
            //and returns the list courses
            FileInputStream fis = new FileInputStream("courses");
            ObjectInputStream ois = new ObjectInputStream(fis);
            courses = (List<Course>) ois.readObject();
            ois.close();
            fis.close();
            }
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> readStudentsFromFile(){
        try {
            List<Student> students = new ArrayList<>();
            File file = new File("students");
            if(file.isFile()){
            //Deserializes list of objects from folder
            //and returns the list of students
            FileInputStream fis = new FileInputStream("students");
            ObjectInputStream ois = new ObjectInputStream(fis);
            students = (List<Student>) ois.readObject();
            ois.close();
            fis.close();
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
