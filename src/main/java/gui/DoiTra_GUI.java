
package gui;

import com.formdev.flatlaf.FlatClientProperties;
import dao.*;
import entity.*;
import main.Main;
import raven.toast.Notifications;
import utilities.FormatNumber;
import utilities.ReturnOrderPrinter;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author HÀ NHƯ
 */
public class DoiTra_GUI extends javax.swing.JPanel {
    private static final Logger LOGGER = Logger.getLogger(DoiTra_GUI.class.getName());

    private ArrayList<ChiTietHoaDon> cart;
    private ChiTietHoaDon cthd;
    private DefaultTableModel tblModel_HD;
    private DefaultTableModel tblModel_SP;
    private HoaDon_DAO hd_DAO;
    private int maxSoLuong;
    private double tongTienTra;
    private HoaDon hoaDon;
    private TaiKhoan tk;
    private NhanVien nv;
    ArrayList<ChiTietHoaDon> listSPHoan;
    ArrayList<ChiTietDoiTra> listDoiTra;
    private DoiTra doiTra;
    private int maxQuantity;
    private double totalRefund;
    private boolean isUpdating = false;
    private JPopupMenu popup = new JPopupMenu();
    private boolean isSelectingFromPopup = false;
    private HoaDon hd;
    private double refund = 0;

    public DoiTra_GUI(TaiKhoan tk) throws RemoteException {
        initComponents();
        initTableRenderers(); // Thêm renderer an toàn
        this.tk = tk;
        cart = new ArrayList<>();
        hd_DAO = new HoaDon_DAO();
        listSPHoan = new ArrayList<>();
        listDoiTra = new ArrayList<>();

        txtMaHDDT.setText(TaoID());
        initNhanVien();
        doiTra = new DoiTra(txtMaHDDT.getText());
        buttonGroup1.add(rdn_Doi);
        buttonGroup1.add(rdn_Tra);
    }

