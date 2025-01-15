package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietHoaDonId implements Serializable {
    private static final long serialVersionUID = -3379715772813315407L;
    @Column(name = "maHD", nullable = false)
    private String maHD;

    @Column(name = "maThuoc", nullable = false)
    private String maThuoc;

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public ChiTietHoaDonId(String maHD, String maThuoc) {
        this.maHD = maHD;
        this.maThuoc = maThuoc;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChiTietHoaDonId entity = (ChiTietHoaDonId) o;
        return Objects.equals(this.maHD, entity.maHD) &&
                Objects.equals(this.maThuoc, entity.maThuoc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHD, maThuoc);
    }

}