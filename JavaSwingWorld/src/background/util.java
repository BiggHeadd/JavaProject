package background;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.regex.*;

public class util {
    public static String printScores(ArrayList<Student> students, String classSubjectName){
        double top=0;
        double min=100;
        double avg=0;
        double excellent=0, good=0, medium=0, at_avg=0, under_avg=0;
        double excellentPecentage=0, goodPecentage=0, mediumPecentage=0, at_avgPecentage=0, under_avgPecentage=0;
        int studentNumber = students.size();
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

            if(scoreTmp>=90){
                excellent+=1;
            }else if(scoreTmp>=80){
                good+=1;
            }else if(scoreTmp>=70){
                medium+=1;
            }else if(scoreTmp>=60){
                at_avg+=1;
            }else{
                under_avg+=1;
            }
        }
        avg = avg / students.size();
        System.out.println("最高分：" + top);
        System.out.println("最低分：" + min);
        System.out.println("平均分：" + avg);
        System.out.println("class numbers: "+students.size());
        excellentPecentage = excellent/studentNumber*100;
        goodPecentage = good/studentNumber*100;
        mediumPecentage = medium/studentNumber*100;
        at_avgPecentage = at_avg/studentNumber*100;
        under_avgPecentage = under_avg/studentNumber*100;
        String json = String.format("%.1f-%.1f-%.1f-%.0f-%.1f-%.0f-%.1f-%.0f-%.1f-%.0f-%.1f-%.0f-%.1f", top, min, avg, excellent,excellentPecentage,good,goodPecentage,medium,mediumPecentage,at_avg,at_avgPecentage,under_avg,under_avgPecentage);
//        return ""+top+"-"+min+"-"+avg+"-"+excellent+"-"+excellentPecentage+"-"+good+"-"+goodPecentage+"-"+medium+"-"+mediumPecentage+"-"+at_avg+"-"+at_avgPecentage+"-"+under_avg+"-"+under_avgPecentage;
        return json;
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
//            System.out.println(students);
        }
        return studentSorted;
    }

    public static void test(ArrayList<Student> students){
        while(!students.isEmpty()){
            students.remove(0);
        }
    }


    public static String searchStudent(ArrayList<Student> students, String name){
        boolean flag = false;
        String target = "";
        for(Student studentTmp: students){
            if(studentTmp.getName().contains(name)) {
//                System.out.println(studentTmp.toString());
                target = studentTmp.toString();
                flag = true;
            }
        }
        if(flag) {
            System.out.println(target);
            return target;
        }else{
            System.out.println("Match Nothing");
            return null;
        }
    }

    public static ArrayList<Student> searchInfo(ArrayList<Student> students, String target, int searchMode){
        boolean flag = false;
        ArrayList<Student> targetStudent = new ArrayList<Student>();
        switch(searchMode){
            case 1: {
                for(Student studentTmp: students){
                    if(studentTmp.getId().contains(target)){
                        targetStudent.add(studentTmp);
                        flag = true;
                    }
                }
            }
            break;
            case 2:{
                for(Student studentTmp: students){
                    if(studentTmp.getName().contains(target)){
                        targetStudent.add(studentTmp);
                        flag = true;
                    }
                }
            }
            break;
            case 3:{
                for(Student studentTmp: students){
                    if(studentTmp.getScore() == Integer.valueOf(target)){
                        targetStudent.add(studentTmp);
                        flag = true;
                    }
                }
            }
            break;
            case 0:{
                for(Student studentTmp: students){
                    if(studentTmp.getName().contains(target) || studentTmp.getId().contains(target)){
                        targetStudent.add(studentTmp);
                        flag = true;
                    }
                }
            }
        }
        if(flag){
            return targetStudent;
        }else{
            Student a = new Student("fail", "fail", 0);
            targetStudent.add(a);
            return targetStudent;
        }
    }
}