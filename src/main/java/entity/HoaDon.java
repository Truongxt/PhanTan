package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maHD;

    private LocalDate ngayLap;
    private double tongTien;

    @ManyToOne
    @JoinColumn(name = "khachHang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhanVien_id", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "maKetToan", nullable = false)
    private KetToan ketToan;

    // Getters and Setters
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDate ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public KetToan getKetToan() {
        return ketToan;
    }

    public void setKetToan(KetToan ketToan) {
        this.ketToan = ketToan;
    }
}