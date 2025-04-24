package entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class DoiTra {
    private final String ORDER_ERROR = "Order không được rỗng";
    private final String EMPLOYEE_ERROR = "Employee không được rỗng";
    private final String REASON_EMPTY = "Lý do không được rỗng";
    private final String TYPE_EMPTY = "Loại đơn đổi trả không được rỗng";
    private final String RETURNORDERID_VALID = "Mã đơn đổi trả không đúng cú pháp";

    private Date ngayDoiTra;

    @Id
    @Column(name = "maHoaDonDoiTra", nullable = false)
    private String maHDDT;

    @ManyToOne
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanvien;

    @OneToOne
    @JoinColumn(name = "maHoaDon")
    private HoaDon hoaDon;

    @Column(name = "loai")
    private boolean loai;

    @Column(name = "tienTra")
    private double tienTra;

    @OneToMany(mappedBy = "doiTra", cascade = CascadeType.ALL)
    private List<ChiTietDoiTra> listDetail = new ArrayList<>();

    @Column(name = "liDo")
    private String liDO;

    private int sldd;
    private int sldt;
    private double ttdt;

    // Default constructor
    public DoiTra() {
        this.listDetail = new ArrayList<>();
    }

    // Constructor for DoiTra(String maHDDT)
    public DoiTra(String maHDDT) {
        this();
        this.maHDDT = maHDDT;
    }

    // Constructor for DoiTra(int totalExchanges, int totalReturns, double totalAmount)
    public DoiTra(int totalExchanges, int totalReturns, double totalAmount) {
        this.sldd = totalExchanges;
        this.sldt = totalReturns;
        this.ttdt = totalAmount;
    }

    public void setTienTra(double tienTra) {
        this.tienTra = tienTra;
        if (!this.loai) {
            this.tienTra = 0;
        } else if (this.listDetail != null) {
            for (ChiTietDoiTra returnOrderDetail : listDetail) {
                this.tienTra += returnOrderDetail.getDonGia();
            }
        }
    }
    public DoiTra(Date ngayDoiTra, String maHDDT, NhanVien nhanvien, HoaDon hoaDon, boolean loai, double tienTra, List<ChiTietDoiTra> listDetail, String liDO) {
        this.ngayDoiTra = ngayDoiTra;
        this.maHDDT = maHDDT;
        this.nhanvien = nhanvien;
        this.hoaDon = hoaDon;
        this.loai = loai;
        this.tienTra = tienTra;
        this.listDetail = listDetail;
        this.liDO = liDO;
    }
}