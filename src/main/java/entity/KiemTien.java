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

    public Double getTong() {
        return this.giaTri * this.soLuong;
    }
}