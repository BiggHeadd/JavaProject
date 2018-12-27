package base;

import java.util.ArrayList;

public class PrintDir {
    ArrayList<OneFile> files = new ArrayList<OneFile>();

    public PrintDir(ArrayList<OneFile> files){
        this.files = files;
    }

    public void printing(){
        for(OneFile file: files){
            System.out.println(file);
        }
    }
}
