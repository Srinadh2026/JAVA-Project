package summerinternship;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SignInFrame {
    JFrame inFrame = new JFrame("SignIn");
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    JLabel inTitle = new JLabel();
    JLabel inFrameEmployeeId = new JLabel("EmployeeID: ");
    JTextField tfForEmployeeId = new JTextField();
    JLabel inFramePassword = new JLabel("Password: ");
    JPasswordField tfForPassword = new JPasswordField();
    JButton showDataButton = new JButton("Login");
    JButton forgotLabel = new JButton("forgotpassword?");

    static Connection connection;

    SignInFrame() {
        // Creation of JFrame
        inFrame.setSize(1000, 600);
        inFrame.setLayout(null);

        // Title
        inTitle.setText("LOGIN DETAILS");
        inTitle.setBounds(100,30,170,30);
        Font f4Font = new Font(null,Font.BOLD,20);
        inTitle.setFont(f4Font);
        inTitle.setForeground(new Color(255,140,0));
        inTitle.setBackground(Color.yellow);
        inFrame.add(inTitle);

        Font f5Font = new Font(null,Font.BOLD,15);

        // EmployeeId Label
        inFrameEmployeeId.setBounds(50, 100, 100, 30);
        inFrameEmployeeId.setFont(f5Font);
        inFrameEmployeeId.setForeground(new Color(184,134,11));
        inFrame.add(inFrameEmployeeId);

        // EmployeeId TextField
        tfForEmployeeId.setBounds(150, 100, 150, 30);
        inFrame.add(tfForEmployeeId);

        // Password Label
        inFramePassword.setBounds(50, 180, 100, 30);
        inFramePassword.setFont(f5Font);
        inFramePassword.setForeground(new Color(184,134,11));
        inFrame.add(inFramePassword);

        // Password TextField
        tfForPassword.setBounds(150, 180, 150, 30);
        inFrame.add(tfForPassword);

        // ForgotPassword
        forgotLabel.setBounds(180,200,150,30);
        forgotLabel.setOpaque(false );
        forgotLabel.setContentAreaFilled(false);
        forgotLabel.setBorderPainted(false);
        inFrame.add(forgotLabel);

        // ShowData Button
        showDataButton.setBounds(100, 250, 100, 30);
        inFrame.add(showDataButton);

        // Scroll Pane for Table
        scrollPane.setBounds(350, 50, 600, 400);
        inFrame.add(scrollPane);

        // Set frame properties
        inFrame.setResizable(false);
        inFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inFrame.setVisible(true);

        // Function calling for DataBase Connection.
        connectDatabase();

        // ShowData Button action
        showDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authenticateAndFetchData();
            }
        });
    }

    private void connectDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/project";
            String userName = "root";
            String password = "Vijaya1982";

            connection = DriverManager.getConnection(url, userName, password);
            if (connection != null) {
                JOptionPane.showMessageDialog(null, "Successfully connected to the database", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void authenticateAndFetchData() {
        String employeeId = tfForEmployeeId.getText();
        String password = new String(tfForPassword.getPassword());

        if (authenticateUser(employeeId, password)) {
            fetchData(employeeId);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid EmployeeID or Password", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean authenticateUser(String employeeId, String password) {
        String query = "SELECT Password1 FROM signup WHERE EmployeeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password1");
                return password.equals(storedPassword);
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error authenticating user: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private void fetchData(String employeeId) {
        String query = "SELECT StuRollNo,StuFirstName,StuLastName,Gender,DOB,PhoneNo,Address FROM STUDENTDETAILS WHERE EmployeeID = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, employeeId);
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            model.setColumnCount(0);

            java.sql.ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(rsmd.getColumnLabel(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error fetching data: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        new SignInFrame();
    }
}
