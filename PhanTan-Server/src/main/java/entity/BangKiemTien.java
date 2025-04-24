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
public class BangKiemTien implements Serializable, Comparable<BangKiemTien>  {
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

    @OneToMany(mappedBy = "maBangKiemTien",fetch = FetchType.LAZY)
    private List<KiemTien> listKiemTien;

    @OneToMany(mappedBy = "bangKiemTien", fetch = FetchType.LAZY)
    private ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien;

    @OneToOne(cascade = CascadeType.ALL)
    private KetToan ketToan;


//    Set get ketToan
    public void setKetToan(KetToan ketToan) {
        this.ketToan = ketToan;
    }
    public KetToan getKetToan() {
        return ketToan;
    }

    public BangKiemTien() {
    }

    public BangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public BangKiemTien(String maBangKiemTien, ArrayList<KiemTien> listKiemTien, ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien, Date ngayBatDau, Date ngayKetThuc) {
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

    public List<KiemTien> getListKiemTien() {
        return listKiemTien;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public ArrayList<ChiTietBangKiemTien> getListChiTietBangKiemTien() {
        return listChiTietBangKiemTien;
    }

    public void setMaBangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public void setListKiemTien(ArrayList<KiemTien> listKiemTien) {
        this.listKiemTien = listKiemTien;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setListChiTietBangKiemTien(ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.maBangKiemTien);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BangKiemTien other = (BangKiemTien) obj;
        return Objects.equals(this.maBangKiemTien, other.maBangKiemTien);
    }

    @Override
    public int compareTo(BangKiemTien o) {
        return this.ngayBatDau.compareTo(o.ngayBatDau);
    }


}