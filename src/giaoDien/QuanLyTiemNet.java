package giaoDien;

import tinhNang.TaoTaiKhoan;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;



public class QuanLyTiemNet extends JFrame {
   
    private final JTable table;

    public QuanLyTiemNet() {
        setTitle("Tiệm Net PKA");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel chứa 6 chức năng bên trái
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnTaoTaiKhoan = new JButton("Tạo Tài Khoản");
        JButton btnXoaMayTinh = new JButton("Xóa Máy Tính");
        JButton btnThemMayTinh = new JButton("Thêm Máy Tính");
        JButton btnTimKiem = new JButton("Tìm Kiếm");
        JButton btnThongKe = new JButton("Thống Kê");
        JButton btnTinhTien = new JButton("Tính Tiền");

        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        btnTaoTaiKhoan.setFont(buttonFont);
        btnXoaMayTinh.setFont(buttonFont);
        btnThemMayTinh.setFont(buttonFont);
        btnTimKiem.setFont(buttonFont);
        btnThongKe.setFont(buttonFont);
        btnTinhTien.setFont(buttonFont);
        
        btnTaoTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the TaoTaiKhoan window when the "Tạo Tài Khoản" button is pressed
                new TaoTaiKhoan(QuanLyTiemNet.this);
            }
        });
        
        btnTinhTien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Calculate the fee when the "Tính Tiền" button is pressed
                    calculateFee();
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyTiemNet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        

        leftPanel.add(btnTaoTaiKhoan);
        leftPanel.add(btnXoaMayTinh);
        leftPanel.add(btnThemMayTinh);
        leftPanel.add(btnTimKiem);
        leftPanel.add(btnThongKe);
        leftPanel.add(btnTinhTien);

        // Panel chứa danh sách 12 máy tính bên phải
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(4, 3, 10, 10));

        for (int i = 1; i <= 12; i++) {
            JButton btnMayTinh = new JButton("Máy " + i);
            btnMayTinh.setFont(buttonFont);
            btnMayTinh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTable(e.getActionCommand());
                }
            });
            rightPanel.add(btnMayTinh);
        }

        String[] columnNames = {"Máy", "Thời gian vào", "Thời gian ra"};
        Object[][] data = new Object[12][3];
        for (int i = 0; i < 12; i++) {
            data[i][0] = "Máy " + (i + 1);
        }
        table = new JTable(data, columnNames);

        setLayout(new BorderLayout(10, 10));

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
        add(new JScrollPane(table), BorderLayout.SOUTH);

        setVisible(true);
    }

    public void updateTable(String computerName) {
        TableUI.updateTable(table, computerName);
    }

    private void calculateFee() throws ParseException {
        TimeIO.calculateFee(table);
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuanLyTiemNet();
            }
        });
    }

    private void setLogicNetUI(LogicNetUI logicNetUI) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
