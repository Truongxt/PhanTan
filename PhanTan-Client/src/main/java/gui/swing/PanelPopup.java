/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing;

import javax.swing.*;
import java.awt.*;

public class PanelPopup extends JPanel {

    public PanelPopup() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(50, 50, 50));
        g2.fillRect(8, 0, (getSize().width - 8), getSize().height);
        int x[] = {0, 10, 10};
        int y[] = {20, 13, 27};
        g2.fillPolygon(x, y, x.length);
        super.paintComponent(g);
    }

}
