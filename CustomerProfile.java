import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerProfile extends JFrame implements ActionListener
{
	JLabel image, labelName, labelId, labelMobile, labelAdress,labelNameOfUser, labelIdOfUser, labelMobileOfUser,labelAdressOfUser;
	JButton Update,LogOut,ChangePassword,SearchBook,delete,borrowed,borrowBook;
	JPanel panel;
    ImageIcon img;
    String id;

	public CustomerProfile (String id)
	{
		super("Customer Profile");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		//this.l=l;
		
		panel = new JPanel();
		panel.setLayout(null);

		this.id=id;
		
		labelName = new JLabel("Name   : ");
		labelName.setBounds(70, 70, 150, 30);
		labelName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelName);

		labelNameOfUser = new JLabel("");
		labelNameOfUser.setBounds(270, 70, 150, 30);
		labelNameOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelNameOfUser);

		
        labelId = new JLabel("ID: ");
		labelId.setBounds(70, 140, 150, 30);      
		labelId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelId);

		labelIdOfUser = new JLabel("");
		labelIdOfUser.setBounds(270, 140, 150, 30);
		labelIdOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelIdOfUser);

		labelMobile = new JLabel("Mobile:+880");
		labelMobile.setBounds(70, 210, 150, 30);
		labelMobile.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobile);

		labelMobileOfUser = new JLabel("");
		labelMobileOfUser.setBounds(270, 210, 150, 30);
		labelMobileOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelMobileOfUser);

        labelAdress = new JLabel("Adress   : ");
		labelAdress.setBounds(70, 280, 150, 30);
		labelAdress.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAdress);

		labelAdressOfUser = new JLabel("");
		labelAdressOfUser.setBounds(270, 280, 150, 30);
		labelAdressOfUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAdressOfUser);

        SearchBook = new JButton("Search Book");
		SearchBook.setBounds(550, 70, 120, 30);
		SearchBook.addActionListener(this);
		panel.add( SearchBook); 
        
		Update = new JButton("Update");
		Update.setBounds(550, 140, 120, 30);
		Update.addActionListener(this);
		panel.add(Update);
/*
		borrowBook = new JButton("Borrow Book");
		borrowBook.setBounds(550, 350, 120, 30);
		borrowBook.addActionListener(this);
		panel.add(borrowBook);
*/
		LogOut = new JButton("Logout");
		LogOut.setBounds(550, 350, 90, 30);
		LogOut.addActionListener(this);
		panel.add(LogOut);

		delete = new JButton("Delete Account");
		delete.setBounds(550, 280, 120, 30);
		delete.addActionListener(this);
		panel.add(delete);

		borrowed = new JButton("Borrowed Info");
		borrowed.setBounds(550, 210, 120, 30);
		borrowed.addActionListener(this);
		panel.add(borrowed);

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
			
		
		if(buttonClicked.equals(LogOut.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(SearchBook.getText()))
		{
			SearchBook a= new SearchBook(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(Update.getText())) 
		{
			CustomerUpdate a= new CustomerUpdate(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(borrowed.getText())) 
		{
			BorrowHistory a= new BorrowHistory(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if (buttonClicked.equals(delete.getText())) 
		{
			deleteAccount();
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
	}

	public void checkCustomer()
	{
		String query = "SELECT * FROM `customer` where userid='"+id+"'"; 		
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
                String name = rs.getString("customerName");
				String phone = rs.getString("phoneNumber");
				String Add = rs.getString("address");
				labelNameOfUser.setText(name);
				labelIdOfUser.setText(ID);
				labelMobileOfUser.setText(phone);
				labelAdressOfUser.setText(Add);
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

	public void deleteAccount()
	{
		String query="DELETE from customer where userId='"+id+"';";
		String query2="DELETE from login where userId='"+id+"';";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			stm.executeUpdate(query);
			stm.executeUpdate(query2);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this,"Account Deleted");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}

