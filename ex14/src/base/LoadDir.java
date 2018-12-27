package base;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadDir {
    private String path;
    private int depth = 0;
    private int java = 0;
    private int lines = 0;
    private int emptyline = 0;
    private long bytes = 0;
    private boolean flag = true;

    public ArrayList<OneFile> getFilesResult() {
        return filesResult;
    }

    public void setFilesResult(ArrayList<OneFile> filesResult) {
        this.filesResult = filesResult;
    }

    private FileAccept fileAccept = new FileAccept();
    private ArrayList<OneFile> filesResult = new ArrayList<OneFile>();

    public LoadDir(){
        fileAccept.setExtendName("java");
    }

    public LoadDir(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void loading(String pathName) throws IOException{
        depth++;
        File file = new File(pathName);
        if(file.exists()){
            File[] files = file.listFiles();
            if(null == files || files.length == 0){
//                System.out.println("empty folder");
                depth = 1;
                return;
            }else{
                for(File file2: files){
                    if(file2.isDirectory()){
                        OneFile oneFile = new OneFile(file2.getAbsolutePath(), false, depth);
                        filesResult.add(oneFile);
//                        System.out.println("folder:" + file2.getName());
                        loading(file2.getAbsolutePath());
                    }else{
                        if(file2.getName().contains(".java")) {
                            OneFile oneFile = new OneFile(file2.getAbsolutePath(), true, depth);
                            filesResult.add(oneFile);
                            java++;
                            setParam(file2.getAbsolutePath());
                        }
//                        System.out.println("file:"+file2.getName());
                    }
                }
            }
            depth = 1;
        }else{
            flag = false;
            System.out.println(pathName+"不是合法的目录名称！");
        }

    }

    public void setParam(String fileName) throws IOException {
        File file = new File(fileName);
        Scanner input = new Scanner(file);
        while(input.hasNext()){
            if(input.nextLine().isEmpty()){
                emptyline++;
            }
            lines++;
        }
        bytes+=file.length();
    }

    public void printDetail(){
        if(flag){
            System.out.println("["+this.path+"]"+"目录分析结果:");
            System.out.printf("%-30s%20d\n","源程序文件个数:",java);
            System.out.printf("%-30s%20d\n","源程序文件行数:",lines);
            System.out.printf("%-30s%20d\n","其中含有空行数:",emptyline);
            System.out.printf("%-30s%20d\n","包含总的字节数:",bytes);
            System.out.println("详细信息如下:");
            System.out.println("+"+this.path.split("/")[this.path.split("/").length-1]);
        }
    }

    public static void main(String[] args) throws IOException{
        LoadDir loadDir = new LoadDir(".");
        loadDir.loading(".");
        ArrayList<OneFile> files = loadDir.getFilesResult();
        PrintDir printDir = new PrintDir(files);
        loadDir.printDetail();
        printDir.printing();
    }
}