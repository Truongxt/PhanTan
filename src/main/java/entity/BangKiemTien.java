package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@ToString
public class BangKiemTien implements Serializable, Comparable<BangKiemTien> {
    @Id
    @Column(name = "maBangKiemTien", nullable = false)
    private String maBangKiemTien;

    @Column(name = "ngayBatDau", nullable = false)
    private Date ngayBatDau;

    @Column(name = "ngayKetThuc", nullable = false)
    private Date ngayKetThuc;

    @Column(name = "chenhLech", nullable = false)
    private double chenhLech;

    @Column(name = "tongTien", nullable = false)
    private double tongTien;

    @OneToMany(mappedBy = "maBangKiemTien", fetch = FetchType.LAZY)
    private List<KiemTien> listKiemTien = new ArrayList<>();

    @OneToMany(mappedBy = "bangKiemTien", fetch = FetchType.LAZY)
    private List<ChiTietBangKiemTien> listChiTietBangKiemTien = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private KetToan ketToan;

    public BangKiemTien() {
        this.listKiemTien = new ArrayList<>();
        this.listChiTietBangKiemTien = new ArrayList<>();
    }

    public BangKiemTien(String maBangKiemTien) {
        this();
        this.maBangKiemTien = maBangKiemTien;
    }

    public BangKiemTien(String maBangKiemTien, List<KiemTien> listKiemTien, List<ChiTietBangKiemTien> listChiTietBangKiemTien, Date ngayBatDau, Date ngayKetThuc) {
        this.maBangKiemTien = maBangKiemTien;
        this.listKiemTien = listKiemTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.listChiTietBangKiemTien = listChiTietBangKiemTien;
        setTongTien();
        setChenhLech();
    }

    public String getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public void setMaBangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public List<KiemTien> getListKiemTien() {
        return listKiemTien;
    }

    public void setListKiemTien(List<KiemTien> listKiemTien) {
        this.listKiemTien = listKiemTien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public List<ChiTietBangKiemTien> getListChiTietBangKiemTien() {
        return listChiTietBangKiemTien;
    }

    public void setListChiTietBangKiemTien(List<ChiTietBangKiemTien> listChiTietBangKiemTien) {
        this.listChiTietBangKiemTien = listChiTietBangKiemTien;
    }

    public double getChenhLech() {
        return chenhLech;
    }

    public void setChenhLech() {
        this.chenhLech = this.tongTien - 1765000;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien() {
        double sum = 0;
        for (KiemTien ct : listKiemTien) {
            sum += ct.getTong();
        }
        this.tongTien = sum;
    }

    public KetToan getKetToan() {
        return ketToan;
    }

    public void setKetToan(KetToan ketToan) {
        this.ketToan = ketToan;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maBangKiemTien);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BangKiemTien other = (BangKiemTien) obj;
        return Objects.equals(maBangKiemTien, other.maBangKiemTien);
    }

    @Override
    public int compareTo(BangKiemTien o) {
        return this.ngayBatDau.compareTo(o.ngayBatDau);
    }
}