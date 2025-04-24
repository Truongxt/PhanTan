/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;

/**
 *
 * @author User
 */
public class ScrollBarCustom extends JScrollBar  {
     public ScrollBarCustom() {
        setUI(new ModernScrollBarUI());
        setPreferredSize(new Dimension(5, 5));
        setForeground(new Color(94, 139, 231));
        setUnitIncrement(20);
        setOpaque(false);
    }
}
