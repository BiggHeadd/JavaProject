package frame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import background.ScoreSaver;
import background.ScoreScanner;
import background.Student;
import background.util;
import listener.analysisListener;

public class MainFrame extends JFrame implements ActionListener{
    private JMenuBar jMenuBar = new JMenuBar();
    private JMenu jMenu = new JMenu("File");
    private JMenuItem openTxtFile = new JMenuItem("openTxtFile");
    private JMenuItem openObjectFile = new JMenuItem("openObjectFile");
    private JMenuItem saveObjectFile = new JMenuItem("saveObjectFile");
    private JPanel searchPanel = new JPanel();
    private JLabel title = new JLabel("Search Area");
    private JTextField searchTextField = new JTextField();
    private JButton searchButton = new JButton("search");
    private JLabel top;
    private JTextField topText;
    private JLabel low;
    private JTextField lowestText;
    private JLabel avg;
    private JTextField avgText;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField excellentText;
    private JTextField goodText;
    private JTextField mediumPercentageTextField;
    private JTextField goodPercentageTextField;
    private JTextField excellentPercentageTextField;
    private JTextField mediumText;
    private JTextField at_avgText;
    private JTextField at_avgPercentageTextField;
    private JTextField under_avgText;
    private JTextField under_avgPercentageTextField;
    private JFileChooser fileDialog;
    private JLabel info;
    private ArrayList<Student> students = null;
    private JButton resetButton = new JButton("reset");
    private String absolutePath;

    public MainFrame(){
        init();
    }

    private void init(){
        setFrameargs();
        setJMenuBar(menu());
        add(searchPanel(), BorderLayout.NORTH);
        add(table(), BorderLayout.WEST);
        add(analysisPanel(), BorderLayout.CENTER);
        add(bottomInfo(), BorderLayout.SOUTH);
        fileDialog = new JFileChooser();
        openTxtFile.addActionListener(this);
        openObjectFile.addActionListener(this);
        searchButton.addActionListener(this);
        resetButton.addActionListener(this);
        saveObjectFile.addActionListener(this);
        pack();
        repaint();
    }

    private void setFrameargs(){
        setTitle("Student Scores System");
        setLocation(500,600);
        setSize(10,800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(2);
        setVisible(true);
    }

    private JMenuBar menu(){
        jMenuBar.add(jMenu);
        jMenu.add(openTxtFile);
        jMenu.add(saveObjectFile);
        jMenu.add(openObjectFile);
        return jMenuBar;
    }

    private JPanel searchPanel(){
        searchPanel.setLayout(new FlowLayout());
        searchTextField.setColumns(12);
        searchPanel.add(title);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);
        searchPanel.setVisible(true);
        searchPanel.setBounds(400, 600, 100, 300);

        return searchPanel;
    }

