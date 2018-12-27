package main;

import base.LoadDir;
import base.OneFile;
import base.PrintDir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入要统计的目录名:");
        String path = input.nextLine();
        LoadDir loadDir = new LoadDir(path);
        loadDir.loading(path);
        ArrayList<OneFile> files = loadDir.getFilesResult();
        PrintDir printDir = new PrintDir(files);
        loadDir.printDetail();
        printDir.printing();
    }
}
