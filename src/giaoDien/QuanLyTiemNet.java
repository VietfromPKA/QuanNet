package giaoDien;

import tinhNang.TaoTaiKhoan;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tinhNang.TimKiem;

public class QuanLyTiemNet extends JFrame {

    private final JTable table;

    public QuanLyTiemNet() {
        setTitle("Tiệm Net PKA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel bên trái
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));

        // Các nút chức năng
        JButton btnTaoTaiKhoan = new JButton("Tạo Tài Khoản");
        JButton btnMenu = new JButton("Menu");
        JButton btnTheGame = new JButton("Thẻ game");
        JButton btnTimKiem = new JButton("Tìm Kiếm");
        JButton btnThongKe = new JButton("Thống Kê");
        JButton btnTinhTien = new JButton("Tính Tiền");

        // Thiết lập font cho các nút
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        btnTaoTaiKhoan.setFont(buttonFont);
        btnMenu.setFont(buttonFont);
        btnTheGame.setFont(buttonFont);
        btnTimKiem.setFont(buttonFont);
        btnThongKe.setFont(buttonFont);
        btnTinhTien.setFont(buttonFont);

        // Xử lý sự kiện khi nhấn vào nút Tạo Tài Khoản
        btnTaoTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TaoTaiKhoan(QuanLyTiemNet.this);
            }
        });

        // Xử lý sự kiện khi nhấn vào nút Tính Tiền
        btnTinhTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    calculateFee();
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyTiemNet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        // Xử lý sự kiện khi nhấn vào nút Tìm Kiếm
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Gọi hàm tìm kiếm với giao diện
                TimKiem.timKiemVoiGiaoDien();
            }
        });

        // Thêm các nút vào Panel bên trái
        leftPanel.add(btnTaoTaiKhoan);
        leftPanel.add(btnMenu);
        leftPanel.add(btnTheGame);
        leftPanel.add(btnTimKiem);
        leftPanel.add(btnThongKe);
        leftPanel.add(btnTinhTien);

        // Panel bên phải
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 3, 10, 10));

        // Tạo các nút đại diện cho máy tính
        for (int i = 1; i <= 12; i++) {
            JButton btnMayTinh = new JButton("Máy " + i);
            btnMayTinh.setFont(buttonFont);

            // Xử lý sự kiện khi chọn máy tính
            btnMayTinh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTable(e.getActionCommand());
                }
            });

            rightPanel.add(btnMayTinh);
        }

        // Tạo bảng để hiển thị thông tin máy tính
        String[] columnNames = {"Máy", "Thời gian vào", "Thời gian ra"};
        Object[][] data = new Object[12][3];
        for (int i = 0; i < 12; i++) {
            data[i][0] = "Máy " + (i + 1);
        }
        table = new JTable(data, columnNames);

        // Thiết lập giao diện chính
        setLayout(new BorderLayout(10, 10));
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        setVisible(true);
    }

    // Cập nhật thông tin máy tính trên bảng
    public void updateTable(String computerName) {
        TableUI.updateTable(table, computerName);
    }

    // Tính tiền và xóa thông tin máy vừa thanh toán
    private void calculateFee() throws ParseException {
        TimeIO.calculateFee(table);
        // Xóa thông tin máy vừa thanh toán
        clearComputerInfo();
    }

    // Xóa thông tin máy vừa thanh toán
    private void clearComputerInfo() {
        int selectedRowIndex = table.getSelectedRow();
        if (selectedRowIndex != -1) {
            // Xóa thông tin của máy vừa thanh toán
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.setValueAt(null, selectedRowIndex, i);
            }
        }
    }

    // Mở giao diện quản lý tiệm net từ giao diện LogicNetUI
    public static void openQuanLyTiemNetFromLogicNetUI(LogicNetUI logicNetUI) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                QuanLyTiemNet quanLyTiemNet = new QuanLyTiemNet();
                quanLyTiemNet.setLogicNetUI(logicNetUI);
                quanLyTiemNet.setVisible(true);
            }
        });
    }

    // Phương thức chính
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyTiemNet();
            }
        });
    }

    // Thiết lập giao diện LogicNetUI
    public void setLogicNetUI(LogicNetUI logicNetUI) {}
}
