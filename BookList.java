import java.lang.*;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.*;

public class BookList extends JFrame implements ActionListener, KeyListener
{
	JTable userTable;
	JPanel panel;
	JScrollPane userScroll;
	JButton deleteUser, buttonLogout, back, go;
	JTextField userTF,textFieldSearchUser, tfUpdate;
	JLabel title, searchUser, labelUpdate,image;
	ImageIcon img;
	String bookTitle,authorName,publicationYear,availableQuantity,bookId ;
	int ID, Age;
	DefaultTableModel tableModel;
	String user;
	
	public BookList(String user)
	{
		super("Book List");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,560);
		
		panel = new JPanel();
        panel.setLayout(null);
		
		int i=0;
		this.user=user;
		
		String []col = {"bookId","bookTitle","authorName","publicationYear","availableQuantity"};
		
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
		
		title = new JLabel("Select a Book to delete");
		title.setBounds(200,325,200,50);
		title.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(title);

		labelUpdate = new JLabel("Enter a Book Id to update");
		labelUpdate.setBounds(400,325,200,50);
		labelUpdate.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelUpdate);

		tfUpdate = new JTextField();
		tfUpdate.setBounds(600,330,100,30);
		tfUpdate.addKeyListener(this);
		panel.add(tfUpdate);
		
		deleteUser = new JButton("Delete");
		deleteUser.setBounds(320,400,80,30);
		deleteUser.addActionListener(this);
		panel.add(deleteUser);

		back = new JButton("Back");
		back.setBounds(200,400,80,30);
		back.addActionListener(this);
		panel.add(back);

		go = new JButton("GO");
		go.setBounds(600,400,80,30);
		go.addActionListener(this);
		panel.add(go);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(650,25,80,30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);

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
				//String Age = rs.getString("age");
				
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
			if(user.equals("L-001")){
				AdminProfile ap = new AdminProfile(user);
			ap.setVisible(true);
			this.setVisible(false);
				
			}else{
			EmployeeProfile ah = new EmployeeProfile(user);
			ah.setVisible(true);
			this.setVisible(false);}
		}
		else if(elementText.equals(buttonLogout.getText()))
		{
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}

		else if(elementText.equals(go.getText()))
		{
			updateBook a= new updateBook(user, tfUpdate.getText());
			a.setVisible(true);
			this.setVisible(false);
		}
		
		else if(elementText.equals(deleteUser.getText()))
		{
			deleteFromDB();
			System.out.println("A user deleted successfully");
			tableModel.setRowCount(0);
			PopulateTable(tableModel, "");
		}
		
		
	}
	
	public void deleteFromDB()
	{
		int row = userTable.getSelectedRow();
		if (row < 0) return;
		String DeleteID = userTable.getModel().getValueAt(row, 0).toString();
		
		//String query = "update user_table set is_active=0 where id="+DeleteID+";";
			String query="DELETE from book where bookId='"+DeleteID+"';";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			stm.executeUpdate(query);
			stm.close();
			con.close();
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
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