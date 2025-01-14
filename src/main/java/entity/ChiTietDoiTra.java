package entity;

import jakarta.persistence.*;

@Entity
public class ChiTietDoiTra {
    @EmbeddedId
    private ChiTietDoiTraId id;

    @MapsId("maHoaDonDoiTra")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHoaDonDoiTra", nullable = false)
    private HoaDonDoiTra maHoaDonDoiTra;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maThuoc", nullable = false)
    private Thuoc maThuoc;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "donGia", nullable = false)
    private Double donGia;

    public ChiTietDoiTraId getId() {
        return id;
    }

    public void setId(ChiTietDoiTraId id) {
        this.id = id;
    }

    public HoaDonDoiTra getMaHoaDonDoiTra() {
        return maHoaDonDoiTra;
    }

    public void setMaHoaDonDoiTra(HoaDonDoiTra maHoaDonDoiTra) {
        this.maHoaDonDoiTra = maHoaDonDoiTra;
    }

    public Thuoc getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(Thuoc maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

}