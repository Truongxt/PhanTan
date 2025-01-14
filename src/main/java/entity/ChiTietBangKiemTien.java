package entity;

import jakarta.persistence.*;

@Entity
public class ChiTietBangKiemTien {
    @EmbeddedId
    private ChiTietBangKiemTienId id;

    @MapsId("maBangKiemTien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien maBangKiemTien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien maNhanVien;

    public ChiTietBangKiemTienId getId() {
        return id;
    }

    public void setId(ChiTietBangKiemTienId id) {
        this.id = id;
    }

    public BangKiemTien getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public void setMaBangKiemTien(BangKiemTien maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

}