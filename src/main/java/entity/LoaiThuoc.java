package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

@Entity
public class LoaiThuoc {

    @Id
    @Column(name = "maLoai", nullable = false)
    private String maLoai;

    @Nationalized
    @Column(name = "tenLoai", nullable = false)
    private String tenLoai;


    public LoaiThuoc() {
    }


    public LoaiThuoc(String maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
