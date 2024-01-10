package Thuc_the;

import java.io.Serializable;

public class NguoiChoi implements Serializable {
    private String taiKhoan;
    private String matKhau;
    private double soDu;

    public NguoiChoi(String taiKhoan, String matKhau, double soDu) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.soDu = soDu;
    }

    // Getters and setters

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public double getSoDu() {
        return soDu;
    }

    public void setSoDu(double soDu) {
        this.soDu = soDu;
    }
}
