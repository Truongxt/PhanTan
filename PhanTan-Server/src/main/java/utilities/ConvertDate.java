/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Xuân Trường
 */
public class ConvertDate {
    public static LocalDate convert(Date date){
        LocalDate newDate = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
        return newDate;
    }
}
