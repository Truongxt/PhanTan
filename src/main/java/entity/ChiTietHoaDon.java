package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDon {
    @EmbeddedId
    private ChiTietHoaDonId id;

    @MapsId("maHD")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private HoaDon hoaDon;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maThuoc", nullable = false)
    private Thuoc thuoc;

    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    @Column(name = "donGia", nullable = false)
    private double donGia;

    public ChiTietHoaDon(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public double thanhTien() {
        return this.donGia * this.soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public ChiTietHoaDon(int soLuong, double donGia, Thuoc thuoc, HoaDon hoaDon) {
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thuoc = thuoc;
        this.hoaDon = hoaDon;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.thuoc, other.thuoc)) {
            return false;
        }
        return Objects.equals(this.hoaDon, other.hoaDon);
    }


}