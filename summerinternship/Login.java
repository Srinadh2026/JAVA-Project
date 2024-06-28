package summerinternship;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

import RR.SignIn;
import summerinternship.Login.ComboBoxButtonActionListener;

public class Login implements ActionListener {
    JFrame window = new JFrame();
    ImageIcon background = new ImageIcon("D:/Java_Project/firstbg.jpg");
    JLabel backgroundLabel = new JLabel(background);
    JLabel india = new JLabel();
    JLabel indian = new JLabel();
    JLabel indian1 = new JLabel();
    JLabel indian2 = new JLabel();
    ImageIcon imageIcon = new ImageIcon("D:/Java_Project/g-ezgif.com-resize.gif");
    JLabel signUpLabel = new JLabel(imageIcon);
    ImageIcon imageIcon1 = new ImageIcon("D:/Java_Project/g1-ezgif.com-gif-maker.gif");
    JLabel signInLabel1 = new JLabel(imageIcon1);
    JLabel signInLabel = new JLabel();
    JButton signup = new JButton(new ImageIcon("D:/Java_Project/r1.png"));
    JButton signin = new JButton(new ImageIcon("D:/Java_Project/new11.jpg"));
    JButton register = new JButton("Register");
    JLabel labelling = new JLabel();
    JLabel labelling1 = new JLabel(imageIcon1);
    JComboBox<String> comboBox = new JComboBox<>(new String[]{"Click here","New Registration","Update/Delete"});

    Login(){

        window.setTitle("School");
        window.setSize(1000,707);
       
        backgroundLabel.setBounds(0,0,1000,639);

        indian.setText("WELCOME");
        indian.setBounds(80,70,800,50);
        indian.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 50));
        indian.setForeground(Color.red);

        india.setText("TO");
        india.setBounds(170,140,800,50);
        india.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 50));
        india.setForeground(Color.red);

        indian1.setText("INDIAN DIGITAL");
        indian1.setBounds(40,210,800,50);
        indian1.setForeground(Color.red);

        indian2.setText("LEAD SCHOOLS");
        indian2.setBounds(140,280,800,50);
        indian2.setForeground(Color.red);

        Font font = new Font("Arial", Font.BOLD, 45);
        indian1.setFont(font);
        indian2.setFont(font);

        // SignUpLabelling
        signUpLabel.setBounds(50,350,80,80);
        window.add(signUpLabel);


        signup.setBounds(150,365,153,50);
        signup.setOpaque(false);
        signup.setContentAreaFilled(false);
        signup.setBorderPainted(false);
        signup.addActionListener(this);
        window.add(signup);


         // SignInLabelling
        signInLabel.setText("Already have an account? Just log in");
        signInLabel.setBounds(50,430,220,80);
        signInLabel.setForeground(new Color(128,0,0));
        Font font1 = new Font("Arial", Font.BOLD, 16);
        signUpLabel.setFont(font1);
        window.add(signInLabel);

        // SignInLabelling1
        signInLabel1.setBounds(245,430,60,80);
        window.add(signInLabel1);


        signin.setBounds(295,440,150,50);
        signin.setContentAreaFilled(false);
        signin.setBorderPainted(false);
        signin.addActionListener(this);
        window.add(signin);

        comboBox.setBounds(465,510,150,50);
        Font font3 = new Font("Arial",Font.CENTER_BASELINE,14);
        comboBox.setFont(font3);
        comboBox.setBackground(new Color(70,130,180));
        comboBox.setForeground(Color.WHITE);
        comboBox.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e){
                comboBox.setBackground(new Color(70,130,180));
            }
            public void mouseExited(MouseEvent e){
                comboBox.setBackground(new Color(70,130,180));
            }
        });

        comboBox.setRenderer(new ComboBoxButtonRenderer());
        comboBox.addActionListener(new ComboBoxButtonActionListener());

        labelling.setText("Click here to Enroll, Update, Delete Student details");
        labelling.setBounds(50,510,400,50);
        labelling.setForeground(new Color(255,102,102));
        labelling.setFont(font1);
        window.add(labelling);

        // Labelling
        labelling1.setBounds(420,495,60,80);
        window.add(labelling1);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String selectedOption = (String) comboBox.getSelectedItem();
                if("New Registration".equals(selectedOption)){
                    window.dispose();
                    RegisterFrame frame2 = new RegisterFrame();
                }
                if("Update/Delete".equals(selectedOption)){
                    window.dispose();
                    UpdateRegisterFrame frame2 = new UpdateRegisterFrame();
                }
            }
        });

        window.add(comboBox);
        window.add(india);
        window.add(indian);
        window.add(indian1);
        window.add(indian2);
        window.add(backgroundLabel);

        window.setLayout(null);
        window.setResizable(false);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public class ComboBoxButtonRenderer extends JButton implements ListCellRenderer<String>{
        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus){
            setText(value);
            return this;
        }
    }

    public class ComboBoxButtonActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
            String selected =(String) comboBox.getSelectedItem();

            if (selected != null) {
                switch (selected) {
                    case "Update Student Info":
                        JOptionPane.showMessageDialog(null, "Update Student Info Clicked");
                        break;
                    case "Delete Student":
                        JOptionPane.showMessageDialog(null, "Delete Student Clicked");
                        break;
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==signin){
            window.dispose();
            SignInFrame frame1 = new SignInFrame();
        }
        if(e.getSource()==signup){
            window.dispose();
            SignUpFrame frame2 = new SignUpFrame();
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
