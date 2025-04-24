package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ChiTietBangKiemTienId implements Serializable {
    private static final long serialVersionUID = -3344009850631984072L;
    @Column(name = "chiSoKiem", nullable = false)
    private Boolean chiSoKiem = false;

    @Column(name = "maBangKiemTien", nullable = false)
    private String maBangKiemTien;

    public Boolean getChiSoKiem() {
        return chiSoKiem;
    }

    public void setChiSoKiem(Boolean chiSoKiem) {
        this.chiSoKiem = chiSoKiem;
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
        ChiTietBangKiemTienId entity = (ChiTietBangKiemTienId) o;
        return Objects.equals(this.chiSoKiem, entity.chiSoKiem) &&
                Objects.equals(this.maBangKiemTien, entity.maBangKiemTien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chiSoKiem, maBangKiemTien);
    }

}