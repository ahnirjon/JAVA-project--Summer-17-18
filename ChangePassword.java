import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.sql.*;

public class ChangePassword extends JFrame //implements ActionListener
{
	JLabel labelOldPassword, labelNewPassword, labelConfirmPassword, image;
	JPasswordField oldPassword, newPassword, confirmPassword;
	JLabel labelId;
	JButton buttonChange,buttonBack,back;
	JPanel panel;
	ImageIcon img;
	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age;
	
	ChangePassword()//String User_Name)
	{
		super("Change Password");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
	
		panel = new JPanel();
		panel.setLayout(null);
		
		labelOldPassword = new JLabel("Old Password : ");
		labelOldPassword.setBounds(170,130,140,30);
		labelOldPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelOldPassword);

		oldPassword = new JPasswordField();
		oldPassword.setBounds(320,130,160,30);
		panel.add(oldPassword);

        labelNewPassword = new JLabel("New Password : ");
		labelNewPassword.setBounds(170,200,140,30);
		labelNewPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelNewPassword);
		
		newPassword = new JPasswordField();
		newPassword.setBounds(320,200,160,30);
		panel.add(newPassword);

		buttonBack = new JButton("Log Out");
		buttonBack.setBounds(650,40,80,30);
		//buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonChange = new JButton("Change");
		buttonChange.setBounds(380,350,80,30);
		//buttonChange.addActionListener(this);
		panel.add(buttonChange);
		
		back = new JButton("BACK");
		back.setBounds(250,350,80,30);
		//back.addActionListener(this);
		panel.add(back);


		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		
		this.add(panel);
		//this.User_Name=User_Name;
	}
	
}