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

    public void Save(ArrayList<Student> students, String className)throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream("src/data/"+className+".score");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(students);
        objectOut.close();
        fileOut.close();
    }

    public static void main(String[] args){
        try{
            ScoreScanner ss = new ScoreScanner();
            ArrayList<Student> students = ss.LoadDataBytes("2014级软件工程8班-面向对象程序设计");
            util.printScores(students, "2014级软件工程8班-面向对象程序设计");
        }catch(IOException e){
            e.printStackTrace();
        }catch(ClassNotFoundException f){
            f.printStackTrace();
        }
    }
}
