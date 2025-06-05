package assignment;

public class SinhVienKyThuat extends SinhVien {
    public static final String[] MON_HOC = {"Toán kỹ thuật", "Vật lý", "Hóa học", "Lập trình", "Cơ học"};

    private double[] diemMon;

    public SinhVienKyThuat(String hoTen, String maSo, String chuyenNganh, double[] diemMon) {
        super(hoTen, maSo, chuyenNganh, diemMon);
        this.diemMon = diemMon;
    }

    @Override
    public String toString() {
        StringBuilder monHocVaDiem = new StringBuilder();
        for (int i = 0; i < MON_HOC.length; i++) {
            monHocVaDiem.append(MON_HOC[i]).append(": ").append(diemMon[i]).append("\n");
        }
        return super.toString() + String.format("\nCác môn học và điểm:\n%s", monHocVaDiem.toString().trim());
    }
}
