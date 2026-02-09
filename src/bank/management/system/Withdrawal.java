package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    TextField textField;
    JButton b1, b2;
    String pin;

    Withdrawal(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS.10,000");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 700, 35);
        l3.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 400, 35);
        l3.add(label2);

        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("System", Font.BOLD, 22));
        l3.add(textField);

        b1 = new JButton("WITHDRAW");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                String amount = textField.getText();
                Date date = new Date();
                if (textField.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter the Amount you want to Withdraw");
                } else {
                    Connn c = new Connn();

                    // Daily Limit Check (Rs. 20,000)
                    String q_limit = "select sum(amount) as daily_total from bank where pin = ? and type = 'Withdrawal' and date >= DATE_SUB(NOW(), INTERVAL 1 DAY)";
                    java.sql.PreparedStatement pstmt_limit = c.connection.prepareStatement(q_limit);
                    pstmt_limit.setString(1, pin);
                    ResultSet rsLimit = pstmt_limit.executeQuery();
                    int totalToday = 0;
                    if (rsLimit.next()) {
                        totalToday = rsLimit.getInt("daily_total");
                    }
                    if (totalToday + Integer.parseInt(amount) > 20000) {
                        JOptionPane.showMessageDialog(null,
                                "Daily withdrawal limit (Rs. 20,000) exceeded. You have already withdrawn Rs. "
                                        + totalToday + " today.");
                        return;
                    }

                    String q_check = "select * from bank where pin = ?";
                    java.sql.PreparedStatement pstmt_check = c.connection.prepareStatement(q_check);
                    pstmt_check.setString(1, pin);
                    ResultSet resultSet = pstmt_check.executeQuery();
                    int balance = 0;
                    while (resultSet.next()) {
                        if (resultSet.getString("type").equals("Deposit")) {
                            balance += Integer.parseInt(resultSet.getString("amount"));
                        } else {
                            balance -= Integer.parseInt(resultSet.getString("amount"));
                        }
                    }
                    if (balance < Integer.parseInt(amount)) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                        return;
                    }

                    String q = "insert into bank values(?, ?, ?, ?)";
                    java.sql.PreparedStatement pstmt = c.connection.prepareStatement(q);
                    pstmt.setString(1, pin);
                    pstmt.setString(2, date.toString());
                    pstmt.setString(3, "Withdrawal");
                    pstmt.setString(4, amount);
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Rs. " + amount + " Debited Successfully");

                    setVisible(false);
                    new main_Class(pin);

                }
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == b2) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
