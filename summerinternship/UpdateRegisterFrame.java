package summerinternship;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.*;

import com.mysql.cj.protocol.Resultset;

public class UpdateRegisterFrame {
    JFrame updateFrame = new JFrame();
    ImageIcon updatImageIcon = new ImageIcon("D:/Java_Project/register1.png");
    JLabel updateImagLabel = new JLabel(updatImageIcon);
    JLabel updateRegisterLabel = new JLabel();
    JLabel updateRollNoLabel = new JLabel();
    TextField tfForUpdateRollNoField = new TextField();
    JLabel employeeUpdateLabel = new JLabel();
    TextField tfForUpdateEmployField = new TextField();
    JLabel updateFirstLabel = new JLabel();
    TextField tfForUpdateFirstField = new  TextField();
    JLabel updateLastLabel = new JLabel();
    TextField tfForUpdateLastField = new  TextField();
    JLabel updateGenderLabel = new JLabel();
    JRadioButton updateMaleButton = new JRadioButton();
    JRadioButton updateFemaleButton = new JRadioButton();
    JRadioButton updateOthersButton = new JRadioButton();
    ButtonGroup updateGenderGroup = new ButtonGroup();
    JPanel updatePanel = new JPanel();
    JLabel updateDOBLabel = new JLabel();
    TextField tfForUpdateDOBField = new  TextField();
    JLabel updatePhoneNoLabel = new JLabel();
    TextField tfForUpdatePhoneNoField = new  TextField();
    JLabel updateAddressLabel = new JLabel();
    TextField tfForUpdateAddressField = new  TextField();
    JButton updateButton = new JButton("Update");
    JLabel deleteRegisterLabel = new JLabel();
    JLabel deleteRollNoLabel = new JLabel();
    TextField tfForDeleteRollNoField = new  TextField( );
    JButton deleteButton = new JButton("Delete");
    JButton navigateButton = new JButton(new ImageIcon("D:/Java_Project/left-arrow_11618416-ezgif.com-gif-maker.png"));

    static Connection connection;

