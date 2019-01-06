package background.searchButtom;

import background.Student;
import background.util;

import java.util.ArrayList;

public class searchButton implements searchInterface{
    public ArrayList<Student> studentDatas;


    public searchButton(ArrayList<Student> studentDatas){
        this.studentDatas = studentDatas;
    }
    @Override
    public String searchSomeone(String student) {
        String target = util.searchStudent(studentDatas, student);
        return target;
    }
}
