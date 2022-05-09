package Test;

import bean.Student;
import daoIMP.StudentDAOIMP;

public class TestStudent {
    public static void main(String[] args) {
        StudentDAOIMP studentDAOIMP = new StudentDAOIMP();


        Student s = studentDAOIMP.findByID(2l);
        System.out.println(s.toString());

    }
}
