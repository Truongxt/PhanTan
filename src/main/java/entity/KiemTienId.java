package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KiemTienId implements Serializable {
    private static final long serialVersionUID = -5796613238098279727L;
    @Column(name = "giaTri", nullable = false)
    private Double giaTri;

    @Column(name = "maBangKiemTien", nullable = false)
    private String maBangKiemTien;

    public Double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(Double giaTri) {
        this.giaTri = giaTri;
    }

    public String getMaBangKiemTien() {
        return maBangKiemTien;
    }

    public void setMaBangKiemTien(String maBangKiemTien) {
        this.maBangKiemTien = maBangKiemTien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        KiemTienId entity = (KiemTienId) o;
        return Objects.equals(this.giaTri, entity.giaTri) &&
                Objects.equals(this.maBangKiemTien, entity.maBangKiemTien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giaTri, maBangKiemTien);
    }

}