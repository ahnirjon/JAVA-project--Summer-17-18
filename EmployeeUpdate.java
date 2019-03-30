import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class EmployeeUpdate extends JFrame implements ActionListener
{
	JLabel labelUserName, labelEmail, labelPassword, labelUserType, labelName, labelMobile, labelAddress,mandatory, image,mobn;
	boolean isActive;
	JTextField tfUserName, tfEmail, tfName, tfMobile, tfAddress;
	JPasswordField  tfPassword;
	JButton buttonBack, buttonLogout,buttonUpdate;
	JPanel panel;
	ImageIcon img;
	String id,role,salary;
	int status;
	
	public EmployeeUpdate(String id)
	{
		super("Update Employee Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		this.id=id;
		
			
		labelUserName = new JLabel("Username : ");
		labelUserName.setBounds(70,50,80,30);
		panel.add(labelUserName);

		tfUserName = new JTextField();
		tfUserName.setBounds(180,50,110,30);
		panel.add(tfUserName);
		
		
		labelPassword = new JLabel("Password : ");
		labelPassword.setBounds(330,50,80,30);
		panel.add(labelPassword);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(410,50,120,30);
		panel.add(tfPassword);


		
		labelMobile = new JLabel("Mobile : +880");
		labelMobile.setBounds(70,120,80,30);
		panel.add(labelMobile);
		
		tfMobile = new JTextField("");
		tfMobile.setBounds(180,120,110,30);
		panel.add(tfMobile);
		
		labelName = new JLabel("Name : ");
		labelName.setBounds(70,180,80,30);
		panel.add(labelName);
		
		tfName = new JTextField();
		tfName.setBounds(180,180,300,30);
		panel.add(tfName);
		
		
		buttonUpdate = new JButton("Update");
		buttonUpdate.setBounds(460,370,90,30);
		buttonUpdate.addActionListener(this);
		panel.add(buttonUpdate);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(300,370,90,30);
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

		checkEmployee();
	
	}

	public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonUpdate.getText()))
		{
		//	JOptionPane.showMessageDialog(this,"Missing jhgyfgh");
			//tfUserName, tfEmail, tfPassword, tfName, tfMobile, tfAddress;
		//if(tfUserName.getText().equals("") || tfEmail.getText().equals("") || tfPassword.getText().equals("") || tfName.getText().equals("") || tfMobile.getText().equals("")|| tfAddress.getText().equals(""))
	   if(tfUserName.getText().equals("")|| tfName.getText().equals("")||tfPassword.getText().equals("")|| tfMobile.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Missing Informations");
			
		}
		else{
			//JOptionPane.showMessageDialog(this,"Inside of it");		
		updateInfo();
		}
		}
		else if(buttonClicked.equals(buttonBack.getText()))
		{
			EmployeeProfile ah = new EmployeeProfile(id);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{  
			//JOptionPane.showMessageDialog(this,"Missingons");

	       Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
		else{}
	}

	public void checkEmployee()
	{
		String query = "SELECT * FROM `employee` where userid='"+id+"'"; 
		String query2 = "SELECT * FROM `login` where userid='"+id+"'";	
        Connection con=null;//for connection
        Statement st = null;//for query execution
		//ResultSet rs2 = null;
		ResultSet rs = null;//to get row by row result from DB
		System.out.println("inside check: " );
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1","root","");
			st = con.createStatement();//create statement
			rs = st.executeQuery(query);//getting result
			
			System.out.println("inside try1: " );
			
			while(rs.next())
			{
				String ID = rs.getString("userId");
                String name = rs.getString("employeeName");
				String phone = rs.getString("phoneNumber");
				role = rs.getString("role");
				salary = rs.getString("salary");
				tfUserName.setText(ID);
				tfName.setText(name);
				tfMobile.setText(""+phone);
			}

			rs = st.executeQuery(query2);
			while(rs.next())
			{
				String pass = rs.getString("password");
				status = rs.getInt("status");
				tfPassword.setText(""+pass);
			}

		}
        catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
			}
        finally
			{
				try                                  
				{
					if(rs!=null)
						rs.close();
					if(st!=null)
						st.close();
					if(con!=null)
						con.close();
				}
				catch(Exception ex){}
			}
	}

	public void updateInfo()
	{
		String query="UPDATE employee SET userid = '"+tfUserName.getText()+"', employeeName = '"+tfName.getText()+"', phoneNumber = '"+tfMobile.getText()+"' WHERE userid = '"+id+"'";// ('"+tfUserName.getText()+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+role+"','"+salary+"');";
        String query2="UPDATE login SET userid = '"+tfUserName.getText()+"', password = '"+tfPassword.getText()+"' WHERE userid = '"+id+"'"; //('"+tfUserName.getText()+"','"+tfPassword.getText()+"','"+status+"');";
		//id=tfPassword.getText();
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
			JOptionPane.showMessageDialog(this,"Info Updated Successfully");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---Row inserted---");
	}
}