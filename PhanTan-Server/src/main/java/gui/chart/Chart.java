package gui.chart;


import gui.chart.blankchart.BlankPlotChart;

import gui.chart.blankchart.BlankPlotChatRender;

import gui.chart.blankchart.SeriesSize;

import java.awt.Color;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Chart extends javax.swing.JPanel {

    private List<ModelLegend> legends = new ArrayList<>();
    private List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;
    private final Animator animator;
    private float animate;
    
    public Chart() {
        initComponents();
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                animate = fraction;
                repaint();
            }
        };
        animator = new Animator(800, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        blankPlotChart1.setBlankPlotChatRender(new BlankPlotChatRender() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            
public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {
    double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
    double x = (size.getWidth() - totalSeriesWidth) / 2;
    double[] values = model.get(index).getValues();
    
    for (int i = 0; i < legends.size(); i++) {
        if (i >= values.length) break; // Kiểm tra độ dài mảng

        ModelLegend legend = legends.get(i);
        g2.setColor(legend.getColor());
        double seriesValues = chart.getSeriesValuesOf(values[i], size.getHeight()) * animate;
        int barX = (int) (size.getX() + x);
        int barY = (int) (size.getY() + size.getHeight() - seriesValues);
        int barHeight = (int) seriesValues;

        // Vẽ cột
        g2.fillRect(barX, barY, seriesSize, barHeight);

        // Đặt màu văn bản và vẽ giá trị phía trên cột
        g2.setColor(Color.BLACK);
        DecimalFormat df = new DecimalFormat("#,##0");
        String valueText = df.format( values[i]) + " VND";

        int textX = barX + (seriesSize / 2) - (g2.getFontMetrics().stringWidth(valueText) / 2);
        int textY = barY - 5; // Khoảng cách từ đỉnh cột lên giá trị
        g2.drawString(valueText, textX, textY);

        x += seriesSpace + seriesSize;
    }
}

        });
    }

    public void addLegend(String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        
        model.add(data);
        blankPlotChart1.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart1.getMaxValues()) {
            blankPlotChart1.setMaxValues(max);
        }
    }
    public void start() {
        if (!animator.isRunning()) {
            animator.start();
        }
    }
    public void resetLegends() {
    legends.clear(); // Xóa tất cả các legend
    panelLegend.removeAll(); // Xóa tất cả các thành phần trong panelLegend
    panelLegend.repaint(); // Cập nhật lại panelLegend
    panelLegend.revalidate(); // Đảm bảo layout được cập nhật
}
   public void clear() {
        animate = 0;
        blankPlotChart1.setLabelCount(0);
        blankPlotChart1.repaint();
        blankPlotChart1.setMaxValues(0);
        model.clear();
        repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLegend = new javax.swing.JPanel();
        blankPlotChart1 = new BlankPlotChart();

        setBackground(new Color(255, 255, 255));

        panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(blankPlotChart1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(blankPlotChart1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private BlankPlotChart blankPlotChart1;
    private javax.swing.JPanel panelLegend;
    // End of variables declaration//GEN-END:variables
}
