package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Thuoc implements Serializable {
    @Id
    @Column(name = "maThuoc", nullable = false)
    private String maThuoc;

    @Nationalized
    @Column(name = "tenThuoc", nullable = false)
    private String tenThuoc;

    @Column(name = "gia", nullable = false)
    private Double gia;

    @Column(name = "hsd", nullable = false)
    private LocalDate hsd;

    @Column(name = "nsx", nullable = false)
    private LocalDate nsx;

    @Column(name = "thue", nullable = false)
    private Double thue;

    @Column(name = "soLuongTon", nullable = false)
    private Integer soLuongTon;

    @Nationalized
    @Column(name = "mota", nullable = false)
    private String mota;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maLoai", nullable = false)
    private LoaiThuoc maLoai;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maXuatXu", nullable = false)
    private XuatXu maXuatXu;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maDonViTinh", nullable = false)
    private DonViTinh maDonViTinh;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maNCC", nullable = false)
    private NhaCungCap maNCC;

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public LocalDate getHsd() {
        return hsd;
    }

    public void setHsd(LocalDate hsd) {
        this.hsd = hsd;
    }

    public LocalDate getNsx() {
        return nsx;
    }

    public void setNsx(LocalDate nsx) {
        this.nsx = nsx;
    }

    public Double getThue() {
        return thue;
    }

    public void setThue(Double thue) {
        this.thue = thue;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public LoaiThuoc getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(LoaiThuoc maLoai) {
        this.maLoai = maLoai;
    }

    public XuatXu getMaXuatXu() {
        return maXuatXu;
    }

    public void setMaXuatXu(XuatXu maXuatXu) {
        this.maXuatXu = maXuatXu;
    }

    public DonViTinh getMaDonViTinh() {
        return maDonViTinh;
    }

    public void setMaDonViTinh(DonViTinh maDonViTinh) {
        this.maDonViTinh = maDonViTinh;
    }

    public NhaCungCap getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(NhaCungCap maNCC) {
        this.maNCC = maNCC;
    }

}