package banking.management.system;

/**
 *
 * @author Jay
 */
import java.awt.*;
import java.awt.event.*;
import static java.lang.Double.parseDouble;
import javax.swing.*;
import java.sql.*;
//import java.util.*;


public class Withdrawl extends JFrame implements ActionListener
{
    JLabel l1,l2,l3,l4;
    JTextField t1,t2;
    JButton b1,b2,b3;
    //JRadioButton r1,r2,r3,r4,r5;
    //JComboBox c1,c2,c3;
    
    
    public Withdrawl() 
    {
        setFont(new Font("System",Font.BOLD,22));
        Font f = getFont();
        FontMetrics fm = getFontMetrics(f);
        int x = fm.stringWidth("Withdrawl");
        int y = fm.stringWidth(" ");
        int z = getWidth() - (3*x);
        int w = z/y;
        String pad = "";
        pad = String.format("%"+w+"s", pad);
        setTitle(pad+"Withdrawl");
        
        
        l1 = new JLabel("Maximum Daily Withdrawal");
        l1.setFont(new Font("System",Font.BOLD,40));
        
        l2 = new JLabel("Is Rs. 10,000");
        l2.setFont(new Font("System",Font.BOLD,40));
        
        l3 = new JLabel("Please Enter your Amount");
        l3.setFont(new Font("System",Font.BOLD,35));
        
        l4 = new JLabel("Enter Pin");
        l4.setFont(new Font("System",Font.BOLD,18));

        
        t1 = new JTextField();
        t1.setFont(new Font("Raleway",Font.BOLD,22));
      
        t2 = new JTextField();
        t2.setFont(new Font("Raleway",Font.BOLD,18));
        
        b1 = new JButton("Withdraw");
        b1.setFont(new Font("System",Font.BOLD,18));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2 = new JButton("Back");
        b2.setFont(new Font("System",Font.BOLD,18));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        b3 = new JButton("Exit");
        b3.setFont(new Font("System",Font.BOLD,18));
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        
        
        
        
        setLayout(null);
        l4.setBounds(590, 10, 80, 30);
        add(l4);
        t2.setBounds(680, 10, 80, 30);
        add(t2);
        l1.setBounds(130, 100, 800, 60);
        add(l1);
        l2.setBounds(270, 160, 800, 60);
        add(l2);
        l3.setBounds(180, 260, 800, 60);
        add(l3);
       
        t1.setBounds(210, 340, 360, 50);
        add(t1);
      
        
       
        b1.setBounds(220, 400, 160, 50);
        add(b1);
        b2.setBounds(400, 400, 160, 50);
        add(b2);
        b3.setBounds(300, 550, 200, 50);
        add(b3); 
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);
        
        setSize(800,800);
        setLocation(500,90);
        setVisible(true);        
    }
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
        String a = t1.getText();
        String b = t2.getText();
        
        if(ae.getSource()==b1)
        {
            if(t1.getText().equals(""))
            {
              JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
            }
            else
            {
                conn cl =  new conn();
                ResultSet rs = cl.s.executeQuery("select * from bank where pin = '"+b+"' ");
                
                if(rs.next())
                {
                    double balance=0;
                    double withdrawamount;
                    String pin = rs.getString(1);
                    balance = rs.getDouble(4);
                    withdrawamount = parseDouble(a);
                    String depositamount=rs.getString(2);
                    balance-=withdrawamount;
                    String ql = "update bank set balance='"+balance+"',withdraw='"+a+"',deposit='"+depositamount+"' where pin='"+b+"'";
                    cl.s.executeUpdate(ql);
                    
                    
                    
                    //if(executeUpdate>0)
                    //{
                    // System.out.println("record inserted....");
                    //} 
                }
                JOptionPane.showMessageDialog(null, "Rs. "+a+" Debited Successfully");
                new Transactions().setVisible(true);
                setVisible(false);
            }
            }else if(ae.getSource()==b2)
            {
                new Transactions().setVisible(true);
                setVisible(false);
            }
            else if(ae.getSource()==b3)
            {
                System.exit(0);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("error: "+e);
        }
    }
    public static void main(String[] args)
    {
    new Withdrawl().setVisible(true);
    }
}
