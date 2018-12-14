package com.hearien.protobuf;

/**
 * @ClassName Test
 * @Author WangHaiyang
 * @Date 2018/12/12 16:06
 **/
public class Test {
    public static void main(String[] args) throws Exception {
        StudentInfo.Student student = StudentInfo.Student.newBuilder().setName("重庆").setAge(12).setAddress("china").build();
        byte[] stuByte = student.toByteArray();
        System.out.println(stuByte);
        StudentInfo.Student stu = StudentInfo.Student.parseFrom(stuByte);
        System.out.println(stu.toString());
        System.out.println(stu.getName());
    }
}
