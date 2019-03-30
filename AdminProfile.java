import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminProfile extends JFrame implements ActionListener
{
	JLabel image, labelName, labelEmail, labelId, labelMobile, labelAdress,labelNameOfUser, labelEmailOfUser, labelIdOfUser, labelMobileOfUser, labelAdressOfUser;
	JButton ChangePassword,LogOut,Userlist,AddEmployee,EmployeeList,ShowUser,ShowBook;
	JPanel panel;
    ImageIcon img;
    String id;

	public AdminProfile (String id)
	{
		super("Admin Profile");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);

		
		panel = new JPanel();
		panel.setLayout(null);

		this.id=id;
		
		labelName = new JLabel("   OWNER PAGE");
		labelName.setBounds(300, 15, 300, 100);
		labelName.setForeground(Color.red);
	
		labelName.setFont(new Font("Nirmala UI",Font.PLAIN,24));
		panel.add(labelName);

		/*labelEmail = new JLabel("Email      : ");
		//labelEmail.setBounds(70, 140, 200, 30);
		labelEmail.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelEmail);
		
        labelId = new JLabel("ID            : ");
		labelId.setBounds(70, 210, 200, 30);         
		labelId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelId);

		labelMobile = new JLabel("Mobile   : ");
		labelMobile.setBounds(70, 280, 200, 30);
	    labelMobile.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobile);

		labelAdress = new JLabel("Designation   : ");
		labelAdress.setBounds(70, 350, 200, 30);
		labelAdress.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAdress);

		

		labelNameOfUser = new JLabel("");
		labelNameOfUser.setBounds(300, 70, 200, 30);
	    labelNameOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelNameOfUser);

		labelEmailOfUser = new JLabel("");
		//labelEmailOfUser.setBounds(300, 140, 200, 30);
	    labelEmailOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelEmailOfUser);

		labelIdOfUser = new JLabel("");
		labelIdOfUser.setBounds(300, 210, 200, 30);
		labelIdOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelIdOfUser);

		labelMobileOfUser = new JLabel("");
		labelMobileOfUser.setBounds(300, 280, 200, 30);
		labelMobileOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobileOfUser);

		labelAdressOfUser = new JLabel("");
		labelAdressOfUser.setBounds(300, 350, 200, 30);
		labelAdressOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAdressOfUser);
*/
		

		AddEmployee = new JButton("Add Employee");
		AddEmployee.setBounds(200, 200, 150, 30);
		AddEmployee.addActionListener(this);
		panel.add(AddEmployee);


		EmployeeList = new JButton("Empolyee list");
		EmployeeList.setBounds(200, 250, 150, 30);
		EmployeeList.addActionListener(this);
		panel.add(EmployeeList);

		ShowUser = new JButton("Show Users");
		ShowUser.setBounds(200, 300, 150, 30);
		ShowUser.addActionListener(this);
		panel.add(ShowUser);
		
		ShowBook = new JButton("Show Books");
		ShowBook.setBounds(200, 350, 150, 30);
		ShowBook.addActionListener(this);
		panel.add(ShowBook);


		LogOut = new JButton("Logout");
		LogOut.setBounds(500, 300, 85, 30);
		LogOut.addActionListener(this);
		panel.add(LogOut);

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

		if(buttonClicked.equals(LogOut.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(EmployeeList.getText())) 
		{
			EmployeeList a= new EmployeeList(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(AddEmployee.getText())) 
		{
			AddEmployee a= new AddEmployee(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(ShowBook.getText())){
			BookList bl=new BookList("L-001");
			bl.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(ShowUser.getText())){
			UserList su=new UserList("L-001");
			su.setVisible(true);
			this.setVisible(false);
		}
	}

	public void checkEmployee()
	{
		String query = "SELECT * FROM `employee` where userid='"+id+"'"; 		
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
				String ID = rs.getString("userId");
                String name = rs.getString("employeeName");
				String Role = rs.getString("role");
				String phone = rs.getString("phoneNumber");
				labelNameOfUser.setText(""+name);
				labelIdOfUser.setText(""+ID);
				labelMobileOfUser.setText("phone");
				labelAdressOfUser.setText(""+Role);
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
}
