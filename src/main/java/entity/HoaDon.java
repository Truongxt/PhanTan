package entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class HoaDon {
    @Id
    @Column(name = "maHD", nullable = false)
    private String maHD;

    @Column(name = "ngayLap", nullable = false)
    private LocalDate ngayLap;

    @Column(name = "tongTien", nullable = false)
    private Double tongTien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien maNhanVien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang maKH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maVoucher")
    private Voucher maVoucher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKetToan")
    private KetToan maKetToan;

    @Column(name = "atm")
    private Boolean atm;

    @Column(name = "tienDaDua")
    private Double tienDaDua;

    @Column(name = "trangThai")
    private Boolean trangThai;

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

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public KhachHang getMaKH() {
        return maKH;
    }

    public void setMaKH(KhachHang maKH) {
        this.maKH = maKH;
    }

    public Voucher getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(Voucher maVoucher) {
        this.maVoucher = maVoucher;
    }

    public KetToan getMaKetToan() {
        return maKetToan;
    }

    public void setMaKetToan(KetToan maKetToan) {
        this.maKetToan = maKetToan;
    }

    public Boolean getAtm() {
        return atm;
    }

    public void setAtm(Boolean atm) {
        this.atm = atm;
    }

    public Double getTienDaDua() {
        return tienDaDua;
    }

    public void setTienDaDua(Double tienDaDua) {
        this.tienDaDua = tienDaDua;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}