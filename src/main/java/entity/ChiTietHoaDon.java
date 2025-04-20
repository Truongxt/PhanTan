package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietHoaDon {
    @EmbeddedId
    private ChiTietHoaDonId id;

    @MapsId("maHD")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maHD", nullable = false)
    private HoaDon hoaDon;

    @MapsId("maThuoc")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maThuoc", nullable = false)
    private Thuoc thuoc;

    @Column(name = "soLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "donGia", nullable = false)
    private Double donGia;



}