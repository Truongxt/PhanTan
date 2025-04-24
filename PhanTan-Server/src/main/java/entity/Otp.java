package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "OTP")
public class Otp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) // Ensure the column name matches the database
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tentaiKhoan", referencedColumnName = "maNhanVien", nullable = false) // Update to match the actual column name
    private NhanVien tentaiKhoan;

    @Nationalized
    @Column(name = "maXacNhan", nullable = false, length = 50)
    private String maXacNhan;

    @ColumnDefault("getdate()")
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NhanVien getTentaiKhoan() {
        return tentaiKhoan;
    }

    public void setTentaiKhoan(NhanVien tentaiKhoan) {
        this.tentaiKhoan = tentaiKhoan;
    }

    public String getMaXacNhan() {
        return maXacNhan;
    }

    public void setMaXacNhan(String maXacNhan) {
        this.maXacNhan = maXacNhan;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}