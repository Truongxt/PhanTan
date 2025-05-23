package message;

import static com.formdev.flatlaf.extras.FlatAnimatedLafChange.duration;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.ThongKeThuoc;
import java.awt.Point;
import java.awt.Window;
import javax.swing.SwingUtilities;

public class Notification extends javax.swing.JComponent {

  
    private JDialog dialog;
    private Animator animator;
    private  JPanel parentPanel = null;  // Đổi tên để rõ ý nghĩa hơn
    private boolean showing;
    private Thread thread;
    private int animate = 10;
    private BufferedImage imageShadow;
    private int shadowSize = 6;
    private Type type;
    private Location location;

   public Notification(JPanel parentPanel1, Type type, Location location, String message) {
        this.parentPanel = parentPanel1;
    this.type = type;
    this.location = location;
    initComponents(); // Khởi tạo giao diện
    init(message);    // Cấu hình thông báo
    initAnimator(); 
    }

  



 private void init(String message) {
        setBackground(Color.WHITE);
        // Tìm Window cha của JPanel
        Window windowAncestor = SwingUtilities.getWindowAncestor(parentPanel);
        if (windowAncestor instanceof JDialog) {
            dialog = (JDialog) windowAncestor;
        } else {
            dialog = new JDialog(SwingUtilities.windowForComponent(parentPanel));
        }
        
        dialog.setUndecorated(true);
        dialog.setFocusableWindowState(false);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.add(this);
        dialog.setSize(getPreferredSize());

        try {
            if (type == Type.SUCCESS) {
                setIcon(lbIcon, "/message/sucess.png");
                lbMessage.setText("Success");
            } else if (type == Type.INFO) {
                setIcon(lbIcon, "/message/info.png");
                lbMessage.setText("Info");
            } else {
                setIcon(lbIcon, "/message/warning.png");
                lbMessage.setText("Warning");
            }
            setIcon(cmdClose, "/message/close.png");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        lbMessageText.setText(message);
    }

private void setIcon(javax.swing.JLabel label, String path) {
    URL imageUrl = getClass().getResource(path);
    if (imageUrl != null) {
        label.setIcon(new javax.swing.ImageIcon(imageUrl));
    } else {
        System.err.println("Không tìm thấy ảnh: " + path);
        label.setIcon(null);
    }
}

private void setIcon(javax.swing.JButton button, String path) {
    URL imageUrl = getClass().getResource(path);
    if (imageUrl != null) {
        button.setIcon(new javax.swing.ImageIcon(imageUrl));
    } else {
        System.err.println("Không tìm thấy ảnh: " + path);
        button.setIcon(null);
    }
}


   private void initAnimator() {
        TimingTarget target = new TimingTargetAdapter() {
            private int x;
            private int top;
            private boolean top_to_bot;

            @Override
            public void timingEvent(float fraction) {
                if (showing) {
                    float alpha = 1f - fraction;
                    int y = (int) ((1f - fraction) * animate);
                    if (top_to_bot) {
                        dialog.setLocation(x, top + y);
                    } else {
                        dialog.setLocation(x, top - y);
                    }
                    dialog.setOpacity(alpha);
                } else {
                    float alpha = fraction;
                    int y = (int) (fraction * animate);
                    if (top_to_bot) {
                        dialog.setLocation(x, top + y);
                    } else {
                        dialog.setLocation(x, top - y);
                    }
                    dialog.setOpacity(alpha);
                }
            }

            @Override
            public void begin() {
                if (!showing) {
                    dialog.setOpacity(0f);
                    int margin = 10;
                    int y = 0;
                    
                    // Chuyển đổi tọa độ từ JPanel sang tọa độ màn hình
                    Point panelLocation = parentPanel.getLocationOnScreen();
                    
                    if (location == Location.TOP_CENTER) {
                        x = panelLocation.x + ((parentPanel.getWidth() - dialog.getWidth()) / 2);
                        y = panelLocation.y;
                        top_to_bot = true;
                    } else if (location == Location.TOP_RIGHT) {
                        x = panelLocation.x + parentPanel.getWidth() - dialog.getWidth() - margin;
                        y = panelLocation.y;
                        top_to_bot = true;
                    } else if (location == Location.TOP_LEFT) {
                        x = panelLocation.x + margin;
                        y = panelLocation.y;
                        top_to_bot = true;
                    } else if (location == Location.BOTTOM_CENTER) {
                        x = panelLocation.x + ((parentPanel.getWidth() - dialog.getWidth()) / 2);
                        y = panelLocation.y + parentPanel.getHeight() - dialog.getHeight();
                        top_to_bot = false;
                    } else if (location == Location.BOTTOM_RIGHT) {
                        x = panelLocation.x + parentPanel.getWidth() - dialog.getWidth() - margin;
                        y = panelLocation.y + parentPanel.getHeight() - dialog.getHeight();
                        top_to_bot = false;
                    } else if (location == Location.BOTTOM_LEFT) {
                        x = panelLocation.x + margin;
                        y = panelLocation.y + parentPanel.getHeight() - dialog.getHeight();
                        top_to_bot = false;
                    } else {
                        x = panelLocation.x + ((parentPanel.getWidth() - dialog.getWidth()) / 2);
                        y = panelLocation.y + ((parentPanel.getHeight() - dialog.getHeight()) / 2);
                        top_to_bot = true;
                    }
                    top = y;
                    dialog.setLocation(x, y);
                    dialog.setVisible(true);
                }
            }
        
         @Override
            public void end() {
                showing = !showing;
                if (showing) {
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            sleep();
                            closeNotification();
                        }
                    });
                    thread.start();
                } else {
                    dialog.dispose();
                }
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(5);
    }


