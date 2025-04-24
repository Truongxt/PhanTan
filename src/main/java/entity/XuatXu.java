package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;

@Entity
@Data
public class XuatXu implements Serializable {
    @Id
    @Column(name = "maXuatXu", nullable = false)

    private String maXuatXu;

    @Nationalized
    @Column(name = "tenXuatXu", nullable = false)
    private String tenXuatXu;



    public String getTenXuatXu() {
        return tenXuatXu;
    }

    public void setTenXuatXu(String tenXuatXu) {
        this.tenXuatXu = tenXuatXu;
    }

}