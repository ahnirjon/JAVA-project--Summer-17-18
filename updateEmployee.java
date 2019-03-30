import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class updateEmployee extends JFrame implements ActionListener
{
	JLabel labelUserName, labelEmail,labelRole, labelPassword, labelSalary, labelName, labelMobile, labelAddress,mandatory, image;
	boolean isActive;
	JTextField tfUserName, tfSalary, tfPassword, tfName, tfMobile, tfAddress,tfRole;
	JButton buttonBack, buttonLogout,buttonUpdate;
	JPanel panel;
	ImageIcon img;
	String role,salary;
	int status;
	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age;
	String id,eid;
	
	public updateEmployee(String id, String eid)
	{
		super("Update Employee Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		this.id=id;
		this.eid=eid;
		
		labelSalary = new JLabel("Salary: ");
		labelSalary.setBounds(70,200,80,30);
		panel.add(labelSalary);

		tfSalary = new JTextField();
		tfSalary.setBounds(180,200,110,30);
		panel.add(tfSalary);
		
		//labelSalary = new JLabel("Salary : ");
		//labelSalary.setBounds(330,50,80,30);
		//panel.add(labelSalary);

		tfPassword = new JTextField();
		//tfPassword.setBounds(410,50,120,30);
		panel.add(tfPassword);


		labelRole = new JLabel("Role : ");
		labelRole.setBounds(70,160,80,30);
		panel.add(labelRole);
		
		tfRole = new JTextField("");
		tfRole.setBounds(180,160,110,30);
		panel.add(tfRole);
		
		labelName = new JLabel("Name : ");
		labelName.setBounds(70,120,80,30);
		panel.add(labelName);
		
		tfName = new JTextField();
		tfName.setBounds(180,120,110,30);
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
			
		if(buttonClicked.equals(buttonBack.getText()))
		{
			EmployeeList a= new EmployeeList(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonUpdate.getText()))
		{
			updateInfo();
		}
		
		
	}

	public void checkEmployee()
	{
		String query = "SELECT * FROM `employee` where userid='"+eid+"'"; 		
        Connection con=null;//for connection
        Statement st = null;//for query execution
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
                String name = rs.getString("employeeName");
				String Role = rs.getString("role");
				float sal = rs.getFloat("salary");
				tfName.setText(""+name);
				tfSalary.setText(""+sal);
				tfRole.setText(""+Role);
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
		int status;
		if(tfRole.getText()=="Admin" || tfRole.getText()=="admin" || tfRole.getText()=="Manager" || tfRole.getText()=="manager")
		{
			status=0;
		}
		else{status=2;}

		String query="UPDATE employee SET  role = '"+tfRole.getText()+"', salary = '"+tfSalary.getText()+"' WHERE userid = '"+eid+"'";// ('"+tfUserName.getText()+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+role+"','"+salary+"');";
        String query2="UPDATE login SET status = '"+status+"' WHERE userid = '"+eid+"'"; //('"+tfUserName.getText()+"','"+tfPassword.getText()+"','"+status+"');";
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