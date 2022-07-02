package Java_Pack;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;

public class ActionClick extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	JPanel panel;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source =(JButton) e.getSource();
		
		if(source.getActionCommand().equals("Cancel"))
			System.exit(0);
			
		if(source.getActionCommand().equals("Sign up")) {
			SignUp obj = new SignUp();
			obj.signUpFrameVisible(true);
		}
	}
}
