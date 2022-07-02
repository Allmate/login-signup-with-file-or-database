package Java_Pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class createStorage{
	String url;
	String name;
	String password;
	
	String fileUrl = "src/Java_Pack/UserAccount.txt";
	File file = new File(fileUrl);
	
	private String userName = null;
	private String userPassword = null;
	
	private Connection con;
	private Statement st;
	private PreparedStatement preparedSt;
		
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public void insertDB() throws SQLException {
		createTable();
		
		if(userName.length() >= 2  && userPassword.length() >= 2) {
			String sql = "Insert Into tblUser Values(?,?)";
			preparedSt = con.prepareStatement(sql);
			preparedSt.setString(1,userName);
			preparedSt.setString(2, userPassword);
			preparedSt.executeUpdate();		
			System.out.println("Successfull saved your account to server.");
		}
		else
			System.err.println("Please fill UserName and Password.");
		
		st.close();
		con.close();
	}
		
	public void createDB() throws SQLException {
		
		url = "jdbc:sqlserver://localhost:1433; databaseName = allmate_DataBase";
		name = "sa";
		password = "allmate232232##";
		
		try {
		con = DriverManager.getConnection(url, name, password);
				
		}catch(SQLException ex) {
			System.err.println("Occurrence error, System terminated.");
			ex.printStackTrace();
		}
	}
	
	public void createTable() throws SQLException {
		String sql = "Execute sp_CreatetblUser";
		
		st = con.createStatement();
		st.execute(sql);
	}
	
	public boolean checkForUserLogin(String username, String password) throws SQLException {
		String query = "Select * From tblUser Where Name = ? And Password = ?";
		preparedSt = con.prepareStatement(query);
		
		preparedSt.setString(1, username);
		preparedSt.setString(2, password);
				
		ResultSet rs = preparedSt.executeQuery();
		return rs.next();
	}
	
	public void createFile() throws IOException{
		
		FileWriter writer = new FileWriter(file.getAbsolutePath(), true);
		
		if(!file.exists()) {
			file.createNewFile();
		}
		
		String inputData = userName + "\t" + userPassword;
			
		writer.append(inputData);
		writer.append("\n");
		
		writer.close();
		System.out.println("Successfully add account to file.");		
	}
	
	public Boolean fileScanner() throws FileNotFoundException {
		Boolean b = null;
		Scanner scanner = new Scanner(file);
		
		String inputData = userName + "\t" + userPassword;
		
		while(scanner.hasNextLine()) {
			String data = scanner.nextLine();
			
			if(data.equals(inputData)) {
				b = true;
				break;
			}
			else 
				b = false;	
		}	
		scanner.close();
		return b;
	}
}