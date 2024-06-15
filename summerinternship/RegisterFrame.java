package summerinternship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.border.Border;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.*;


class RoundedTextField extends JTextField {
    private int arcWidth;
    private int arcHeight;

    public RoundedTextField(int columns, int arcWidth, int arcHeight) {
        super(columns);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(false);  // Make the background transparent
        setBorder(new RoundedBorder(arcWidth, arcHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (g instanceof Graphics2D) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        }
        super.paintComponent(g);
    }

    private static class RoundedBorder implements Border {
        private int arcWidth;
        private int arcHeight;

        public RoundedBorder(int arcWidth, int arcHeight) {
            this.arcWidth = arcWidth;
            this.arcHeight = arcHeight;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.GRAY);  // Border color
            g2.drawRoundRect(x, y, width - 1, height - 1, arcWidth, arcHeight);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 4, 4, 4);  // Adjust as needed
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}


public class RegisterFrame {
    JFrame registerFrame = new JFrame();
    ImageIcon registerImageIcon = new ImageIcon("D:/Java_Project/register1.png");
    JLabel registerImagLabel = new JLabel(registerImageIcon);
    JLabel studentlLabel = new JLabel();
    JLabel rollNoLabel = new JLabel();
    RoundedTextField tfForrollNoField = new RoundedTextField(15, 30, 30);
    JLabel employeeLabel = new JLabel();
    RoundedTextField tfForEmployField = new RoundedTextField(15, 30, 30);
    JLabel firstLabel = new JLabel();
    RoundedTextField tfForFirstField = new RoundedTextField(15, 30, 30);
    JLabel lastLabel = new JLabel();
    RoundedTextField tfForLastField = new RoundedTextField(15, 30, 30);
    JLabel genderLabel = new JLabel();
    JRadioButton maleButton = new JRadioButton();
    JRadioButton femaleButton = new JRadioButton();
    JRadioButton othersButton = new JRadioButton();
    ButtonGroup genderGroup = new ButtonGroup();
    JPanel panel = new JPanel();
    JLabel dobLabel = new JLabel();
    RoundedTextField tfFordobField = new RoundedTextField(15, 30, 30);
    JLabel phoneNoLabel = new JLabel();
    RoundedTextField tfForPhoneNoField = new RoundedTextField(15, 30, 30);
    JButton enrollButton = new JButton("Enroll");
    JLabel addressLabel = new JLabel();
    RoundedTextField tfForAddressField = new RoundedTextField(15, 30, 30);
    JCheckBox checkBox = new JCheckBox();
    JLabel checkBoxLabel = new JLabel();
    JButton navigateButton = new JButton(new ImageIcon("D:/Java_Project/left-arrow_11618416-ezgif.com-gif-maker.png"));

    static Connection connection;

