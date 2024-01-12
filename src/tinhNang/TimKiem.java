package tinhNang;

import Thuc_the.NguoiChoi;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TimKiem {

    public static void timKiemVoiGiaoDien() {
        // Hiển thị hộp thoại để nhập tên tài khoản cần tìm kiếm
        String taiKhoanCanTim = JOptionPane.showInputDialog(null, "Nhập tên tài khoản cần tìm kiếm:");

        // Kiểm tra xem người chơi có tồn tại hay không
        NguoiChoi nguoiChoi = timKiemTaiKhoan(taiKhoanCanTim);

        // Hiển thị kết quả
        if (nguoiChoi != null) {
            JOptionPane.showMessageDialog(null, "Tài khoản: " + nguoiChoi.getTaiKhoan() + "\nSố dư: " + nguoiChoi.getSoDu());
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản hoặc có lỗi khi đọc từ file XML.");
        }
    }

    private static List<NguoiChoi> docTuXml() {
        List<NguoiChoi> nguoiChoiList = new ArrayList<>();
        File file = new File("nguoiChoi.xml");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                while (true) {
                    NguoiChoi nguoiChoi = (NguoiChoi) ois.readObject();
                    nguoiChoiList.add(nguoiChoi);
                }
            } catch (EOFException e) {
                // Đã đọc hết file
            } catch (Exception e) {
               
            }
        } else {
            JOptionPane.showMessageDialog(null, "File XML không tồn tại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return nguoiChoiList;
    }

    private static NguoiChoi timKiemTaiKhoan(String taiKhoan) {
        List<NguoiChoi> danhSachNguoiChoi = docTuXml();
        if (danhSachNguoiChoi != null) {
            for (NguoiChoi nguoiChoi : danhSachNguoiChoi) {
                if (nguoiChoi.getTaiKhoan().equals(taiKhoan)) {
                    return nguoiChoi;
                }
            }
        }
        return null; // Tài khoản không tồn tại hoặc danh sách là null
    }
}
