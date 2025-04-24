package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietDoiTraId implements Serializable {
    private static final long serialVersionUID = 5591114340404316901L;
    @Column(name = "maHoaDonDoiTra", nullable = false)
    private String maHoaDonDoiTra;

    @Column(name = "maThuoc", nullable = false)
    private String maThuoc;

    public ChiTietDoiTraId() {

    }


    public String getMaHoaDonDoiTra() {
        return maHoaDonDoiTra;
    }

    public void setMaHoaDonDoiTra(String maHoaDonDoiTra) {
        this.maHoaDonDoiTra = maHoaDonDoiTra;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public ChiTietDoiTraId(String maHoaDonDoiTra, String maThuoc) {
        this.maHoaDonDoiTra = maHoaDonDoiTra;
        this.maThuoc = maThuoc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChiTietDoiTraId entity = (ChiTietDoiTraId) o;
        return Objects.equals(this.maHoaDonDoiTra, entity.maHoaDonDoiTra) &&
                Objects.equals(this.maThuoc, entity.maThuoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHoaDonDoiTra, maThuoc);
    }

}