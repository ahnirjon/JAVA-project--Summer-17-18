import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

public class BorrowHistory extends JFrame implements ActionListener, KeyListener
{
	private JTable userTable;
	private JPanel panel;
	private JScrollPane userScroll;
	private JButton Logout, back;
	private JTextField userTF,textFieldSearchUser;
	private JLabel title, searchUser,image;
	ImageIcon img;
	String bookTitle,authorName,publicationYear,availableQuantity,bookId ;
	int ID, Age;
	DefaultTableModel tableModel;
	String userId;
	public BorrowHistory(String user)
	{
		super("Borrow History");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,560);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		int i=0;
		userId=user;
		String []col = {"bookId","bookTitle","BorrowDate","ReturnDate"};
		
		searchUser = new JLabel("Search Book");
		searchUser.setBounds(230,70,90,30);
		searchUser.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(searchUser);
		
		textFieldSearchUser = new JTextField();
		textFieldSearchUser.setBounds(360,70,150,30);
		textFieldSearchUser.addKeyListener(this);
		panel.add(textFieldSearchUser);
		
		userTable = new JTable();
		tableModel = new DefaultTableModel(0,4);
		tableModel.setColumnIdentifiers(col);
		userTable.setModel(tableModel);
		userScroll = new JScrollPane(userTable);
		userScroll.setBounds(200,150,400,150);
		panel.add(userScroll);
		
		PopulateTable(tableModel, "");
		
		Connection con = null;
		Statement stm = null;
		
		title = new JLabel("Select a user to delete");
		//title.setBounds(270,325,200,50);
		title.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(title);
		
		
		back = new JButton("BACK");
		back.setBounds(270,400,80,30);
		back.addActionListener(this);
		panel.add(back);
		
		Logout = new JButton("Logout");
		Logout.setBounds(650,25,80,30);
		Logout.addActionListener(this);
		panel.add(Logout);
		
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		this.add(panel);
		
	}
	
	private void PopulateTable( DefaultTableModel tableModel, String searchKey) {
		String query = "";
		if (searchKey.equalsIgnoreCase("")){
			query = "select book.bookId, book.bookTitle, borrowinfo.borrowDate,borrowinfo.returnDate from book,borrowinfo WHERE borrowinfo.userId='"+userId+"'";
		}else{
			query = "select book.bookId, book.bookTitle, borrowinfo.borrowDate,borrowinfo.returnDate from book,borrowinfo WHERE book.bookTitle LIKE '%"+searchKey+"%' and borrowinfo.userId='"+userId+"'";
		}
		int i = 0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1","root","");
			Statement stm = con.createStatement();
			stm.executeQuery(query);
            ResultSet rs = stm.getResultSet();

//"bookId","bookTitle","BorrowDate","ReturnDate"
			while (rs.next())
			{
				String bookId = rs.getString("bookId");
				String bookTitle = rs.getString("bookTitle");
				String BorrowDate = rs.getString("BorrowDate");
				String ReturnDate = rs.getString("ReturnDate");
			
				//String Age = rs.getString("age");
				
				i++;
				String k = Integer.toString(i);
				tableModel.addRow (new Object[] {bookId,bookTitle,BorrowDate,ReturnDate});
			}

		}

		catch(Exception ex)
		{
			System.out.println("Exception : " + ex.getMessage());
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(back.getText()))
		{
			CustomerProfile ah = new CustomerProfile(userId);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(Logout.getText())){  
			Login l=new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
		
	}
	
	
	
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		tableModel.setRowCount(0);
		//comboMovie.removeAllItems();
		PopulateTable(tableModel, textFieldSearchUser.getText());
	}
	
}