INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250124001', N'Nguyễn Văn A', N'a@gmail.com', N'Hà Nội', N'0987654321', N'123456789', 1, N'2023-01-01');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250224002', N'Nguyễn Văn B', N'b@gmail.com', N'Hồ Chí Minh', N'0987654322', N'32322333', 1, N'2024-09-11');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250324003', N'Nguyễn Văn C', N'c@gmail.com', N'Đà Nẵng', N'0987654323', N'3223322332', 1, N'2022-01-04');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250424004', N'Nguyễn Văn D', N'd@gmail.com', N'Cần Thơ', N'0987654324', N'32323232332', 1, N'2023-09-09');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NVTRANVietNHan', N'Trần Việt Nhân', N'nhantran.03042021@gmail.com', N'Tiền Giang', N'098234123', N'23323232325', 1, N'2024-01-01');



INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVBH0824', N'Bán hàng');
INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVQL0824', N'Quản lý');
INSERT INTO QLNT.dbo.TaiKhoan (trangThai, maNhanVien, maVaiTro, password, tenTaiKhoan) VALUES (1, N'NVTRANVietNHan', N'NVBH0824', N'5c11b43be7e405a6b4c6c0e727f54ef919cc6116c223db0f93698a613bba5d47', N'NVTRANVietNHan');



INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT001', N'Thực phẩm chức năng');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT002', N'Thuốc giảm đau');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT003', N'Thuốc kháng sinh');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT004', N'Thuốc tiêu hóa');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT005', N'Thuốc ho & cảm lạnh');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT006', N'Thuốc bổ');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT007', N'Thuốc điều trị huyết áp');
INSERT INTO LoaiThuoc (maLoai, tenLoai) VALUES (N'LT008', N'Thuốc trị tiểu đường');


INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX001', N'Việt Nam');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX002', N'Mỹ');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX003', N'Nhật Bản');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX004', N'Hàn Quốc');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX005', N'Pháp');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX006', N'Đức');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX007', N'Thái Lan');
INSERT INTO XuatXu (maXuatXu, tenXuatXu) VALUES (N'XX008', N'Ấn Độ');


INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT001', N'Viên');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT002', N'Hộp');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT003', N'Chai');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT004', N'Túi');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT005', N'Ống');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT006', N'Lọ');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT007', N'Vỉ');
INSERT INTO DonViTinh (maDonViTinh, ten) VALUES (N'DVT008', N'Thùng');

INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt, trangThai)
VALUES (N'NCC001', N'Công ty Dược Việt Mỹ', N'123 Lê Lợi, Quận 1, TP.HCM', N'vietmy@duoc.vn', '0909123456', 1);

INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt, trangThai)
VALUES (N'NCC002', N'Tập đoàn Dược Nhật Bản', N'456 Nguyễn Trãi, Quận 5, TP.HCM', N'info@jppharma.jp', '0988123456', 1);

INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt, trangThai)
VALUES (N'NCC003', N'Nhà thuốc Pháp', N'789 Hai Bà Trưng, Quận 3, TP.HCM', N'contact@pharmafr.fr', '0911223344', 0);

INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt, trangThai)
VALUES (N'NCC004', N'Dược phẩm Minh Châu', N'10A Cách Mạng Tháng 8, TP.Đà Nẵng', NULL, '0933445566', 1);

INSERT INTO NhaCungCap (maNCC, tenNCC, diaChi, email, sdt, trangThai)
VALUES (N'NCC005', N'Dược Đông Y', N'85 Trần Hưng Đạo, TP.Hà Nội', NULL, '0977665544', 0);



INSERT INTO Thuoc (maThuoc, tenThuoc, gia, hsd, nsx, thue, soLuongTon, mota, maLoai, maXuatXu, maDonViTinh, maNCC)
VALUES (N'T001', N'Paracetamol 500mg', 15000, '2026-12-31', '2024-12-31', 5.0, 200, N'Hạ sốt, giảm đau', 'LT001', 'XX001', 'DVT001', 'NCC001');

INSERT INTO Thuoc (maThuoc, tenThuoc, gia, hsd, nsx, thue, soLuongTon, mota, maLoai, maXuatXu, maDonViTinh, maNCC)
VALUES (N'T002', N'Amoxicillin 250mg', 22000, '2026-10-15', '2024-10-15', 5.0, 150, N'Kháng sinh điều trị viêm họng', 'LT002', 'XX002', 'DVT002', 'NCC002');

INSERT INTO Thuoc (maThuoc, tenThuoc, gia, hsd, nsx, thue, soLuongTon, mota, maLoai, maXuatXu, maDonViTinh, maNCC)
VALUES (N'T003', N'Vitamin C 500mg', 18000, '2025-06-01', '2023-06-01', 2.0, 300, N'Tăng sức đề kháng', 'LT003', 'XX003', 'DVT003', 'NCC003');

INSERT INTO Thuoc (maThuoc, tenThuoc, gia, hsd, nsx, thue, soLuongTon, mota, maLoai, maXuatXu, maDonViTinh, maNCC)
VALUES (N'T004', N'Oresol', 12000, '2026-03-20', '2024-03-20', 1.5, 100, N'Bù nước và điện giải', 'LT004', 'XX004', 'DVT004', 'NCC004');

INSERT INTO Thuoc (maThuoc, tenThuoc, gia, hsd, nsx, thue, soLuongTon, mota, maLoai, maXuatXu, maDonViTinh, maNCC)
VALUES (N'T005', N'Thuốc ho Prospan', 45000, '2025-12-01', '2023-12-01', 4.0, 180, N'Giảm ho, tiêu đờm từ thảo dược', 'LT005', 'XX005', 'DVT005', 'NCC005');


