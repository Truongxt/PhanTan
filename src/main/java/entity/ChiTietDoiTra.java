package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
public class ChiTietDoiTra {
    public static final String ORDERID_EMPTY = "Hoá đơn không được phép rỗng";
    public static final String PRODUCT_EMPTY = "Sản phẩm không được phép rỗng";
    public static final String QUANTITY_VALID = "Số lượng phải là số dương";

    @EmbeddedId
    private ChiTietDoiTraId id;

    @MapsId("maHoaDonDoiTra")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHoaDonDoiTra", nullable = false)
    private DoiTra doiTra;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maThuoc", nullable = false)
    private Thuoc thuoc;

    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    @Column(name = "donGia", nullable = false)
    private double donGia;

    public ChiTietDoiTra() {
    }

    public ChiTietDoiTra(DoiTra doiTra, Thuoc thuoc, int soLuong, double gia) {
        if (doiTra == null) throw new IllegalArgumentException("DoiTra không được null");
        if (thuoc == null) throw new IllegalArgumentException("Thuoc không được null");

        this.doiTra = doiTra;
        this.thuoc = thuoc;
        this.soLuong = soLuong;
        this.donGia = gia;
        this.id = new ChiTietDoiTraId(doiTra.getMaHDDT(), thuoc.getMaThuoc());
    }

    public ChiTietDoiTra(DoiTra doiTra, Thuoc thuoc) {
        this.doiTra = doiTra;
        this.thuoc = thuoc;
    }

    public ChiTietDoiTra(Thuoc thuoc, int soLuong, double gia) {
        this.thuoc = thuoc;
        this.soLuong = soLuong;
        this.donGia = gia;
    }

    public double thanhTien() {
        return this.donGia * this.soLuong;
    }

    public void setDoiTra(DoiTra doiTra) throws Exception {
        if (doiTra == null) throw new Exception(ORDERID_EMPTY);
        this.doiTra = doiTra;
    }

    public void setThuoc(Thuoc thuoc) throws Exception {
        if (thuoc == null) throw new Exception(PRODUCT_EMPTY);
        this.thuoc = thuoc;
    }

    public void setSoLuong(int soLuong) throws Exception {
        if (soLuong < 0) throw new Exception(QUANTITY_VALID);
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.doiTra);
        hash = 41 * hash + Objects.hashCode(this.thuoc);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChiTietDoiTra other = (ChiTietDoiTra) obj;
        return Objects.equals(this.doiTra, other.doiTra) &&
                Objects.equals(this.thuoc, other.thuoc);
    }

    @Override
    public String toString() {
        return "ChiTietDoiTra{" +
                "doiTra=" + doiTra +
                ", thuoc=" + thuoc +
                ", soLuong=" + soLuong +
                ", gia=" + donGia +
                '}';
    }
}
