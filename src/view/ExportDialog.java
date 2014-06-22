package view;

import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.text.*;
import javax.swing.text.rtf.RTFEditorKit;
import model.FileContent;
import model.HighlightedText;
import model.ProjectData;
import model.Theme;

/**
 * This class contains the UI code for the "Export" Dialog.
 *
 */
public class ExportDialog extends javax.swing.JDialog {

    /**
     *
     * To store theme UI Panels to dynamic display of user added themes for a
     * specific project. Check "ThemePanel" class for more details
     */
    ArrayList<ThemePanel> tpanel = new ArrayList<ThemePanel>();
    /**
     *
     * Stores the whole project details. Check "ProjectData" class for more
     * details
     */
    ProjectData data;

    public ExportDialog(java.awt.Frame parent, ArrayList<Theme> themes, ProjectData data) {
        super(parent, true);
        initComponents();
        this.data = data;
        themePanel.setPreferredSize(new Dimension(350, themes.size() * 40));
        for (int i = 0; i < themes.size(); i++) {
            ThemePanel tp = new ThemePanel();
            tpanel.add(tp);
            tp.setTheme(themes.get(i));
            themePanel.add(tp);
        }
        jTextField1.setText(data.getName());
        setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        themePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jCheckBox9 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jCheckBox10 = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Export Themes");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(204, 0, 204))); // NOI18N

        themePanel.setPreferredSize(new java.awt.Dimension(350, 200));
        themePanel.setLayout(new javax.swing.BoxLayout(themePanel, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(themePanel);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/export.png"))); // NOI18N
        jLabel1.setText("<html>Please select the theme(s)<br>you would like to export");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jLabel1.setIconTextGap(40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("What would you like to name the export file  ");

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText(".rtf");

        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Will be saved in the project folder");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton1.setMnemonic('D');
        jButton1.setText("Done");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setMnemonic('o');
        jButton2.setText("Close");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Export Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12), new java.awt.Color(0, 0, 153))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Heading1 : ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Font Size :");

        jSpinner1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(18), Integer.valueOf(1), null, Integer.valueOf(1)));

        jCheckBox1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Bold");

