package entity;

import jakarta.persistence.*;

@Entity
public class KiemTien {
    @EmbeddedId
    private KiemTienId id;

    @MapsId("maBangKiemTien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien maBangKiemTien;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    public KiemTienId getId() {
        return id;
    }

    public void setId(KiemTienId id) {
        this.id = id;
    }

    public BangKiemTien getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public void setMaBangKiemTien(BangKiemTien maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

}