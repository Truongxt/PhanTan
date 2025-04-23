package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "OTP")
public class Otp {

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
    private LocalDateTime createdAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}