        jCheckBox2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox2.setText("Italic");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Heading2 : ");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Font Size :");

        jSpinner2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(16), Integer.valueOf(1), null, Integer.valueOf(1)));

        jCheckBox3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox3.setSelected(true);
        jCheckBox3.setText("Bold");

        jCheckBox4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox4.setText("Italic");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Heading3 : ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Font Size :");

        jSpinner3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(14), Integer.valueOf(1), null, Integer.valueOf(1)));

        jCheckBox5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox5.setSelected(true);
        jCheckBox5.setText("Bold");

        jCheckBox6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox6.setText("Italic");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Text : ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Font Size :");

        jSpinner4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(12), Integer.valueOf(1), null, Integer.valueOf(1)));

        jCheckBox7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox7.setText("Bold");

        jCheckBox8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox8.setText("Italic");

        jCheckBox9.setText("Show full path in File Names");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Text Description : ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("Font Size :");

        jSpinner5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(11), Integer.valueOf(1), null, Integer.valueOf(1)));

        jCheckBox10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox10.setText("Bold");

        jCheckBox11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jCheckBox11.setSelected(true);
        jCheckBox11.setText("Italic");

        jCheckBox12.setText("Surround description with brackets");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 20, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox3)
                    .addComponent(jCheckBox4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox5)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox7)
                    .addComponent(jCheckBox8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox10)
                    .addComponent(jCheckBox11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox9)
                    .addComponent(jCheckBox12)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     *
     *
     * to disable/enable the "Done" button when user entering the export file
     * name(Checking for empty)
     */
    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        jButton1.setEnabled(!jTextField1.getText().trim().isEmpty());
    }//GEN-LAST:event_jTextField1KeyReleased

    /**
     *
     *
     * Export button function
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // Create the user selected file with its full path on project folder
            File f = new File(data.getProjectFolder() + File.separator + jTextField1.getText().trim() + ".rtf");


            //Check whether there's any file like it(Same name and extension)
            if (f.exists()) {
                int res = JOptionPane.showConfirmDialog(this, "This file already exists\nDo you want to overwrite it?", "Confirm overwrite", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (res == 0) {//When user press "yes" on above Option Pane (To overwrite)
                    writeData(f); //Export data to file
                    JOptionPane.showMessageDialog(this, "Export completed\nSaved on " + f.getPath(), "Done", JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    try {
                        Desktop.getDesktop().open(f);
                    } catch (Exception e) {
                    }
                }
            } else { //If similar file not exists, start export data
                writeData(f);//Export data to file
                JOptionPane.showMessageDialog(this, "Export completed\nSaved on " + f.getPath(), "Done", JOptionPane.INFORMATION_MESSAGE);

                dispose();
                try {
                    Desktop.getDesktop().open(f); //Opens the exported file on system default application for it
                } catch (Exception e) {
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Export failed\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            //Logger.getLogger(ExportDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        jButton1.doClick();
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     *
     * This is the Export file function. This handles the format and content for
     * the export file
     */
    private void writeData(File f) throws Exception {

        //To store user selected themes to export as Theme
        ArrayList<Theme> selectedThemes = new ArrayList<Theme>();

        //Read user selected only themes from Theme Panel on screen
        for (int i = 0; i < tpanel.size(); i++) {
            ThemePanel tp = tpanel.get(i);
            if (tp.getTheme() != null) {//getTheme() will return null if it is not selected, So we need only not null themes to ensure it is user selected
                selectedThemes.add(tp.getTheme());
            }
        }

//Stream to output file
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            RTFEditorKit rte = new RTFEditorKit();//Used to handle .rtf format files
            DefaultStyledDocument doc = new DefaultStyledDocument();//Handles whole document of the .rtf file
            //////
            SimpleAttributeSet atts1 = new SimpleAttributeSet(); //Handles Style information for a specific text part

            StyleConstants.setBold(atts1, jCheckBox1.isSelected()); //Set the style to bold if user selected the check box to bold
            StyleConstants.setItalic(atts1, jCheckBox2.isSelected());//Set the style to italic if user selected the check box to italic
            StyleConstants.setFontSize(atts1, Integer.parseInt(jSpinner1.getValue() + ""));// Sets the style font size from the user selected Spinner value

            doc.insertString(doc.getLength(), data.getName() + "\n\n", atts1); //add the Project name to document with above style (atts1)


            SimpleAttributeSet attDesc = new SimpleAttributeSet(); //New style for next document content

            StyleConstants.setBold(attDesc, jCheckBox10.isSelected());//Same as above style
            StyleConstants.setItalic(attDesc, jCheckBox11.isSelected());
            StyleConstants.setFontSize(attDesc, Integer.parseInt(jSpinner5.getValue() + ""));/////////////////////////////////////


            //List of user opened files for this project
            ArrayList<FileContent> files = data.getFiles();

            //Looping to check all selected themes and its highlighted text content
            for (int j = 0; j < selectedThemes.size(); j++) {
                Theme t = selectedThemes.get(j);//Get a selected theme from the list


                SimpleAttributeSet atts2 = new SimpleAttributeSet();//Style data

                StyleConstants.setBold(atts2, jCheckBox3.isSelected());//Same as above style
                StyleConstants.setItalic(atts2, jCheckBox4.isSelected());
                StyleConstants.setFontSize(atts2, Integer.parseInt(jSpinner2.getValue() + ""));//////////////////////////////////
                doc.insertString(doc.getLength(), t.getName() + "\n", atts2);


//Looping to check all the files and selected text for above readed theme
                for (int i = 0; i < files.size(); i++) {

                    String content = "";
                    FileContent fl = files.get(i);


                    ArrayList<HighlightedText> hg = fl.getText();//Gets the selected text from the file data
                    for (int k = 0; k < hg.size(); k++) {
                        HighlightedText h = hg.get(k);
                        if (h.getTheme().equals(t)) { //Check whether this highlighted text is relevant to above theme
                            content += h.getText(); //IF it is ok with the theme, adds the highlighted text to a String to be add in future to the document
                        }
                    }

                    //Check whether the above highlighted text is empty.
                    if (!content.trim().isEmpty()) { //If not empty, add those content to the document

                        SimpleAttributeSet atts = new SimpleAttributeSet();//Style of the File name text to be export

                        StyleConstants.setBold(atts, jCheckBox5.isSelected());
                        StyleConstants.setItalic(atts, jCheckBox6.isSelected());
                        StyleConstants.setFontSize(atts, Integer.parseInt(jSpinner3.getValue() + ""));///////////////////////////////////////

                        if (jCheckBox9.isSelected()) {//If user selected to include full path of file name
                            doc.insertString(doc.getLength(), "\n" + data.getProjectFolder() + fl.getFileName() + "\n\n", atts);
                        } else {//Just the file name adds to the document
                            doc.insertString(doc.getLength(), "\n" + fl.getFileName().substring(1) + "\n\n", atts);
                        }
                        atts = new SimpleAttributeSet();//Style of the Highlighted Text to be export

                        StyleConstants.setBold(atts, jCheckBox7.isSelected());
                        StyleConstants.setItalic(atts, jCheckBox8.isSelected());
                        StyleConstants.setFontSize(atts, Integer.parseInt(jSpinner4.getValue() + ""));/////////////////////////////////////




                        for (int k = 0; k < hg.size(); k++) {//Loop to get all highlighted texts
                            HighlightedText h = hg.get(k);
                            if (h.getTheme().equals(t)) {//checks its similar to current handling Theme 
                                doc.insertString(doc.getLength(), h.getText(), atts); //Insert highlighted text to document
                                doc.insertString(doc.getLength(), data.getDelimiter().isEmpty() ? " " : data.getDelimiter(), atts);//Insert delimiter character to document
                                if (data.getDelimiter().contains("\n")) { //If it is the newline character as delimiter, insert line to the document
                                    doc.insertString(doc.getLength(), "\n", atts);
                                }


                                if (jCheckBox12.isSelected()) { //If user selected to add "Braces" to surround text description(Personal Notes), adds it
                                    doc.insertString(doc.getLength(), "(", attDesc);
                                }
                                doc.insertString(doc.getLength(), h.getDescription(), attDesc); //Insert Personal notes (Text Description) for above added Highlighted Text
                                if (jCheckBox12.isSelected()) {//If user selected to add "Braces" to surround text description(Personal Notes), adds it
                                    doc.insertString(doc.getLength(), ")", attDesc);
                                }


                                if (data.getDelimiter().contains("\n")) {//If it is the newline character as delimiter, insert line to the document
                                    doc.insertString(doc.getLength(), "\n", atts);
                                }
                                doc.insertString(doc.getLength(), data.getDelimiter().isEmpty() ? " " : data.getDelimiter(), attDesc);//Insert delimiter character to document
                            }
                        }

                        doc.insertString(doc.getLength(), "\n", atts);//Inserts an empty line to document after each file content
                    }
                }
                doc.insertString(doc.getLength(), "\n", atts2);//Inserts an empty line to document after each theme content
            }
            rte.write(fos, doc, 0, doc.getLength()); //Write all data  to the export file
        } catch (Exception ex) {
            throw ex;
        } finally {
            try {
                fos.close();
            } catch (Exception ex) {
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox10;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JPanel themePanel;
    // End of variables declaration//GEN-END:variables
}
