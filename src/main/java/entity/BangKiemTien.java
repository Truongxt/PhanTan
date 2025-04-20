package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BangKiemTien implements Serializable {
    @Id
    @Column(name = "maBangKiemTien", nullable = false)
    private String maBangKiemTien;

    @Column(name = "ngayBatDau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngayKetThuc", nullable = false)
    private LocalDate ngayKetThuc;


    @OneToMany(mappedBy = "maBangKiemTien",fetch = FetchType.LAZY)
    private List<KiemTien> listKiemTien;

    @OneToMany(mappedBy = "bangKiemTien", fetch = FetchType.LAZY)
    private ArrayList<ChiTietBangKiemTien> listChiTietBangKiemTien;

}