package org.example.design.patterns.proxy;

import org.example.design.patterns.proxy.jdk.IStudentService;

/**
 * @author Jdx
 * @version 1.0
 * @description
 * @date 2021/1/19 12:29
 */
public class StudentService implements IStudentService {
    @Override
    public void insertStudent() {
        System.out.println("准备添加学生");
        //添加学生
        System.out.println("添加学生成功");

    }

    @Override
    public void deleteStudent() {
        System.out.println("准备删除学生");
        //删除学生
        System.out.println("删除学生成功");
    }
}