    /**
     * Khởi tạo renderer tùy chỉnh để xử lý giá trị null trong bảng
     */
    private void initTableRenderers() {
        DefaultTableCellRenderer nullSafeRenderer = new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText(value != null ? value.toString() : "");
            }
        };
        LOGGER.info("Applying null-safe renderer to tables");
        if (table_SanPham != null) {
            for (int i = 0; i < table_SanPham.getColumnCount(); i++) {
                table_SanPham.getColumnModel().getColumn(i).setCellRenderer(nullSafeRenderer);
            }
            LOGGER.info("Renderer applied to table_SanPham");
        } else {
            LOGGER.warning("table_SanPham is null in initTableRenderers");
        }
        if (table_HD != null) {
            for (int i = 0; i < table_HD.getColumnCount(); i++) {
                table_HD.getColumnModel().getColumn(i).setCellRenderer(nullSafeRenderer);
            }
            LOGGER.info("Renderer applied to table_HD");
        } else {
            LOGGER.warning("table_HD is null in initTableRenderers");
        }
    }

    public void initNhanVien() throws RemoteException {
        this.nv = new NhanVien_DAO().getNhanVien(tk.getNhanVien().getMaNhanVien());
        if (nv == null) {
            LOGGER.warning("Không tìm thấy nhân viên với mã: " + tk.getNhanVien().getMaNhanVien());
            return;
        }
        txtMaNV.setText(nv.getMaNhanVien() != null ? nv.getMaNhanVien() : "");
        txtTenNV.setText(nv.getTenNhanVien() != null ? nv.getTenNhanVien() : "");
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        txt_MaHD = new JTextField();
        btn_searchReturnOrder = new JButton();
        btn_barcode = new JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_HD = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        lbl_MaHDDT = new javax.swing.JLabel();
        txtMaHDDT = new JTextField();
        lbl_MaHD = new javax.swing.JLabel();
        txtMaHD = new JTextField();
        lbl_NgayDT = new javax.swing.JLabel();
        lbl_BangSanPham = new javax.swing.JLabel();
        lbl_TienHoan = new javax.swing.JLabel();
        lbl_LyDo = new javax.swing.JLabel();
        txt_soTienTra = new JTextField();
        txt_MoTa = new JTextField();
        btn_XoaTrang = new JButton();
        btn_Them = new JButton();
        btn_TaoHDDT = new JButton();
        jLabel12 = new javax.swing.JLabel();
        lbl_LoaiDT = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_SanPham = new javax.swing.JTable();
        jdatechooser_return = new com.toedter.calendar.JDateChooser();
        button_group = new javax.swing.JPanel();
        rdn_Doi = new javax.swing.JRadioButton();
        rdn_Tra = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        lbl_MaNV = new javax.swing.JLabel();
        txtMaNV = new JTextField();
        lbl_TenNV = new javax.swing.JLabel();
        txtTenNV = new JTextField();

        txt_MaHD.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mã hóa đơn");
        txt_MaHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_MaHDMouseClicked(evt);
            }
        });
        txt_MaHD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txt_MaHDActionPerformed(evt);
            }
        });
        txt_MaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                txt_MaHDKeyPressed(evt);
            }
            public void keyReleased(KeyEvent evt) {
                txt_MaHDKeyReleased(evt);
            }
        });

        btn_searchReturnOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/circle.png")));
        btn_searchReturnOrder.setMaximumSize(new java.awt.Dimension(79, 43));
        btn_searchReturnOrder.setPreferredSize(new java.awt.Dimension(90, 50));
        btn_searchReturnOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btn_searchReturnOrderActionPerformed(evt);
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error in btn_searchReturnOrderActionPerformed", e);
                }
            }
        });

        btn_barcode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/nhanvien/barcode.png")));
        btn_barcode.setText("Barcode");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_searchReturnOrder, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_barcode, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_searchReturnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_barcode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16)));

        table_HD.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã hóa đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"}
        ));
        jScrollPane1.setViewportView(table_HD);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin đổi trả", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16)));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_MaHDDT.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_MaHDDT.setText("Mã HDDT:");
        jPanel3.add(lbl_MaHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 119, 30));

        txtMaHDDT.setEnabled(false);
        jPanel3.add(txtMaHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 32, 310, 30));

        lbl_MaHD.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_MaHD.setText("Mã hóa đơn:");
        jPanel3.add(lbl_MaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 119, 30));
        jPanel3.add(txtMaHD, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 70, 310, 30));

        lbl_NgayDT.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_NgayDT.setText("Ngày đổi trả:");
        jPanel3.add(lbl_NgayDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 119, 30));

        lbl_BangSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_BangSanPham.setText("Sản phẩm:");
        jPanel3.add(lbl_BangSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 119, 30));

        lbl_TienHoan.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_TienHoan.setText("Tiền hoàn:");
        jPanel3.add(lbl_TienHoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 80, 20));

        lbl_LyDo.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_LyDo.setText("Lý do:");
        jPanel3.add(lbl_LyDo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 70, 30));

        txt_soTienTra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                txt_soTienTraActionPerformed(evt);
            }
        });
        jPanel3.add(txt_soTienTra, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 300, 30));
        jPanel3.add(txt_MoTa, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 300, 30));

        btn_XoaTrang.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btn_XoaTrang.setText("Xóa trắng");
        btn_XoaTrang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_XoaTrangActionPerformed(evt);
            }
        });
        jPanel3.add(btn_XoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, 40));

        btn_Them.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btn_Them.setText("Thêm sản phẩm");
        btn_Them.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                    btn_ThemActionPerformed(evt);
                } catch (RemoteException e) {
                    LOGGER.log(Level.SEVERE, "Error in btn_ThemActionPerformed", e);
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi thêm sản phẩm: " + e.getMessage());
                }
            }
        });
        jPanel3.add(btn_Them, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 480, -1, 40));

        btn_TaoHDDT.setFont(new java.awt.Font("Segoe UI", 0, 16));
        btn_TaoHDDT.setText("Tạo đơn đổi trả");
        btn_TaoHDDT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btn_TaoHDDTActionPerformed(evt);
            }
        });
        jPanel3.add(btn_TaoHDDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 480, -1, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jLabel12.setText("Sản phẩm:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 119, 30));

        lbl_LoaiDT.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_LoaiDT.setText("Loại:");
        jPanel3.add(lbl_LoaiDT, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jScrollPane2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jScrollPane2PropertyChange(evt);
            }
        });

        table_SanPham.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng"}
        ));
        table_SanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_SanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table_SanPham);

        jScrollPane3.setViewportView(jScrollPane2);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 450, 170));
        jPanel3.add(jdatechooser_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 112, 310, 30));

        button_group.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_groupMouseClicked(evt);
            }
        });

        rdn_Doi.setFont(new java.awt.Font("Segoe UI", 0, 16));
        rdn_Doi.setText("Đổi");
        rdn_Doi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rdn_DoiActionPerformed(evt);
            }
        });

        rdn_Tra.setFont(new java.awt.Font("Segoe UI", 0, 16));
        rdn_Tra.setText("Trả");
        rdn_Tra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rdn_TraActionPerformed(evt);
            }
        });

        rdn_Doi.setSelected(true);

        javax.swing.GroupLayout button_groupLayout = new javax.swing.GroupLayout(button_group);
        button_group.setLayout(button_groupLayout);
        button_groupLayout.setHorizontalGroup(
            button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_groupLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(rdn_Tra)
                .addGap(59, 59, 59)
                .addComponent(rdn_Doi)
                .addContainerGap(141, Short.MAX_VALUE))
        );
        button_groupLayout.setVerticalGroup(
            button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(button_groupLayout.createSequentialGroup()
                .addGroup(button_groupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdn_Tra)
                    .addComponent(rdn_Doi))
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel3.add(button_group, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 310, 30));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 16)));

        lbl_MaNV.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_MaNV.setText("Mã nhân viên: ");

        txtMaNV.setEnabled(false);

        lbl_TenNV.setFont(new java.awt.Font("Segoe UI", 0, 16));
        lbl_TenNV.setText("Tên nhân viên: ");

        txtTenNV.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaNV))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenNV)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void init() throws Exception {
        LOGGER.info("Starting init");
        String maHD = txt_MaHD.getText().trim();
        if (maHD.isEmpty()) {
            LOGGER.warning("Empty invoice ID in init");
            Notifications.getInstance().show(Notifications.Type.WARNING, "Mã hóa đơn không được để trống!");
            return;
        }
        hoaDon = hd_DAO.getHoaDon(maHD);
        if (hoaDon == null) {
            LOGGER.warning("No invoice found for maHD: " + maHD);
            Notifications.getInstance().show(Notifications.Type.WARNING, "Không tìm thấy hóa đơn với mã " + maHD);
            return;
        }
        txtMaHD.setText(hoaDon.getMaHD());

        cart = hd_DAO.getChiTietHoaDon(maHD);
        if (cart == null || cart.isEmpty()) {
            LOGGER.warning("No invoice details found for maHD: " + maHD);
            Notifications.getInstance().show(Notifications.Type.WARNING, "Không có chi tiết hóa đơn cho mã " + maHD);
            return;
        }

        tblModel_HD = new DefaultTableModel(new String[]{"Mã hoá đơn", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Tổng tiền"}, 0) {
            @Override
            public Object getValueAt(int row, int column) {
                Object value = super.getValueAt(row, column);
                return value != null ? value : "";
            }
        };
        table_HD.setModel(tblModel_HD);

        for (ChiTietHoaDon cthd : cart) {
            if (cthd == null || cthd.getThuoc() == null) {
                LOGGER.warning("Invalid ChiTietHoaDon: " + cthd);
                continue;
            }
            Object[] obj = initObject(cthd);
            if (obj != null && obj.length == tblModel_HD.getColumnCount()) {
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] == null) {
                        obj[i] = "";
                    }
                }
                tblModel_HD.addRow(obj);
            } else {
                LOGGER.warning("Error creating row for ChiTietHoaDon: " + cthd);
            }
        }

        tblModel_SP = new DefaultTableModel(new Object[]{"Mã thuốc", "Tên thuốc", "Số lượng"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Chỉ cho phép chỉnh sửa cột "Số lượng"
                return column == 2;
            }
        };

        table_SanPham.setModel(tblModel_SP);

        suaSoLuong();
        LOGGER.info("Completed init");
    }

    private void renderCartTable() {
        LOGGER.info("Starting renderCartTable");
        if (tblModel_SP == null) {
            LOGGER.severe("tblModel_SP is null in renderCartTable");
            return;
        }
        tblModel_SP.setRowCount(0);
        refund = 0;

        if (cart == null) {
            LOGGER.warning("cart is null in renderCartTable");
            return;
        }

        for (ChiTietHoaDon item : cart) {
            if (item == null || item.getThuoc() == null) {
                LOGGER.warning("Invalid ChiTietHoaDon in cart: " + item);
                continue;
            }
            String maThuoc = item.getThuoc().getMaThuoc() != null ? item.getThuoc().getMaThuoc() : "";
            String tenThuoc = item.getThuoc().getTenThuoc() != null ? item.getThuoc().getTenThuoc() : "";
            if (maThuoc.isEmpty() || tenThuoc.isEmpty()) {
                LOGGER.warning("Invalid Thuoc data: maThuoc=" + maThuoc + ", tenThuoc=" + tenThuoc);
                continue;
            }
            Object[] newRow = new Object[]{maThuoc, tenThuoc, item.getSoLuong()};
            tblModel_SP.addRow(newRow);
            refund += item.getDonGia() * item.getSoLuong();
        }

        totalRefund = refund;

        double tienDoi = 0;
        if (rdn_Doi.isSelected()) {
            tienDoi = refund * 0.9;
            txt_soTienTra.setText(FormatNumber.toVND(tienDoi));
        } else {
            txt_soTienTra.setText(FormatNumber.toVND(refund));
        }
        LOGGER.info("Completed renderCartTable with refund: " + refund);
    }

    public void suaSoLuong() {
        LOGGER.info("Starting suaSoLuong");
        if (table_SanPham == null || table_SanPham.getModel() == null) {
            LOGGER.severe("table_SanPham or its model is null in suaSoLuong");
            return;
        }
        table_SanPham.getModel().addTableModelListener((evt) -> {
            if (isUpdating) {
                return;
            }
            int row = evt.getFirstRow();
            int col = evt.getColumn();
            if (row == -1 || col == -1 || col != 2) {
                return;
            }

            try {
                Object soLuongMoiObj = table_SanPham.getValueAt(row, col);
                if (soLuongMoiObj == null) {
                    LOGGER.warning("Null quantity at row " + row + " in suaSoLuong");
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không hợp lệ");
                    return;
                }
                int soLuongMoi = Integer.parseInt(soLuongMoiObj.toString());
                Object soLuongCuObj = table_HD.getValueAt(row, 3);
                if (soLuongCuObj == null) {
                    LOGGER.warning("Null original quantity at row " + row + " in table_HD");
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Dữ liệu hóa đơn không hợp lệ");
                    return;
                }
                int soLuongCu = Integer.parseInt(soLuongCuObj.toString());
                if (cart == null || row >= cart.size()) {
                    LOGGER.warning("Invalid cart or row index in suaSoLuong: row=" + row);
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Dữ liệu giỏ hàng không hợp lệ");
                    return;
                }
                ChiTietHoaDon current = cart.get(row);
                if (current == null || current.getThuoc() == null) {
                    LOGGER.warning("Invalid ChiTietHoaDon at row " + row + ": " + current);
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Dữ liệu sản phẩm không hợp lệ");
                    return;
                }

                if (soLuongMoi < 0) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không được bé hơn 0");
                    table_SanPham.setValueAt(current.getSoLuong(), row, col);
                    return;
                }
                if (soLuongMoi == 0) {
                    if (JOptionPane.showConfirmDialog(this, "Xóa sản phẩm " + current.getThuoc().getMaThuoc() + " ra khỏi giỏ hàng", "Xóa sản phẩm khỏi giỏ", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        cart.remove(current);
                        listDoiTra.removeIf(dt -> dt != null && dt.getThuoc() != null && dt.getThuoc().getMaThuoc().equals(current.getThuoc().getMaThuoc()));
                        renderCartTable();
                        return;
                    } else {
                        table_SanPham.setValueAt(current.getSoLuong(), row, col);
                        return;
                    }
                }
                if (soLuongMoi > soLuongCu) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng đổi không hợp lệ");
                    table_SanPham.setValueAt(current.getSoLuong(), row, col);
                    return;
                }
                for (ChiTietDoiTra dt : listDoiTra) {
                    if (dt != null && dt.getThuoc() != null && dt.getThuoc().getMaThuoc().equals(current.getThuoc().getMaThuoc())) {
                        dt.setSoLuong(soLuongMoi);

                        break;
                    }
                }
                renderCartTable();
            } catch (NumberFormatException e) {
                LOGGER.warning("Invalid quantity format at row " + row + ": " + e.getMessage());
                Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không hợp lệ");
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Unexpected error in suaSoLuong", e);
            }
        });
        LOGGER.info("Completed suaSoLuong");
    }

    private void renderOrderDetail(String maHD) {
        LOGGER.info("Starting renderOrderDetail for maHD: " + maHD);
        try {
            List<ChiTietHoaDon> chiTietHoaDonList = hd_DAO.getChiTietHoaDon(maHD);
            if (chiTietHoaDonList == null || chiTietHoaDonList.isEmpty()) {
                LOGGER.warning("No products found for maHD: " + maHD);
                Notifications.getInstance().show(Notifications.Type.INFO, "Không có sản phẩm nào trong hóa đơn này.");
                return;
            }
            tblModel_HD.setRowCount(0);

            for (ChiTietHoaDon chiTiet : chiTietHoaDonList) {
                if (chiTiet == null || chiTiet.getThuoc() == null) {
                    LOGGER.warning("Invalid ChiTietHoaDon: " + chiTiet);
                    continue;
                }
                Object[] rowData = new Object[]{
                    chiTiet.getHoaDon() != null ? chiTiet.getHoaDon().getMaHD() : "",
                    chiTiet.getThuoc().getMaThuoc() != null ? chiTiet.getThuoc().getMaThuoc() : "",
                    chiTiet.getThuoc().getTenThuoc() != null ? chiTiet.getThuoc().getTenThuoc() : "",
                    chiTiet.getSoLuong(),
                    FormatNumber.toVND(chiTiet.getDonGia()),
                    FormatNumber.toVND(chiTiet.thanhTien())
                };
                tblModel_HD.addRow(rowData);
            }
            Notifications.getInstance().show(Notifications.Type.SUCCESS, "Hiển thị chi tiết hóa đơn thành công.");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error in renderOrderDetail", ex);
        }
        LOGGER.info("Completed renderOrderDetail");
    }

    public Object[] initObjectSP(Thuoc t, int soLuong, int stt, HoaDon hd) {
        if (t == null || hd == null) {
            LOGGER.warning("Invalid Thuoc or HoaDon in initObjectSP");
            return null;
        }
        Object[] obj = new Object[5];
        obj[0] = stt;
        obj[1] = t.getTenThuoc() != null ? t.getTenThuoc() : "";
        obj[2] = soLuong;
        obj[3] = t.getGia();
        obj[4] = new ChiTietHoaDon(soLuong, t.getGia(), t, hd).thanhTien();
        return obj;
    }

    public Object[] initObject(ChiTietHoaDon item) {
        if (item == null || item.getThuoc() == null || hoaDon == null) {
            LOGGER.warning("Invalid ChiTietHoaDon or hoaDon in initObject");
            return null;
        }
        Object[] obj = new Object[6];
        obj[0] = hoaDon.getMaHD() != null ? hoaDon.getMaHD() : "";
        obj[1] = item.getThuoc().getMaThuoc() != null ? item.getThuoc().getMaThuoc() : "";
        obj[2] = item.getThuoc().getTenThuoc() != null ? item.getThuoc().getTenThuoc() : "";
        obj[3] = item.getSoLuong();
        obj[4] = item.getDonGia();
        obj[5] = item.thanhTien();
        return obj;
    }

    private void btn_searchReturnOrderActionPerformed(ActionEvent evt) throws Exception {
        LOGGER.info("Starting btn_searchReturnOrderActionPerformed");
        String maHoaDon = txt_MaHD.getText().trim();
        if (maHoaDon.isEmpty()) {
            LOGGER.warning("Empty invoice ID in btn_searchReturnOrderActionPerformed");
            Notifications.getInstance().show(Notifications.Type.WARNING, "Mã hóa đơn không được để trống!");
            return;
        }
        DoiTra_DAO dt_dao = new DoiTra_DAO();
        if (dt_dao.isReturnOrderExist(maHoaDon)) {
            LOGGER.warning("Invoice already has a return order: " + maHoaDon);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hóa đơn này đã thực hiện đổi trả, không thể tìm kiếm!");
            return;
        }

        hoaDon = hd_DAO.getHoaDon(maHoaDon);
        if (!isInvoiceEligibleForReturn(hoaDon)) {
            LOGGER.warning("Invoice not eligible for return: " + maHoaDon);
            return;
        }

        init();
        LOGGER.info("Completed btn_searchReturnOrderActionPerformed");
    }

    private void btn_TaoHDDTActionPerformed(ActionEvent evt) {
        LOGGER.info("Starting btn_TaoHDDTActionPerformed");
        try {
            if (hoaDon == null) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Vui lòng chọn hóa đơn để đổi trả");
                txt_MaHD.requestFocus();
                return;
            }

            if (!isInvoiceEligibleForReturn(hoaDon)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể đổi trả vì hóa đơn được lập trước 7 ngày.");
                return;
            }

            if (tblModel_SP == null || tblModel_SP.getRowCount() == 0) {
                Notifications.getInstance().show(Notifications.Type.WARNING, "Chưa chọn sản phẩm để đổi trả");
                return;
            }

            String reason = txt_MoTa.getText().trim();
            if (reason.isEmpty()) {
                LOGGER.warning("Reason is empty in btn_TaoHDDTActionPerformed");
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lý do đổi trả không được để trống.");
                return;
            }

            if (listDoiTra == null || listDoiTra.isEmpty()) {
                LOGGER.warning("listDoiTra is null or empty in btn_TaoHDDTActionPerformed");
                Notifications.getInstance().show(Notifications.Type.WARNING, "Danh sách sản phẩm đổi trả trống");
                return;
            }

            DoiTra newReturnOrder = null;
            try {
                LOGGER.info("Creating new return order");
                newReturnOrder = getNewValues();
                if (newReturnOrder == null) {
                    LOGGER.warning("getNewValues returned null");
                    Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể tạo hóa đơn đổi trả do dữ liệu không hợp lệ");
                    return;
                }
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error in getNewValues", ex);
                return;
            }

            try {
                LOGGER.info("Saving new return order");
                createNewReturnOrder(newReturnOrder);
            } catch (Exception ex) {
                return;
            }

            try {
                LOGGER.info("Printing return order");
                Print(newReturnOrder);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Error in Print", ex);
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi in hóa đơn đổi trả: " + ex.getMessage());
            }

            renderReturnOrderInfor();
            try {
                LOGGER.info("Refreshing application");
                refesh();
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, "Error in refesh", ex);
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi làm mới dữ liệu: " + ex.getMessage());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unexpected error in btn_TaoHDDTActionPerformed", e);
        }
        LOGGER.info("Completed btn_TaoHDDTActionPerformed");
    }

    private void Print(DoiTra returnOrder) {
        LOGGER.info("Starting Print");
        if (returnOrder == null) {
            LOGGER.warning("returnOrder is null in Print");
            Notifications.getInstance().show(Notifications.Type.ERROR, "Hóa đơn đổi trả không hợp lệ để in");
            return;
        }
        if (returnOrder.getListDetail() == null || returnOrder.getListDetail().isEmpty()) {
            LOGGER.warning("ChiTietDoiTras is null or empty in Print");
            Notifications.getInstance().show(Notifications.Type.ERROR, "Danh sách chi tiết đổi trả không hợp lệ để in");
            return;
        }
        try {
            ReturnOrderPrinter printer = new ReturnOrderPrinter(returnOrder);
            printer.generatePDF();
            ReturnOrderPrinter.PrintStatus status = printer.printFile();
            if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_FILE) {
                LOGGER.warning("Print failed: File not found");
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy file");
            } else if (status == ReturnOrderPrinter.PrintStatus.NOT_FOUND_PRINTER) {
                LOGGER.warning("Print failed: Printer not found");
                Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi không thể in hóa đơn: Không tìm thấy máy in");
            } else {
                LOGGER.info("Successfully printed return order: " + returnOrder.getMaHDDT());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "In hóa đơn đổi trả thành công");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error during printing for returnOrder: " + returnOrder.getMaHDDT(), e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi in hóa đơn: " + e.getMessage());
        }
        LOGGER.info("Completed Print");
    }

    private DoiTra getNewValues() throws Exception {
        LOGGER.info("Starting getNewValues");

        // 1. Kiểm tra ngày đổi trả
        Date returnDate = jdatechooser_return.getDate();
        if (returnDate == null) {
            throw new Exception("Ngày đổi trả không được để trống.");
        }

        // 2. Kiểm tra mã đổi trả
        String returnOrderID = txtMaHDDT.getText().trim();
        if (returnOrderID.isEmpty()) {
            throw new Exception("Mã hóa đơn đổi trả không được để trống.");
        }

        // 3. Kiểm tra nhân viên
        if (nv == null || nv.getMaNhanVien() == null) {
            throw new Exception("Thông tin nhân viên không hợp lệ.");
        }

        // 4. Kiểm tra hóa đơn
        if (hoaDon == null || hoaDon.getMaHD() == null) {
            throw new Exception("Hóa đơn không hợp lệ.");
        }

        // 5. Kiểm tra lý do
        String reason = txt_MoTa.getText().trim();
        if (reason.isEmpty()) {
            throw new Exception("Lý do đổi trả không được để trống.");
        }

        // 6. Kiểm tra danh sách sản phẩm đổi trả
        if (listDoiTra == null || listDoiTra.isEmpty()) {
            throw new Exception("Danh sách sản phẩm đổi trả không được để trống.");
        }

        // 7. Xoá các phần tử null hoặc không hợp lệ khỏi list
        listDoiTra.removeIf(dt -> dt == null || dt.getThuoc() == null || dt.getThuoc().getMaThuoc() == null || dt.getSoLuong() <= 0);

        if (listDoiTra.isEmpty()) {
            throw new Exception("Danh sách sản phẩm đổi trả trống sau khi kiểm tra.");
        }

        // 8. Tạo DoiTra
        boolean isReturn = rdn_Tra.isSelected();
        double refund = calculateRefund();

        DoiTra newReturnOrder = new DoiTra(returnDate, returnOrderID, nv, hoaDon, isReturn, refund, new ArrayList<>(listDoiTra), reason);

        // 9. Gán DoiTra cho từng ChiTietDoiTra
        for (ChiTietDoiTra dt : newReturnOrder.getListDetail()) {
            dt.setDoiTra(newReturnOrder);
        }

        // 10. Kiểm tra mã đổi trả trùng
        DoiTra_DAO dtDao = new DoiTra_DAO();
        if (dtDao.isReturnOrderExist(returnOrderID)) {
            throw new Exception("Mã hóa đơn đổi trả đã tồn tại trong cơ sở dữ liệu.");
        }

        LOGGER.info("Completed getNewValues with returnOrderID: " + returnOrderID);
        return newReturnOrder;
    }


    private void createNewReturnOrder(DoiTra newReturnOrder) throws Exception {
        LOGGER.info("Starting createNewReturnOrder for returnOrder: " + newReturnOrder.getMaHDDT());
        if (newReturnOrder == null) {
            LOGGER.warning("newReturnOrder is null in createNewReturnOrder");
            throw new Exception("Hóa đơn đổi trả không hợp lệ.");
        }

        DoiTra_DAO dtDao = new DoiTra_DAO();
        try {
            LOGGER.info("Attempting to create DoiTra with maHDDT: " + newReturnOrder.getMaHDDT());
            if (dtDao.create(newReturnOrder)) {
                LOGGER.info("Successfully created DoiTra: " + newReturnOrder.getMaHDDT());
                ChiTietDoiTra_DAO ctdtDao = new ChiTietDoiTra_DAO();
                LOGGER.info("Attempting to create ChiTietDoiTra for maHDDT: " + newReturnOrder.getMaHDDT());
                ctdtDao.createReturnOrderDetail(newReturnOrder, newReturnOrder.getListDetail());
                LOGGER.info("Successfully created ChiTietDoiTra for maHDDT: " + newReturnOrder.getMaHDDT());
                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Thêm hóa đơn đổi trả thành công");
            } else {
                LOGGER.warning("Failed to create DoiTra: " + newReturnOrder.getMaHDDT());
                throw new Exception("Không thể thêm hóa đơn đổi trả vào cơ sở dữ liệu.");
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "SQLException in createNewReturnOrder for maHDDT: " + newReturnOrder.getMaHDDT() + ", SQLState: " + ex.getSQLState() + ", ErrorCode: " + ex.getErrorCode(), ex);
            throw new Exception("Lỗi cơ sở dữ liệu khi lưu hóa đơn đổi trả: " + ex.getMessage());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Unexpected error in createNewReturnOrder for maHDDT: " + newReturnOrder.getMaHDDT(), ex);
            throw new Exception("Lỗi không xác định khi lưu hóa đơn đổi trả: " + ex.getMessage());
        }
        LOGGER.info("Completed createNewReturnOrder");
    }

    private void btn_ThemActionPerformed(ActionEvent evt) throws RemoteException {
        LOGGER.info("Starting btn_ThemActionPerformed");
        int rowIndex = table_HD.getSelectedRow();

        if (rowIndex == -1) {
            LOGGER.warning("No row selected in table_HD");
            Notifications.getInstance().show(Notifications.Type.WARNING, "Hãy chọn một sản phẩm trong bảng hóa đơn!");
            return;
        }

        if (!isInvoiceEligibleForReturn(hoaDon)) {
            LOGGER.warning("Invoice not eligible for return: " + (hoaDon != null ? hoaDon.getMaHD() : "null"));
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể đổi trả vì hóa đơn được lập trước 7 ngày.");
            return;
        }

        Object maThuocObj = table_HD.getValueAt(rowIndex, 1);
        Object tenThuocObj = table_HD.getValueAt(rowIndex, 2);
        Object soLuongObj = table_HD.getValueAt(rowIndex, 3);
        if (maThuocObj == null || tenThuocObj == null || soLuongObj == null) {
            LOGGER.warning("Null value in table_HD at row " + rowIndex + ": maThuoc=" + maThuocObj + ", tenThuoc=" + tenThuocObj + ", soLuong=" + soLuongObj);
            Notifications.getInstance().show(Notifications.Type.WARNING, "Dữ liệu sản phẩm không hợp lệ!");
            return;
        }

        String maThuoc = maThuocObj.toString();
        String tenThuoc = tenThuocObj.toString();
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuongObj.toString());
        } catch (NumberFormatException e) {
            LOGGER.warning("Invalid quantity format: " + soLuongObj);
            Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không hợp lệ!");
            return;
        }

        for (int i = 0; i < tblModel_SP.getRowCount(); i++) {
            Object existingMaThuocObj = tblModel_SP.getValueAt(i, 0);
            if (existingMaThuocObj != null && existingMaThuocObj.toString().equals(maThuoc)) {
                LOGGER.warning("Product already exists in return list: " + maThuoc);
                Notifications.getInstance().show(Notifications.Type.WARNING, "Sản phẩm đã tồn tại trong danh sách đổi trả!");
                return;
            }
        }

        tblModel_SP.addRow(new Object[]{maThuoc, tenThuoc, soLuong});
        tblModel_SP.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 2 && e.getType() == TableModelEvent.UPDATE) {
                    try {
                        String maThuoc = tblModel_SP.getValueAt(row, 0).toString();
                        int soLuongMoi = Integer.parseInt(tblModel_SP.getValueAt(row, 2).toString());

                        for (ChiTietDoiTra ct : listDoiTra) {
                            if (ct.getThuoc().getMaThuoc().equals(maThuoc)) {
                                ct.setSoLuong(soLuongMoi);
                                break;
                            }
                        }

                        updateTienTra();
                    } catch (NumberFormatException | RemoteException ex) {
                        LOGGER.log(Level.WARNING, "Lỗi khi cập nhật số lượng", ex);
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không hợp lệ!");
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });


        if (listSPHoan == null) {
            listSPHoan = new ArrayList<>();
        }
        if (cart != null && rowIndex < cart.size()) {
            listSPHoan.add(cart.get(rowIndex));
        } else {
            LOGGER.warning("Invalid cart or row index: row=" + rowIndex);
        }

        Thuoc sanPham = new Thuoc_DAO().getThuocTheoMa(maThuoc);
        if (sanPham == null) {
            LOGGER.warning("Product not found for maThuoc: " + maThuoc);
            Notifications.getInstance().show(Notifications.Type.WARNING, "Không tìm thấy sản phẩm với mã " + maThuoc);
            return;
        }
        if (listDoiTra == null) {
            listDoiTra = new ArrayList<>();
        }
        double thanhTien = sanPham.getGia() * soLuong;
        listDoiTra.add(new ChiTietDoiTra(new DoiTra(txtMaHDDT.getText()), sanPham, soLuong, thanhTien));

        try {
            updateTienTra();
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Error updating refund in btn_ThemActionPerformed", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi cập nhật số tiền hoàn: " + e.getMessage());
        }
        LOGGER.info("Completed btn_ThemActionPerformed");
    }

    private void txt_MaHDActionPerformed(ActionEvent evt) {
    }

    private void txt_MaHDKeyPressed(KeyEvent evt) {
        // Nếu đang chọn từ popup, không xử lý thêm sự kiện bàn phím.
        if (isSelectingFromPopup) {
            return;
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String maHD = txt_MaHD.getText().trim();
            if (!maHD.isEmpty()) {
                renderOrderDetail(maHD);
            }
        }
    }

    private void renderReturnOrderInfor() {
        LOGGER.info("Starting renderReturnOrderInfor");
        txtMaHD.setText("");
        txtMaHDDT.setText(TaoID());
        jdatechooser_return.setDate(java.sql.Date.valueOf(LocalDate.now()));
        if (tblModel_SP != null) {
            tblModel_SP.setRowCount(0);
        }
        if (tblModel_HD != null) {
            tblModel_HD.setRowCount(0);
        }
        rdn_Doi.setSelected(true);
        txt_MoTa.setText("");
        txt_soTienTra.setText("");
        txt_MaHD.setText("");
        cart = new ArrayList<>();
        listDoiTra = new ArrayList<>();
        listSPHoan = new ArrayList<>();
        LOGGER.info("Completed renderReturnOrderInfor");
    }

    private double calculateRefund() throws RemoteException {
        LOGGER.info("Starting calculateRefund");
        double refund = 0;
        if (rdn_Tra.isSelected()) {
            if (tblModel_SP == null || tblModel_SP.getRowCount() == 0) {
                LOGGER.warning("tblModel_SP is null or empty in calculateRefund");
                return 0;
            }
            for (int i = 0; i < tblModel_SP.getRowCount(); i++) {
                Object maThuocObj = tblModel_SP.getValueAt(i, 0);
                Object soLuongObj = tblModel_SP.getValueAt(i, 2);
                if (maThuocObj == null || soLuongObj == null) {
                    LOGGER.warning("Null value at row " + i + " in tblModel_SP: maThuoc=" + maThuocObj + ", soLuong=" + soLuongObj);
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Dữ liệu sản phẩm không hợp lệ tại dòng " + (i + 1));
                    continue;
                }
                String maThuoc = maThuocObj.toString();
                try {
                    int soLuong = Integer.parseInt(soLuongObj.toString());
                    Thuoc sanPham = new Thuoc_DAO().getThuocTheoMa(maThuoc);
                    if (sanPham != null) {
                        refund += sanPham.getGia() * soLuong;
                    } else {
                        LOGGER.warning("Product not found for maThuoc: " + maThuoc);
                        Notifications.getInstance().show(Notifications.Type.WARNING, "Không tìm thấy sản phẩm với mã " + maThuoc);
                    }
                } catch (NumberFormatException e) {
                    LOGGER.warning("Invalid quantity at row " + i + ": " + soLuongObj);
                    Notifications.getInstance().show(Notifications.Type.WARNING, "Số lượng không hợp lệ tại dòng " + (i + 1));
                }
            }
        }
        LOGGER.info("Completed calculateRefund with refund: " + refund);
        return refund;
    }

    private void updateTienTra() throws RemoteException {
        LOGGER.info("Starting updateTienTra");
        if (txt_soTienTra == null) {
            LOGGER.severe("txt_soTienTra is null in updateTienTra");
            return;
        }
        DecimalFormat df = new DecimalFormat("#,###");
        double refund = calculateRefund();
        double tienDoi = rdn_Doi.isSelected() ? refund * 0.9 : refund;
        txt_soTienTra.setText(df.format(tienDoi) + " VND");
        LOGGER.info("Completed updateTienTra with amount: " + tienDoi);
    }

    private boolean isInvoiceEligibleForReturn(HoaDon hoaDon) {
        if (hoaDon == null) {
            LOGGER.warning("hoaDon is null in isInvoiceEligibleForReturn");
            return false;
        }
        LocalDate ngayLapHoaDon = hoaDon.getNgayLap();
        if (ngayLapHoaDon == null) {
            LOGGER.warning("ngayLapHoaDon is null for hoaDon: " + hoaDon.getMaHD());
            return false;
        }
        LocalDate ngayHienTai = LocalDate.now();
        LocalDate ngayDoiTra = ngayHienTai.minusDays(7);
        return !ngayLapHoaDon.isBefore(ngayDoiTra);
    }

    private void btn_XoaTrangActionPerformed(ActionEvent evt) {
        LOGGER.info("Starting btn_XoaTrangActionPerformed");
        renderReturnOrderInfor();
        LOGGER.info("Completed btn_XoaTrangActionPerformed");
    }

    private void txt_MaHDMouseClicked(java.awt.event.MouseEvent evt) {
        txt_MaHD.selectAll();
    }

    private void button_groupMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void jScrollPane2PropertyChange(java.beans.PropertyChangeEvent evt) {
    }

    private void table_SanPhamMouseClicked(java.awt.event.MouseEvent evt) {
    }

    private void txt_MaHDKeyReleased(KeyEvent evt) {
        // Nếu đang chọn từ popup, không thực hiện thêm gợi ý.
        if (isSelectingFromPopup) {
            return;
        }

        String maHD = txt_MaHD.getText().trim();
        if (!maHD.isEmpty()) {
            showSuggestions(maHD);
        } else {
            hideSuggestions();
        }
    }

    private void rdn_DoiActionPerformed(ActionEvent evt) {
        try {
            updateTienTra();
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Error updating refund in rdn_DoiActionPerformed", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi cập nhật số tiền hoàn: " + e.getMessage());
        }
    }

    private void rdn_TraActionPerformed(ActionEvent evt) {
        try {
            updateTienTra();
        } catch (RemoteException e) {
            LOGGER.log(Level.SEVERE, "Error updating refund in rdn_TraActionPerformed", e);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi cập nhật số tiền hoàn: " + e.getMessage());
        }
    }

    private void txt_soTienTraActionPerformed(ActionEvent evt) {
    }

    private void showSuggestions(String keyword) {
        LOGGER.info("Starting showSuggestions for keyword: " + keyword);
        try {
            if (popup.isVisible()) {
                popup.setVisible(false);
            }
            List<HoaDon> suggestions = hd_DAO.getHoaDonSuggestions(keyword);
            popup.removeAll();
            if (suggestions == null || suggestions.isEmpty()) {
                LOGGER.info("No suggestions found for keyword: " + keyword);
                return;
            }
            for (HoaDon hd : suggestions) {
                if (hd == null || hd.getMaHD() == null) {
                    LOGGER.warning("Invalid HoaDon in suggestions");
                    continue;
                }
                JMenuItem item = new JMenuItem(hd.getMaHD());
                item.addActionListener(e -> {
                    isSelectingFromPopup = true;


                    SwingUtilities.invokeLater(() -> {
                        txt_MaHD.setText(hd.getMaHD());
                        popup.setVisible(false);
                        renderOrderDetail(hd.getMaHD());
                        isSelectingFromPopup = false;
                        txt_MaHD.setText(hd.getMaHD());
                    });
                });

                popup.add(item);
            }
            popup.setFocusable(false);
            popup.show(txt_MaHD, 0, txt_MaHD.getHeight());
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error in showSuggestions", ex);
            Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể hiển thị gợi ý: " + ex.getMessage());
        }
        LOGGER.info("Completed showSuggestions");
    }


    private void hideSuggestions() {
        if (popup.isVisible()) {
            popup.setVisible(false);
        }
    }

    public String TaoID() {
        String prefix = "HDDT";
        int nam = LocalDate.now().getYear() % 100;
        int thang = LocalDate.now().getMonthValue();
        int ngay = LocalDate.now().getDayOfMonth();
        prefix += String.format("%02d%02d%02d", nam, thang, ngay) + generateRandomString(6);
        LOGGER.info("Generated ID: " + prefix);
        return prefix;
    }

    public String generateRandomString(int length) {
        char[] number = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        char[] charArray = chars.toCharArray();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomNumber = (int) Math.floor(Math.random() * 2);
            if (randomNumber == 0) {
                sb.append(number[(int) (Math.random() * 9)]);
            } else {
                sb.append(charArray[(int) (Math.random() * charArray.length)]);
            }
        }
        return sb.toString();
    }

    public static void refesh() throws SQLException {
        LOGGER.info("Starting refesh");
        try {
            Main.app.refeshReturnOrder();
        } catch (UnsupportedLookAndFeelException | RemoteException ex) {
            LOGGER.log(Level.SEVERE, "Error in refesh", ex);
            throw new SQLException("Lỗi khi làm mới: " + ex.getMessage());
        }
        LOGGER.info("Completed refesh");
    }

    private String getVND(double totalAmount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Variables declaration
    private JButton btn_TaoHDDT;
    private JButton btn_Them;
    private JButton btn_XoaTrang;
    private JButton btn_barcode;
    private JButton btn_searchReturnOrder;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel button_group;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdatechooser_return;
    private javax.swing.JLabel lbl_BangSanPham;
    private javax.swing.JLabel lbl_LoaiDT;
    private javax.swing.JLabel lbl_LyDo;
    private javax.swing.JLabel lbl_MaHD;
    private javax.swing.JLabel lbl_MaHDDT;
    private javax.swing.JLabel lbl_MaNV;
    private javax.swing.JLabel lbl_NgayDT;
    private javax.swing.JLabel lbl_TenNV;
    private javax.swing.JLabel lbl_TienHoan;
    private javax.swing.JRadioButton rdn_Doi;
    private javax.swing.JRadioButton rdn_Tra;
    private javax.swing.JTable table_HD;
    private javax.swing.JTable table_SanPham;
    private JTextField txtMaHD;
    private JTextField txtMaHDDT;
    private JTextField txtMaNV;
    private JTextField txtTenNV;
    private JTextField txt_MaHD;
    private JTextField txt_MoTa;
    private JTextField txt_soTienTra;
}