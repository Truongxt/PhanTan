package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Entity
@Table(name = "TaiKhoan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiKhoan implements Serializable {

    @Id
    @Column(name = "tenTaiKhoan", nullable = false, unique = true) // Update to match the actual column name
    private String ten;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "trangThai", nullable = false)
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "maVaiTro", nullable = false) // Update to match the actual column name
    private VaiTro vaiTro;

    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false) // Update to match the actual column name
    private NhanVien nhanVien;

    // Getters and setters
}