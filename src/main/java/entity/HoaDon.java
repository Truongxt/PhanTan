package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String maHD;

    private LocalDate ngayLap;
    private double tongTien;

    @ManyToOne
    @JoinColumn(name = "khachHang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhanVien_id", nullable = false)
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "maKetToan", nullable = false)
    private KetToan ketToan;

    private boolean atm;
    private double tienDaDua;
    private boolean trangThai;

    // Constructor for maHD
    public HoaDon(String maHD) {
        this.maHD = maHD;
    }
}