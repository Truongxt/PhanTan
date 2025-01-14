package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

@Entity
public class NhaCungCap {
    @Id
    @Column(name = "maNCC", nullable = false)
    private String maNCC;

    @Nationalized
    @Column(name = "tenNCC", nullable = false)
    private String tenNCC;

    @Nationalized
    @Column(name = "diaChi", nullable = false)
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt", nullable = false)
    private String sdt;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai = false;

    public String getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    public String getTenNCC() {
        return tenNCC;
    }

    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}