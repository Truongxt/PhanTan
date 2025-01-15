package entity;

import jakarta.persistence.*;

@Entity
public class TaiKhoan {
    @Id
    @Column(name = "tenTaiKhoan", nullable = false)
    private String tenTaiKhoan;

    @Column(name = "password", nullable = false)
    private String password;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maVaiTro", nullable = false)
    private VaiTro maVaiTro;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien maNhanVien;

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public VaiTro getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(VaiTro maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public NhanVien getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(NhanVien maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

}