    UpdateRegisterFrame(){
        //Frame Creation
        updateFrame.setTitle("Update Frame");
        updateFrame.setSize(500,800);

        //Setting Image lables
        updateImagLabel.setBounds(0, 0, 500, 800);

        // Main Title
        updateRegisterLabel.setBounds(155, 15, 190, 30);
        updateRegisterLabel.setText("Update DETAILS");
        updateRegisterLabel.setForeground(Color.pink);
        Font updateFont = new Font(null,Font.BOLD,20);
        updateRegisterLabel.setFont(updateFont);

        // Student Roll Number.
        updateRollNoLabel.setBounds(60, 70, 200, 30);
        updateRollNoLabel.setText("Student Roll Number: ");
        Font f3Font = new Font(null, Font.BOLD, 15);
        updateRollNoLabel.setFont(f3Font);

        // Student Roll Number(Text Field).
        tfForUpdateRollNoField.setBounds(220, 70, 200, 30);
        tfForUpdateRollNoField.setFont(f3Font);

        // Employee Name
        employeeUpdateLabel.setBounds(60, 120, 200, 30);
        employeeUpdateLabel.setText("EmployeeID: ");
        employeeUpdateLabel.setFont(f3Font);

        //Employee Name(JLabel)
        tfForUpdateEmployField.setBounds(220, 120, 200, 30);
        tfForUpdateEmployField.setFont(f3Font);

        // Student First Name
        updateFirstLabel.setBounds(60, 170, 200, 30);
        updateFirstLabel.setText("Student First Name: ");
        updateFirstLabel.setFont(f3Font);

        // Student First Name(Text Field)
        tfForUpdateFirstField.setBounds(220, 170, 200, 30);
        tfForUpdateFirstField.setFont(f3Font);

        // Student Last Name
        updateLastLabel.setBounds(60, 220, 200, 30);
        updateLastLabel.setText("Student Last Name: ");
        updateLastLabel.setFont(f3Font);

        // Student Last Name(Text Field)
        tfForUpdateLastField.setBounds(220, 220, 200, 30);
        tfForUpdateLastField.setFont(f3Font);

        // Student Gender.
        updateGenderLabel.setBounds(60, 270, 200, 30);
        updateGenderLabel.setText("Gender: ");
        updateGenderLabel.setFont(f3Font);

        // Male Gender RadioButton.
        updateMaleButton.setText("Male");
        updateMaleButton.setBounds(150, 270, 60, 30);
        updateMaleButton.setOpaque(false);

        // Female Gender RadioButton.
        updateFemaleButton.setText("Female");
        updateFemaleButton.setBounds(240, 270, 70, 30);
        updateFemaleButton.setOpaque(false);

        // Others Gender RadioButton.
        updateOthersButton.setText("Others");
        updateOthersButton.setBounds(340, 270, 70, 30);
        updateOthersButton.setOpaque(false);
        updateOthersButton.setFocusPainted(false);

        // Grouping Gender Button.
        updateGenderGroup.add(updateMaleButton);
        updateGenderGroup.add(updateFemaleButton);
        updateGenderGroup.add(updateOthersButton);

        // Panel Grouping
        updatePanel.add(updateMaleButton);
        updatePanel.add(updateFemaleButton);
        updatePanel.add(updateOthersButton);

        // Date Of Birth
        updateDOBLabel.setBounds(60, 320, 200, 30);
        updateDOBLabel.setText("Date Of Birth: ");
        updateDOBLabel.setFont(f3Font);

        // Date Of Birth(Text Field)
        tfForUpdateDOBField .setBounds(220, 320, 200, 30);
        tfForUpdateDOBField .setFont(f3Font);

        // Parent Phone Number
        updatePhoneNoLabel.setBounds(60, 370, 200, 30);
        updatePhoneNoLabel.setText("Parent Phno: ");
        updatePhoneNoLabel.setFont(f3Font);

        // Parent Phone Number(Text Field)
        tfForUpdatePhoneNoField.setBounds(220, 370, 200, 30);
        tfForUpdatePhoneNoField.setFont(f3Font);

        // Address
        updateAddressLabel.setBounds(60, 420, 200, 30);
        updateAddressLabel.setText("Address: ");
        updateAddressLabel.setFont(f3Font);

        // Address(Text Field)
        tfForUpdateAddressField.setBounds(220, 420, 200, 100);
        tfForUpdateAddressField.setFont(f3Font);

        //Update Button
        updateButton.setBounds(150, 540, 150, 30);

         // Main Title(Delete)
         deleteRegisterLabel.setBounds(155, 600, 190, 30);
         deleteRegisterLabel.setText("Delete DETAILS");
         deleteRegisterLabel.setForeground(new Color(255,51,153));
         deleteRegisterLabel.setFont(updateFont);

        //Delete Student Roll Number.
        deleteRollNoLabel.setBounds(60, 650, 200, 30);
        deleteRollNoLabel.setText("Student Roll Number: ");
        updateRollNoLabel.setFont(f3Font);

        // Student Roll Number(Text Field).
        tfForDeleteRollNoField.setBounds(220, 650, 200, 30);
        tfForDeleteRollNoField.setFont(f3Font);

        //Delete Button
        deleteButton.setBounds(150, 700, 150, 30);

        // Navigate Button.
        navigateButton.setBounds(30,690,100,50);
        navigateButton.setContentAreaFilled(false);
        navigateButton.setBorderPainted(false);
        navigateButton.setOpaque(false);
    
        //Update Button Action
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String stringRollNumber = tfForUpdateRollNoField.getText();
                    int updateRollNo = Integer.parseInt(stringRollNumber);
                    String employeeid = tfForUpdateEmployField.getText();
                    Long employeeidInt = employeeid.isEmpty() ? null :Long.parseLong(employeeid);
                    String firstName = tfForUpdateFirstField.getText();
                    String lastName = tfForUpdateLastField.getText();
                    String gender = updateMaleButton.isSelected() ? "Male" : (updateFemaleButton.isSelected() ? "Female" : "Others");
                    String dateOfBirth = tfForUpdateDOBField.getText();
                    String phoneNumber = tfForUpdatePhoneNoField.getText();
                    String address = tfForUpdateAddressField.getText();
        
                    StringBuilder sql = new StringBuilder("UPDATE STUDENTDETAILS SET ");
                    boolean setComma = false;
        
                    if (employeeidInt!=null) {
                        sql.append("EmployeeID = ?");
                        setComma = true;
                    }
                    if (!firstName.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("StuFirstName = ?");
                        setComma = true;
                    }
                    if (!lastName.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("StuLastName = ?");
                        setComma = true;
                    }
                    if (!gender.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("Gender = ?");
                        setComma = true;
                    }
                    if (!dateOfBirth.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("DOB = ?");
                        setComma = true;
                    }
                    if (!phoneNumber.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("PhoneNo = ?");
                        setComma = true;
                    }
                    if (!address.isEmpty()) {
                        if (setComma) sql.append(", ");
                        sql.append("Address = ?");
                        setComma = true;
                    }
                    sql.append(" WHERE StuRollNo = ?;");
        
                    PreparedStatement UpdateStudentDetailsStatement = connection.prepareStatement(sql.toString());
        
                    int index = 1;
                    if (employeeidInt!=null) UpdateStudentDetailsStatement.setLong(index++, employeeidInt);
                    if (!firstName.isEmpty()) UpdateStudentDetailsStatement.setString(index++, firstName);
                    if (!lastName.isEmpty()) UpdateStudentDetailsStatement.setString(index++, lastName);
                    if (!gender.isEmpty()) UpdateStudentDetailsStatement.setString(index++, gender);
                    if (!dateOfBirth.isEmpty()) UpdateStudentDetailsStatement.setString(index++, dateOfBirth);
                    if (!phoneNumber.isEmpty()) UpdateStudentDetailsStatement.setString(index++, phoneNumber);
                    if (!address.isEmpty()) UpdateStudentDetailsStatement.setString(index++, address);
                    UpdateStudentDetailsStatement.setInt(index, updateRollNo);
        
                    int updateRowsAffectedStudent = UpdateStudentDetailsStatement.executeUpdate();
                    if (updateRowsAffectedStudent > 0) {
                        JOptionPane.showMessageDialog(null, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records Found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Invalid input for Roll Number or Phone Number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                updateFrame.dispose();
            }
        });

