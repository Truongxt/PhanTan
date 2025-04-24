package utilities;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderSupport {

    public static void setPlaceholder(JTextField textField, String placeholder) {
        // Tạo FocusListener để vẽ placeholder khi JTextField trống
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals(placeholder)) {
                    textField.setForeground(Color.BLACK); // Chuyển văn bản nhập về màu mặc định
                    textField.setText(""); // Xóa placeholder khi có focus
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY); // Màu của placeholder
                    textField.setText(placeholder); // Hiển thị lại placeholder khi trống
                }
            }
        });

        // DocumentListener để tự động xóa placeholder khi người dùng nhập ký tự
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                removePlaceholder();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkForPlaceholder();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Không dùng cho PlainDocument, nhưng bắt buộc phải override
            }

            private void removePlaceholder() {
                if (textField.getForeground().equals(Color.GRAY) && textField.getText().equals(placeholder)) {
                    textField.setText(""); // Xóa placeholder
                    textField.setForeground(Color.BLACK); // Chuyển lại màu đen khi người dùng nhập
                }
            }

            private void checkForPlaceholder() {
                if (textField.getText().isEmpty() && !textField.isFocusOwner()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder); // Hiển thị lại placeholder khi trống
                }
            }
        });

        // Nếu JTextField khởi tạo trống, hiển thị placeholder luôn
        if (textField.getText().isEmpty()) {
            textField.setForeground(Color.GRAY);
            textField.setText(placeholder);
        }
    }
}
