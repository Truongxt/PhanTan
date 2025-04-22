package entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TaiKhoan")
public class TaiKhoan implements Serializable {

    @Id
    @Column(name = "ten", nullable = false, unique = true)
    private String ten;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "trangThai", nullable = false)
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "vaiTro_id", nullable = false)
    private VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "nhanVien_id", nullable = false)
    private NhanVien nhanVien;

    public TaiKhoan() {
    }

    public TaiKhoan(String ten) {
        this.ten = ten;
    }

    public TaiKhoan(String ten, String password, boolean trangThai, VaiTro vaiTro, NhanVien nhanVien) {
        this.ten = ten;
        this.password = password;
        this.trangThai = trangThai;
        this.vaiTro = vaiTro;
        this.nhanVien = nhanVien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public VaiTro getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(VaiTro vaiTro) {
        this.vaiTro = vaiTro;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "ten='" + ten + '\'' +
                ", password='" + password + '\'' +
                ", trangThai=" + trangThai +
                ", vaiTro=" + vaiTro +
                ", nhanVien=" + nhanVien +
                '}';
    }
}