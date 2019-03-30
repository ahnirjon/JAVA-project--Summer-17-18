import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
public class BorrowBook extends JFrame implements ActionListener
{//borrowIdTF
    JLabel bookId,userId,borrowId,image;
	JTextField bookIdTF, userIdTF,borrowIdTF;
	JButton borrow,Return,back,logout;
	ImageIcon img;
	JPanel panel;
	private boolean passFlag=false, uIdFlag=false;
	String BorrowId,curDate,retDate;
	String id;
	public BorrowBook (String id)
	{
		super("Borrow Book");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		
		panel = new JPanel();
		panel.setLayout(null);

		this.id=id;
		
		borrowId = new JLabel("Borrow Id   : ");
		borrowId.setBounds(100, 50, 85, 30);
		borrowId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(borrowId);

		borrowIdTF = new JTextField();                        
		borrowIdTF.setBounds(240, 50, 150, 30);                
		panel.add(borrowIdTF);
		
		bookId = new JLabel("Book Id   : ");
		bookId.setBounds(100, 120, 85, 30);
		bookId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(bookId);

		bookIdTF = new JTextField();                        
		bookIdTF.setBounds(240, 120, 150, 30);                
		panel.add(bookIdTF);
		
        userId= new JLabel("User Id : ");
		userId.setBounds(100, 190, 85, 30);
		userId.setBackground(Color.GREEN);
		userId.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(userId);

		userIdTF = new JTextField();                        
		userIdTF.setBounds(240, 190, 150, 30);                
		panel.add(userIdTF);
		
		
		logout = new JButton("Logout");
		logout.setBounds(650, 40, 80, 30);
		logout.addActionListener(this);
		panel.add(logout);
		
		Return = new JButton("Return");
		Return.setBounds(410, 300, 80, 30);
		Return.addActionListener(this);
		panel.add(Return);

		borrow = new JButton("Borrow");
		borrow.setBounds(280, 300, 80, 30);
		borrow.addActionListener(this);
		panel.add(borrow);

		back = new JButton("Back");
		back.setBounds(540, 300, 80, 30);
		back.addActionListener(this);
		panel.add(back);
		
		
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		
		String buttonClicked = ae.getActionCommand();
			
		if(buttonClicked.equals(logout.getText())) //Mytaskhere to connect 
		{//JOptionPane.showMessageDialog(this,"Wrong password");
			
			Login a= new Login();
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(back.getText())) //Mytaskhere to connect 
		{//JOptionPane.showMessageDialog(this,"Wrong password");
			
			EmployeeProfile a= new EmployeeProfile(id);
			a.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(Return.getText()))
		{
//bookIdTF, userIdTF
				if(borrowIdTF.getText().equals(""))	
		{
			JOptionPane.showMessageDialog(this,"Missing Informations");
			
		}	else{
		generateBorrowId();
             UpdateDB(0);
	}
		}
			
	//	}
		else if(buttonClicked.equals(borrow.getText()))
		{
						if(bookIdTF.getText().equals("") || userIdTF.getText().equals(""))	
		{
			JOptionPane.showMessageDialog(this,"Missing Informations");
			
		}	else{
		 UpdateDB(1);
	}
		   
		}
		
	}
	
		public void UpdateDB(int a)
	{
		String query="",q2="",q3="";
	      query = "SELECT availableQuantity FROM `book` where  bookId='"+bookIdTF.getText()+"';"; 
	   //   q2 = "update administrator set ad_Pass='"+password_field.getText()+"' where admin_Id='"+a_id+"'";
		//  q2="INSERT INTO borrowinfo VALUES ('"+UserId+"','"+pfPassword.getText()+"','"+sts+"');";
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1", "root", "");
			Statement stm = con.createStatement();
			Statement s= con.createStatement();
Statement s2= con.createStatement();
            ResultSet rs = null;//to get row by row result from DB
		    rs=stm.executeQuery(query);
			if(a==0){
			JOptionPane.showMessageDialog(this,"Book Returned");}
			else{JOptionPane.showMessageDialog(this,"Book Borrowed");}
//stm.close();
           // int AvlBook=Integer.parseInt(rs.getString("availableQuantity")); 
int AvlBook=0;
while(rs.next()){  
           AvlBook=Integer.parseInt(rs.getString("availableQuantity"));  
}	
			System.out.println("avlbook " + AvlBook);
            if(a==1){
            if(AvlBook>1)
          {
             AvlBook=AvlBook-1;
            q2 = "update book set availableQuantity='"+AvlBook+"' where bookId='"+bookIdTF.getText()+"';"; 
          generateBorrowId();
          q3="INSERT INTO borrowinfo VALUES ('"+BorrowId+"','"+bookIdTF.getText()+"','"+userIdTF.getText()+"','"+curDate+"','"+retDate+"');";
	
          }
else{
JOptionPane.showMessageDialog(this,"Not Available");
}
}
else if(a==0)
{
             AvlBook=AvlBook+1;
          //  q2 = "update book set availableQuantity='"+AvlBook+"' where bookId='"+bookIdTF.getText()+"';"; 
q2="update book set availableQuantity=availableQuantity+1 where bookId=(select bookId from borrowinfo where borrowId='"+borrowIdTF.getText()+"');";
           q3="DELETE from borrowinfo where borrowId='"+borrowIdTF.getText()+"';";
}

			s.execute(q2);
			
            s2.execute(q3);
			s.close();
			s2.close();
			con.close();
			rs.close();	
			//JOptionPane.showMessageDialog(this,"Book Successfully Returned");
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
		
		System.out.println("---done---");
    }
	
	 public void generateBorrowId()
	{
      Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmmss");  
      LocalDateTime now = LocalDateTime.now();   
    	String  x=dtf.format(now).toString();
	  calendar.add(Calendar.DATE, +7);
      Date date = calendar.getTime();
    DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    curDate=dtf2.format(now).toString();
      int day = calendar.get(Calendar.DATE);
      int month = calendar.get(Calendar.MONTH) + 1;
      int year = calendar.get(Calendar.YEAR);
      retDate=""+year+"-"+month+"-"+day;
      String d=""+day+""+month+""+year+""+x;
     BorrowId=d;
     
     System.out.println("Borrow " + BorrowId);
	}

}