package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDon implements Serializable {
    @EmbeddedId
    private ChiTietHoaDonId id;

    @MapsId("maHD")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private HoaDon hoaDon;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
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

    public ChiTietHoaDon(ChiTietHoaDonId id, int soLuong, double donGia, Thuoc thuoc, HoaDon hoaDon) {
        this.id = id;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thuoc = thuoc;
        this.hoaDon = hoaDon;
    }

    public ChiTietHoaDon(int soLuong, double donGia, Thuoc thuoc, HoaDon hoaDon) {
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thuoc = thuoc;
        this.hoaDon = hoaDon;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ChiTietHoaDon that = (ChiTietHoaDon) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}