select * from loaithuoc
select * from donvitinh
select * from XuatXu
select * from Thuoc
select * from nhanvien
select * from khachhang
select *from hoadon
select * from chitietHoaDon
select * from dbo.ChiTietBangKiemTien
select * from BangKiemTien
select * from dbo.KiemTien
select * from dbo.VaiTro
select * from dbo.TaiKhoan
select * from dbo.ChiTietDoiTra
select * from dbo.KetToan
select * from dbo.NhaCungCap
select * from dbo.LoaiThuoc


-- doanh thu bán trong tháng 7 năm 2024
SELECT SUM(tongTien) AS totalRevenue
FROM hoadon
WHERE MONTH(ngayLap) = 7 AND YEAR(ngayLap) = 2024;
--thuốc thuộc loại thuốc là jojoba oil
SELECT t.*
FROM Thuoc t
         JOIN LoaiThuoc lt ON t.maLoai = lt.maLoai
WHERE lt.tenLoai = 'jojoba oil';
--số lượng hóa đơn án trong tháng 5 năm 2024
SELECT COUNT(*) AS invoiceCount
FROM hoadon
WHERE MONTH(ngayLap) = 5 AND YEAR(ngayLap) = 2024;
--số lượng khách hàng của cửa hành
SELECT COUNT(*) AS customerCount
FROM khachhang;
--thuốc có xuất xừ từ Zambia
SELECT t.*
FROM Thuoc t
         JOIN XuatXu x ON t.maXuatXu = x.maXuatXu
WHERE x.tenXuatXu = 'Zambia';

--số lượng hóa đơn của nhân viên NV-95773848 bán trong tháng 7 năm 2024
SELECT COUNT(*) AS invoiceCount
FROM hoadon
WHERE maNhanVien = 'NV-95773848'
  AND MONTH(ngayLap) = 7
  AND YEAR(ngayLap) = 2024;

    --    tổng doanh thu của khách hàng KH-01542310
SELECT SUM(tongTien) AS totalRevenue
FROM hoadon
WHERE maKH = 'KH-01542310';

