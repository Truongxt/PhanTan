package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ChiTietBangKiemTien implements Serializable {
    @EmbeddedId
    private ChiTietBangKiemTienId id;

    @MapsId("maBangKiemTien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien bangKiemTien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien maNhanVien;

    @Column(name = "chiSo", nullable = false)
    private boolean chiSo;


    public ChiTietBangKiemTien() {
    }

    public ChiTietBangKiemTien(boolean chiSo, NhanVien nhanVien, BangKiemTien bangKiemTien) {
        this.chiSo = chiSo;
        this.maNhanVien = nhanVien;
        this.bangKiemTien = bangKiemTien;
    }

    public boolean getChiSo() {
        return chiSo;
    }

    public void setChiSo(boolean chiSo) {
        this.chiSo = chiSo;
    }

    public NhanVien getNhanVien() {
        return maNhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.maNhanVien = nhanVien;
    }

    public BangKiemTien getBangKiemTien() {
        return bangKiemTien;
    }

    public void setBangKiemTien(BangKiemTien bangKiemTien) {
        this.bangKiemTien = bangKiemTien;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.maNhanVien);
        hash = 97 * hash + Objects.hashCode(this.bangKiemTien);
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
        final ChiTietBangKiemTien other = (ChiTietBangKiemTien) obj;
        if (!Objects.equals(this.maNhanVien, other.maNhanVien)) {
            return false;
        }
        return Objects.equals(this.bangKiemTien, other.bangKiemTien);
    }


}