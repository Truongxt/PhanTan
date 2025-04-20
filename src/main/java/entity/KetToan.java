package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KetToan {

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

    // Getters and Setters
    public String getMaKetToan() {
        return maKetToan;
    }

    public void setMaKetToan(String maKetToan) {
        this.maKetToan = maKetToan;
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

    public BangKiemTien getBangKiemTien() {
        return bangKiemTien;
    }

    public void setBangKiemTien(BangKiemTien bangKiemTien) {
        this.bangKiemTien = bangKiemTien;
    }

    public List<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(List<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
}