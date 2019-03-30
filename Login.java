import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
    JLabel id,pass,image;
	JTextField idTF;
	JPasswordField passPF;
	JButton login,Exit,SignUp;
	private boolean passFlag=false, uIdFlag=false;
	ImageIcon img;
	//JButton buttonLogin, buttonClose;
	JPanel panel;
	//LiberianProfile l;
	public Login ()
	{
		super(" LOGIN");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 550);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		idTF = new JTextField();                        
		idTF.setBounds(380, 150, 150, 30);              
		panel.add(idTF);
		
		
		passPF = new JPasswordField();                        
		passPF.setBounds(380, 200, 150, 30);
		passPF.setEchoChar('*');
		panel.add(passPF);
		
		
		id = new JLabel("ID   : ");
		id.setBounds(230, 150, 85, 30);
		id.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(id);
		
        pass= new JLabel("Password : ");
		pass.setBounds(230, 200, 85, 30);
		pass.setFont(new Font("Nirmala UI",Font.PLAIN,15));
		panel.add(pass);
		
		
		login = new JButton("Login");
		login.setBounds(280, 300, 80, 30);
		login.addActionListener(this);
		panel.add(login);
		
		Exit = new JButton("Exit");
		Exit.setBounds(340, 370, 90, 30);
		Exit.addActionListener(this);
		panel.add(Exit);

		SignUp = new JButton("Sign Up");
		SignUp.setBounds(410, 300, 80, 30);
		SignUp.addActionListener(this);
		panel.add(SignUp);
		
		
		img = new ImageIcon("00.jpg");
		image = new JLabel(img);
		image.setBounds(0, 0,850,550);
		panel.add(image);
		
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String elementText = ae.getActionCommand();
		if(elementText.equals(Exit.getText()))
		{
		    System.exit(0);
		}
		else if(elementText.equals(login.getText()))
		{
		if(idTF.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Missing ID");
			
		}else if(passPF.getText().equals(""))
		{
			JOptionPane.showMessageDialog(this,"Missing Password");
		}
		else{
			login_info();
		}
		}
		else if(elementText.equals(SignUp.getText()))
		{
			//login_info();
		SignUp su=new SignUp();
		this.setVisible(false);
		su.setVisible(true);
		}
		
		else{}
	}
	
		public void login_info()
	{
        String query = "SELECT * FROM `Login` ;";  
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/d1t1","root","");
			st = con.createStatement();//create statement
			rs = st.executeQuery(query);//getting result
			
			while(rs.next())
			{
                String pass = rs.getString("password");			
			    String id = rs.getString("userId");
				String stss = rs.getString("status");
                int sts=Integer.parseInt(stss);
				
				if(id.equals(idTF.getText()))
					{
						uIdFlag=true;
						passFlag=true;
						if(pass.equals(passPF.getText()))
							{
								if(sts==0){
								System.out.println("an Admin Logged IN " ); //task starts here
								AdminProfile admPro = new AdminProfile(id);
								this.setVisible(false);
								admPro.setVisible(true);
								}
								else if(sts==1)
								{
									System.out.println("A user Logged IN " );
									CustomerProfile sb = new CustomerProfile(id);
									this.setVisible(false);
									sb.setVisible(true);
								}
								else if(sts==2)
								{
									System.out.println("A Employee Logged IN " );
									EmployeeProfile emp = new EmployeeProfile(id);
									this.setVisible(false);
									emp.setVisible(true);
								}
								passFlag=false;
							}
					}
			}	
			if(uIdFlag==false){JOptionPane.showMessageDialog(this,"Wrong ID");}
			if(passFlag==true){JOptionPane.showMessageDialog(this,"Wrong password");passFlag=false;}
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