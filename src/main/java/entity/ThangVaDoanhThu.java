/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lenovo
 */
public class ThangVaDoanhThu {
    private int thang;
    private double doanhThu;

    public ThangVaDoanhThu(int thang, double doanhThu) {
        this.thang = thang;
        this.doanhThu = doanhThu;
    }

    public int getThang() {
        return thang;
    }

    public double getDoanhThu() {
        return doanhThu;
    }

    public void setThang(int thang) {
        this.thang = thang;
    }

    public void setDoanhThu(double doanhThu) {
        this.doanhThu = doanhThu;
    }
    
}
