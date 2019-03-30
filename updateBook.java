import java.sql.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class updateBook extends JFrame implements ActionListener
{
    JPanel panel;
    JLabel labelBookName,labelBookId, labelAuthorName,labelPublicationYear, labelAvailableQuantity, image;
    JTextField textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
    JButton buttonlogout,back,buttonAddToDatabase;
    ImageIcon img;
	//String Name,User_Name,Email,Password,User_Type,Mobile_No,Address,Gender;
	//int ID, Age, HallID, MovieID;
	String id, bid;

	
	
    public updateBook(String id, String bid)//String User_Name)
    {
        super("Update Book Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,550);

        panel = new JPanel();
        panel.setLayout(null);
		
        this.id=id;
        this.bid=bid;

		labelBookName = new JLabel("Book Name : ");
        labelBookName.setBounds(90,50,100,30);
        labelBookName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelBookName);

        textFieldBookName = new JTextField();
        textFieldBookName.setBounds(220,50,150,30);
        panel.add(textFieldBookName);

        labelAuthorName = new JLabel("Author Name : ");
        labelAuthorName.setBounds(90,120,100,30);
        labelAuthorName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelAuthorName);

        textFieldAuthorName = new JTextField();
        textFieldAuthorName.setBounds(220,120,150,30);
        panel.add(textFieldAuthorName);

        labelBookId = new JLabel("Book Id : ");
        labelBookId.setBounds(90,190,100,30);
        labelBookId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelBookId);

        textFieldId = new JTextField();
        textFieldId.setBounds(220,190,150,30);
        panel.add(textFieldId);

        labelPublicationYear = new JLabel("Publication Year : ");
        labelPublicationYear.setBounds(90,260,120,30);
        labelPublicationYear.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelPublicationYear);

        textFieldPublicationYear = new JTextField();
        textFieldPublicationYear.setBounds(220,260,150,30);
        panel.add(textFieldPublicationYear);
		
		labelAvailableQuantity = new JLabel("AvailableQuantity : ");
		labelAvailableQuantity.setBounds(90,330,120,30);
		labelAvailableQuantity.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAvailableQuantity);
		
		textFieldAvailableQuantity = new JTextField();
		textFieldAvailableQuantity.setBounds(220,330,150,30);
		panel.add(textFieldAvailableQuantity);
		
		buttonAddToDatabase = new JButton("Update Book");
		buttonAddToDatabase.setBounds(300,410,180,30);
		buttonAddToDatabase.addActionListener(this);
		panel.add(buttonAddToDatabase);
		
		back = new JButton("Back");
		back.setBounds(150,410,80,30);
		back.addActionListener(this);
		panel.add(back);

		buttonlogout = new JButton("Logout");
		buttonlogout.setBounds(650,40,80,30);
		buttonlogout.addActionListener(this);
		panel.add(buttonlogout);

		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
		checkBook();

	}
	
		public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
			
		if(buttonClicked.equals(back.getText()))
		{
			BookList a= new BookList(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonlogout.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonAddToDatabase.getText()))
		{
			updateInfo();
		}
		
		
	}


	public void checkBook()
	{
		String query = "SELECT * FROM `book` where bookid='"+bid+"'"; 
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
				String ID = rs.getString("bookId");
                String name = rs.getString("bookTitle");
                String aname = rs.getString("authorName");
				int year = rs.getInt("publicationYear");
				int quan = rs.getInt("availableQuantity");
				textFieldBookName.setText(name);
				textFieldAuthorName.setText(aname);
				textFieldId.setText(ID);
				textFieldPublicationYear.setText(""+year);
				textFieldAvailableQuantity.setText(""+quan);
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
		String query="UPDATE book SET bookid = '"+textFieldId.getText()+"', bookTitle = '"+textFieldBookName.getText()+"', authorName = '"+textFieldAuthorName.getText()+"', publicationYear = '"+textFieldPublicationYear.getText()+"', availableQuantity = '"+textFieldAvailableQuantity.getText()+"' WHERE bookid = '"+bid+"'";// ('"+tfUserName.getText()+"','"+tfName.getText()+"','"+tfMobile.getText()+"','"+role+"','"+salary+"');";
        //String query2="UPDATE login SET userid = '"+tfUserName.getText()+"', password = '"+tfPassword.getText()+"' WHERE userid = '"+id+"'"; //('"+tfUserName.getText()+"','"+tfPassword.getText()+"','"+status+"');";
		//id=tfPassword.getText();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			Statement s= con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this," Book Info Updated Successfully");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---Row inserted---");
	}
	
	
}