package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class analysisListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonName = e.getActionCommand();
        System.out.println(buttonName);
        if(buttonName.equals("search")){

        }
    }
}
