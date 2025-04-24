package utilities;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import entity.ChiTietDoiTra;
import entity.DoiTra;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import raven.toast.Notifications;

public class ReturnOrderPrinter {

    private final DoiTra returnOrder;
    public static final String FONT = "resources/fonts/arial-unicode-ms.ttf";
    private static final String FILE_PATH = "hoadondoitra.pdf";

    public static enum PrintStatus {
        NOT_FOUND_FILE,
        NOT_FOUND_PRINTER,
        COMPLETE
    }

    enum TextAlign {
        LEFT, CENTER, RIGHT;
    }

    public ReturnOrderPrinter(DoiTra returnOrder) {
        this.returnOrder = returnOrder;
    }

    private String getVND(double number) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return currencyFormat.format(number);
    }

    private void addTableHeader(PdfPTable table, Font font) {
        Stream.of("VAT", "Giá", "Số lượng", "Tổng tiền")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle, font));  // Sử dụng font iText thay vì java.awt.Font
                    header.setPadding(4);
                    table.addCell(header);
                });
    }

    public PrintStatus printFile() {
        try {
            File pdfFile = new File(FILE_PATH);

            if (!pdfFile.exists()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không tìm thấy file hóa đơn đổi trả.");
                return PrintStatus.NOT_FOUND_FILE;
            }

            if (!Desktop.isDesktopSupported()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không hỗ trợ mở file trên hệ điều hành này.");
                return PrintStatus.NOT_FOUND_PRINTER;
            }

            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                desktop.open(pdfFile);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Không thể mở file PDF trên thiết bị.");
                return PrintStatus.NOT_FOUND_PRINTER;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi khi mở file hóa đơn đổi trả.");
            return PrintStatus.NOT_FOUND_FILE;
        }

        return PrintStatus.COMPLETE;
    }

    public static byte[] resizeImage(byte[] imageData, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(imageData));
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        double widthRatio = (double) targetWidth / originalWidth;
        double heightRatio = (double) targetHeight / originalHeight;

        double ratio = Math.min(widthRatio, heightRatio);

        int scaledWidth = (int) Math.round(originalWidth * ratio);
        int scaledHeight = (int) Math.round(originalHeight * ratio);

        BufferedImage resizedImage = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.SCALE_SMOOTH);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();

        byte[] resizedImageData;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(resizedImage, "PNG", baos);
            resizedImageData = baos.toByteArray();
        }

        return resizedImageData;
    }

    public void generatePDF() {
        try {
            // Kiểm tra dữ liệu null
            if (returnOrder == null || returnOrder.getHoaDon() == null || returnOrder.getNhanvien() == null
                    || returnOrder.getHoaDon().getKhachHang() == null || returnOrder.getListDetail() == null
                    || returnOrder.getListDetail().isEmpty()) {
                Notifications.getInstance().show(Notifications.Type.ERROR, "Dữ liệu hóa đơn đổi trả không đầy đủ.");
                return;
            }

            // Tạo file PDF
            Document document = new Document();
            document.setMargins(16, 16, 32, 24);
            OutputStream outputStream = new FileOutputStream(new File(FILE_PATH));
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setLanguage("VN");

            document.open();
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            // Sử dụng Font từ iText thay vì java.awt.Font
            Font headingFont = new Font(bf, 20, Font.BOLD);
            Font subHeadingFont = new Font(bf, 18, Font.BOLD);
            Font bold = new Font(bf, 16, Font.BOLD);
            Font italic = new Font(bf, 16, Font.ITALIC);
            Font font = new Font(bf, 16);
            LineSeparator separator = new LineSeparator(font);

            // Header
            Paragraph sofware = new Paragraph("PHARMAHOME", headingFont);
            Paragraph desc = new Paragraph("\"Liều thuốc từ trái tim\"", italic);
            Paragraph store = new Paragraph("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh", bold);

            sofware.setAlignment(TextAlign.CENTER.ordinal());
            desc.setAlignment(TextAlign.CENTER.ordinal());
            store.setAlignment(TextAlign.CENTER.ordinal());

            document.add(sofware);
            document.add(desc);
            document.add(store);
            document.add(separator);

            // Tiêu đề hóa đơn
            Paragraph orderTitle = new Paragraph("HÓA ĐƠN ĐỔI TRẢ", subHeadingFont);
            orderTitle.setAlignment(TextAlign.CENTER.ordinal());
            document.add(orderTitle);
            document.add(separator);

            // Thông tin đơn
            String maHD = returnOrder.getHoaDon() != null ? returnOrder.getHoaDon().getMaHD() : "N/A";
            document.add(new Paragraph("Số hóa đơn: " + maHD, font));
            document.add(new Paragraph("Ngày tạo:  " + returnOrder.getNgayDoiTra(), font));
            document.add(new Paragraph("Nhân viên:  " + returnOrder.getNhanvien().getTenNhanVien(), font));
            document.add(new Paragraph("Khách hàng:  " + returnOrder.getHoaDon().getKhachHang().getTenKhachHang(), font));
            document.add(separator);

            // Bảng chi tiết
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(20);
            table.setWidthPercentage(100);
            addTableHeader(table, subHeadingFont);

            int index = 0;
            double tongTien = 0;

            for (ChiTietDoiTra detail : returnOrder.getListDetail()) {
                if (detail == null || detail.getThuoc() == null) continue;
                PdfPCell title = new PdfPCell(new Phrase(String.format("%d. %s", ++index, detail.getThuoc().getTenThuoc()), font));
                title.setColspan(4);
                table.addCell(title);

                table.addCell(new Phrase(String.valueOf(detail.getThuoc().getThue()), font));
                table.addCell(new Phrase(getVND(detail.getThuoc().getGia()), font));
                table.addCell(new Phrase(String.valueOf(detail.getSoLuong()), font));
                table.addCell(new Phrase(getVND(detail.getThuoc().getGia() * detail.getSoLuong()), font));

                tongTien += detail.getThuoc().getGia() * detail.getSoLuong();
            }

            document.add(table);

            double tienHoan = returnOrder.isLoai() ? tongTien : 0;
            document.add(new Paragraph("Tổng tiền: " + getVND(tongTien), font));
            document.add(new Paragraph("Tiền hoàn: " + getVND(tienHoan), subHeadingFont));
            document.add(new Paragraph("Loại đơn: " + (returnOrder.isLoai() ? "Đơn trả" : "Đơn đổi"), font));
            document.add(new Paragraph("Lý do: " + returnOrder.getLiDO(), font));
            document.add(separator);

            // Footer
            Paragraph hotline = new Paragraph("Tổng đài góp ý/khiếu nại: 1800 0000", bold);
            Paragraph note = new Paragraph("Lưu ý: PharmaHome chỉ xuất hóa đơn trong ngày, vui lòng liên hệ thu ngân nếu cần in lại.", italic);
            Paragraph thank = new Paragraph("Cảm ơn quý khách. Hẹn gặp lại!", font);

            hotline.setAlignment(TextAlign.CENTER.ordinal());
            note.setAlignment(TextAlign.CENTER.ordinal());
            note.setIndentationLeft(50);
            note.setIndentationRight(50);
            thank.setAlignment(TextAlign.CENTER.ordinal());

            document.add(hotline);
            document.add(note);
            document.add(thank);

            // Barcode
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(BarcodeGenerator.generateBarcode(returnOrder.getMaHDDT()), "PNG", baos);
                byte[] resized = resizeImage(baos.toByteArray(), 500, 200);
                Image barcode = Image.getInstance(resized);
                barcode.setAlignment(Image.ALIGN_CENTER);
                document.add(barcode);
            } catch (Exception e) {
                System.err.println("Không thể thêm mã vạch: " + e.getMessage());
            }

            document.close();
            outputStream.close();

            // Mở file
            File pdfFile = new File(FILE_PATH);
            if (pdfFile.exists()) {
                Desktop.getDesktop().open(pdfFile);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            Notifications.getInstance().show(Notifications.Type.ERROR, "Lỗi tạo hóa đơn đổi trả!");
        }
    }
}
