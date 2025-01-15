package entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
public class KetToan {
    @Id
    @Column(name = "maKetToan", nullable = false)
    private String maKetToan;

    @Column(name = "ngayBatDau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc", nullable = false)
    private LocalDate ngayKetThuc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien maBangKiemTien;

    public String getMaKetToan() {
        return maKetToan;
    }

    public void setMaKetToan(String maKetToan) {
        this.maKetToan = maKetToan;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public BangKiemTien getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public void setMaBangKiemTien(BangKiemTien maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

}