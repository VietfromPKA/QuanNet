package giaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicNetUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogicNetUI() {
        setTitle("Tiệm Net PKA");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));

        JLabel usernameLabel = new JLabel("Tài khoản:");
        JLabel passwordLabel = new JLabel("Mật khẩu:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("13")) {
                    QuanLyTiemNet.openQuanLyTiemNetFromLogicNetUI(LogicNetUI.this);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng.");
                }
            }
        });

        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);

        add(panel);

        setVisible(true);
    }

    public void setQuanLyTiemNet(QuanLyTiemNet quanLyTiemNet) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogicNetUI();
            }
        });
    }
}
