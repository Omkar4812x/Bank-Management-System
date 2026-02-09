package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField1;
    JPasswordField passwordField2;
    JButton button1, button2, button3;

    AdminLogin() {
        super("Admin - Bank Management System");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100, 100);
        add(image);

        label1 = new JLabel("ADMIN LOGIN");
        label1.setFont(new Font("Osward", Font.BOLD, 38));
        label1.setBounds(250, 125, 450, 40);
        add(label1);

        label2 = new JLabel("Username:");
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setBounds(125, 190, 375, 30);
        add(label2);

        textField1 = new JTextField(15);
        textField1.setBounds(300, 190, 230, 30);
        textField1.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField1);

        label3 = new JLabel("Password:");
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setBounds(125, 250, 375, 30);
        add(label3);

        passwordField2 = new JPasswordField(15);
        passwordField2.setBounds(300, 250, 230, 30);
        passwordField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField2);

        button1 = new JButton("SIGN IN");
        button1.setBounds(300, 300, 100, 30);
        button1.setBackground(new Color(65, 125, 128));
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setBounds(430, 300, 100, 30);
        button2.setBackground(new Color(65, 125, 128));
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("USER LOGIN");
        button3.setBounds(300, 350, 230, 30);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button3.addActionListener(this);
        add(button3);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                Connn c = new Connn();
                String username = textField1.getText();
                String password = new String(passwordField2.getPassword());

                String q = "select * from admin where username = ? and password = ?";
                java.sql.PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    setVisible(false);
                    new AdminDashboard();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Admin Credentials");
                }
            } else if (e.getSource() == button2) {
                textField1.setText("");
                passwordField2.setText("");
            } else if (e.getSource() == button3) {
                setVisible(false);
                new Login();
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}
