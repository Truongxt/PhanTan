package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class KetToan implements Comparable<KetToan>, Serializable {

    @Id
    private String maKetToan;

    @Temporal(TemporalType.DATE)
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    private Date ngayKetThuc;

    @ManyToOne
    @JoinColumn(name = "maBangKiemTien")
    private BangKiemTien bangKiemTien;

    @OneToMany(mappedBy = "ketToan")
    private List<HoaDon> hoaDons;
    private double doanhThu;
    private double atm;
    private double tienLayRa;
    private double chenhLech;

    public KetToan() {
    }

    public KetToan(String maKetToan, Date ngayBatDau, Date ngayKetThuc, BangKiemTien bangKiemTien, List<HoaDon> listHoaDon) {
        this.maKetToan = maKetToan;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.bangKiemTien = bangKiemTien;
        this.hoaDons = listHoaDon;
        setDoanhThu();
        setAtm();
        setTienLayRa();
        setChenhLech();
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu() {
        double sum = 0;
        for (HoaDon hd : hoaDons) {
            sum += hd.getTongTien();
        }
        this.doanhThu = sum;
    }

    public double getAtm() {
        return atm;
    }

    public void setAtm() {
        double sum  =0;
        for (HoaDon hd : hoaDons) {
            if(hd.isAtm()){
                sum += hd.getTongTien();
            }
        }
        this.atm = sum;
    }

    public double getTienLayRa() {
        return tienLayRa;
    }

    public void setTienLayRa() {
        this.tienLayRa = this.doanhThu - this.atm;
    }

    public double getChenhLech() {
        return chenhLech;
    }

    public void setChenhLech() {
        this.chenhLech = bangKiemTien.getTongTien() - this.tienLayRa - 1765000;
    }

    public KetToan(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaKetToan() {
        return maKetToan;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public BangKiemTien getBangKiemTien() {
        return bangKiemTien;
    }

    public List<HoaDon> getListHoaDon() {
        return hoaDons;
    }

    public void setMaKetToan(String maKetToan) {
        this.maKetToan = maKetToan;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public void setBangKiemTien(BangKiemTien bangKiemTien) {
        this.bangKiemTien = bangKiemTien;
    }

    public void setListHoaDon(List<HoaDon> listHoaDon) {
        this.hoaDons = listHoaDon;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.maKetToan);
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
        final KetToan other = (KetToan) obj;
        return Objects.equals(this.maKetToan, other.maKetToan);
    }

    @Override
    public int compareTo(KetToan o) {
        return this.ngayBatDau.compareTo(o.ngayBatDau);
    }

}