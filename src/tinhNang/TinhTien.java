package tinhNang;

import java.util.Date;

public class TinhTien {

    private static final long DON_GIA = 10000; // Đơn giá mỗi 6 giây

    public long tinhTien(Date thoiGianVao, Date thoiGianRa) {
        long thoiGian = thoiGianRa.getTime() - thoiGianVao.getTime();
        // Chia thời gian theo đơn giá mỗi 6 giây
        return (thoiGian / 6000) * DON_GIA;
    }
}
