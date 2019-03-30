import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EmployeeProfile extends JFrame implements ActionListener
{
	JLabel image, labelName, labelId, labelDesignation,labelNameOfUser, labelIdOfUser,labelDesignationOfUser;
	JButton Addbook, BorrowInfo,ChangePassword,LogOut,UserList,Update,BookList,updateBook,borrowBook;
	JPanel panel;
    ImageIcon img;
	String id;
	public EmployeeProfile (String id)
	{
		super("Empolyee Profile");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		this.id = id;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelName = new JLabel("Name     : ");
		labelName.setBounds(70, 70, 150, 30);
		labelName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelName);
		
        labelId = new JLabel("ID           : ");
		labelId.setBounds(70, 140, 150, 30);        
		labelId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelId);


		labelDesignation = new JLabel("Designation   : ");
		labelDesignation.setBounds(70, 210, 150, 30);
		labelDesignation.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelDesignation);


		labelNameOfUser = new JLabel();
		labelNameOfUser.setBounds(270, 70, 150, 30);
		labelNameOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelNameOfUser);

		labelIdOfUser = new JLabel("");
		labelIdOfUser.setBounds(270, 140, 150, 30);
		labelIdOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelIdOfUser);

		labelDesignationOfUser = new JLabel("");
		labelDesignationOfUser.setBounds(270, 210, 150, 30);
		labelDesignationOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelDesignationOfUser);
		
		Update = new JButton("Update Info");
		Update.setBounds(550, 70, 120, 30);
		Update.addActionListener(this);
		panel.add(Update);
	

		borrowBook = new JButton("Borrow Book");
		borrowBook.setBounds(550, 140, 120, 30);
		borrowBook.addActionListener(this);
		panel.add(borrowBook);

        Addbook = new JButton("Add book");
		Addbook.setBounds(550, 210, 120, 30);
		Addbook.addActionListener(this);
		panel.add(Addbook);

		UserList = new JButton("User List");
		UserList.setBounds(550, 350, 120, 30);
		UserList.addActionListener(this);
		panel.add(UserList);

		BookList = new JButton("Book List");
		BookList.setBounds(550, 280, 120, 30);
		BookList.addActionListener(this);
		panel.add(BookList);

		LogOut = new JButton("Logout");
		LogOut.setBounds(550, 420, 85, 30);
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
		
		if(buttonClicked.equals(Update.getText()))
		{
			EmployeeUpdate ah = new EmployeeUpdate(id);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(Addbook.getText()))
		{
			AddBook ah = new AddBook(id);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(LogOut.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(BookList.getText())) 
		{
			BookList a= new BookList(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(UserList.getText())) 
		{
			UserList a = new UserList(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(borrowBook.getText())) 
		{
			BorrowBook a = new BorrowBook(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else{}
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
				labelNameOfUser.setText(""+name);
				labelIdOfUser.setText(""+ID);
				labelDesignationOfUser.setText(""+Role);
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
