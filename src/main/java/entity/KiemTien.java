package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KiemTien {

    @EmbeddedId
    private KiemTienId id;

    @MapsId("maBangKiemTien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien maBangKiemTien;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "giaTri", nullable = false)
    private Double giaTri;

    @Transient
    private Double tong;


    @OneToOne(cascade = CascadeType.ALL)
    private KetToan ketToan;

    public KiemTien(int soLuong, double giaTri) {
        setSoLuong(soLuong);
        setGiaTri(giaTri);
        setTong();
    }

    public int getSoLuong() {
        return soLuong;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public double getTong() {
        return tong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    public void setTong() {
        this.tong = this.giaTri * this.soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.giaTri) ^ (Double.doubleToLongBits(this.giaTri) >>> 32));
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
        final KiemTien other = (KiemTien) obj;
        return Double.doubleToLongBits(this.giaTri) == Double.doubleToLongBits(other.giaTri);
    }

}