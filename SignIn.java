package Java_Pack;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.JComponent;
import java.awt.FlowLayout;
import java.awt.ComponentOrientation;
import javax.swing.Box;
import java.awt.Dimension;
import static javax.swing.GroupLayout.Alignment.BASELINE;

public class SignIn {
	
	public SignIn() {
	}

	public JPanel createLblAndTextField(JComponent...args) {
		JPanel panel = new JPanel();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		
		gl.setHorizontalGroup( gl.createSequentialGroup()
				.addGroup( gl.createParallelGroup()
						.addComponent(args[0])
						.addComponent(args[2]) )
						
				.addGroup( gl.createParallelGroup()
						.addComponent(args[1],GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(args[3], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) )
				);
		
		gl.setVerticalGroup( gl.createSequentialGroup()
				.addGroup( gl.createParallelGroup(BASELINE)
						.addComponent(args[0])
						.addComponent(args[1]) )
				.addGroup( gl.createParallelGroup(BASELINE)
						.addComponent(args[2])
						.addComponent(args[3]) )
				);		
		return panel;
	}
	
	public JPanel createButtonPanel(JComponent...args) {
		JPanel panel = new JPanel();
		FlowLayout layout = new FlowLayout(FlowLayout.TRAILING);
		panel.setLayout(layout);
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		panel.add(args[0]); 
		panel.add(args[1]);
		panel.add(args[2]);
		
		return panel;
	}
	
	public Box createSignUpPanel(JComponent...args) {
		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createRigidArea(new Dimension(15,20)));
		hbox.add(args[0]);
		hbox.add(Box.createHorizontalGlue());
		hbox.add(args[1]);
		
		return hbox;
	}
}
