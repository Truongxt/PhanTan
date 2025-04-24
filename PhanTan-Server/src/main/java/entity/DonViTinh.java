package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

@Data
@Entity
@Table(name = "DonViTinh")
public class DonViTinh implements Serializable {
    @Id
    @Column(name = "maDonViTinh", nullable = false)
    private String maDonViTinh;

    @Nationalized
    @Column(name = "ten", nullable = false)
    private String ten;

    public String getMaDonViTinh() {
        return maDonViTinh;
    }

    public void setMaDonViTinh(String maDonViTinh) {
        this.maDonViTinh = maDonViTinh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}