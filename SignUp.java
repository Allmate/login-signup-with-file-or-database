package Java_Pack;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.AbstractAction;

public class SignUp{
	
	private JFrame f;
	
	private JLabel nameLbl;
	private JTextField nameTextField;
	
	private JLabel passwordLbl;
	private JTextField passwordTextField;
	
	private JButton addBtn;
	private JButton clearBtn;
	
	private JButton fileBtn;
		
	public SignUp() {
		f = new JFrame("SignUp your account");
		f.setSize(new Dimension(300,120));
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setResizable(false);
		
		createComponent();
		layoutComponent();
	}
	
	public void signUpFrameVisible(boolean b) {
		f.setVisible(b);
	}
	
	private void createComponent() {
		
		nameLbl = new JLabel("Name");
		nameTextField = new JTextField(20);
		
		passwordLbl = new JLabel("Password");
		passwordTextField = new JTextField(20);	
		
		addBtn = new JButton();
		clearBtn = new JButton("Clear");
		
		fileBtn = new JButton("Add to file");
		
		addBtnAction action = new addBtnAction();
		action.putValue(Action.NAME, "Add");
		
		addBtn.setAction(action);
		
		fileBtn.addActionListener(action);		
		
		clearBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			 nameTextField.setText(null);
			 passwordTextField.setText(null);
			}
		});
		
	}
	
	private void layoutComponent() {
		SignIn signInobj = new SignIn();
		JPanel panel = signInobj.createLblAndTextField(nameLbl, nameTextField, passwordLbl, passwordTextField);
		f.add(panel,BorderLayout.CENTER);
		
		JPanel buttonPanel = signInobj.createButtonPanel(fileBtn,addBtn, clearBtn);
		f.add(buttonPanel,BorderLayout.SOUTH);
		}
	
	private class addBtnAction extends AbstractAction{

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getActionCommand().equals("Add")) {
			try {
				createStorage createStorageObj = addUserAndPassword();
				createStorageObj.createDB();
				createStorageObj.insertDB();
				f.setVisible(false);
			} catch (SQLException e1) {
				e1.printStackTrace();
				}
			}//Add to DataBase
			
			if(e.getActionCommand().equals("Add to file")) {
				createStorage createStorageObj = addUserAndPassword();
				try {

					createStorageObj.createFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}	
		}
	}
	
	private createStorage addUserAndPassword() {
		createStorage createStorageObj = new createStorage();
		createStorageObj.setUserName(nameTextField.getText());
		createStorageObj.setPassword(passwordTextField.getText());
		
		return createStorageObj;
	}
		
}
