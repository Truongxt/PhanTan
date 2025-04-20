//package entity;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.Nationalized;
//
//import java.time.Instant;
//import java.time.LocalDate;
//
//@Entity
//public class HoaDonDoiTra {
//    @Id
//    @Column(name = "maHoaDonDoiTra", nullable = false)
//    private String maHoaDonDoiTra;
//
//    @Column(name = "ngayDoiTra", nullable = false)
//    private LocalDate ngayDoiTra;
//
//    @Column(name = "soTienTra", nullable = false)
//    private Double soTienTra;
//
//    @Nationalized
//    @Column(name = "moTa", length = 100)
//    private String moTa;
//
//    @Column(name = "loaiDoiTra", nullable = false)
//    private Boolean loaiDoiTra = false;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "maNhanVien", nullable = false)
//    private NhanVien maNhanVien;
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "maHoaDon", nullable = false)
//    private HoaDon maHoaDon;
//
//    public String getMaHoaDonDoiTra() {
//        return maHoaDonDoiTra;
//    }
//
//    public void setMaHoaDonDoiTra(String maHoaDonDoiTra) {
//        this.maHoaDonDoiTra = maHoaDonDoiTra;
//    }
//
//    public LocalDate getNgayDoiTra() {
//        return ngayDoiTra;
//    }
//
//    public void setNgayDoiTra(LocalDate ngayDoiTra) {
//        this.ngayDoiTra = ngayDoiTra;
//    }
//
//    public Double getSoTienTra() {
//        return soTienTra;
//    }
//
//    public void setSoTienTra(Double soTienTra) {
//        this.soTienTra = soTienTra;
//    }
//
//    public String getMoTa() {
//        return moTa;
//    }
//
//    public void setMoTa(String moTa) {
//        this.moTa = moTa;
//    }
//
//    public Boolean getLoaiDoiTra() {
//        return loaiDoiTra;
//    }
//
//    public void setLoaiDoiTra(Boolean loaiDoiTra) {
//        this.loaiDoiTra = loaiDoiTra;
//    }
//
//    public NhanVien getMaNhanVien() {
//        return maNhanVien;
//    }
//
//    public void setMaNhanVien(NhanVien maNhanVien) {
//        this.maNhanVien = maNhanVien;
//    }
//
//    public HoaDon getMaHoaDon() {
//        return maHoaDon;
//    }
//
//    public void setMaHoaDon(HoaDon maHoaDon) {
//        this.maHoaDon = maHoaDon;
//    }
//
//}