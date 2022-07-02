/**
 * Need feature to check User account name duplicate.
 */

package Java_Pack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.sql.SQLException;


public class Demo{
	
	private JFrame f;
	
	private JLabel nameLbl;
	private JTextField nameTextField;
	
	private JLabel passwordLbl;
	private JTextField passwordTextField;
	
	private JButton loginBtn;
	private JButton cancelBtn;
	private JButton signUpBtn;
	private JButton fileBtn;
	
	private JPanel panel;
		
	Icon loginIcon = new ImageIcon(new ImageIcon("src/Images/login.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
	private JLabel loginLbl;
	
	public Demo() {
	}
	
	private void initUI() {
		
		f = new JFrame("Connect our Organization");
		f.setSize(new Dimension(400,170));
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setVisible(!f.isVisible());
		f.setResizable(false);
		
		createComponent();
		createLayout();
		addActionListener();
	}
	
	private void createLayout() {
		SignIn signInObj = new SignIn();
		JPanel signInPanel = signInObj.createLblAndTextField(nameLbl, nameTextField, passwordLbl, passwordTextField);
		JPanel buttonPanel = signInObj.createButtonPanel(fileBtn,loginBtn, cancelBtn);
		Box signUpPanel = signInObj.createSignUpPanel(loginLbl, signUpBtn);
		
		f.add(signUpPanel, BorderLayout.PAGE_START);
		f.add(signInPanel,BorderLayout.CENTER);
		f.add(buttonPanel,BorderLayout.PAGE_END);
	}
	
	private void createComponent() {
		nameLbl = new JLabel("Name");
		nameLbl.setLabelFor(nameTextField);
		nameTextField = new JTextField(20);
		
		passwordLbl = new JLabel("Password");
		passwordLbl.setLabelFor(passwordTextField);
		passwordTextField = new JTextField(20);	
		
		loginBtn = new JButton("Login");
		cancelBtn = new JButton("Cancel");
		
		loginLbl = new JLabel("Login", loginIcon, JLabel.LEFT);
		signUpBtn = new JButton("Sign up");	
		fileBtn = new JButton("Login with file");
		
		fileBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createStorage obj = new createStorage();
				try {
					String name = nameTextField.getText();
					String password = passwordTextField.getText();
					
					obj.setUserName(name);
					obj.setPassword(password);
					
					Boolean b = obj.fileScanner();
					if(name.length() >= 2 && password.length() >= 2) 
						loginInformationDialog(b);
					else
						System.err.println("Enter your userName And Password.");
											
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
				
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				String password = passwordTextField.getText();
				
				createStorage createStorageobj = new createStorage();
				try {
					createStorageobj.createDB();
					Boolean b = createStorageobj.checkForUserLogin(name,password);
					
					panel = new JPanel();
					if(name.length() >= 2 && password.length() >= 2) 
						loginInformationDialog(b);
					else
						System.err.println("Enter your userName And Password.");
						
				} catch (SQLException e1) {
					e1.printStackTrace();
				}		
			}
		});
	}
	
	private void loginInformationDialog(Boolean b) {
		if(b == true)
			JOptionPane.showMessageDialog(panel, "Login Successful.", "", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(panel, "Login failed.", "", JOptionPane.ERROR_MESSAGE);
	}
	
	private void addActionListener() {
		ActionClick action = new ActionClick();		

		cancelBtn.addActionListener(action);
		
		signUpBtn.addActionListener(action);
	}
		
	public static void main(String [] args) {
		EventQueue.invokeLater(() -> {
			new Demo().initUI();
		});
	}
}