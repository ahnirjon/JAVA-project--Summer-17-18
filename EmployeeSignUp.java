import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class EmployeeSignUp extends JFrame //implements ActionListener
{
	JLabel labelUserName, labelEmail, labelPassword, labelUserType, labelName, labelMobile, labelAddress,mandatory, image;
	boolean isActive;
	JTextField tfUserName, tfEmail, tfPassword, tfName, tfMobile, tfAddress;
	JButton buttonBack, Logout,buttonRegister;
	JPanel panel;
	ImageIcon img;

	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age;
	
	public EmployeeSignUp()
	{
		super("Create New User Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelUserName = new JLabel("Username : *");
		labelUserName.setBounds(70,50,80,30);
		labelUserName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelUserName);

		tfUserName = new JTextField();
		tfUserName.setBounds(180,50,110,30);
		panel.add(tfUserName);
		
		/*
		labelEmail = new JLabel("E-Mail : *");
		labelEmail.setBounds(70,120,80,30);
		panel.add(labelEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(180,120,110,30);
		panel.add(tfEmail);
		*/
		labelPassword = new JLabel("Password : *");
		labelPassword.setBounds(330,50,80,30);
		labelPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelPassword);

		tfPassword = new JTextField();
		tfPassword.setBounds(410,50,120,30);
		panel.add(tfPassword);


		labelMobile = new JLabel("Mobile : ");
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
		
		labelAddress = new JLabel("Address : ");
		labelAddress.setBounds(70,250,80,30);
		labelAddress.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAddress);
		
		tfAddress =new JTextField();
		tfAddress.setBounds(180,250,300,30);
		panel.add(tfAddress);
		
		mandatory = new JLabel("  *  : Mandatory field");
		mandatory.setForeground(Color.RED);
		mandatory.setBounds(70,330,150,30);
		mandatory.setFont(new Font("Nirmala UI",Font.PLAIN,15));
	    panel.add(mandatory);
		
		buttonRegister = new JButton("Register");
		buttonRegister.setBounds(460,370,90,30);
		//buttonRegister.addActionListener(this);
		panel.add(buttonRegister);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(300,370,90,30);
		//buttonBack.addActionListener(this);
		panel.add(buttonBack);

		Logout = new JButton("Logout");
		Logout.setBounds(650,40,80,30);
		//Logout.addActionListener(this);
		panel.add(Logout);
	    
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
	
	}
}