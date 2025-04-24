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
@Table(name = "NhanVien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien implements Serializable {

    @Id
    @Column(name = "maNhanVien", nullable = false)
    private String maNhanVien;

    @Nationalized
    @Column(name = "tenNhanVien", nullable = false)
    private String tenNhanVien;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Nationalized
    @Column(name = "diaChi", nullable = false)
    private String diaChi;

    @Column(name = "sdt", nullable = false, length = 15)
    private String sdt;

    @Column(name = "cccd", nullable = false, unique = true, length = 12)
    private String cccd;

    @Column(name = "trangThai", nullable = false)
    private Boolean trangThai = false;

    @Column(name = "ngayVaoLam")
    private LocalDate ngayVaoLam;

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNhanVien='" + maNhanVien + '\'' +
                ", tenNhanVien='" + tenNhanVien + '\'' +
                ", email='" + email + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", sdt='" + sdt + '\'' +
                ", cccd='" + cccd + '\'' +
                ", trangThai=" + trangThai +
                ", ngayVaoLam=" + ngayVaoLam +
                '}';
    }
}