    public void showNotification() {
        if (animator == null) {
            System.err.println("Animator không được khởi tạo!");
            return;
        }
        animator.start(); // Chỉ gọi start khi animator không null
    
    }

    private void closeNotification() {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
        if (animator.isRunning()) {
            if (!showing) {
                animator.stop();
                showing = true;
                animator.start();
            }
        } else {
            showing = true;
            animator.start();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.drawImage(imageShadow, 0, 0, null);
        int x = shadowSize;
        int y = shadowSize;
        int width = getWidth() - shadowSize * 2;
        int height = getHeight() - shadowSize * 2;
        g2.fillRect(x, y, width, height);
        if (type == Type.SUCCESS) {
            g2.setColor(new Color(18, 163, 24));
        } else if (type == Type.INFO) {
            g2.setColor(new Color(28, 139, 206));
        } else {
            g2.setColor(new Color(241, 196, 15));
        }
        g2.fillRect(6, 5, 5, getHeight() - shadowSize * 2 + 1);
        g2.dispose();
        super.paint(grphcs);
    }

    @Override
    public void setBounds(int i, int i1, int i2, int i3) {
        super.setBounds(i, i1, i2, i3);
        createImageShadow();
    }

    private void createImageShadow() {
        imageShadow = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageShadow.createGraphics();
        g2.drawImage(createShadow(), 0, 0, null);
        g2.dispose();
    }

    private BufferedImage createShadow() {
        BufferedImage img = new BufferedImage(getWidth() - shadowSize * 2, getHeight() - shadowSize * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.fillRect(0, 0, img.getWidth(), img.getHeight());
        g2.dispose();
        return new ShadowRenderer(shadowSize, 0.3f, new Color(100, 100, 100)).createShadow(img);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbIcon = new javax.swing.JLabel();
        panel = new JPanel();
        lbMessage = new javax.swing.JLabel();
        lbMessageText = new javax.swing.JLabel();
        cmdClose = new javax.swing.JButton();

        lbIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/message/sucess.png"))); // NOI18N

        panel.setOpaque(false);

        lbMessage.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbMessage.setForeground(new Color(38, 38, 38));
        lbMessage.setText("Message");

        lbMessageText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMessageText.setForeground(new Color(127, 127, 127));
        lbMessageText.setText("Message Text");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMessage)
                    .addComponent(lbMessageText))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbMessage)
                .addGap(3, 3, 3)
                .addComponent(lbMessageText)
                .addContainerGap())
        );

        cmdClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/message/close.png")));
        cmdClose.setBorder(null);
        cmdClose.setContentAreaFilled(false);
        cmdClose.setFocusable(false);
        cmdClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCloseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbIcon)
                .addGap(10, 10, 10)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdClose)
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCloseActionPerformed
        closeNotification();
    }//GEN-LAST:event_cmdCloseActionPerformed

    public static enum Type {
        SUCCESS, INFO, WARNING
    }

    public static enum Location {
        TOP_CENTER, TOP_RIGHT, TOP_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT, BOTTOM_LEFT, CENTER
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClose;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbMessageText;
    private JPanel panel;
    // End of variables declaration//GEN-END:variables
}
