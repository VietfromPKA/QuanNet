package giaoDien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogicNetUI extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogicNetUI() {
        // Cài đặt cửa sổ
        setTitle("Tiệm Net PKA");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel chính
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel nhập liệu
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 5, 5));

        // Nhãn
        JLabel usernameLabel = new JLabel("Tài khoản:");
        JLabel passwordLabel = new JLabel("Mật khẩu:");

        // Trường văn bản
        usernameField = new JTextField();
        passwordField = new JPasswordField();

        // Thiết lập kích thước ưu tiên cho trường văn bản
        usernameField.setPreferredSize(new Dimension(150, 20));
        passwordField.setPreferredSize(new Dimension(150, 20));

        // Thêm nhãn và trường văn bản vào panel nhập liệu
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // Nút đăng nhập
        JButton loginButton = new JButton("Đăng nhập");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("13")) {
                    // Mở giao diện quản lý tiệm Net khi đăng nhập thành công
                    QuanLyTiemNet.openQuanLyTiemNetFromLogicNetUI(LogicNetUI.this);
                    dispose(); // Đóng cửa sổ đăng nhập
                } else {
                    JOptionPane.showMessageDialog(null, "Tài khoản hoặc mật khẩu không đúng.");
                }
            }
        });

        // Thêm panel nhập liệu và nút đăng nhập vào panel chính
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(loginButton, BorderLayout.SOUTH);

        // Thêm panel chính vào frame
        add(panel);

        // Hiển thị cửa sổ
        setVisible(true);
    }

    public void setQuanLyTiemNet(QuanLyTiemNet quanLyTiemNet) {
        // TODO: Implement if needed
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
