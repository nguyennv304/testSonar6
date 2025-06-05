package assignment;

public interface ISinhVien {

    // Thêm sinh viên
    void themSinhVien(SinhVien sv);

    // Cập nhật sinh viên
    void capNhatSinhVien(String maSo, SinhVien svMoi);

    // Xóa sinh viên
    boolean xoaSinhVien(String maSo);

    // Tìm kiếm sinh viên
    SinhVien timKiem(String maSo);

    // Lọc sinh viên giỏi
    void locSinhVienGioi();

    // Lọc sinh viên xuất sắc
    void locSinhVienXuatSac();

    // Sắp xếp sinh viên theo GPA giảm dần
    void sapXepTheoGPA();

    // Sắp xếp sinh viên theo tên tăng dần
    void sapXepTheoTen();

    // Xuất danh sách sinh viên
    void xuatDanhSach();

    // Lưu danh sách sinh viên vào file
    void luuFile(String tenFile);

    // Đọc danh sách sinh viên từ file
    void docFile(String tenFile);
    
    // lấy số lượng sinh viên 
    int soLuongSinhVien(); 
}
