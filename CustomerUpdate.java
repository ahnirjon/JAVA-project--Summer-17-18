import java.lang.*;
import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class CustomerUpdate extends JFrame implements ActionListener
{
	JLabel labelPassword,labelCnfPassword,labelUserName,labelName, labelMobile, labelAddress,mandatory, image;
	boolean isActive;
	JTextField tfUserName,tfName, tfMobile, tfAddress,tfPassword;
	JButton buttonBack, buttonInsert,Logout;
	JPasswordField pfPassword,pfCPassword;
	JPanel panel;
	ImageIcon img;
    String UserID;
	
	String id;
	
	public CustomerUpdate(String id)
	{
		super("Update New User Account");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,550);
		
		panel = new JPanel();
		panel.setLayout(null);

		this.id=id;
		
		labelUserName = new JLabel("Username : ");
		labelUserName.setBounds(70,50,85,30);
		labelUserName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelUserName);

		tfUserName = new JTextField();
		tfUserName.setBounds(180,50,110,30);
		panel.add(tfUserName);

		labelPassword = new JLabel("Password : ");
		labelPassword.setBounds(330,50,80,30);
		labelPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelPassword);

		tfPassword = new JPasswordField();
		tfPassword.setBounds(490,50,120,30);
		panel.add(tfPassword);
	
		labelCnfPassword = new JLabel("Confirm Password : ");
		//labelCnfPassword.setBounds(330,120,140,30);
		labelCnfPassword.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelCnfPassword);

		pfCPassword = new  JPasswordField();
		//pfCPassword.setBounds(490,120,120,30);
		panel.add(pfCPassword);


		labelMobile = new JLabel("Mobile: +880");
		labelMobile.setBounds(70,120,80,30);
		labelMobile.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobile);
		
		tfMobile = new JTextField("");
		tfMobile.setBounds(180,120,110,30);
		panel.add(tfMobile);
		
		labelName = new JLabel("Name : ");
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
		//mandatory.setBounds(70,300,150,30);
		mandatory.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(mandatory);
		
		buttonInsert = new JButton("Update");
		buttonInsert.setBounds(400,350,100,30);
		buttonInsert.addActionListener(this);
		panel.add(buttonInsert);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(200,350,100,30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);

		Logout = new JButton("Logout");
		Logout.setBounds(650,25,80,30);
		Logout.addActionListener(this);
		panel.add(Logout);
	    
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
		checkCustomer();
	
	}

	public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonInsert.getText()))
		{
			updateInfo();
		}
		else if(buttonClicked.equals(buttonBack.getText()))
		{
			CustomerProfile ah = new CustomerProfile(id);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(Logout.getText())){  //task here 
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}

	public void checkCustomer()
	{
		String query = "SELECT * FROM `customer` where userid='"+id+"'"; 
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
                String name = rs.getString("customerName");
				String phone = rs.getString("phoneNumber");
				String Add = rs.getString("address");
				tfName.setText(name);
				tfUserName.setText(ID);
				tfMobile.setText(phone);
				tfAddress.setText(Add);
			}

			rs = st.executeQuery(query2);
			while(rs.next())
			{
				String pass = rs.getString("password");
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
		String query="UPDATE customer SET userid = '"+tfUserName.getText()+"', customerName = '"+tfName.getText()+"', phoneNumber = '"+tfMobile.getText()+"', address = '"+tfAddress.getText()+"' WHERE userid = '"+id+"'";// ('"+tfUserName.getText()+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+role+"','"+salary+"');";
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


