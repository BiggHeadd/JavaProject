package frame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.concurrent.Flow;

public class MainFrame extends JFrame{
    public JMenuBar jMenuBar = new JMenuBar();
    public JMenu jMenu = new JMenu("File");
    public JMenuItem openTxtFile = new JMenuItem("openTxtFile");
    public JMenuItem openObjectFile = new JMenuItem("openObjectFile");
    public JMenuItem saveTxtFile = new JMenuItem("saveTxtFile");
    public JMenuItem saveObjectFile = new JMenuItem("saveObjectFile");
    public JPanel searchPanel = new JPanel();
    public JLabel title = new JLabel("Text Area");
    public JTextField textField = new JTextField();
    public JTable table;
//    public JScrollPane;


    public MainFrame(){
        init();
    }

    public void init(){
        setFrameargs();
        setJMenuBar(menu());
        getContentPane().add(searchPanel(), BorderLayout.NORTH);
        getContentPane().add(table(), BorderLayout.WEST);
        getContentPane().add(analysisPanel(), BorderLayout.CENTER);
        pack();
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
        return jMenuBar;
    }

    public JPanel searchPanel(){
        searchPanel.setLayout(new FlowLayout());
        textField.setColumns(12);
        searchPanel.add(title);
        searchPanel.add(textField);
        searchPanel.setVisible(true);
        return searchPanel;
    }

    public JPanel table(){
        JPanel wrap = new JPanel();
        wrap.setLayout(new BorderLayout());

        Object[][] playerinfo= {
                {
                    "wangpeng", 91, 100, 191, true
                },
                {
                    "zhuxuelian", 82, 69, 151, true
                },
        };

        String[] Names = {"name", "chinese", "math", "total", "avg"};

        JTable table = new JTable(playerinfo, Names);
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
        JScrollPane scrollPane = new JScrollPane(table);
        JLabel title = new JLabel("Scores Table");
        scrollPane.add(title);
        wrap.add(scrollPane);
        return wrap;
    }

    public JPanel analysisPanel(){
        JPanel analysis = new JPanel();
        analysis.setLayout(new GridLayout(0,1));
        JLabel title = new JLabel("Data Analysis");

        JPanel score = new JPanel();
        score.add(title);
        score.setLayout(new GridLayout(0,1));
        score.add(analysisItem("top"));
        score.add(analysisItem("lowest"));
        score.add(analysisItem("avg"));
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

    public JPanel analysisItem(String name){
        JPanel item = new JPanel();
        JLabel top = new JLabel(name + ": ");
        JTextField itemText = new JTextField("0.0", 12);
        item.setLayout(new FlowLayout());
        item.add(top, BorderLayout.WEST);
        item.add(itemText, BorderLayout.WEST);
        return item;
    }

    public JPanel analysisSort(String name){
        JPanel item = new JPanel();
        JLabel level = new JLabel(name + ": ");
        JTextField itemText = new JTextField("0.0", 12);
        JLabel percentageLabel = new JLabel("percentage: ");
        JTextField percentage = new JTextField("0.0",6);
        item.setLayout(new FlowLayout());
        item.add(level, BorderLayout.WEST);
        item.add(itemText, BorderLayout.WEST);
        item.add(percentageLabel, BorderLayout.WEST);
        item.add(percentage, BorderLayout.WEST);
        return item;
    }

    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
    }
}