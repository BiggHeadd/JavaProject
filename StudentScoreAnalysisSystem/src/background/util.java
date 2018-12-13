package background;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.*;

public class util {
    public static void printScores(ArrayList<Student> students, String classSubjectName){
        double top=0;
        double min=100;
        double avg=0;
        String[] classSubjectInfo = classSubjectName.split("-");
        String className = classSubjectInfo[0];
        String subjectName = classSubjectInfo[1];
        System.out.println("班级名称："+className);
        System.out.println("课程名称："+subjectName);
        System.out.println(String.format("%-14s %-4s %-4s", "学号","姓名","成绩"));
        for(Student tmp: students) {
            String[] infosTmp = tmp.toString().split(",");
            System.out.println(String.format("%-15s %-4s %-4s", infosTmp[0], infosTmp[1], infosTmp[2]));
            String[] info = tmp.toString().split(",");
            double scoreTmp = Double.valueOf(info[2]);
            if (scoreTmp > top) {
                top = scoreTmp;
            }
            if (scoreTmp < min) {
                min = scoreTmp;
            }
            avg += scoreTmp;
        }
        avg = avg / students.size();
        System.out.println("最高分：" + top);
        System.out.println("最低分：" + min);
        System.out.println("平均分：" + avg);
    }


    public static ArrayList<Student> sortStudents(ArrayList<Student> students){
        ArrayList<Student> studentSorted = new ArrayList<>();
        Student studentTop;
        while(!students.isEmpty()){
            studentTop = students.get(0);
            for(Student studentTmp: students){
                if(studentTmp.biggerThan(studentTop)){
                    studentTop = studentTmp;
                }
            }
            studentSorted.add(studentTop);
            students.remove(studentTop);
            System.out.println(students);
        }
        return studentSorted;
    }

    public static void test(ArrayList<Student> students){
        while(!students.isEmpty()){
            students.remove(0);
        }
    }
}
