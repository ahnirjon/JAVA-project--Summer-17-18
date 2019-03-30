import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class AddEmployee extends JFrame implements ActionListener
{
	JLabel labelUserId,labelEmployeeName,labelPhoneNumber,labelRole,labelSalary,image, labelPass;
	boolean isActive;
	JTextField tfUserId,tfEmployeeName,tfPhoneNumber, tfSalary, tfRole;
	JButton buttonBack, buttonLogout, buttonAdd;
	JPanel panel;
	ImageIcon img;
	JPasswordField pfPassword;
	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age;
	String id;
	
	public AddEmployee(String id)//String User_Name)
	{
		super("Add New Employee");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);

		this.id=id;
		
		labelUserId = new JLabel("ID : ");
		labelUserId.setBounds(50,50,80,30);
		labelUserId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelUserId);

		tfUserId = new JTextField();
		tfUserId.setBounds(200,50,100,30);
		panel.add(tfUserId);
		
		labelPass = new JLabel("Password : ");
		labelPass.setBounds(320,50,80,30);
		labelPass.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelPass);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(400,50,100,30);
		panel.add(pfPassword);
		
		labelEmployeeName = new JLabel("Employee Name : ");
		labelEmployeeName.setBounds(50,120,130,30);
		labelEmployeeName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelEmployeeName);
		
		tfEmployeeName = new JTextField();
		tfEmployeeName.setBounds(200,120,200,30);
		panel.add(tfEmployeeName);
		
		labelPhoneNumber = new JLabel("Ph Number: +880");
		labelPhoneNumber.setBounds(50,190,130,30);
		labelPhoneNumber.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelPhoneNumber);
		
		tfPhoneNumber = new JTextField();
		tfPhoneNumber.setBounds(200,190,200,30);
		panel.add(tfPhoneNumber);
		
		labelRole = new JLabel("Role : ");
		labelRole.setBounds(50,260,80,30);
		labelRole.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelRole);
		
		tfRole = new JTextField();
		tfRole.setBounds(200,260,200,30);
		panel.add(tfRole);
		
		labelSalary = new JLabel("Salary : ");
		labelSalary.setBounds(50,330,80,30);
		labelSalary.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelSalary);
		
		tfSalary = new JTextField();
		tfSalary.setBounds(200,330,200,30);
		panel.add(tfSalary);
	
		
		buttonAdd = new JButton("Add Employee");
		buttonAdd.setBounds(300,420,130,30);
		buttonAdd.addActionListener(this); 
		panel.add(buttonAdd);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(150,420,80,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(650,40,80,30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);

		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
		//this.User_Name=User_Name;
	}

	public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
			
		if(buttonClicked.equals(buttonBack.getText()))
		{
			AdminProfile ad = new AdminProfile(id);
			ad.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonAdd.getText()))  
		{
			//tfUserId,tfEmployeeName,tfPhoneNumber, tfSalary, tfRole
			if(tfUserId.getText().equals("")|| tfEmployeeName.getText().equals("")||tfPhoneNumber.getText().equals("")|| tfSalary.getText().equals("") || tfSalary.getText().equals("") || pfPassword.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Missing Informations");
			
		}else{
			insertIntoDB();
		}
		}
		
		
	}

		
	
	public void insertIntoDB()
	{
		//textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
		//String query="INSERT INTO book VALUES ('"+textFieldBookName.getText()+"','"+textFieldId.getText()+"','"+textFieldAuthorName.getText()+"','"+textFieldPublicationYear.getText()+"','"+textFieldAvailableQuantity.getText()+"');";
		String query="INSERT INTO employee VALUES ('"+tfUserId.getText()+"','"+tfEmployeeName.getText()+"','"+tfPhoneNumber.getText()+"','"+tfRole.getText()+"','"+tfSalary.getText()+"');";
        String query2="INSERT INTO login VALUES ('"+tfUserId.getText()+"','"+pfPassword.getText()+"','"+2+"');";
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			Statement s= con.createStatement();
			stm.execute(query);
			stm.execute(query2);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this,"Employee Added Successfully");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---Row inserted---");
    }
}
