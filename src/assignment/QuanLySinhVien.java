package assignment;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuanLySinhVien implements ISinhVien {

    private List<SinhVien> danhSach;

    public QuanLySinhVien() {
        danhSach = new ArrayList<>();
    }

    // Thêm sinh viên
    @Override
    public void themSinhVien(SinhVien sv) {
        if (timKiem(sv.getMaSo()) == null) {
            danhSach.add(sv);
            System.out.println("Thêm sinh viên thành công!");
        } else {
            System.err.println("Mã số sinh viên đã tồn tại. Không thể thêm sinh viên !");
        }

    }

    // Cập nhật sinh viên
    @Override
    public void capNhatSinhVien(String maSo, SinhVien svMoi) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaSo().equals(maSo)) {
                danhSach.set(i, svMoi);
                return;
            }
        }
    }

    // Xóa sinh viên
    @Override
    public boolean xoaSinhVien(String maSo) {
        return danhSach.removeIf(sv -> sv.getMaSo().equals(maSo));
        // lamda expression 
    }

    // Tìm kiếm sinh viên
    @Override
    public SinhVien timKiem(String maSo) {
        return danhSach.stream()
                .filter(sv -> sv.getMaSo().equals(maSo))
                .findFirst() // tìm thấy object đầu tiền và return nó 
                .orElse(null);
        // return sinh viên cần tiềm kiếm 
    }

    // Lọc sinh viên giỏi
    @Override
    public void locSinhVienGioi() {
        List<SinhVien> svGioi = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getGpa() >= 8 && sv.getGpa() < 9) {
                svGioi.add(sv);
            }
        }
        if (svGioi.isEmpty()) {
            System.out.println("Không có sinh viên giỏi");
        } else {
            System.out.println("Danh sách sinh viên giỏi:");
            System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s%n",
                    "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email");
            int stt = 1; // Biến đếm số thứ tự
            for (SinhVien sv : svGioi) {
                System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s%n",
                        stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail());
            }
        }

    }

    // Lọc sinh viên xuất sắc
    @Override
    public void locSinhVienXuatSac() {
        List<SinhVien> svXuatSac = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getGpa() >= 9) {
                svXuatSac.add(sv);
            }
        }
        if (svXuatSac.isEmpty()) {
            System.out.println("Không có sinh viên xuất sắc");
        } else {
            System.out.println("Danh sách sinh viên xuất sắc:");
            System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s%n",
                    "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email");
            int stt = 1; // Biến đếm số thứ tự
            for (SinhVien sv : svXuatSac) {
                System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s%n",
                        stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail());
            }

        }
    }

    @Override
    // 10 9 8 6
    public void sapXepTheoGPA() {

        List<SinhVien> danhSachCopy = new ArrayList<>(danhSach);
        // sắp xếp giảm dần 
        danhSachCopy.sort((sv1, sv2) -> Double.compare(sv2.getGpa(), sv1.getGpa()));
        //danhSachCopy.sort(Comparator.comparingDouble(SinhVien::getGpa).reversed());
        System.out.println("Danh sách sinh viên sắp xếp theo gpa:");
        System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s%n",
                "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email");
        int stt = 1; // Biến đếm số thứ tự
        for (SinhVien sv : danhSachCopy) {
            System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s%n",
                    stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail());
        }

    }

    @Override
    public void sapXepTheoTen() {
        // sắp xếp theo tên chính // {"khuat",  "quang", "hung"}       len = 3 

        List<SinhVien> danhSachCopy = new ArrayList<>(danhSach);
        // a[i]       // array               2 =   //index    array.length() - 1 
        danhSachCopy.sort(Comparator.comparing(sv -> sv.getHoTen().split(" ")[sv.getHoTen().split(" ").length - 1]));
        System.out.println("Danh sách sinh viên sắp xếp theo tên:");
        System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s%n",
                "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email");
        int stt = 1; // Biến đếm số thứ tự
        for (SinhVien sv : danhSachCopy) {
            System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s%n",
                    stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail());
        }

    }

    // Xuất danh sách sinh viên
    @Override
    public void xuatDanhSach() {
        if (danhSach.isEmpty()) {
            System.out.println("Danh sách sinh viên đang trống !");
        } else {
            System.out.println("Danh sách sinh viên: ");
            System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s%n",
                    "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email");
            int stt = 1; // Biến đếm số thứ tự
            for (SinhVien sv : danhSach) {
                System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s%n",
                        stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail());
            }
        }

    }

    @Override
    public int soLuongSinhVien() {
        return this.danhSach.size();
    }

    public void hienThiHocBongSinhVien() {
        List<SinhVien> svHocBong = new ArrayList<>();
        for (SinhVien sv : danhSach) {
            if (sv.getGpa() >= 8) {
                svHocBong.add(sv);
            }
        }

        if (svHocBong.isEmpty()) {
            System.out.println("Không có sinh viên để trao học bổng");
        } else {
            svHocBong.sort(Comparator.comparingDouble(SinhVien::getGpa).reversed());
            System.out.println("Danh sách sinh viên nhận học bổng:");
            System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s %-10s %-15s%n",
                    "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email", "Xếp loại", "Học bổng");
            int stt = 1;
            for (SinhVien sv : svHocBong) {
                String hocBong = sv.getGpa() >= 9 ? "1 triệu VNĐ" : "500k VNĐ";
                String xepLoai = sv.getGpa() >= 9 ? "Xuất sắc" : "Giỏi";
                System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s %-10s %-15s%n",
                        stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail(), xepLoai, hocBong);
            }
        }
    }

    public void hienThiPhatSinhVien() {
        List<SinhVien> svPhat = new ArrayList<>();

        for (SinhVien sv : danhSach) {
            if (sv.getGpa() < 5) {
                svPhat.add(sv);
            }
        }

        if (svPhat.isEmpty()) {
            System.out.println("Không có sinh viên bị phạt");
        } else {
            System.out.println("Danh sách sinh viên bị phạt:");
            System.out.printf("%-5s %-20s %-10s %-10s %-20s %-30s %-15s%n",
                    "STT", "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email", "Phạt");
            int stt = 1;
            for (SinhVien sv : svPhat) {
                System.out.printf("%-5d %-20s %-10s %-10s %-20s %-30s %-15s%n",
                        stt++, sv.getHoTen(), sv.getMaSo(), sv.getGpa(), sv.getChuyenNganh(), sv.getEmail(), "300k VNĐ");
            }
        }
    }

    public void hienThiCocVang() {
        SinhVien cocVangKyThuat = null;
        SinhVien cocVangKinhTe = null;

        for (SinhVien sv : danhSach) {
            if (sv.getChuyenNganh().equalsIgnoreCase("Kỹ thuật")) {
                if (cocVangKyThuat == null || sv.getGpa() > cocVangKyThuat.getGpa()) {
                    cocVangKyThuat = sv;
                }
            } else if (sv.getChuyenNganh().equalsIgnoreCase("Kinh tế")) {
                if (cocVangKinhTe == null || sv.getGpa() > cocVangKinhTe.getGpa()) {
                    cocVangKinhTe = sv;
                }
            }
        }

        if (cocVangKyThuat != null) {
            System.out.println("Cóc Vàng ngành Kỹ thuật:");
            System.out.printf("%-20s %-10s %-10s %-20s %-30s %-15s%n",
                    "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email", "Giải thưởng");
            System.out.printf("%-20s %-10s %-10s %-20s %-30s %-15s%n",
                    cocVangKyThuat.getHoTen(), cocVangKyThuat.getMaSo(), cocVangKyThuat.getGpa(), cocVangKyThuat.getChuyenNganh(), cocVangKyThuat.getEmail(), "10 triệu VNĐ");
        } else {
            System.out.println("Không có sinh viên ngành Kỹ thuật");
        }

        if (cocVangKinhTe != null) {
            System.out.println("Cóc Vàng ngành Kinh tế:");
            System.out.printf("%-20s %-10s %-10s %-20s %-30s %-15s%n",
                    "Họ và Tên", "MSSV", "Điểm", "Chuyên ngành", "Email", "Giải thưởng");
            System.out.printf("%-20s %-10s %-10s %-20s %-30s %-15s%n",
                    cocVangKinhTe.getHoTen(), cocVangKinhTe.getMaSo(), cocVangKinhTe.getGpa(), cocVangKinhTe.getChuyenNganh(), cocVangKinhTe.getEmail(), "10 triệu VNĐ");
        } else {
            System.out.println("Không có sinh viên ngành Kinh tế");
        }
    }

    // Lưu danh sách sinh viên vào file
    @Override
    public void luuFile(String tenFile) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tenFile))) {
            oos.writeObject(danhSach);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    // Đọc danh sách sinh viên từ file
    @Override
    public void docFile(String tenFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(tenFile))) {
            danhSach = (List<SinhVien>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

}
