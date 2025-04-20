package entity;

import java.time.LocalDate;

public class Voucher {

	private String maVoucher;
	private String tenVoucher;
	private String moTa;
	private LocalDate ngayApDung;
	private LocalDate ngayKetThuc;
        private double giaGiam;

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Voucher(String maVoucher, String tenVoucher, String moTa, LocalDate ngayApDung, LocalDate ngayKetThuc, double giaGiam) {
        this.maVoucher = maVoucher;
        this.tenVoucher = tenVoucher;
        this.moTa = moTa;
        this.ngayApDung = ngayApDung;
        this.ngayKetThuc = ngayKetThuc;
        this.giaGiam = giaGiam;
    }

    

    public Voucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getMaVoucher() {
        return maVoucher;
    }

    public void setMaVoucher(String maVoucher) {
        this.maVoucher = maVoucher;
    }

    public String getTenVoucher() {
        return tenVoucher;
    }

    public void setTenVoucher(String tenVoucher) {
        this.tenVoucher = tenVoucher;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public LocalDate getNgayApDung() {
        return ngayApDung;
    }

    public void setNgayApDung(LocalDate ngayApDung) {
        this.ngayApDung = ngayApDung;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

}