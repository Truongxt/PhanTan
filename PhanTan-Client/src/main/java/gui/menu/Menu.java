/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.menu;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatArcDarkIJTheme;
import gui.event.EventMenu;
import gui.event.EventMenuSelected;
import gui.event.EventShowPopupMenu;
import gui.model.ModelMenu;
import gui.swing.MenuAnimation;
import gui.swing.MenuItem;
import gui.swing.ScrollBarCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author User
 */
public class Menu extends JPanel {

    //kiểm soát vẽ components
    private boolean customPaintEnabled = true;

    public boolean isCustomPaintEnabled() {
        return customPaintEnabled;
    }

    public void setCustomPaintEnabled(boolean enabled) {
        this.customPaintEnabled = enabled;
        repaint();
    }

    public void addEvent(EventMenuSelected event) {
        this.event = event;
    }

    public void setMenuItemCustomPaint(boolean enabled) {
        if (menuItem != null) {
            menuItem.setCustomPaintEnabled(enabled);
        }
    }

    /**
     * @return the enableMenu
     */
    public boolean isEnableMenu() {
        return enableMenu;
    }

    /**
     * @param enableMenu the enableMenu to set
     */
    public void setEnableMenu(boolean enableMenu) {
        this.enableMenu = enableMenu;
    }

    /**
     * @return the showMenu
     */
    public boolean isShowMenu() {
        return showMenu;
    }

    /**
     * @param showMenu the showMenu to set
     */
    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }

    public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }
    private final MigLayout layout;
//    private final Animator animator;
    private EventMenuSelected event;
    private EventShowPopupMenu eventShowPopup;
    private boolean enableMenu = true;
    private boolean showMenu = true;
    private static MenuItem menuItem;

    public MenuItem getItemFull() {
        return itemFull;
    }

    public void setItemFull(MenuItem itemFull) {
        this.itemFull = itemFull;
    }
    private MenuItem itemFull;

    public EventMenuSelected getEvent() {
        return event;
    }

    public void setEvent(EventMenuSelected event) {
        this.event = event;
    }

    public EventShowPopupMenu getEventShowPopup() {
        return eventShowPopup;
    }

    public void setEventShowPopup(EventShowPopupMenu eventShowPopup) {
        this.eventShowPopup = eventShowPopup;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Profile getProfile2() {
        return profile2;
    }

    public void setProfile2(Profile profile2) {
        this.profile2 = profile2;
    }

    public JScrollPane getSp() {
        return sp;
    }

    public void setSp(JScrollPane sp) {
        this.sp = sp;
    }

    public Menu() {
        initComponents();
        setOpaque(false);

        sp.getViewport().setOpaque(false);
        sp.setVerticalScrollBar(new ScrollBarCustom());
        layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]-25[]");
        panel.setLayout(layout);

    }

    public void initMenuItem() {
        addMenu(new ModelMenu(new ImageIcon("/img/business.png"), "Bán hàng"));

        addMenu(new ModelMenu(new ImageIcon("/img/drugs.png"), "Thuốc"));
        addMenu(new ModelMenu(new ImageIcon("/img/checklist.png"), "Hóa đơn", "Tạo hóa đơn đổi trả", "Lịch sử hóa đơn", "Lịch sử đổi trả"));
        addMenu(new ModelMenu(new ImageIcon("/img/analysis.png"), "Thống kê", "Thuốc", "Khách hàng", "Doanh thu"));
        addMenu(new ModelMenu(new ImageIcon("/img/computer.png"), "Báo cáo", "Phiếu kiểm tiền", "Kết toán", "Danh sách phiếu kiểm tiền", "Danh sách kết toán"));
        addMenu(new ModelMenu(new ImageIcon("/img/end.png"), "Khách hàng"));
        addMenu(new ModelMenu(new ImageIcon("/img/employee.png"), "Nhân viên"));
        addMenu(new ModelMenu(new ImageIcon("/img/transportation.png"), "Nhà cung cấp"));
        addMenu(new ModelMenu(new ImageIcon("/img/log-out.png"), "Đăng xuất"));

    }

    private void addMenu(ModelMenu menu) {
        itemFull = new MenuItem(menu, getEventMenu(), event, panel.getComponentCount());
        panel.add(itemFull, "h 100!");
    }

    // ẩn tất cả menu
    public void hideAllMenu() {
        for (Component c : panel.getComponents()) {
            MenuItem item = (MenuItem) c;
            if (item.isOpen()) {
                new MenuAnimation(layout, c, 500).closeMenu();
                item.setOpen(false);
            }
        }
    }

    private EventMenu getEventMenu() {
        return new EventMenu() {
            @Override
            public boolean menuPressed(Component com, boolean open) {
                if (enableMenu) {
                    if (showMenu) {
                        if (open) {
                            new MenuAnimation(layout, com).openMenu();
                        } else {
                            new MenuAnimation(layout, com).closeMenu();
                        }
                        return true;
                    } else {
                        eventShowPopup.showPopup(com);
                    }
                }
                return false;
            }
        };
    }

    //update menu cho darkmode hoac lightmode
    public void updateMenuColors(boolean isDarkMode) {
        if (isDarkMode) {
            this.setBackground(new Color(45, 45, 45)); // Màu nền tối
            this.setForeground(Color.WHITE); // Màu chữ trắng
        } else {
            this.setBackground(Color.WHITE); // Màu nền sáng
            this.setForeground(Color.BLACK); // Màu chữ đen
        }
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new JScrollPane();
        panel = new JPanel();
        profile2 = new Profile();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.setBackground(new Color(255, 255, 255));
        panel.setOpaque(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );

        sp.setViewportView(panel);

        profile2.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profile2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        if (!customPaintEnabled) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0, Color.decode("#3C3B3F"), getWidth(), 0, Color.decode("#605C3C"));
            g2.setPaint(gp);
            g2.fillRect(0, 0, getWidth(), getHeight());
            super.paintComponent(g);
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp = new GradientPaint(0, 0, Color.decode("#11998e"), getWidth(), 0, Color.decode("#38ef7d"));
        g2.setPaint(gp);

        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    public void setDarkBackGround() {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("theme");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15));
        FlatArcDarkIJTheme.setup();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel;
    private Profile profile2;
    private JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
