INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250124001', N'Nguyễn Văn A', N'a@gmail.com', N'Hà Nội', N'0987654321', N'123456789', 1, N'2023-01-01');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250224002', N'Nguyễn Văn B', N'b@gmail.com', N'Hồ Chí Minh', N'0987654322', N'123456789', 1, N'2024-09-11');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250324003', N'Nguyễn Văn C', N'c@gmail.com', N'Đà Nẵng', N'0987654323', N'123456789', 1, N'2022-01-04');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NV250424004', N'Nguyễn Văn D', N'd@gmail.com', N'Cần Thơ', N'0987654324', N'123456789', 1, N'2023-09-09');
INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NVTRANVietNHan', N'Trần Việt Nhân', N'nhantran.03042021@gmail.com', N'Tiền Giang', N'098234123', N'123456789', 1, N'2024-01-01');



INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVBH0824', N'Bán hàng');
INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVQL0824', N'Quản lý');
INSERT INTO QLNT.dbo.TaiKhoan (trangThai, maNhanVien, maVaiTro, password, tenTaiKhoan) VALUES (1, N'NVTRANVietNHan', N'NVBH0824', N'5c11b43be7e405a6b4c6c0e727f54ef919cc6116c223db0f93698a613bba5d47', N'NVTRANVietNHan');
