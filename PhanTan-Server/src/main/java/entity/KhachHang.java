package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class KhachHang implements Serializable {
    @Id
    @Column(name = "maKhachHang", nullable = false)
    private String maKhachHang;

    @Nationalized
    @Column(name = "tenKhachHang", nullable = false)
    private String tenKhachHang;

    @Column(name = "sdt", nullable = false)
    private String sdt;

    @Nationalized
    @Column(name = "diaChi", length = 150)
    private String diaChi;

    @Column(name = "NgayLapTaiKhoan")
    private LocalDate ngayLapTaiKhoan;



    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgayLapTaiKhoan() {
        return ngayLapTaiKhoan;
    }

    public void setNgayLapTaiKhoan(LocalDate ngayLapTaiKhoan) {
        this.ngayLapTaiKhoan = ngayLapTaiKhoan;
    }

}