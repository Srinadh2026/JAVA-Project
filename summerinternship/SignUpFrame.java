package summerinternship;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;

public class SignUpFrame{
    JFrame upFrame = new JFrame();
    ImageIcon signUpImage = new ImageIcon("D:/Java_Project/login1.jpg");
    JLabel signUpImageLabel = new JLabel(signUpImage);
    JLabel label = new JLabel();
    JLabel employeeIdLabel = new JLabel();
    JTextField tfForEmployeeIdLabel = new JTextField();
    JLabel password = new JLabel();
    JPasswordField tfForPassword = new JPasswordField();
    JLabel confirmPassword = new JLabel();
    JPasswordField tfForConfirmPassword = new JPasswordField();
    JButton showHideButton = new JButton("Show Password");
    JButton submit = new JButton(new ImageIcon("D:/Java_Project/submit.png"));
    JCheckBox checkBox = new JCheckBox();
    JLabel checkBoxLabel = new JLabel();
    JButton navigateButton = new JButton(new ImageIcon("D:/Java_Project/backwards_1251365-ezgif.com-gif-maker.png"));
    JButton nextButton = new JButton(new ImageIcon("D:/Java_Project/backwards_1251365-ezgif.com-rotate.png"));

    static Connection connection;

    SignUpFrame(){ 
        // Frame Creation.
        upFrame.setTitle("SignUp");
        upFrame.setSize(500,750);

        // Frame BackGround.
        signUpImageLabel.setBounds(0,0,500,750);
        
        // Center Heading for Frame.
        label.setBounds(175, 150, 150, 30);
        label.setText("Create Account");
        Font f1 = new Font(null, Font.BOLD, 20);
        label.setFont(f1);

        // UserName Field(JLabel).
        employeeIdLabel.setBounds(70,230,200,30);
        employeeIdLabel.setText("EmployeeID: ");
        Font f2Font = new Font(null,Font.BOLD,15);
        employeeIdLabel.setFont(f2Font);

        // UserName Field(JTextField).
        tfForEmployeeIdLabel.setBounds(220,230,200,30);
        tfForEmployeeIdLabel.setFont(f2Font);

        // Password Field(JLabel).
        password.setBounds(70,300,200,30);
        password.setText("Password: ");
        password.setFont(f2Font);

        // Password Field(JPasswordField).
        tfForPassword.setBounds(220,300,200,30);
        tfForPassword.setFont(f2Font);

        // ConfirmPassword Field(JLabel).
        confirmPassword.setBounds(70,370,200,30);
        confirmPassword.setText("Confirm Password: ");
        confirmPassword.setFont(f2Font);

        // ConfirmPassword Field(JPasswordField).
        tfForConfirmPassword.setBounds(220,370,200,30);
        tfForConfirmPassword.setFont(f2Font);

        // CheckBox
        checkBox.setBounds(150,440,20,30);
        checkBox.setOpaque(false);

         // CheckBox Label
         checkBoxLabel.setBounds(175,440,100,30);
         checkBoxLabel.setText("showpassword");

        //Submit button creation.
        submit.setBounds(200,500,95,40);

        // Navigatebutton creation.
        navigateButton.setBounds(30,490,100,50);
        navigateButton.setContentAreaFilled(false);
        navigateButton.setBorderPainted(false);
        navigateButton.setOpaque(false);
  
        // Nextbutton creation.
        nextButton.setBounds(350,490,100,50);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setOpaque(false);
  

        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                if(checkBox.isSelected()){
                    tfForPassword.setEchoChar((char)0);
                    tfForConfirmPassword.setEchoChar((char)0);
                }
                else{
                    tfForPassword.setEchoChar('\u2022'); // Hide characters
                    tfForConfirmPassword.setEchoChar('\u2022');
                }
            }
        });

        //Action of Submit button
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String empString = tfForEmployeeIdLabel.getText();
                String password = new String(tfForPassword.getPassword());
                String confirmPassword = new String(tfForConfirmPassword.getPassword());
                if(!password.equals(confirmPassword)){
                    JOptionPane.showMessageDialog(null, "Passwords do not match", "Error",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try{
                    String insertQuery = "INSERT INTO signup (EmployeeID, Password1) VALUES (?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                    preparedStatement.setString(1, empString);
                    preparedStatement.setString(2, password);
                    
                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Navigation
        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                upFrame.dispose();
                Login login1 = new Login();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                upFrame.dispose();
                RegisterFrame registerFrame1 = new RegisterFrame();
            }
        });

        upFrame.add(nextButton);
        upFrame.add(navigateButton);
        upFrame.add(submit);
        upFrame.add(checkBoxLabel);
        upFrame.add(checkBox);
        upFrame.add(tfForConfirmPassword);
        upFrame.add(confirmPassword);
        upFrame.add(tfForPassword);
        upFrame.add(password);
        upFrame.add(tfForEmployeeIdLabel);
        upFrame.add(employeeIdLabel);
        upFrame.add(label);
        upFrame.add(signUpImageLabel);

        upFrame.setLayout(null);
        upFrame.setVisible(true);
        upFrame.setResizable(false);
        upFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            String url = "jdbc:mysql://localhost:3306/project";
            String dbUsername = "root"; // MySQL username
            String dbPassword = "Vijaya1982"; // MySQL password

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Successfully connected to the database", "Success ",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUpFrame();
    }
}
