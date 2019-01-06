package frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import background.ScoreScanner;
import background.Student;
import background.util;
import listener.analysisListener;

public class MainFrame extends JFrame{
    public JMenuBar jMenuBar = new JMenuBar();
    public JMenu jMenu = new JMenu("File");
    public JButton openTxtFile = new JButton("openTxtFile");
    public JButton openObjectFile = new JButton("openObjectFile");
    public JButton saveTxtFile = new JButton("saveTxtFile");
    public JButton saveObjectFile = new JButton("saveObjectFile");
    public JPanel searchPanel = new JPanel();
    public JLabel title = new JLabel("Text Area");
    public JTextField textField = new JTextField();
    public JButton searchButton = new JButton("search");
    public JLabel top;
    public JTextField topText;
    public JLabel low;
    public JTextField lowestText;
    public JLabel avg;
    public JTextField avgText;
    public JTable table;
    public JScrollPane scrollPane;


    public MainFrame(){
        init();
    }

    public void init(){
        setFrameargs();
        setJMenuBar(menu());
        add(searchPanel(), BorderLayout.NORTH);
        add(table(), BorderLayout.WEST);
        add(analysisPanel(), BorderLayout.CENTER);
        add(bottomInfo(), BorderLayout.SOUTH);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("search")){
                    String studentName = textField.getText();
                    Object[][] playerinfo_change= {
                            {
                                    "wangpeng", 91, 100, 11111, true
                            },
                            {
                                    "zhuxuelian", 82, 69, 151, true
                            },
                    };
                    String[] Names = {"name", "chinese", "math", "total", "avg"};
                    table = new JTable(playerinfo_change, Names);
                    scrollPane = new JScrollPane(table);
                }
            }
        });

        openObjectFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("openObjectFile")){
                    ScoreScanner ss = new ScoreScanner();
                    try{
                        ss.LoadDataBytes("2014级软件工程8班-面向对象程序设计");
                        ArrayList<Student> students = ss.LoadDataBytes("2014级软件工程8班-面向对象程序设计");
                        String target = util.printScores(students, "2014级软件工程8班-面向对象程序设计");
                        String top, min, avg;
                        System.out.print(target);
                        top = target.split("-")[0];
                        min = target.split("-")[1];
                        avg = target.split("-")[2];
                        topText.setText(top);
                        lowestText.setText(min);
                        avgText.setText(avg);
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }catch(ClassNotFoundException cnfe){
                        cnfe.printStackTrace();
                    }catch(NullPointerException npe){
                        npe.printStackTrace();
                    }
                }
            }
        });

        pack();
        repaint();
    }

    public void setFrameargs(){
        setTitle("Test extends JFrame");
        setBounds(400,600,500,800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(2);
        setVisible(true);
    }

    public JMenuBar menu(){
        jMenuBar.add(jMenu);
        jMenu.add(openTxtFile);
        jMenu.add(saveTxtFile);
        jMenu.add(saveTxtFile);
        jMenu.add(saveObjectFile);
        jMenu.add(openObjectFile);
        return jMenuBar;
    }

    public JPanel searchPanel(){
        searchPanel.setLayout(new FlowLayout());
        textField.setColumns(12);
        searchPanel.add(title);
        searchPanel.add(textField);
        searchPanel.add(searchButton);
        searchPanel.setVisible(true);
        searchPanel.setBounds(400, 600, 100, 300);

        return searchPanel;
    }

    public JPanel table(){
        JPanel wrap = new JPanel();
//        wrap.setLayout(new BorderLayout());
//
//        Object[][] playerinfo= {
//                {
//                    "wangpeng", 91, 100, 191, true
//                },
//                {
//                    "zhuxuelian", 82, 69, 151, true
//                },
//        };
//
//
//        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
//        scrollPane = new JScrollPane(table);
//        JLabel title = new JLabel("Scores Table");
//        scrollPane.add(title);
//        wrap.add(scrollPane);
        try{
            ScoreScanner ss = new ScoreScanner();
            ArrayList<Student> students = ss.LoadDataBytes("2014级软件工程8班-面向对象程序设计");
            students = util.sortStudents(students);
            Object[][] studentInfo = new Object[30][];
            int index = 0;
            for(Student student: students){
                studentInfo[index++] = new Object[]{student.toString().split(",")[0], student.toString().split(",")[1], student.toString().split(",")[2]};
            }
            String[] Names = {"id", "name", "score"};
            JTable table = new JTable(studentInfo, Names);
            scrollPane = new JScrollPane(table);
            wrap.add(scrollPane);
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return wrap;
    }

    public JPanel analysisPanel(){
        JPanel analysis = new JPanel();
        analysis.setLayout(new GridLayout(0,1));
        JLabel title = new JLabel("Data Analysis");

        JPanel score = new JPanel();
        score.add(title);
        score.setLayout(new GridLayout(0,1));

        JPanel itemTop = new JPanel();
        top = new JLabel("top: ");
        topText = new JTextField("0.0", 12);
        topText.setEditable(false);
        itemTop.setLayout(new FlowLayout());
        itemTop.add(top, BorderLayout.WEST);
        itemTop.add(topText, BorderLayout.WEST);
        score.add(itemTop);//top score

        JPanel itemLowest = new JPanel();
        low = new JLabel("lowest: ");
        lowestText = new JTextField("0.0",12);
        lowestText.setEditable(false);
        itemLowest.setLayout(new FlowLayout());
        itemLowest.add(low, BorderLayout.WEST);
        itemLowest.add(lowestText, BorderLayout.WEST);
        score.add(itemLowest);//lowest score

        JPanel itemAvg = new JPanel();
        avg = new JLabel("Avg");
        avgText = new JTextField("0.0",12);
        avgText.setEditable(false);
        itemAvg.setLayout(new FlowLayout());
        itemAvg.add(avg, BorderLayout.WEST);
        itemAvg.add(avgText, BorderLayout.WEST);
        score.add(itemAvg);//avg score
        analysis.add(score);

        JPanel percentage = new JPanel();
        percentage.setLayout(new GridLayout(5, 0));
        percentage.add(analysisSort("excellent"));
        percentage.add(analysisSort("good"));
        percentage.add(analysisSort("medium"));
        percentage.add(analysisSort("at avg"));
        percentage.add(analysisSort("under avg"));
        analysis.add(percentage);
        return analysis;
    }

    public JPanel analysisSort(String name){
        JPanel item = new JPanel();
        JLabel level = new JLabel(name + ": ");
        JTextField itemText = new JTextField("0.0", 12);
        itemText.setEditable(false);
        JLabel percentageLabel = new JLabel("percentage: ");
        JTextField percentage = new JTextField("0.0",6);
        percentage.setEditable(false);
        item.setLayout(new FlowLayout());
        item.add(level, BorderLayout.WEST);
        item.add(itemText, BorderLayout.WEST);
        item.add(percentageLabel, BorderLayout.WEST);
        item.add(percentage, BorderLayout.WEST);
        return item;
    }

    public JPanel bottomInfo(){
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JLabel info = new JLabel("E://");
        bottomPanel.add(info, BorderLayout.WEST);
        return bottomPanel;
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
    }
}