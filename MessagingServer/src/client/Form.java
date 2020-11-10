package client;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
public class Form extends JFrame implements ActionListener {
   JPanel panel;
   JLabel user_label, password_label, message;
   JTextField userName_text;
   JPasswordField password_text;
   JButton submit, cancel;
   protected static String uName=" ";
   
   public Form() {
      // Username Label
      user_label = new JLabel();
      user_label.setText("User Name :");
      userName_text = new JTextField();
      // Password Label
      password_label = new JLabel();
      password_label.setText("Password :");
      password_text = new JPasswordField();

      // Submit
      submit = new JButton("SUBMIT");
      panel = new JPanel(new GridLayout(3, 1));
      panel.add(user_label);
      panel.add(userName_text);
      panel.add(password_label);
      panel.add(password_text);
      message = new JLabel();
      panel.add(message);
      panel.add(submit);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Adding the listeners to components..
      submit.addActionListener(this);
      add(panel, BorderLayout.CENTER);
      setTitle("LogIn");
      setSize(750,350);
      setVisible(true);
   }
   
   protected static String getUserName() {
	   return uName;
   }
   
   public static void main(String[] args) {
      new Form();
   }
   @SuppressWarnings("deprecation")
@Override
   public void actionPerformed(ActionEvent ae) {
      String userName = userName_text.getText();
      @SuppressWarnings("deprecation")
	String password = password_text.getText();
    
      uName=userName;
      
      Autentificate a = new Autentificate();
    
      List<String> data = new ArrayList<String>(); 
      data = a.selectAll();

      for(int i = 0;i < data.size();i++)
      {
    	  if (data.get(i).contains(userName) && data.get(i).contains(password))
    	  {
    		  message.setText(" Hello " + userName + "");
    		  TextDemo.createAndShowGUI();
    		  this.hide();
    		  return;
    	  }  
      }
      
      message.setText(" Invalid user ");

 
   }
}