        //Delete Button Action
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String dStringRollNumber = tfForDeleteRollNoField.getText().trim();
                    // if (dStringRollNumber.isEmpty()) {
                    //     JOptionPane.showMessageDialog(null, "Please enter a roll number", "Input Error", JOptionPane.ERROR_MESSAGE);
                    //     return;
                    // }
                    int deleteRollNo = Integer.parseInt(dStringRollNumber);
                    String deleteQuery = "DELETE FROM STUDENTDETAILS WHERE StuRollNo = ?";
                    PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
                    deleteStatement.setInt(1, deleteRollNo); // Correct parameter index
                    int dRowsAffected = deleteStatement.executeUpdate();
                    if (dRowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records found", "Failure", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid roll number format", "Input Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                updateFrame.dispose();
            }
        });
        
        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                updateFrame.dispose();
                Login login2 = new Login();
            }
        });

        updateFrame.add(navigateButton);
        updateFrame.add(deleteButton);
        updateFrame.add(deleteRegisterLabel);
        updateFrame.add(tfForDeleteRollNoField);
        updateFrame.add(deleteRollNoLabel);
        updateFrame.add(updateButton);
        updateFrame.add(tfForUpdateAddressField);
        updateFrame.add(updateAddressLabel);
        updateFrame.add(tfForUpdatePhoneNoField);
        updateFrame.add(updatePhoneNoLabel);
        updateFrame.add(tfForUpdateDOBField);
        updateFrame.add(updateDOBLabel);
        updateFrame.add(updatePanel);
        updateFrame.add(updateOthersButton);
        updateFrame.add(updateFemaleButton);
        updateFrame.add(updateMaleButton);
        updateFrame.add(updateGenderLabel);
        updateFrame.add(tfForUpdateLastField);
        updateFrame.add(updateLastLabel);
        updateFrame.add(tfForUpdateFirstField);
        updateFrame.add(updateFirstLabel);
        updateFrame.add(tfForUpdateRollNoField);
        updateFrame.add(updateRollNoLabel);
        updateFrame.add(tfForUpdateEmployField);
        updateFrame.add(employeeUpdateLabel);
        updateFrame.add(updateRegisterLabel);
        updateFrame.add(updateImagLabel);
        updateFrame.setLayout(null);
        updateFrame.setVisible(true);
        updateFrame.setResizable(false);
        updateFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
    public static void main(String[] args){
        new UpdateRegisterFrame();
    }
}
