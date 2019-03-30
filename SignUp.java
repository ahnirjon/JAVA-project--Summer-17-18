import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class SignUp extends JFrame implements ActionListener
{
	JLabel labelPassword,labelCnfPassword,labelUserName,labelName, labelMobile, labelAddress,mandatory, image;
	boolean isActive;
	JTextField tfUserName,tfName, tfMobile, tfAddress;
	JButton buttonBack, buttonInsert;
	JPasswordField pfPassword,pfCPassword;
	JPanel panel;
	ImageIcon img;
    String UserID;
	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age;
	
	public SignUp()
	{
		super("Create New User Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelUserName = new JLabel("Username : *");
		labelUserName.setBounds(70,50,85,30);
		labelUserName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelUserName);

		tfUserName = new JTextField();
		tfUserName.setBounds(180,50,110,30);
		panel.add(tfUserName);

		labelPassword = new JLabel("Password : *");
		labelPassword.setBounds(330,50,80,30);
		labelPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(430,50,120,30);
		panel.add(pfPassword);
	
		
	/*	labelEmail = new JLabel("E-Mail : *");
		labelEmail.setBounds(70,120,80,30);
		panel.add(labelEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(180,120,110,30);
		panel.add(tfEmail);
*/		
		labelCnfPassword = new JLabel("ConfPass: *");
		labelCnfPassword.setBounds(330,120,80,30);
		labelCnfPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelCnfPassword);

		pfCPassword = new  JPasswordField();
		pfCPassword.setBounds(410,120,120,30);
		panel.add(pfCPassword);


		labelMobile = new JLabel("Mob:+880");
		labelMobile.setBounds(70,120,80,30);
		labelMobile.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobile);
		
		tfMobile = new JTextField("");
		tfMobile.setBounds(180,120,110,30);
		panel.add(tfMobile);
		
		labelName = new JLabel("Name : *");
		labelName.setBounds(70,180,80,30);
		labelName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelName);
		
		tfName = new JTextField();
		tfName.setBounds(180,180,300,30);
		panel.add(tfName);
		
		labelAddress = new JLabel("Address : *");
		labelAddress.setBounds(70,250,80,30);
		labelAddress.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAddress);
		
		tfAddress =new JTextField();
		tfAddress.setBounds(180,250,300,30);
		panel.add(tfAddress);
		
		mandatory = new JLabel("  *  : Mandatory field");
		mandatory.setForeground(Color.RED);
		mandatory.setBounds(70,300,150,30);
		mandatory.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(mandatory);
		
		buttonInsert = new JButton("Register");
		buttonInsert.setBounds(400,350,100,30);
		buttonInsert.addActionListener(this);
		panel.add(buttonInsert);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(400,400,100,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
	    
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
	
	}
		public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
			
		if(buttonClicked.equals(buttonBack.getText()))
		{//JOptionPane.showMessageDialog(this,"Wrong password");
			//generateId();
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonInsert.getText()))
		{
			insertIntoDB();
		}
		
		
	}
	
	public void insertIntoDB()
	{
		
		//textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
		//String query="INSERT INTO book VALUES ('"+textFieldBookName.getText()+"','"+textFieldAuthorName.getText()+"','"+textFieldPublicationYear.getText()+"','"+textFieldAvailableQuantity.getText()+"','"+textFieldId.getText()+"');";
		
		String query=null,q2=null;
		if(pfPassword.getText().toString().equals(pfCPassword.getText().toString()) && !pfPassword.getText().equals("") && !tfUserName.getText().equals("") && !tfName.getText().equals("") && !tfAddress.getText().equals("") )
		{
			//generateId();
			
			
		int sts=1;
		query="INSERT INTO customer VALUES ('"+tfUserName.getText()+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+tfAddress.getText()+"');";
		q2="INSERT INTO login VALUES ('"+tfUserName.getText()+"','"+pfPassword.getText()+"','"+sts+"');";

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			Statement s= con.createStatement();
			stm.execute(query);
			s.execute(q2);
			stm.close();
			s.close();
			con.close();
			JOptionPane.showMessageDialog(this,"Registration Successful");
		}
        catch(Exception ex)
		{
						JOptionPane.showMessageDialog(this,"UserName Already In Use");

			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---Row inserted---");
		}
		else{
			
			if(pfPassword.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Enter A Password");
			}
			else if(tfUserName.getText().equals("") || tfName.getText().equals("") || tfAddress.getText().equals("") )
			{
				
				JOptionPane.showMessageDialog(this,"Please Fill The Mandetory Information");
			}
			else{
			JOptionPane.showMessageDialog(this,"password do not match");
			}
		}
        
    }

	/*
   public void	generateId()
	{
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");  
   LocalDateTime now = LocalDateTime.now();  
   //System.out.println(dtf.format(now));  
	UserID=dtf.format(now).toString();
    System.out.println(UserID);  
	}*/
}