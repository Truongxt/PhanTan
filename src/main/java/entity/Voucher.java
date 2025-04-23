package entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Voucher")
public class Voucher {

    @Id
    @Column(name = "ma_voucher",length = 50)
    private String maVoucher;

    @Column(name = "ten_voucher", length = 100)
    private String tenVoucher;

    @Column(name = "mo_ta", length = 255)
    private String moTa;

    @Column(name = "ngay_ap_dung")
    private LocalDate ngayApDung;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "gia_giam")
    private double giaGiam;

    public Voucher() {
    }

    public Voucher(String maVoucher, String tenVoucher, String moTa, LocalDate ngayApDung, LocalDate ngayKetThuc, double giaGiam) {
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.moTa = moTa;
        this.ngayApDung = ngayApDung;
        this.ngayKetThuc = ngayKetThuc;
        this.giaGiam = giaGiam;
    }

    public Voucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(LocalDate ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }
}