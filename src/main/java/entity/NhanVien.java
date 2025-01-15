package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
public class NhanVien {
    @Id
    @Column(name = "maNhanVien", nullable = false)
    private String maNhanVien;

    @Nationalized
    @Column(name = "tenNhanVien", nullable = false)
    private String tenNhanVien;

    @Column(name = "email", nullable = false)
    private String email;

    @Nationalized
    @Column(name = "diaChi", nullable = false)
    private String diaChi;

    @Column(name = "sdt", nullable = false)
    private String sdt;

    @Column(name = "cccd", nullable = false)
    private String cccd;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai = false;

    @Column(name = "ngayVaoLam")
    private LocalDate ngayVaoLam;

    public NhanVien() {
        // Default constructor
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDate getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(LocalDate ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public NhanVien(String maNV) {
        this.maNhanVien = maNV;
    }
}