    private JPanel table(){
        JPanel wrap = new JPanel();
//        wrap.setLayout(new BorderLayout());
//
        Object[][] playerinfo= {
                {
                    "IDSLOT", "NAMESLOT", "SCORESLOT"
                }
        };
//
//
//        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
//        scrollPane = new JScrollPane(table);
//        JLabel title = new JLabel("Scores Table");
//        scrollPane.add(title);
//        wrap.add(scrollPane);
        try{
            ScoreScanner ss = new ScoreScanner();
            ArrayList<Student> students = ss.LoadDataBytes("/home/bighead/IdeaProjects/JavaProject/JavaSwingWorld/src/data/2014级软件工程8班-面向对象程序设计.score");
            students = util.sortStudents(students);
            Object[][] studentInfo = new Object[30][];
//            int index = 0;
//            for(Student student: students){
//                studentInfo[index++] = new Object[]{student.toString().split(",")[0], student.toString().split(",")[1], student.toString().split(",")[2]};
//            }
            String[] Names = {"id", "name", "score"};
            table = new JTable(playerinfo, Names);
            scrollPane = new JScrollPane(table);
            wrap.add(scrollPane);
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        return wrap;
    }

    private JPanel analysisPanel(){
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
        percentage.setLayout(new GridLayout(5, 10));

        JPanel item = new JPanel(new GridLayout(1, 2));
        JLabel excellentLevel = new JLabel("fine:");
        excellentText = new JTextField("0.0000", 12);
        excellentText.setEditable(false);
        JLabel excellentPercentageLabel = new JLabel("percentage:");
        excellentPercentageTextField = new JTextField("0.00000",6);
        excellentPercentageTextField.setEditable(false);
        item.setLayout(new FlowLayout());
        percentage.add(excellentLevel, BorderLayout.WEST);
        percentage.add(excellentText, BorderLayout.WEST);
        percentage.add(excellentPercentageLabel, BorderLayout.WEST);
        percentage.add(excellentPercentageTextField, BorderLayout.WEST);
        //percentage.add(item);//excellent

        item = new JPanel();
        JLabel goodLevel = new JLabel("good:");
        goodText = new JTextField("0.0000", 12);
        goodText.setEditable(false);
        JLabel goodPercentageLabel = new JLabel("percentage:");
        goodPercentageTextField = new JTextField("0.0000",6);
        goodPercentageTextField.setEditable(false);
        item.setLayout(new FlowLayout());
        percentage.add(goodLevel, BorderLayout.WEST);
        percentage.add(goodText, BorderLayout.WEST);
        percentage.add(goodPercentageLabel, BorderLayout.WEST);
        percentage.add(goodPercentageTextField, BorderLayout.WEST);
        //percentage.add(item);//good

        item = new JPanel();
        JLabel mediumLevel = new JLabel("mid:");
        mediumText = new JTextField("0.0000", 12);
        mediumText.setEditable(false);
        JLabel mediumPercentageLabel = new JLabel("percentage:");
        mediumPercentageTextField = new JTextField("0.0000",6);
        mediumPercentageTextField.setEditable(false);
        item.setLayout(new FlowLayout());
        percentage.add(mediumLevel, BorderLayout.WEST);
        percentage.add(mediumText, BorderLayout.WEST);
        percentage.add(mediumPercentageLabel, BorderLayout.WEST);
        percentage.add(mediumPercentageTextField, BorderLayout.WEST);
        //percentage.add(item);//medium

        item = new JPanel();
        JLabel at_avgLevel = new JLabel("pass:");
        at_avgText = new JTextField("0.0000", 12);
        at_avgText.setEditable(false);
        JLabel at_avgPercentageLabel = new JLabel("percentage:");
        at_avgPercentageTextField = new JTextField("0.0000",6);
        at_avgPercentageTextField.setEditable(false);
        item.setLayout(new FlowLayout());
        percentage.add(at_avgLevel, BorderLayout.WEST);
        percentage.add(at_avgText, BorderLayout.WEST);
        percentage.add(at_avgPercentageLabel, BorderLayout.WEST);
        percentage.add(at_avgPercentageTextField, BorderLayout.WEST);
        //percentage.add(item);//at avg

        item = new JPanel();
        JLabel under_avgLevel = new JLabel("fail:");
        under_avgText = new JTextField("0.0000", 12);
        under_avgText.setEditable(false);
        JLabel under_avgPercentageLabel = new JLabel("percentage:");
        under_avgPercentageTextField = new JTextField("0.0000",6);
        under_avgPercentageTextField.setEditable(false);
        item.setLayout(new FlowLayout());
        percentage.add(under_avgLevel, BorderLayout.WEST);
        percentage.add(under_avgText, BorderLayout.WEST);
        percentage.add(under_avgPercentageLabel, BorderLayout.WEST);
        percentage.add(under_avgPercentageTextField, BorderLayout.WEST);
        //percentage.add(item);//at avg

        analysis.add(percentage);
        analysis.setSize(100,1000);
        return analysis;
    }

    private JPanel bottomInfo(){
        JPanel bottomPanel = new JPanel(new FlowLayout());
        info = new JLabel("FILEABSOLUTEPATH");
        bottomPanel.add(info, BorderLayout.WEST);
        return bottomPanel;
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openTxtFile){
            int state = fileDialog.showOpenDialog(this);
            if(state == JFileChooser.APPROVE_OPTION){
                String absolutePath = fileDialog.getSelectedFile().getAbsolutePath();
                this.absolutePath = absolutePath;
                if(absolutePath.contains(".txt")){
                    //open and add to Jtable
                    ScoreScanner ss = new ScoreScanner();
                    try{
                        ArrayList<Student> students = ss.LoadData(absolutePath);
                        this.students = students;
                        String target = util.printScores(students, absolutePath.split("/")[absolutePath.split("/").length-1]);
                        String top, min, avg;
                        String exc, good, medium, at_avg, under_avg;
                        String excPecentage, goodPecentage, mediumPecentage, at_avgPecentage, under_avgPecentage;
                        System.out.print(target);
                        String[] targetSplit = target.split("-");
                        top = targetSplit[0];
                        min = targetSplit[1];
                        avg = targetSplit[2];
                        exc = targetSplit[3];
                        excPecentage = targetSplit[4];
                        good = targetSplit[5];
                        goodPecentage = targetSplit[6];
                        medium = targetSplit[7];
                        mediumPecentage = targetSplit[8];
                        at_avg = targetSplit[9];
                        at_avgPecentage = targetSplit[10];
                        under_avg = targetSplit[11];
                        under_avgPecentage = targetSplit[12];
                        topText.setText(top);
                        lowestText.setText(min);
                        avgText.setText(avg);
                        excellentText.setText(exc);
                        excellentPercentageTextField.setText(excPecentage+"%");
                        goodText.setText(good);
                        goodPercentageTextField.setText(goodPecentage+"%");
                        mediumText.setText(medium);
                        mediumPercentageTextField.setText(mediumPecentage+"%");
                        at_avgText.setText(at_avg);
                        at_avgPercentageTextField.setText(at_avgPecentage+"%");
                        under_avgText.setText(under_avg);
                        under_avgPercentageTextField.setText(under_avgPecentage+"%");

                        class myTableModel extends DefaultTableModel{
                            private static final long servialVersionUID = 1L;
                            Object[][] datas;
                            ArrayList<Student> students;
                            String[] colums = {"id", "name", "score"};

                            myTableModel(ArrayList<Student> students, int studentNumber){
                                datas = new Object[studentNumber][];
                                this.students = students;
                                setparams();
                                this.setDataVector(datas, colums);
                            }
                            int index = 0;
                            public void setparams(){
                                for(Student student: this.students){
                                    datas[index++] = new Object[]{student.toString().split(",")[0], student.toString().split(",")[1], student.toString().split(",")[2]};
                                }
                            }
                        }

                        myTableModel model = new myTableModel(students, students.size());
                        table.setModel(model);
                        table.setAutoCreateRowSorter(true);
                        info.setText(absolutePath);
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "NOT A TXT FILE!", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else if(e.getSource() == openObjectFile){
            int state = fileDialog.showOpenDialog(this);
            if(state == JFileChooser.APPROVE_OPTION){
                String absolutePath = fileDialog.getSelectedFile().getAbsolutePath();
                this.absolutePath = absolutePath;
                if(absolutePath.contains(".score")){
                    ScoreScanner ss = new ScoreScanner();
                    try{
                        ArrayList<Student> students = ss.LoadDataBytes(absolutePath);
                        this.students = students;
                        String target = util.printScores(students, absolutePath.split("/")[absolutePath.split("/").length-1]);
                        String top, min, avg;
                        String exc, good, medium, at_avg, under_avg;
                        String excPecentage, goodPecentage, mediumPecentage, at_avgPecentage, under_avgPecentage;
                        System.out.print(target);
                        String[] targetSplit = target.split("-");
                        top = targetSplit[0];
                        min = targetSplit[1];
                        avg = targetSplit[2];
                        exc = targetSplit[3];
                        excPecentage = targetSplit[4];
                        good = targetSplit[5];
                        goodPecentage = targetSplit[6];
                        medium = targetSplit[7];
                        mediumPecentage = targetSplit[8];
                        at_avg = targetSplit[9];
                        at_avgPecentage = targetSplit[10];
                        under_avg = targetSplit[11];
                        under_avgPecentage = targetSplit[12];
                        topText.setText(top);
                        lowestText.setText(min);
                        avgText.setText(avg);
                        excellentText.setText(exc);
                        excellentPercentageTextField.setText(excPecentage+"%");
                        goodText.setText(good);
                        goodPercentageTextField.setText(goodPecentage+"%");
                        mediumText.setText(medium);
                        mediumPercentageTextField.setText(mediumPecentage+"%");
                        at_avgText.setText(at_avg);
                        at_avgPercentageTextField.setText(at_avgPecentage+"%");
                        under_avgText.setText(under_avg);
                        under_avgPercentageTextField.setText(under_avgPecentage+"%");

                        class myTableModel extends DefaultTableModel{
                            private static final long servialVersionUID = 1L;
                            Object[][] datas;
                            ArrayList<Student> students;
                            String[] colums = {"id", "name", "score"};

                            myTableModel(ArrayList<Student> students, int studengNumber){
                                datas = new Object[studengNumber][];
                                this.students = students;
                                setparams();
                                this.setDataVector(datas, colums);
                            }
                            int index = 0;
                            public void setparams(){
                                for(Student student: this.students){
                                    datas[index++] = new Object[]{student.toString().split(",")[0], student.toString().split(",")[1], student.toString().split(",")[2]};
                                }
                            }
                        }

                        myTableModel model = new myTableModel(students, students.size());
                        table.setModel(model);
                        table.setAutoCreateRowSorter(true);
                        info.setText(absolutePath);
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }catch(ClassNotFoundException cnfe){
                        cnfe.printStackTrace();
                    }catch(NullPointerException npe){
                        npe.printStackTrace();
                    }
                }else{
                    System.out.println("not score");
                    JOptionPane.showMessageDialog(this, "NOT A BINARY FILE!", "warning", JOptionPane.WARNING_MESSAGE);
                }
            }
        }else if(e.getSource() == searchButton){
            System.out.println("yes");
            String searchTarget = searchTextField.getText();
            if(this.students == null){
                JOptionPane.showMessageDialog(this, "you hava to load the file first", "warning", JOptionPane.WARNING_MESSAGE);
            }else if(searchTarget.isEmpty()){
                JOptionPane.showMessageDialog(this, "you have to type something!", "warning", JOptionPane.WARNING_MESSAGE);
            }else{
                ArrayList<Student> searchResult = util.searchInfo(this.students, searchTarget);
                class tableModel extends DefaultTableModel{
                    private static final long servialVersionUID = 1L;
                    Object[][] datas;
                    ArrayList<Student> searchStudents;
                    String[] column = {"id", "name", "score"};
                    int index = 0;

                    tableModel(ArrayList<Student> searchStudents){
                        datas = new Object[searchResult.size()][];
                        this.searchStudents = searchStudents;
                        this.setparam();
                        this.setDataVector(datas, column);
                    }

                    private void setparam(){
                        for(int i=0; i<searchStudents.size(); i++){
                            datas[i] = new Object[]{searchStudents.get(i).toString().split(",")[0], searchStudents.get(i).toString().split(",")[1], searchStudents.get(i).toString().split(",")[2]};
                        }
                    }
                }
                if(searchResult.get(0).getId().equals("fail")){
                    JOptionPane.showMessageDialog(this, "no target!", "warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    tableModel myModel = new tableModel(searchResult);
                    table.setModel(myModel);
                }
            }
        }else if(e.getSource() == resetButton){
            class tableModel extends DefaultTableModel{
                private static final long servialVersionID = 1L;
                Object[][] datas;
                String[] colums = {"id", "name", "score"};
                int index = 0;

                tableModel(){
                    datas = new Object[students.size()][];
                    this.setparams();
                    this.setDataVector(datas, colums);
                }

                private void setparams(){
                    for(Student student: students){
                        datas[index++] = new Object[]{student.toString().split(",")[0], student.toString().split(",")[1], student.toString().split(",")[2]};
                    }
                }
            }

            if(this.students == null){
                JOptionPane.showMessageDialog(this, "no data!", "warning", JOptionPane.WARNING_MESSAGE);
            }else{
                tableModel myModel = new tableModel();
                table.setModel(myModel);
            }
        }else if(e.getSource() == saveObjectFile){
            int state = fileDialog.showSaveDialog(this);
            if(state==JFileChooser.APPROVE_OPTION){
                if(this.students == null){
                    JOptionPane.showMessageDialog(this, "no data!", "warning", JOptionPane.WARNING_MESSAGE);
                }else if(this.absolutePath.contains(".score")){
                    JOptionPane.showMessageDialog(this, "Data is binary already!\nDo no need to Save!", "warning", JOptionPane.WARNING_MESSAGE);
                }else{
                    String absolutePath = fileDialog.getSelectedFile().getAbsolutePath();
                    ScoreSaver ss = new ScoreSaver();
                    try{
                        if(absolutePath.contains(".score")){
                            ss.Save(this.students, absolutePath);
                            JOptionPane.showMessageDialog(this, "Save successfully!", "message",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(this, "Binary file name should be end with \".score\"");
                        }
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        }
    }
}