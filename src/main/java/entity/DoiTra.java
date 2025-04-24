/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 * @author HÀ NHƯ
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
//@NoArgsConstructor
public class DoiTra implements Serializable {
    private final String ORDER_ERROR="Order không được rỗng";
    private final String EMPLOYEE_ERROR="Employee không được rỗng";
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
    private ArrayList<ChiTietDoiTra> listDetail;
    @Column(name = "liDo")
    private String liDO;


    private int sldd;
    private int sldt;

    private double ttdt;


    public DoiTra(Date ngayDoiTra, String maHDDT, NhanVien nhanvien, HoaDon hoaDon, boolean loai, double tienTra, ArrayList<ChiTietDoiTra> listDetail, String liDO) {
        this.ngayDoiTra = ngayDoiTra;
        this.maHDDT = maHDDT;
        this.nhanvien = nhanvien;
        this.hoaDon = hoaDon;
        this.loai = loai;
        this.tienTra = tienTra;
        this.listDetail = listDetail;
        this.liDO = liDO;
    }

    public DoiTra(int sldd, int sldt, double ttdt) {
        this.sldd = sldd;
        this.sldt = sldt;
        this.ttdt = ttdt;
    }

    public DoiTra(String maHDDT) {
        this.maHDDT = maHDDT;
    }



    public DoiTra() {
    }

    public Date getNgayDoiTra() {
        return ngayDoiTra;
    }

    public void setNgayDoiTra(Date ngayDoiTra) {
        this.ngayDoiTra = ngayDoiTra;
    }



    public String getMaHDDT() {
        return maHDDT;
    }

    public void setMaHDDT(String maHDDT) {
        this.maHDDT = maHDDT;
    }

    public NhanVien getNhanvien() {
        return nhanvien;
    }

    public void setNhanvien(NhanVien nhanvien) throws Exception {
        if(nhanvien!=null)
            this.nhanvien = nhanvien;
        else
            throw new Exception(EMPLOYEE_ERROR);
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public boolean isLoai() {
        return loai;
    }

    public void setLoai(boolean loai) {
        this.loai = loai;
    }

    public double getTienTra() {
        return tienTra;
    }

    public void setTienTra(double tienTra) {
        this.tienTra = tienTra;
         if(this.loai == false
                )
            this.tienTra = 0;
        else {
            for (ChiTietDoiTra returnOrderDetail : listDetail) {
                this.tienTra += returnOrderDetail.getDonGia();
            }
        }
    }

    public String getLiDO() {
        return liDO;
    }

    public void setLiDO(String liDO) throws Exception {
        this.liDO = liDO;
        if(liDO.equalsIgnoreCase(""))
            throw new Exception(REASON_EMPTY);
        this.liDO = liDO;
    }

    public ArrayList<ChiTietDoiTra> getListDetail() {
        return listDetail;
    }

    public void setListDetail(ArrayList<ChiTietDoiTra> listDetail) {
        this.listDetail = listDetail;
    }

}
