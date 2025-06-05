package assignment;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        QuanLySinhVien qlsv = new QuanLySinhVien();
        Scanner scanner = new Scanner(System.in);
        int luaChon = -1;

        do {

            System.out.println("----- QUẢN LÝ SINH VIÊN -----");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Cập nhật sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Tìm kiếm sinh viên");
            System.out.println("5. Lọc sinh viên giỏi");
            System.out.println("6. Lọc sinh viên xuất sắc");
            System.out.println("7. Xuất danh sách sinh viên");
            System.out.println("8. Lưu danh sách sinh viên vào file");
            System.out.println("9. Đọc danh sách sinh viên từ file");
            System.out.println("10. Sắp xếp sinh viên theo GPA");
            System.out.println("11. Sắp xếp sinh viên theo tên");
            System.out.println("12. Số lượng sinh viên");
            System.out.println("13. Danh sách sinh viên được trao học bổng");
            System.out.println("14. Danh sách sinh viên đóng tiền phạt (gpa < 5)");
            System.out.println("15. Danh sách cóc vàng của kỳ học");
            System.out.println("0. Thoát");
            System.out.print("Vui lòng chọn chức năng: ");

            // lọc bỏ các trường hợp ko nhập số nguyên 
            if (!scanner.hasNextInt()) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập số nguyên.");
                scanner.next(); // Xóa invalid input
                continue;
            }
            luaChon = scanner.nextInt();
            scanner.nextLine(); // xóa buffer 

            // lọc bỏ trường hợp ngoài phạm vi lựa chọn 
            if (luaChon < 0 || luaChon > 15) {
                System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                continue;
            }

            switch (luaChon) {
                case 1:
                    String hoTen;
                    do {
                        System.out.print("Nhập họ và tên: ");
                        hoTen = scanner.nextLine().trim();
                        if (hoTen.isEmpty()) {
                            System.out.println("Họ và tên không được để trống. Vui lòng nhập lại.");
                        } else if (!hoTen.matches("^[a-zA-Z\\s]+$")) {
                            System.out.println("Họ và tên không được chứa số hoặc ký tự đặc biệt. Vui lòng nhập lại.");
                        }
                    } while (hoTen.isEmpty() || !hoTen.matches("^[a-zA-Z\\s]+$"));

                    String maSo;
                    do {
                        System.out.print("Nhập mã số sinh viên (bắt đầu bằng 'he'): ");
                        maSo = scanner.nextLine().trim();
                        if (maSo.isEmpty()) {
                            System.out.println("Mã số sinh viên không được để trống. Vui lòng nhập lại.");
                        } else if (!maSo.matches("^(?i)he\\d{6}$")) {
                            System.out.println("Mã số sinh viên không hợp lệ. Vui lòng nhập lại.");
                        } else if (qlsv.timKiem(maSo.toUpperCase()) != null) {
                            System.out.println("Mã số sinh viên đã tồn tại. Vui lòng nhập lại.");
                        }
                    } while (maSo.isEmpty() || !maSo.matches("^(?i)he\\d{6}$") || qlsv.timKiem(maSo.toUpperCase()) != null);

                    int chuyenNganh;
                    do {
                        try {
                            System.out.print("Nhập chuyên ngành (1: Kỹ thuật, 2: Kinh tế): ");
                            chuyenNganh = scanner.nextInt();
                            scanner.nextLine(); // Clear the buffer

                            if (chuyenNganh != 1 && chuyenNganh != 2) {
                                System.out.println("Chuyên ngành không hợp lệ. Vui lòng nhập lại!");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Bạn phải nhập một số nguyên. Vui lòng nhập lại!");
                            scanner.nextLine(); // Xóa bỏ dữ liệu không hợp lệ

                            chuyenNganh = -1;
                            // Quay lại vòng lặp để nhập lại
                            // Gán giá trị không hợp lệ để tiếp tục vòng lặp
                        }
                    } while (chuyenNganh != 1 && chuyenNganh != 2);

                    SinhVien sinhVien;
                    if (chuyenNganh == 1) {
                        double[] diemMon = new double[SinhVienKyThuat.MON_HOC.length];
                        for (int i = 0; i < SinhVienKyThuat.MON_HOC.length; i++) {
                            diemMon[i] = nhapDiemMon(scanner, SinhVienKyThuat.MON_HOC[i]);
                        }
                        sinhVien = new SinhVienKyThuat(hoTen, maSo, "Kỹ thuật", diemMon);
                    } else {
                        double[] diemMon = new double[SinhVienKinhTe.MON_HOC.length];
                        for (int i = 0; i < SinhVienKinhTe.MON_HOC.length; i++) {
                            diemMon[i] = nhapDiemMon(scanner, SinhVienKinhTe.MON_HOC[i]);
                        }
                        sinhVien = new SinhVienKinhTe(hoTen, maSo, "Kinh tế", diemMon);
                    }
                    qlsv.themSinhVien(sinhVien);

                    break;
                case 2:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Danh sách sinh viên đang trống không thể cập nhật !");
                        break;
                    }
                    System.out.print("Nhập mã số sinh viên cần cập nhật: ");
                    String maSoCapNhat = scanner.nextLine().trim().toUpperCase();
                    SinhVien svCapNhat = qlsv.timKiem(maSoCapNhat);
                    if (svCapNhat != null) {
                        do {
                            System.out.print("Nhập họ và tên mới: ");
                            hoTen = scanner.nextLine().trim();
                            if (hoTen.isEmpty()) {
                                System.out.println("Họ và tên không được để trống. Vui lòng nhập lại.");
                            } else if (!hoTen.matches("^[a-zA-Z\\s]+$")) {  // chỉ chấp nhận a- z A - Z 
                                System.out.println("Họ và tên không được chứa số hoặc ký tự đặc biệt. Vui lòng nhập lại.");
                            }
                        } while (hoTen.isEmpty() || !hoTen.matches("^[a-zA-Z\\s]+$"));

                        System.out.print("Nhập chuyên ngành mới (1: Kỹ thuật, 2: Kinh tế): ");
                        chuyenNganh = scanner.nextInt();
                        scanner.nextLine(); // Clear the buffer

                        if (chuyenNganh == 1) {
                            double[] diemMon = new double[SinhVienKyThuat.MON_HOC.length];
                            for (int i = 0; i < SinhVienKyThuat.MON_HOC.length; i++) {
                                diemMon[i] = nhapDiemMon(scanner, SinhVienKyThuat.MON_HOC[i]);
                            }
                            svCapNhat = new SinhVienKyThuat(hoTen, maSoCapNhat, "Kỹ thuật", diemMon);
                        } else {
                            double[] diemMon = new double[SinhVienKinhTe.MON_HOC.length];
                            for (int i = 0; i < SinhVienKinhTe.MON_HOC.length; i++) {
                                diemMon[i] = nhapDiemMon(scanner, SinhVienKinhTe.MON_HOC[i]);
                            }
                            svCapNhat = new SinhVienKinhTe(hoTen, maSoCapNhat, "Kinh tế", diemMon);
                        }
                        qlsv.capNhatSinhVien(maSoCapNhat, svCapNhat);
                        System.out.println("Cập nhật sinh viên thành công!");
                    } else {
                        System.out.println("Không tìm thấy sinh viên với mã số: " + maSoCapNhat);

                    }
                    break;
                case 3:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Danh sách sinh viên đang trống không thể xóa!");
                        break;
                    }
                    System.out.print("Nhập mã số sinh viên cần xóa: ");
                    String maSoXoa = scanner.nextLine().trim().toUpperCase();
                    if (qlsv.xoaSinhVien(maSoXoa)) {
                        System.out.println("Xóa sinh viên thành công!");
                    } else {
                        System.out.println("Không tìm thấy sinh viên với mã số: " + maSoXoa);
                    }
                    break;
                case 4:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Danh sách sinh viên đang trống không tìm kiếm !");
                        break;
                    }
                    System.out.print("Nhập mã số sinh viên cần tìm: ");
                    String maSoTimKiem = scanner.nextLine().trim().toUpperCase();
                    SinhVien svTimKiem = qlsv.timKiem(maSoTimKiem);
                    if (svTimKiem != null) {
                        System.out.println("Thông tin sinh viên tìm thấy: \n" + svTimKiem);
                    } else {
                        System.out.println("Không tìm thấy sinh viên với mã số: " + maSoTimKiem);
                    }
                    break;
                case 5:
                    // Lọc sinh viên giỏi
                    qlsv.locSinhVienGioi();
                    break;
                case 6:
                    // Lọc sinh viên xuất sắc
                    qlsv.locSinhVienXuatSac();
                    break;
                case 7:
                    qlsv.xuatDanhSach();
                    break;
                case 8:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Không có sinh viên để lưu !");
                    } else {
                        System.out.print("Nhập tên file để lưu: ");
                        String tenFileLuu = scanner.nextLine().trim();
                        qlsv.luuFile(tenFileLuu);
                        System.out.println("Lưu danh sách sinh viên thành công!");
                    }

                    break;
                case 9:
                    System.out.print("Nhập tên file để đọc: ");
                    String tenFileDoc = scanner.nextLine().trim();
                    qlsv.docFile(tenFileDoc);
                    System.out.println("Đọc danh sách sinh viên thành công!");
                    break;
                case 10:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Danh sách sinh viên đang trống không thể sắp xếp!");
                        break;
                    }
                    qlsv.sapXepTheoGPA();
                    System.out.println("Danh sách sinh viên đã được sắp xếp theo GPA giảm dần.");
                    break;
                case 11:
                    if (qlsv.soLuongSinhVien() == 0) {
                        System.out.println("Danh sách sinh viên đang trống không thể sắp xếp!");
                        break;
                    }
                    qlsv.sapXepTheoTen();
                    System.out.println("Danh sách sinh viên đã được sắp xếp theo tên tăng dần.");
                    break;
                case 12:
                    int soluong = qlsv.soLuongSinhVien();
                    System.out.println("Tổng số sinh viên: " + soluong);
                    break;
                case 13:
                    qlsv.hienThiHocBongSinhVien();
                    break;
                case 14:
                    qlsv.hienThiPhatSinhVien();
                    break;
                case 15:
                    qlsv.hienThiCocVang();
                    break;

                case 0:
                    System.out.println("Chương trình kết thúc.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                    break;
            }
        } while (luaChon != 0);

        scanner.close();
    }

    private static double nhapDiemMon(Scanner scanner, String tenMon) {
        double diem = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.printf("Nhập điểm môn %s: ", tenMon);
                diem = scanner.nextDouble();
                if (diem < 0 || diem > 10) {
                    System.out.println("Điểm không hợp lệ. Vui lòng nhập lại!");
                } else {
                    isValidInput = true;
                }
            } catch (Exception e) {
                System.out.println("Điểm không hợp lệ. Vui lòng nhập lại!");
                scanner.nextLine(); // xóa buffer
            }
        } while (!isValidInput);

        return diem;
    }

}
