package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietDoiTra {
    @EmbeddedId
    private ChiTietDoiTraId id;

    @MapsId("maHoaDonDoiTra")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHoaDonDoiTra", nullable = false)
    private DoiTra doiTra;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maThuoc", nullable = false)
    private Thuoc Thuoc;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "donGia", nullable = false)
    private Double donGia;

    public ChiTietDoiTraId getId() {
        return id;
    }

    public void setId(ChiTietDoiTraId id) {
        this.id = id;
    }

    public DoiTra getMaHoaDonDoiTra() {
        return doiTra;
    }

    public void setMaHoaDonDoiTra(DoiTra maHoaDonDoiTra) {
        this.doiTra = maHoaDonDoiTra;
    }

}