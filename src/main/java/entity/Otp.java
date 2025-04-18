package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@Entity
@Table(name = "OTP")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tentaiKhoan", nullable = false)
    private NhanVien tentaiKhoan;

    @Nationalized
    @Column(name = "maXacNhan", nullable = false, length = 50)
    private String maXacNhan;

    @ColumnDefault("getdate()")
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}