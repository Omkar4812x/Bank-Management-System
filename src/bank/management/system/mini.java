package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;

    mini(String pin) {
        this.pin = pin;
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel l1 = new JLabel();
        l1.setBounds(20, 140, 400, 200);
        add(l1);

        JLabel l2 = new JLabel("Bank Management System");
        l2.setFont(new Font("System", Font.BOLD, 15));
        l2.setBounds(100, 20, 200, 20);
        add(l2);

        JLabel l3 = new JLabel();
        l3.setBounds(20, 80, 300, 20);
        add(l3);

        JLabel l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);

        try {
            Connn c = new Connn();
            String q1 = "select * from login where pin = ?";
            java.sql.PreparedStatement pstmt1 = c.connection.prepareStatement(q1);
            pstmt1.setString(1, pin);
            ResultSet resultSet = pstmt1.executeQuery();
            while (resultSet.next()) {
                l3.setText("Card Number: " + resultSet.getString("card_number").substring(0, 4) + "XXXXXXXX"
                        + resultSet.getString("card_number").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            Connn c = new Connn();
            String q2 = "select * from bank where pin = ?";
            java.sql.PreparedStatement pstmt2 = c.connection.prepareStatement(q2);
            pstmt2.setString(1, pin);
            ResultSet resultSet = pstmt2.executeQuery();

            while (resultSet.next()) {
                l1.setText(l1.getText() + "<html>" + resultSet.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                        + resultSet.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + resultSet.getString("amount")
                        + "<br><br><html>");

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }
            l4.setText("Your Total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        JButton exportBtn = new JButton("Export to TXT");
        exportBtn.setBounds(150, 500, 150, 25);
        exportBtn.setBackground(new Color(65, 125, 128));
        exportBtn.setForeground(Color.WHITE);
        exportBtn.addActionListener(e -> {
            try {
                String desktopPath = System.getProperty("user.home") + "/Desktop/Bank_Statement.txt";
                java.io.FileWriter writer = new java.io.FileWriter(desktopPath);
                writer.write("----- BANK MANAGEMENT SYSTEM - MINI STATEMENT -----\n\n");
                writer.write(l3.getText() + "\n\n");
                // Clean up HTML tags for the text file
                String transactions = l1.getText().replaceAll("<br>", "\n").replaceAll("&nbsp;", " ")
                        .replaceAll("<html>", "").replaceAll("</html>", "");
                writer.write(transactions + "\n");
                writer.write("\n" + l4.getText() + "\n");
                writer.write("\nGenerated on: " + new java.util.Date());
                writer.close();
                JOptionPane.showMessageDialog(null, "Statement exported successfully to Desktop!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error exporting file: " + ex.getMessage());
            }
        });
        add(exportBtn);

        setVisible(true);
        setSize(400, 600);
        setLocation(20, 20);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
