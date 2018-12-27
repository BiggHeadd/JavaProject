package base;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class OneFile {
    private String name;
    private boolean flag;
    private int depth;
    private int total;
    private int blank;
    private long bytes;

    public OneFile(){

    }

    public OneFile(String name, boolean flag, int depth){
        this.name = name;
        this.flag = flag;
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public String toString(){
        String tmp = "";
        String returnString = "";
        for(int i=0; i<depth; i++){
            tmp = tmp.concat("    ");
        }
        try{
            returnString = tmp.concat(getLastName(name));
//            returnString = String.format("%-20s%-30s",tmp,getLastName(name));
        }catch(IOException e){
            System.out.print("");
        }
        return returnString;
    }

    public String getLastName(String name)throws IOException{
        String[] names = name.split("/");
        String nameAdd = "";
        if(flag){
            nameAdd = nameAdd.concat("-");
            nameAdd = nameAdd.concat(names[names.length-1]);
            nameAdd = String.format("%-30s%-40s",nameAdd,getDetail(name));
//            nameAdd = nameAdd.concat(getDetail(name));
        }else{
            nameAdd = nameAdd.concat("+");
            nameAdd = nameAdd.concat(names[names.length-1]);
        }
        return nameAdd;
    }

    public String getDetail(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        bytes = file.length();
        while(input.hasNext()){
            if(input.nextLine().isEmpty()){
                blank++;
            }
            total++;
        }
        String returnString = String.format("Total:%5d, Blank:%5d, %15d Bytes", total, blank, bytes);
        return returnString;
    }
}
