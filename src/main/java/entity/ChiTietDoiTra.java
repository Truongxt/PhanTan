package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
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
    private Integer soLuong;

    @Column(name = "donGia", nullable = false)
    private Double donGia;

    public ChiTietDoiTra(DoiTra doiTra, Thuoc thuoc, int soLuong, double gia) {
        this.doiTra = doiTra;
        this.thuoc = thuoc;
        this.soLuong = soLuong;
        this.donGia = gia;
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



    public ChiTietDoiTra() {
    }



    public DoiTra getDoiTra() {
        return doiTra;
    }

    public void setDoiTra(DoiTra doiTra) throws Exception {
        this.doiTra = doiTra;
        if(doiTra == null)
            throw new Exception(ORDERID_EMPTY);
        this.doiTra = doiTra;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public void setThuoc(Thuoc thuoc) throws Exception {
        if(thuoc == null)
            throw new Exception(PRODUCT_EMPTY);
        this.thuoc = thuoc;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) throws Exception {

        if(soLuong < 0)
            throw new Exception(QUANTITY_VALID);
        this.soLuong = soLuong;
    }

    public double getGia() {
        return donGia;
    }

    public void setGia(double gia) {
        this.donGia = gia;
    }
    public double thanhTien(){
        return this.donGia * this.soLuong;
    }


    public DoiTra getReturnOrder() {
        return doiTra;
    }

    public void setReturnOrder(DoiTra returnOrder) throws Exception {
        if(returnOrder == null)
            throw new Exception(ORDERID_EMPTY);
        this.doiTra = returnOrder;
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietDoiTra other = (ChiTietDoiTra) obj;
        if (!Objects.equals(this.doiTra, other.doiTra)) {
            return false;
        }
        return Objects.equals(this.thuoc, other.thuoc);
    }

    @Override
    public String toString() {
        return "ChiTietDoiTra{" + "doiTra=" + doiTra + ", thuoc=" + thuoc + ", soLuong=" + soLuong + ", gia=" + donGia + '}';
    }


}