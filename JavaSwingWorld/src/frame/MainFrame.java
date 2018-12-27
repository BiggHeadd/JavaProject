package frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{
    public JMenuBar jMenuBar = new JMenuBar();
    public JMenu jMenu = new JMenu("File");
    public JMenuItem openTxtFile = new JMenuItem("openTxtFile");
    public JMenuItem openObjectFile = new JMenuItem("openObjectFile");
    public JMenuItem saveTxtFile = new JMenuItem("saveTxtFile");
    public JMenuItem saveObjectFile = new JMenuItem("saveObjectFile");
    public JPanel jPanel = new JPanel();
    public JLabel title = new JLabel("Text Area");
    public JTextField textField = new JTextField();


    public MainFrame(){
        init();
    }

    public void init(){
        setFrameargs();
        setMenu();
        setSearchPanel();
    }

    public void setFrameargs(){
        setTitle("Test extends JFrame");
        setBounds(200,300,400,500);
        setLayout(new GridLayout());
        setDefaultCloseOperation(2);
        setVisible(true);
    }

    public void setMenu(){
        jMenuBar.add(jMenu);
        jMenu.add(openTxtFile);
        jMenu.add(saveTxtFile);
        jMenu.add(saveTxtFile);
        jMenu.add(saveObjectFile);
        setJMenuBar(jMenuBar);
    }

    public void setSearchPanel(){
        jPanel.setLayout(new FlowLayout());
        textField.setColumns(12);
        jPanel.add(title);
        jPanel.add(textField);
        jPanel.setVisible(true);
        add(jPanel);
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
    }
}