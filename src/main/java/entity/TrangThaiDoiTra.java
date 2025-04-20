/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HÀ NHƯ
 */
public enum TrangThaiDoiTra {    
    PENDING(0), SUCCESS(1), CANCEL(2);

    private final int value;

    private TrangThaiDoiTra(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public boolean compare(int value) {
        return this.value == value;
    }

    public static TrangThaiDoiTra fromInt(int value) {
        for (TrangThaiDoiTra type : values()) {
            if (type.compare(value)) {
                return type;
            }
        }
        return null;
    }
}
