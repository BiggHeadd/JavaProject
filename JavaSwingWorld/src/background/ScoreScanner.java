package background;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreScanner {

    public ScoreScanner(){

    }

    public ArrayList<Student> LoadData(String path) throws IOException {
        ArrayList<Student> students = new ArrayList<Student>();
        File file = new File(path);
        Scanner dataInput = new Scanner(file);

        while(dataInput.hasNext()){
            String[] studentInfos = dataInput.next().split(",");
            Student studentTmp = new Student(studentInfos[0], studentInfos[1], Integer.valueOf(studentInfos[2]));
            students.add(studentTmp);
        }
        return students;
    }

    public ArrayList<Student> LoadDataBytes(String className) throws IOException, ClassNotFoundException{
        FileInputStream fileInput = new FileInputStream(className);
        ObjectInputStream objectInput = new ObjectInputStream(fileInput);
        ArrayList<Student> students =  (ArrayList<Student>)objectInput.readObject();
        objectInput.close();
        return students;
    }

    public static void main(String[] args) throws IOException{
        ScoreScanner ss = new ScoreScanner();
        ArrayList<Student> students = ss.LoadData("src/data/2014级软件工程8班-面向对象程序设计.txt");
        System.out.println(students);
    }
}