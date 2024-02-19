package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import view.LoginFrame;

/**
 * @author Martin Ramonda
 */
public class LoginListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton b = (JButton)e.getSource();
        LoginFrame myFrame = (LoginFrame)b.getParent().getParent();
        if(myFrame.getuNameField().getText().isBlank() || myFrame.getPassField().getPassword().length == 0){
            JOptionPane.showMessageDialog(myFrame, "Fill the form to continue", "Empty Form", JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            
        }
    }
    
}