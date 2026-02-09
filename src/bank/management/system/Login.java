package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2, button3;

    Login() {
        super("Bank Management System");

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 100, 100);
        add(image);

        label1 = new JLabel("WELCOME TO ATM");
        label1.setFont(new Font("Osward", Font.BOLD, 38));
        label1.setBounds(200, 125, 450, 40);
        add(label1);

        label2 = new JLabel("Card No:");
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setBounds(125, 190, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(300, 190, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        label3 = new JLabel("PIN:");
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setBounds(125, 250, 375, 30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(300, 250, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("SIGN IN");
        button1.setBounds(300, 300, 100, 30);
        button1.setBackground(Color.BLACK);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("CLEAR");
        button2.setBounds(430, 300, 100, 30);
        button2.setBackground(Color.BLACK);
        button2.setForeground(Color.WHITE);
        button2.addActionListener(this);
        add(button2);

        button3 = new JButton("SIGN UP");
        button3.setBounds(300, 350, 230, 30);
        button3.setBackground(Color.BLACK);
        button3.setForeground(Color.WHITE);
        button3.addActionListener(this);
        add(button3);

        getContentPane().setBackground(Color.WHITE);

        setSize(800, 480);
        setVisible(true);
        setLocation(450, 200);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                Connn c = new Connn();
                String cardno = textField2.getText();
                String pin = new String(passwordField3.getPassword());

                // Check if account is locked
                String lockCheck = "select is_locked from login where card_number = ?";
                java.sql.PreparedStatement pk1 = c.connection.prepareStatement(lockCheck);
                pk1.setString(1, cardno);
                ResultSet rsLock = pk1.executeQuery();
                if (rsLock.next() && rsLock.getBoolean("is_locked")) {
                    JOptionPane.showMessageDialog(null,
                            "Account is locked due to too many failed attempts. Please contact bank.");
                    return;
                }

                String q = "select * from login where card_number = ? and pin = ?";
                java.sql.PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, cardno);
                pstmt.setString(2, pin);
                ResultSet resultSet = pstmt.executeQuery();

                if (resultSet.next()) {
                    // Reset failed attempts on success
                    String reset = "update login set failed_attempts = 0 where card_number = ?";
                    java.sql.PreparedStatement pr = c.connection.prepareStatement(reset);
                    pr.setString(1, cardno);
                    pr.executeUpdate();

                    setVisible(false);
                    new main_Class(pin);
                } else {
                    // Update failed attempts
                    String updateFail = "update login set failed_attempts = failed_attempts + 1 where card_number = ?";
                    java.sql.PreparedStatement pf = c.connection.prepareStatement(updateFail);
                    pf.setString(1, cardno);
                    pf.executeUpdate();

                    // Check if should lock
                    String checkFail = "select failed_attempts from login where card_number = ?";
                    java.sql.PreparedStatement pcf = c.connection.prepareStatement(checkFail);
                    pcf.setString(1, cardno);
                    ResultSet rsFail = pcf.executeQuery();
                    if (rsFail.next() && rsFail.getInt("failed_attempts") >= 3) {
                        String lock = "update login set is_locked = true where card_number = ?";
                        java.sql.PreparedStatement pl = c.connection.prepareStatement(lock);
                        pl.setString(1, cardno);
                        pl.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account LOCKED after 3 failed attempts.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                    }
                }
            } else if (e.getSource() == button2) {
                textField2.setText("");
                passwordField3.setText("");
            } else if (e.getSource() == button3) {
                setVisible(false);
                new Signup();
            }
        } catch (Exception E) {
            // Handle missing columns gracefully by attempting to add them if they don't
            // exist
            if (E.getMessage().contains("failed_attempts")) {
                try {
                    Connn c2 = new Connn();
                    c2.statement.executeUpdate("alter table login add column failed_attempts int default 0");
                    c2.statement.executeUpdate("alter table login add column is_locked boolean default false");
                    JOptionPane.showMessageDialog(null, "Database initialized. Please try login again.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            E.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Login();
    }
}