    public RegisterFrame() {
        // Creation of Frame.
        registerFrame.setTitle("Register Form");
        registerFrame.setSize(500, 800);

        // Setting Background.
        registerImagLabel.setBounds(0, 0, 500, 800);

        // Main Title
        studentlLabel.setBounds(155, 30, 190, 30);
        studentlLabel.setText("STUDENT DETAILS");
        studentlLabel.setForeground(Color.pink);
        Font f2 = new Font(null, Font.BOLD, 20);
        studentlLabel.setFont(f2);

        // Employee Name
        employeeLabel.setBounds(60, 100, 200, 30);
        employeeLabel.setText("EmployeeID: ");
        Font f3Font = new Font(null, Font.BOLD, 15);
        employeeLabel.setFont(f3Font);

        // Employee Name(Text Field)
        tfForEmployField.setBounds(220, 100, 200, 30);
        tfForEmployField.setFont(f3Font);

        // Student Roll Number.
        rollNoLabel.setBounds(60, 150, 200, 30);
        rollNoLabel.setText("Student Roll Number: ");
        rollNoLabel.setFont(f3Font);

        // Student Roll Number(Text Field).
        tfForrollNoField.setBounds(220, 150, 200, 30);
        tfForrollNoField.setFont(f3Font);

        // Student First Name
        firstLabel.setBounds(60, 200, 200, 30);
        firstLabel.setText("Student First Name: ");
        firstLabel.setFont(f3Font);

        // Student First Name(Text Field)
        tfForFirstField.setBounds(220, 200, 200, 30);
        tfForFirstField.setFont(f3Font);

        // Student Last Name
        lastLabel.setBounds(60, 250, 200, 30);
        lastLabel.setText("Student Last Name: ");
        lastLabel.setFont(f3Font);

        // Student Last Name(Text Field)
        tfForLastField.setBounds(220, 250, 200, 30);
        tfForLastField.setFont(f3Font);

        // Student Gender.
        genderLabel.setBounds(60, 300, 200, 30);
        genderLabel.setText("Gender: ");
        genderLabel.setFont(f3Font);

        // Male Gender RadioButton.
        maleButton.setText("Male");
        maleButton.setBounds(150, 300, 60, 30);
        maleButton.setOpaque(false);

        // Female Gender RadioButton.
        femaleButton.setText("Female");
        femaleButton.setBounds(240, 300, 70, 30);
        femaleButton.setOpaque(false);

        // Others Gender RadioButton.
        othersButton.setText("Others");
        othersButton.setBounds(340, 300, 70, 30);
        othersButton.setOpaque(false);
        othersButton.setFocusPainted(false);

        // Grouping Gender Button.
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(othersButton);

        // Panel Grouping
        panel.add(maleButton);
        panel.add(femaleButton);
        panel.add(othersButton);

        // Date Of Birth
        dobLabel.setBounds(60, 350, 200, 30);
        dobLabel.setText("Date Of Birth: ");
        dobLabel.setFont(f3Font);

        // Date Of Birth(Text Field)
        tfFordobField.setBounds(220, 350, 200, 30);
        tfFordobField.setFont(f3Font);

        // Parent Phone Number
        phoneNoLabel.setBounds(60, 400, 200, 30);
        phoneNoLabel.setText("Parent Phno: ");
        phoneNoLabel.setFont(f3Font);

        // Parent Phone Number(Text Field)
        tfForPhoneNoField.setBounds(220, 400, 200, 30);
        tfForPhoneNoField.setFont(f3Font);

        // Address
        addressLabel.setBounds(60, 450, 200, 30);
        addressLabel.setText("Address: ");
        addressLabel.setFont(f3Font);

        // Address(Text Field)
        tfForAddressField.setBounds(220, 450, 200, 100);
        tfForAddressField.setFont(f3Font);

        // Enroll Button.
        enrollButton.setBounds(150, 650, 150, 30);

        //Check Box
        checkBox.setBounds(60,580,20,30);
        checkBox.setOpaque(false);

         //Check Box Label
        checkBoxLabel.setBounds(80,580,200,30);
        checkBoxLabel.setText("Agree to terms and conditions");

        // NavigateButton.
        navigateButton.setBounds(30,640,100,50);
        navigateButton.setContentAreaFilled(false);
        navigateButton.setBorderPainted(false);
        navigateButton.setOpaque(false);

        enrollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkBox.isSelected()){
                    JOptionPane.showMessageDialog(null, "Please Accept terms and conditions", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else{
                    try {
                        String rollNumber = tfForrollNoField.getText();
                        int IntrollNumber = Integer.parseInt(rollNumber);
                        String employeeIdString = tfForEmployField.getText();
                        int EmployeeIdInt = Integer.parseInt(employeeIdString);
                        String firstName = tfForFirstField.getText();
                        String lastName = tfForLastField.getText();
                        String gender = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "Others");
                        String dateOfBirth = tfFordobField.getText();
                        String phoneNumber = tfForPhoneNoField.getText();
                        String address = tfForAddressField.getText();

                        String signUpQuery = "SELECT COUNT(*) FROM signup WHERE EmployeeID = ?";
                        PreparedStatement signUpStatement = connection.prepareStatement(signUpQuery);
                        signUpStatement.setInt(1, EmployeeIdInt);
                        ResultSet resultSet = signUpStatement.executeQuery();
                        resultSet.next();
                        int count = resultSet.getInt(1);

                        if (count > 0) {
                            String studentDetailsInsertQuery = "INSERT INTO STUDENTDETAILS (EmployeeID, StuRollNo,StuFirstName,StuLastName,Gender,DOB,PhoneNo,Address) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                            PreparedStatement studentDetailsStatement = connection.prepareStatement(studentDetailsInsertQuery);

                            studentDetailsStatement.setInt(1, EmployeeIdInt); 
                            studentDetailsStatement.setInt(2, IntrollNumber);
                            studentDetailsStatement.setString(3, firstName);
                            studentDetailsStatement.setString(4, lastName);
                            studentDetailsStatement.setString(5, gender);
                            studentDetailsStatement.setString(6, dateOfBirth);
                            studentDetailsStatement.setString(7, phoneNumber);
                            studentDetailsStatement.setString(8, address);

                            int rowsAffectedStudent=studentDetailsStatement.executeUpdate();
                            if (rowsAffectedStudent>0) {
                                JOptionPane.showMessageDialog(null, "Successfully Registered", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Registration Failed", "Error", JOptionPane.ERROR_MESSAGE);
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Please Create Employee Account", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    registerFrame.dispose();
            }
        }
        
        });

        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                registerFrame.dispose();
                Login login1 = new Login();
            }
        });

        registerFrame.add(navigateButton);
        registerFrame.add(checkBoxLabel);
        registerFrame.add(checkBox);
        registerFrame.add(enrollButton);
        registerFrame.add(tfForAddressField);
        registerFrame.add(addressLabel);
        registerFrame.add(tfFordobField);
        registerFrame.add(dobLabel);
        registerFrame.add(tfForPhoneNoField);
        registerFrame.add(phoneNoLabel);
        registerFrame.add(panel);
        registerFrame.add(othersButton);
        registerFrame.add(femaleButton);
        registerFrame.add(maleButton);
        registerFrame.add(genderLabel);
        registerFrame.add(tfForLastField);
        registerFrame.add(lastLabel);
        registerFrame.add(tfForFirstField);
        registerFrame.add(firstLabel);
        registerFrame.add(tfForEmployField);
        registerFrame.add(employeeLabel);
        registerFrame.add(tfForrollNoField);
        registerFrame.add(rollNoLabel);
        registerFrame.add(studentlLabel);
        registerFrame.add(registerImagLabel);

        registerFrame.setLayout(null);
        registerFrame.setVisible(true);
        registerFrame.setResizable(false);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            String url = "jdbc:mysql://localhost:3306/project";
            String dbUsername = "root"; 
            String dbPassword = "Vijaya1982";

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
        new RegisterFrame();
    }
}
