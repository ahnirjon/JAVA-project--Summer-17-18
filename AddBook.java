import java.sql.*;
import java.lang.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.util.Date;

public class AddBook extends JFrame implements ActionListener
{
    JPanel panel;
    JLabel labelBookName,labelBookId, labelAuthorName,labelPublicationYear, labelAvailableQuantity, image;
    JTextField textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
    JButton buttonlogout,back,buttonAddToDatabase;
    ImageIcon img;
	
	String id;

	
	
    public AddBook(String id)
    {
        super("Add Book");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,550);

        panel = new JPanel();
        panel.setLayout(null);
		
        this.id=id;

		labelBookName = new JLabel("Book Name : ");
        labelBookName.setBounds(90,50,100,30);
        labelBookName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelBookName);

        textFieldBookName = new JTextField();
        textFieldBookName.setBounds(250,50,150,30);
        panel.add(textFieldBookName);

        labelAuthorName = new JLabel("Author Name : ");
        labelAuthorName.setBounds(90,120,100,30);
        labelAuthorName.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelAuthorName);

        textFieldAuthorName = new JTextField();
        textFieldAuthorName.setBounds(250,120,150,30);
        panel.add(textFieldAuthorName);

        labelBookId = new JLabel("Book Id : ");
        labelBookId.setBounds(90,190,100,30);
        labelBookId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelBookId);

        textFieldId = new JTextField();
        textFieldId.setBounds(250,190,150,30);
        panel.add(textFieldId);

        labelPublicationYear = new JLabel("Publication Year : ");
        labelPublicationYear.setBounds(90,260,120,30);
        labelPublicationYear.setFont(new Font("Nirmala UI",Font.PLAIN,15));
        panel.add(labelPublicationYear);

        textFieldPublicationYear = new JTextField();
        textFieldPublicationYear.setBounds(250,260,150,30);
        panel.add(textFieldPublicationYear);
		
		labelAvailableQuantity = new JLabel("Available Quantity : ");
		labelAvailableQuantity.setBounds(90,330,150,30);
		labelAvailableQuantity.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(labelAvailableQuantity);
		
		textFieldAvailableQuantity = new JTextField();
		textFieldAvailableQuantity.setBounds(250,330,150,30);
		panel.add(textFieldAvailableQuantity);
		
		buttonAddToDatabase = new JButton("Add Book");
		buttonAddToDatabase.setBounds(300,410,100,30);
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

	}
	
		public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
			
		if(buttonClicked.equals(back.getText()))
		{
			EmployeeProfile a= new EmployeeProfile(id);
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
			//textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
		if(textFieldBookName.getText().equals("") || textFieldId.getText().equals("") || textFieldAuthorName.getText().equals("") || textFieldPublicationYear.getText().equals("") || textFieldAvailableQuantity.getText().equals(""))	
		{
			JOptionPane.showMessageDialog(this,"Missing Informations");
			
		}	else{
		insertIntoDB();}
		}
		
		
	}
	
	public void insertIntoDB()
	{
		//textFieldBookName, textFieldId, textFieldAuthorName, textFieldPublicationYear, textFieldAvailableQuantity;
		//String query="INSERT INTO book VALUES ('"+textFieldBookName.getText()+"','"+textFieldId.getText()+"','"+textFieldAuthorName.getText()+"','"+textFieldPublicationYear.getText()+"','"+textFieldAvailableQuantity.getText()+"');";
		String query="INSERT INTO book VALUES ('"+textFieldBookName.getText()+"','"+textFieldAuthorName.getText()+"','"+textFieldPublicationYear.getText()+"','"+textFieldAvailableQuantity.getText()+"','"+textFieldId.getText()+"');";
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			Statement s= con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();JOptionPane.showMessageDialog(this,"Book Added Successfully");	
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---Row inserted---");
    }
}