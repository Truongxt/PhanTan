/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lenovo
 */
public class ThuocvaDoanhThu {
    private Thuoc thuoc;
    private double doanhThu;

    public ThuocvaDoanhThu(Thuoc thuoc, double doanhThu) {
        this.thuoc = thuoc;
        this.doanhThu = doanhThu;
    }

    public Thuoc getThuoc() {
        return thuoc;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
}

