package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import model.Theme;

/**
 * This class contains the UI code for the "Select ThemePanel" in the "Export"
 * Dialog.
 *
 */
public class ThemePanel extends javax.swing.JPanel {

    /**
     *
     * Specific Theme associate with this Theme UI Panel
     */
    Theme t;
    /**
     * Theme ColorBox Width
     */
    private final int lWidth = 25;
    /**
     * Theme ColorBox Height
     */
    private final int lHeight = 25;

    /**
     *
     * Sets this panels details for a specific Theme. (Theme name and Theme
     * color)
     */
    public void setTheme(Theme t) {
        this.t = t;
        jLabel2.setText(t.getName());


        BufferedImage bi = new BufferedImage(lWidth, lHeight, BufferedImage.TYPE_INT_RGB);//For create Icon for display Theme color
        Graphics g = bi.getGraphics();

        try {
            g.setColor(t.getColor());
        } catch (Exception e) {
        }
        g.fillRect(0, 0, lWidth - 1, lHeight - 1);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, lWidth, lHeight);
        jLabel1.setIcon(new ImageIcon(bi));
    }

    /**
     * Checks whether this panel is selected. (Theme is selected)
     */
    public boolean getSelected() {
        return jCheckBox1.isSelected();
    }

    /**
     *
     * Returns the theme associated with this panel
     */
    public Theme getTheme() {
        return jCheckBox1.isSelected() ? this.t : null;//If the theme selected returns the theme, else null
    }

    public ThemePanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jCheckBox1.setSelected(true);

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Theme1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jCheckBox1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
