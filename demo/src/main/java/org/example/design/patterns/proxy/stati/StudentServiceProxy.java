package org.example.design.patterns.proxy.stati;

import org.example.design.patterns.proxy.StudentService;
import org.example.design.patterns.proxy.jdk.IStudentService;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 12:30
 */
public class StudentServiceProxy implements IStudentService {
    IStudentService studentService;

    public StudentServiceProxy(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void insertStudent() {
        System.out.println("准备添加学生");
        studentService.insertStudent();
        System.out.println("添加学生成功");
    }

    @Override
    public void deleteStudent() {
        System.out.println("准备删除学生");
        studentService.deleteStudent();
        System.out.println("删除学生成功");
    }

//    public static void main(String[] args) {
//        IStudentService studentServiceProxy = new StudentServiceProxy(new StudentService());
//        studentServiceProxy.insertStudent();
//        studentServiceProxy.deleteStudent();
//    }
}
