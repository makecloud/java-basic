package com.liuyihui.common.dataType;

public class Test {
    static class Student {
        String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        swap(s1, s2);

        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());

        changeName(s1);
        System.out.println(s1.getName());
    }

    public static void swap(Student s1, Student s2) {
        Student temp = s1;
        s1 = s2;
        s2 = temp;

        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
        //结果显示交换了。

        //我的理解是，s1 s2存的是地址，这俩地址交换了，打印这俩地址的name，肯定交换了。
        //但是main方法中的s1和s2这俩地址指向没变，打印他们的name肯定没变。



        s1.name = "jjj";
        //然后我调用这个地址的name，name变了。main方法中指向这个的地址的name也会变。
    }

    public static void changeName(Student x) {
        x.name = "jfkdjf";
    }
}
