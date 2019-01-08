package background;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScoreSaver {

    public ScoreSaver(){

    }

    public void Save(ArrayList<Student> students, String classNamePath)throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(classNamePath);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(students);
        objectOut.close();
        fileOut.close();
    }



    public static void main(String[] args){
        try{
            ScoreScanner ss = new ScoreScanner();
//            ArrayList<Student> student = ss.LoadData("src/data/2014级软件工程8班-面向对象程序设计.txt");
//            ScoreSaver saver = new ScoreSaver();
//            saver.Save(student, "2014级软件工程8班-面向对象程序设计");
            ArrayList<Student> students = ss.LoadDataBytes("2014级软件工程8班-面向对象程序设计");
            students = util.sortStudents(students);
            util.printScores(students, "2014级软件工程8班-面向对象程序设计");
            util.searchStudent(students, "赵一");
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException f){
            f.printStackTrace();
        }
    }
}