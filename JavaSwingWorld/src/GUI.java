import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    private JPanel panel;
    private JTextField searchField;
    private JButton goButton;
    private JTable table1;
    private JTextField highestTextField;
    private JTextField lowestTextField1;
    private JTextField excellentTextField3;
    private JTextField goodTextField4;
    private JTextField mediumTextField5;
    private JTextField at_avgTextField6;
    private JTextField under_avgTextField7;
    private JTextArea fileInfoTextArea;
    private JLabel bighead;
    private JLabel searchLabel;
    private JScrollPane scoreTable;
    private JLabel analysisLabel;
    private JLabel highestLabel;
    private JLabel lowestLabel;
    private JLabel avgLabel;
    private JLabel excellentLabel;
    private JLabel goodLabel;
    private JPanel medium;
    private JLabel at_avgLabel;
    private JLabel under_avgLabel;
    private JTextField avgtextField;
    private JMenu topMenu = new JMenu("File");
    private JMenuBar topMenuBar = new JMenuBar();
    private JMenuItem topMenuItem = new JMenuItem("open...");

    public Object[][] playerinfo= {
                {
                        "wangpeng", 91, 100, 191, true
                },
                {
                        "zhuxuelian", 82, 69, 151, true
                },
        };

    public String[] Names = {"name", "chinese", "math", "total", "avg"};


    public GUI() {


        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}