INSERT INTO QLNT.dbo.NhanVien (maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam) VALUES (N'NVTRANVietNHan', N'Trần Việt Nhân', N'nhantran.03042021@gmail.com', N'Tiền Giang', N'098234123', N'123456789', 1, N'2024-01-01');
INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVBH0824', N'Bán hàng');
INSERT INTO QLNT.dbo.VaiTro (maVaiTro, tenVaiTro) VALUES (N'NVQL0824', N'Quản lý');
INSERT INTO QLNT.dbo.TaiKhoan (trangThai, maNhanVien, maVaiTro, password, tenTaiKhoan) VALUES (1, N'NVTRANVietNHan', N'NVBH0824', N'5c11b43be7e405a6b4c6c0e727f54ef919cc6116c223db0f93698a613bba5d47', N'NVTRANVietNHan');

INSERT INTO QLNT.dbo.NhanVien
(maNhanVien, tenNhanVien, email, diaChi, sdt, cccd, trangThai, ngayVaoLam)
VALUES
    (N'NVHANhu', N'Hà Như', N'hanhu.01012025@gmail.com', N'Hồ Chí Minh', N'0911222333', N'987654321', 1, N'2025-01-01');

INSERT INTO QLNT.dbo.TaiKhoan
(trangThai, maNhanVien, maVaiTro, password, tenTaiKhoan)
VALUES
    (1, N'NVHANhu', N'NVQL0824', N'5c11b43be7e405a6b4c6c0e727f54ef919cc6116c223db0f93698a613bba5d47', N'NVHANhu');
