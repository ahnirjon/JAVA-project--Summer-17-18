import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

public class SearchBook extends JFrame implements ActionListener, KeyListener
{
	JTable userTable;
	JPanel panel;
	JScrollPane userScroll;
	JButton Logout, back;
	JTextField userTF,textFieldSearchUser;
	JLabel title, searchUser,image;
	ImageIcon img;
	String bookTitle,authorName,publicationYear,availableQuantity,bookId ;
	int ID, Age;
	DefaultTableModel tableModel;
	String user;
	
	public SearchBook(String user)
	{
		super("Book List");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,560);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		int i=0;
		this.user = user;
		
		String []col = {"Book Id","Book Title","Author Name","Publication Year","Available Quantity"};
		
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
			query = "select * from book";
		}else{
			query = "select * from book WHERE bookTitle LIKE '%"+searchKey+"%' ";
		}
		int i = 0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1","root","");
			Statement stm = con.createStatement();
			stm.executeQuery(query);
            ResultSet rs = stm.getResultSet();

			while (rs.next())
			{
				String bookTitle = rs.getString("bookTitle");
				String authorName = rs.getString("authorName");
				String publicationYear = rs.getString("publicationYear");
				String availableQuantity = rs.getString("availableQuantity");
				String bookId = rs.getString("bookId");
				
				i++;
				String k = Integer.toString(i);
				tableModel.addRow (new Object[] {bookId,bookTitle,authorName,publicationYear,availableQuantity});
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
			CustomerProfile ah = new CustomerProfile(user);
			ah.setVisible(true);
			this.setVisible(false);
		}
		else if(elementText.equals(Logout.getText())){  //task here 
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
		PopulateTable(tableModel, textFieldSearchUser.getText());
	}
	
}