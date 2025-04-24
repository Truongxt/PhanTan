/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.notifications;

import dao.Thuoc_DAO;
import entity.Thuoc;
import gui.swing.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.layout.AC;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Xuân Trường
 */
public class Notifications extends javax.swing.JPanel {

    private JPopupMenu expiredMedsPopup;

    public Notifications() throws RemoteException {
        initComponents();

        setOpaque(false);
        JScrollBar sb = scroll.getVerticalScrollBar();
        sb.setOpaque(false);
        sb.setBackground(new Color(33, 140, 206));
        sb.setPreferredSize(new Dimension(8, 8));
        sb.setUI(new ModernScrollBarUI());
        scroll.getViewport().setOpaque(false);
        scroll.setViewportBorder(null);
        panel.setLayout(new MigLayout("insets 0, fillx, wrap", "[fill]"));
        showExpiredMedications();
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#11998e"), getWidth(), 0, Color.decode("#38ef7d"));
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    private void showExpiredMedications() throws RemoteException {
        ArrayList<Thuoc> listThuocHetHan = new Thuoc_DAO().getThuocHetHan();
        for (Thuoc med : listThuocHetHan) {
            JLabel label = new JLabel();
            long p2 = ChronoUnit.DAYS.between(med.getHsd(), LocalDate.now());
            panel.add(new Item(med.getMaThuoc(), med.getTenThuoc(), "Vừa hết hạn " + p2 + " ngày"), "growx, wrap");
        }

        panel.revalidate();
        panel.repaint();
        scroll.revalidate();
        scroll.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new JLabel();
        scroll = new javax.swing.JScrollPane();
        panel = new javax.swing.JPanel();

        setBackground(new Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        jLabel1.setFont(new Font("Segoe UI", 1, 15)); // NOI18N
        jLabel1.setForeground(new Color(97, 97, 97));
        jLabel1.setText("Notifications");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setOpaque(false);

        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 239, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );

        scroll.setViewportView(panel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(26, 26, 26)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    // End of variables declaration//GEN-END:variables
}
