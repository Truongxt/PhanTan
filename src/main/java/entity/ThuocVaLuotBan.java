package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Lớp ThuocVaLuotBan lưu thông tin về thuốc, số lượng bán và doanh thu.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThuocVaLuotBan {
    private Thuoc thuoc;    // Đối tượng thuốc
    private int luotBan;    // Số lượng bán
    private double doanhThu; // Doanh thu

    // Constructor cho JPQL
    public ThuocVaLuotBan(Thuoc thuoc, Number luotBan, Number doanhThu) {
        this.thuoc = thuoc;
        this.luotBan = luotBan.intValue();
        this.doanhThu = doanhThu.doubleValue();
    }

    public ThuocVaLuotBan(Thuoc thuoc, int i) {
        this.thuoc = thuoc;
        this.luotBan = i;
    }
}