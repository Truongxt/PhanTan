package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class KiemTien implements Serializable {

    @EmbeddedId
    private KiemTienId id;

    @MapsId("maBangKiemTien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maBangKiemTien", nullable = false)
    private BangKiemTien maBangKiemTien;

    @Column(name = "soLuong", nullable = false)
    private int soLuong;

    @Column(name = "giaTri", nullable = false)
    private double giaTri;

    @Transient
    private double tong;

    @OneToOne(cascade = CascadeType.ALL)
    private KetToan ketToan;

    public KiemTien(int soLuong, double giaTri) {
        setSoLuong(soLuong);
        setGiaTri(giaTri);
        setTong();
    }

    public void setTong() {
        this.tong = this.giaTri * this.soLuong;
    }
}