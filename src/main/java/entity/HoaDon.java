package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "hoa_don") // Ensure this matches the actual table name in the database
@Getter
@Setter
public class HoaDon implements Serializable {

    @Id
    @Column(name = "ma_hd", nullable = false, unique = true)
    private String maHD;

    @Column(name = "ngay_lap", nullable = true)
    private LocalDate ngayLap;

    @Column(name = "tong_tien", nullable = true)
    private double tongTien;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "khach_hang_id", nullable = false)
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "ket_toan_id")
    private KetToan ketToan;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietHoaDon> listCTHD = new ArrayList<>();

    @Column(name = "atm")
    private boolean atm;

    @Column(name = "tien_da_dua")
    private double tienDaDua;

    @Column(name = "trang_thai")
    private boolean trangThai;

    public HoaDon() {
    }

    public HoaDon(String maHD) {
        this.maHD = maHD;
    }
}