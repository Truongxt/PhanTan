package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Nationalized;

@Entity
public class VaiTro {
    @Id
    @Column(name = "maVaiTro", nullable = false)
    private String maVaiTro;

    @Nationalized
    @Column(name = "tenVaiTro", nullable = false)
    private String tenVaiTro;

    public String getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(String maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }

    public void setTenVaiTro(String tenVaiTro) {
        this.tenVaiTro = tenVaiTro;
    }

    public VaiTro(String maVT) {
        this.maVaiTro = maVT;
    }

    public VaiTro() {
    }
}