/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Lenovo
 */
public class ThuocVaLuotBan {
   
    private Thuoc thuoc;  // Đối tượng thuốc
    private int luotBan;   // Số lượng bán

    // Constructor
    public ThuocVaLuotBan(Thuoc thuoc, int luotBan) {
        this.thuoc = thuoc;
        this.luotBan = luotBan;
    }

    // Getter cho thuốc
    public Thuoc getThuoc() {
        return thuoc;
    }

    // Getter cho số lượng bán
    public int getLuotBan() {
        return luotBan;
    }

    // Setter cho thuốc
    public void setThuoc(Thuoc thuoc) {
        this.thuoc = thuoc;
    }

    // Setter cho số lượng bán
    public void setLuotBan(int luotBan) {
        this.luotBan = luotBan;
    }


}
