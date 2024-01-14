package giaoDien;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import tinhNang.TinhTien;

public class TimeIO {
    // Định dạng thời gian
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    // Tính toán và hiển thị phí sử dụng máy tính
    public static void calculateFee(JTable table) throws ParseException {
        // Tạo đối tượng TinhTien để tính phí
        TinhTien tt = new TinhTien();

        // Duyệt qua từng dòng trong bảng
        for (int i = 0; i < table.getRowCount(); ++i) {
            // Kiểm tra nếu cả thời gian vào và thời gian ra đều có giá trị
            if (table.getValueAt(i, 1) != null && table.getValueAt(i, 2) != null) {
                try {
                    // Chuyển đổi thời gian vào và thời gian ra từ chuỗi sang đối tượng Date
                    Date inTime = sdf.parse((String) table.getValueAt(i, 1));
                    Date outTime = sdf.parse((String) table.getValueAt(i, 2));

                    // Tính phí sử dụng và hiển thị thông báo
                    long fee = tt.tinhTien(inTime, outTime);
                    JOptionPane.showMessageDialog(null, "Số tiền cho " + table.getValueAt(i, 0) + " là " + fee);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
