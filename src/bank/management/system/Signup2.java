package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox<String> comboBox, comboBox2, comboBox3, comboBox4, comboBox5;
    JTextField textPan, textAadhar;
    JRadioButton r1, r2, e1, e2;
    JButton next;
    String formno;

    Signup2(String formno) {
        super("APPLICATION FORM");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(150, 0, 100, 100);
        add(image);

        this.formno = formno;

        JLabel l1 = new JLabel("Page 2: Additional Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 30, 600, 40);
        add(l1);

        JLabel l2 = new JLabel("Religion:");
        l2.setFont(new Font("Raleway", Font.BOLD, 18));
        l2.setBounds(100, 120, 100, 30);
        add(l2);

        String religion[] = { "Hindu", "Muslim", "Sikh", "Christian", "Other" };
        comboBox = new JComboBox<>(religion);
        comboBox.setBackground(new Color(252, 208, 76));
        comboBox.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox.setBounds(350, 120, 320, 30);
        add(comboBox);

        JLabel l3 = new JLabel("Category:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 170, 100, 30);
        add(l3);

        String category[] = { "General", "OBC", "SC", "ST", "Other" };
        comboBox2 = new JComboBox<>(category);
        comboBox2.setBackground(new Color(252, 208, 76));
        comboBox2.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox2.setBounds(350, 170, 320, 30);
        add(comboBox2);

        JLabel l4 = new JLabel("Income:");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 220, 100, 30);
        add(l4);

        String income[] = { "Null", "<1,50,000", "<2,50,000", "<5,00,000", "Up to 10,00,000", "Above 10,00,000" };
        comboBox3 = new JComboBox<>(income);
        comboBox3.setBackground(new Color(252, 208, 76));
        comboBox3.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox3.setBounds(350, 220, 320, 30);
        add(comboBox3);

        JLabel l5 = new JLabel("Educational");
        l5.setFont(new Font("Raleway", Font.BOLD, 18));
        l5.setBounds(100, 270, 150, 30);
        add(l5);

        JLabel l11 = new JLabel("Qualification:");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 290, 150, 30);
        add(l11);

        String education[] = { "Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Others" };
        comboBox4 = new JComboBox<>(education);
        comboBox4.setBackground(new Color(252, 208, 76));
        comboBox4.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox4.setBounds(350, 270, 320, 30);
        add(comboBox4);

        JLabel l6 = new JLabel("Occupation:");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(100, 340, 150, 30);
        add(l6);

        String occupation[] = { "Salaried", "Self-Employed", "Business", "Student", "Retired", "Others" };
        comboBox5 = new JComboBox<>(occupation);
        comboBox5.setBackground(new Color(252, 208, 76));
        comboBox5.setFont(new Font("Raleway", Font.BOLD, 14));
        comboBox5.setBounds(350, 340, 320, 30);
        add(comboBox5);

        JLabel l7 = new JLabel("PAN Number:");
        l7.setFont(new Font("Raleway", Font.BOLD, 18));
        l7.setBounds(100, 390, 150, 30);
        add(l7);

        textPan = new JTextField();
        textPan.setFont(new Font("Raleway", Font.BOLD, 14));
        textPan.setBounds(350, 390, 320, 30);
        add(textPan);

        JLabel l8 = new JLabel("Aadhar Number:");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 440, 180, 30);
        add(l8);

        textAadhar = new JTextField();
        textAadhar.setFont(new Font("Raleway", Font.BOLD, 14));
        textAadhar.setBounds(350, 440, 320, 30);
        add(textAadhar);

        JLabel l9 = new JLabel("Senior Citizen:");
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(100, 490, 180, 30);
        add(l9);

        r1 = new JRadioButton("Yes");
        r1.setFont(new Font("Raleway", Font.BOLD, 14));
        r1.setBackground(new Color(252, 208, 76));
        r1.setBounds(350, 490, 100, 30);
        add(r1);

        r2 = new JRadioButton("No");
        r2.setFont(new Font("Raleway", Font.BOLD, 14));
        r2.setBackground(new Color(252, 208, 76));
        r2.setBounds(460, 490, 100, 30);
        add(r2);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);

        JLabel l10 = new JLabel("Existing Account:");
        l10.setFont(new Font("Raleway", Font.BOLD, 18));
        l10.setBounds(100, 540, 180, 30);
        add(l10);

        e1 = new JRadioButton("Yes");
        e1.setFont(new Font("Raleway", Font.BOLD, 14));
        e1.setBackground(new Color(252, 208, 76));
        e1.setBounds(350, 540, 100, 30);
        add(e1);

        e2 = new JRadioButton("No");
        e2.setFont(new Font("Raleway", Font.BOLD, 14));
        e2.setBackground(new Color(252, 208, 76));
        e2.setBounds(460, 540, 100, 30);
        add(e2);

        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(e1);
        buttonGroup1.add(e2);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBackground(Color.WHITE);
        next.setForeground(Color.BLACK);
        next.setBounds(570, 640, 100, 30);
        next.addActionListener(this);
        add(next);

        setLayout(null);
        setSize(850, 750);
        setLocation(450, 80);
        getContentPane().setBackground(new Color(252, 208, 76));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String rel = (String) comboBox.getSelectedItem();
        String cate = (String) comboBox2.getSelectedItem();
        String inc = (String) comboBox3.getSelectedItem();
        String edu = (String) comboBox4.getSelectedItem();
        String occ = (String) comboBox5.getSelectedItem();

        String pan = textPan.getText();
        String addhar = textAadhar.getText();

        String scitizen = r1.isSelected() ? "Yes" : "No";
        String eaccount = e1.isSelected() ? "Yes" : "No";

        try {
            if (pan.equals("") || addhar.equals("")) {
                JOptionPane.showMessageDialog(null, "Fill all the fields");
            } else {
                Connn c = new Connn();
                String q = "insert into signuptwo values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                java.sql.PreparedStatement pstmt = c.connection.prepareStatement(q);
                pstmt.setString(1, formno);
                pstmt.setString(2, rel);
                pstmt.setString(3, cate);
                pstmt.setString(4, inc);
                pstmt.setString(5, edu);
                pstmt.setString(6, occ);
                pstmt.setString(7, pan);
                pstmt.setString(8, addhar);
                pstmt.setString(9, scitizen);
                pstmt.setString(10, eaccount);
                pstmt.executeUpdate();

                new Signup3(formno);
                setVisible(false);
            }
        } catch (Exception E) {
            E.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + E.getMessage());
        }
    }

    public static void main(String[] args) {
        new Signup2("");
    }
}
