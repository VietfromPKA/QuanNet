package tinhNang;

import Thuc_the.NguoiChoi;
import giaoDien.QuanLyTiemNet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaoTaiKhoan extends JFrame {

    private final JTextField tfTaiKhoan;
    private final JPasswordField pfMatKhau;
    private final JButton btnTaoTaiKhoan;
    private final QuanLyTiemNet quanLyTiemNet;

    public TaoTaiKhoan(QuanLyTiemNet quanLyTiemNet) {
        this.quanLyTiemNet = quanLyTiemNet;

        setTitle("Tạo Tài Khoản");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel chứa components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        tfTaiKhoan = new JTextField();
        pfMatKhau = new JPasswordField();
        btnTaoTaiKhoan = new JButton("Tạo Tài Khoản");

        panel.add(new JLabel("Tài Khoản:"));
        panel.add(tfTaiKhoan);
        panel.add(new JLabel("Mật Khẩu:"));
        panel.add(pfMatKhau);
        panel.add(new JLabel(""));
        panel.add(btnTaoTaiKhoan);

        btnTaoTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taoTaiKhoan();
            }
        });

        // Sử dụng FlowLayout cho panel
        setLayout(new FlowLayout());
        add(panel);

        setVisible(true);
    }

    private void taoTaiKhoan() {
        String taiKhoan = tfTaiKhoan.getText();
        String matKhau = new String(pfMatKhau.getPassword());

        // Validate taiKhoan and matKhau if needed

        // Check if the account already exists
        if (kiemTraTonTai(taiKhoan)) {
            JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại. Vui lòng chọn tên khác.");
            return;
        }

        NguoiChoi nguoiChoi = new NguoiChoi(taiKhoan, matKhau, 0.0);

        // Save NguoiChoi to XML file
        saveToXml(nguoiChoi);

        // Optionally, perform any other actions needed after creating the account

        JOptionPane.showMessageDialog(this, "Tạo tài khoản thành công!");
        dispose(); // Close the window after account creation

        // Optionally, update the QuanLyTiemNet table with the new account
        quanLyTiemNet.updateTable(taiKhoan);
    }

    private boolean kiemTraTonTai(String taiKhoan) {
        // Check if the account already exists in the XML file
        List<NguoiChoi> nguoiChoiList = readFromXml();
        for (NguoiChoi nguoiChoi : nguoiChoiList) {
            if (nguoiChoi.getTaiKhoan().equals(taiKhoan)) {
                return true;
            }
        }
        return false;
    }

    private static List<NguoiChoi> readFromXml() {
    List<NguoiChoi> nguoiChoiList = new ArrayList<>();
    File file = new File("nguoiChoi.xml");

    if (file.exists()) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    NguoiChoi nguoiChoi = (NguoiChoi) ois.readObject();
                    nguoiChoiList.add(nguoiChoi);
                } catch (EOFException e) {
                    // Đã đọc hết file
                    break;
                }
            }
        } catch (Exception e) {
        }
    } else {
        JOptionPane.showMessageDialog(null, "File XML không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    return nguoiChoiList;
}

    private void saveToXml(NguoiChoi nguoiChoi) {
        // Save NguoiChoi to the XML file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("nguoiChoi.xml", true))) {
            oos.writeObject(nguoiChoi);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi lưu tài khoản vào file XML");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TaoTaiKhoan(null); 
            }
        });
    }
}