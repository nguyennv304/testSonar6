package assignment;

import java.io.Serializable;

public abstract class SinhVien implements Serializable {

    private String hoTen;
    private String maSo;
    private String chuyenNganh;
    private double diemMon1;
    private double diemMon2;
    private double diemMon3;
    private double diemMon4;
    private double diemMon5;
    private double gpa;
    private String email;
    final private String emailDomain = "@fpt.edu.vn";

    public SinhVien(String hoTen, String maSo, String chuyenNganh, double[] diemMon) {
        // in hoa họ và tên sinh viên  
        String[] phanTen = hoTen.split("\\s+");
        for (int i = 0; i < phanTen.length; i++) {
            phanTen[i] = Character.toUpperCase(phanTen[i].charAt(0)) + phanTen[i].substring(1).toLowerCase();
        }
        this.hoTen = String.join(" ", phanTen);

        this.maSo = maSo.substring(0, 2).toUpperCase() + maSo.substring(2);  // in hoa HE
        this.chuyenNganh = chuyenNganh;
        this.diemMon1 = diemMon[0];
        this.diemMon2 = diemMon[1];
        this.diemMon3 = diemMon[2];
        this.diemMon4 = diemMon[3];
        this.diemMon5 = diemMon[4];

        this.email = taoEmail();
        this.gpa = getDiemTongKet();
    }

    public String getMaSo() {
        return maSo;
    }

    public double getDiemTongKet() {
        return (diemMon1 + diemMon2 + diemMon3 + diemMon4 + diemMon5) / 5;
    }
                                   //  0     1     2   3 
    public String taoEmail() {     // phung nguyen ha vu 
        String[] parts = hoTen.split(" "); 
        String ho = parts[0];  // phung  
        String ten = parts[parts.length - 1];  // vu 
        StringBuilder dem = new StringBuilder();   
        for (int i = 1; i < parts.length - 1; i++) {    
            dem.append(parts[i].charAt(0));     // nh
        }
        return (ten + ho.charAt(0) + dem.toString() + maSo + emailDomain).toLowerCase();
    }        // vupnhhe190895@fpt.edu.vn

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChuyenNganh() {
        return chuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        this.chuyenNganh = chuyenNganh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getGpa() {
        return Math.round(gpa * 10.0) / 10.0;  // làm tròn chứ số thập phân thứ 1
    }  

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("Họ tên: %s, Mã số: %s, Chuyên ngành: %s, Điểm tổng kết: %.2f, Email: %s",
                hoTen, maSo, chuyenNganh, gpa, email);
    }
}
