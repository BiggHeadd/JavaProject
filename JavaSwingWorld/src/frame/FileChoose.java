package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FileChoose extends JFrame implements ActionListener {
    private JFileChooser fileDialog;
    private JMenuBar menubar;
    private JMenuItem itemSave, itemOpen;
    private JMenu menu;
    private JTextArea text;
    private FileReader fileReader;
    private BufferedReader in;
    private BufferedReader out;
    private FileWriter fileWriter;

    public FileChoose(){
        init();
        setSize(300, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        text = new JTextArea(10, 10);
        text.setFont(new Font("楷体_gb2312", Font.PLAIN, 28));
        add(new JScrollPane(text), BorderLayout.CENTER);
        menubar = new JMenuBar();
        menu = new JMenu("file");
        itemSave = new JMenuItem("Save File");
        itemOpen = new JMenuItem("Open File");
        itemSave.addActionListener(this);
        itemOpen.addActionListener(this);
        menu.add(itemSave);
        menu.add(itemOpen);
        menubar.add(menu);
        setJMenuBar(menubar);
        fileDialog = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == itemSave){
            int state = fileDialog.showSaveDialog(this);
            if(state == JFileChooser.APPROVE_OPTION){
                try{
                    File dir = fileDialog.getCurrentDirectory();
                    String name = fileDialog.getSelectedFile().getAbsolutePath();
                    System.out.println(name);
                }finally {
                    System.out.println("yes");
                }
            }
        }
    }

    public static void main(String[] args){
        FileChoose win = new FileChoose();
        win.setTitle("FILE");
    }
}
