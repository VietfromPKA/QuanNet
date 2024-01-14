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
            // Chỉnh sửa tiêu đề và nút trong hộp thoại
            Object[] options = {"Nạp tiền", "Xóa tài khoản"};
            int choice = JOptionPane.showOptionDialog(null,
                    "Tài khoản: " + nguoiChoi.getTaiKhoan() + "\nSố dư: " + nguoiChoi.getSoDu(),
                    "Thông tin tài khoản",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            // Xử lý sự kiện khi người dùng chọn một trong hai nút
            if (choice == JOptionPane.YES_OPTION) {
                // Người chơi chọn "Nạp tiền"
                napTien(nguoiChoi);
            } else if (choice == JOptionPane.NO_OPTION) {
                // Người chơi chọn "Xóa tài khoản"
                xoaTaiKhoan(nguoiChoi);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy tài khoản hoặc có lỗi khi đọc từ file XML.");
        }
    }

    private static void napTien(NguoiChoi nguoiChoi) {
        // Viết mã xử lý khi người chơi chọn "Nạp tiền"
        // Có thể hiển thị hộp thoại nhập số tiền cần nạp và cập nhật số dư của người chơi
    }

    private static void xoaTaiKhoan(NguoiChoi nguoiChoi) {
        // Viết mã xử lý khi người chơi chọn "Xóa tài khoản"
        // Có thể hiển thị hộp thoại xác nhận và xóa tài khoản khỏi danh sách
    }

    private static List<NguoiChoi> docTuXml() {
        List<NguoiChoi> nguoiChoiList = new ArrayList<>();
        File file = new File("nguoiChoi.xml");

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Object obj = ois.readObject();

                if (obj instanceof List) {
                    // Kiểm tra nếu obj là một danh sách
                    nguoiChoiList = (List<NguoiChoi>) obj;
                } else {
                    // Nếu obj không phải là danh sách, thêm nó vào danh sách mới
                    nguoiChoiList.add((NguoiChoi) obj);
                }
            } catch (EOFException e) {
                // Đã đọc hết file
            } catch (Exception e) {
                e.printStackTrace();
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
