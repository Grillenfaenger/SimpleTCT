package view;

import controller.FileHandler;
import controller.JFileTree;
import controller.JListHandler;
import controller.TreeChange;
import java.awt.Color;
import java.awt.Cursor;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.StyledEditorKit;
import model.FileContent;
import model.HighlightedText;
import model.ProjectData;
import model.Theme;

/**
 * This class contains the UI code for the "Main" Window. (Main User screen)
 *
 */
public class MainForm extends javax.swing.JFrame implements TreeChange {

    /**
     *
     * Stores the project name when a new project or opened project
     */
    private String projectName = "Untitled";
    /**
     *
     * Stores the handler for Themes List
     */
    private JListHandler lst;
    /**
     *
     * Stores the File Directory Tree
     */
    private JFileTree jt;
    /**
     *
     * Stores the project Themes
     */
    private ArrayList<Theme> themes = new ArrayList<Theme>();
    /**
     *
     * Stores the whole project data
     */
    private ProjectData project = new ProjectData();
    /**
     *
     * Stores the file data for the opened project
     */
    private ArrayList<FileContent> files = new ArrayList<FileContent>();
    /**
     * File Chooser for Open projects
     */
    private JFileChooser jc;
    /**
     * File Filter to display project files only
     */
    private FileNameExtensionFilter filter;
    /**
     * Stores whether the current project is saved
     */
    private boolean saved = true;
    /**
     *
     * Stores the Project Folder path
     */
    private String projectFile;

    public MainForm() {
        initComponents();

        selectTitle(null);
        filter = new FileNameExtensionFilter("SimpleTCT Projects(*.tct)", "tct");
        jc = new JFileChooser();
        jc.setAcceptAllFileFilterUsed(false);
        jc.addChoosableFileFilter(filter);

        jc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jc.setMultiSelectionEnabled(false);
        jButton4.setEnabled(false);
        jButton6.setEnabled(false);
        try {
            setIconImage(new ImageIcon(getClass().getResource("/gui/highlight.png")).getImage());
        } catch (Exception e) {
        }

        StyledEditorKit editorKit = new StyledEditorKit();

        jEditorPane1.setEditorKit(editorKit);
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     *
     * Check whether this project is saved or notm and notify to save when
     * creating or opening projects
     *
     * @return
     */
    private boolean checkSaved() {
        if (!saved) {
            int res = JOptionPane.showConfirmDialog(this, "Save current changes?\nPress 'Yes' to save current project", "Comfirm save changes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == 0) {
                saveProject(false);
                return true;
            } else if (res == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     *
     * Adds the Starting Themes(Theme1, Theme2) to the Themes List
     */
    private void addBasicThemes() {
        themes = new ArrayList<Theme>();
        Theme t1 = new Theme();
        t1.setName("Theme1");
        t1.setColor(Color.GREEN);
        t1.setIndex(0);

        Theme t2 = new Theme();
        t2.setName("Theme2");
        t2.setColor(Color.RED);
        t2.setIndex(1);

        themes.add(t1);
        themes.add(t2);

        lst = new JListHandler(jList1, themes);
        lst.refreshThemes(themes);

        try {
            jList1.setSelectedIndex(0);
        } catch (Exception e) {
        }
    }

    /**
     *
     * Edit theme function, invokes when user press "OK" on "Edit Theme" dialog
     * box
     */
    public void editTheme(Theme t) {
        int ind = jList1.getSelectedIndex();
        themes.remove(ind);
        themes.add(ind, t);
        lst.refreshThemes(themes);

        refreshHighlights();
        try {
            jList1.setSelectedIndex(ind);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /**
     *
     * Edit theme function, invokes when user press "OK" on "Add Theme" dialog
     * box
     */
    public void addTheme(Theme t) {
        themes.add(t);
        lst.refreshThemes(themes);
        try {
            jList1.setSelectedIndex(jList1.getModel().getSize() - 1);
        } catch (Exception e) {
        }

    }

    /**
     *
     * Check theme function, invokes when user press "OK" on "Edit Theme" or
     * "Add Theme" dialog boxes, to check if exists a theme with same name or
     * same color
     */
    public boolean checkTheme(Theme t, int index) {
        for (int i = 0; i < themes.size(); i++) {
            if (i != index && (themes.get(i).getName().equalsIgnoreCase(t.getName()) || themes.get(i).getColor().equals(t.getColor()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Invokes when user press "Delete" button on Themes list. To delete
     * selected Theme
     */
    private void deleteTheme() {

        for (int j = 0; j < files.size(); j++) {
            FileContent f = files.get(j);

            ArrayList<HighlightedText> high = f.getText();
            for (int i = 0; i < high.size(); i++) {
                try {
                    HighlightedText hl = high.get(i);
                    if (hl.getTheme().getName().equals(themes.get(jList1.getSelectedIndex()).getName())) {
                        f.getText().remove(i);
                    }
                } catch (Exception e) {
                    //  System.out.println(e);
                }
            }
        }

        themes.remove(jList1.getSelectedIndex());
        lst.refreshThemes(themes);
        refreshHighlights();
    }

    //Implemented method from "TreeChange" interface. Invokes when user double click on a file on File Tree (Files List)
    public void changeValue(File f) {
        if (f != null && !f.isDirectory()) {
            System.out.println("read");
            String s = f.getName().toLowerCase();
            if (s.endsWith(".doc")) {
                FileHandler.readDoc(f, jEditorPane1);
            } else if (s.endsWith(".docx")) {
                FileHandler.readDocx(f, jEditorPane1);
            } else if (s.endsWith(".rtf")) {
                FileHandler.readRTF(f, jEditorPane1);
            } else if (s.endsWith(".txt")) {
                FileHandler.readTxt(f, jEditorPane1);
            }
            openedFile = jt.getSelectFile().getPath();
            refreshHighlights();
        }
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        new NewProject1(this).setVisible(b);
    }

    /**
     *
     * Creates a new project and resets the current details of the project
     */
    public void newProject(String name, File location) {
        projectName = name;
        selectTitle(location);
        jButton4.setEnabled(true);
        jButton6.setEnabled(true);
        project = new ProjectData();
        project.setName(projectName);
        project.setProjectFolder(location.getPath());
        files = new ArrayList<FileContent>();
        project.setFiles(files);
        ht = new HighlightedText();
        if (jList1.getModel().getSize() > 0) {
            int res = JOptionPane.showConfirmDialog(this, "Discard current Themes?", "Confirm delete themes", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == 0) {
                addBasicThemes();
            }
        } else {
            addBasicThemes();
        }


        if (jt == null) {
            jt = new JFileTree(location, this);
            jScrollPane4.setViewportView(jt);
        } else {
            jt.setLocation(location);
        }


        jEditorPane1.setText(null);
        jEditorPane2.setText("");
    }

    /**
     * Creates the title of the current main window to display the project name
     * and path
     */
    public void selectTitle(File f) {
        setTitle(projectName + " - SimpleTCT " + (f != null ? "(" + f.getPath() + "\\)" : ""));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Text Highlighter");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project Folder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 0, 204))); // NOI18N

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/refresh.png"))); // NOI18N
        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("About");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton7, jButton8});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Project Options", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/newP.png"))); // NOI18N
        jButton1.setText("New Project");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/open.png"))); // NOI18N
        jButton2.setText("Open Project");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/save.png"))); // NOI18N
        jButton3.setText("Save Project");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "File Content", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jEditorPane1.setEditable(false);
        jEditorPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jEditorPane1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                jEditorPane1CaretUpdate(evt);
            }
        });
        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Themes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jList1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jList1.setToolTipText("Double click on any Theme to edit");
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jList1.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jList1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/new.png"))); // NOI18N
        jButton4.setMnemonic('N');
        jButton4.setText("New");
        jButton4.setToolTipText("Click here to create new Theme");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/delete.png"))); // NOI18N
        jButton5.setText("Delete");
        jButton5.setToolTipText("Click here to delete the selected theme");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/highlight.png"))); // NOI18N
        jToggleButton1.setText("Select");
        jToggleButton1.setToolTipText("Click this button and drag over text to highlight");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/eraser.png"))); // NOI18N
        jToggleButton2.setText("Clear");
        jToggleButton2.setToolTipText("Click this and drag the mouse over a highlighted text to clear");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)))
        );

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/export.png"))); // NOI18N
        jButton6.setText("Export");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Description of the highlighted text (Personal Notes)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(102, 0, 102))); // NOI18N

        jEditorPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jEditorPane2KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jEditorPane2);

        jLabel1.setText("Delimiter :");

        buttonGroup2.add(jCheckBox1);
        jCheckBox1.setSelected(true);
        jCheckBox1.setText("New Line");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCheckBox2);
        jCheckBox2.setText("Tab");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        buttonGroup2.add(jCheckBox3);
        jCheckBox3.setText("Space");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 5, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2)
                    .addComponent(jCheckBox3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * "New Project" action
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (checkSaved()) {
            new NewProject1(this).setVisible(true);
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * "Refresh" button action on the FileTree
     *
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        try {
            jt.refresh();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * A private subclass of the default highlight painter. Used to make
     * highlights on JEditorPane(File Content area) with different colors
     * according to themes
     */
    class MyHighlightPainter extends DefaultHighlighter.DefaultHighlightPainter {

        public MyHighlightPainter(Color color) {
            super(color);
        }
    }
    /**
     * Stores the current opened file name on the File Content area
     */
    private String openedFile = "";

    /**
     *
     * Add the highlight text detail on user drag over the text
     */
    private void addHighlight() throws Exception {

        saved = false;
        HighlightedText ht = new HighlightedText();
        ht.setStartIndex(jEditorPane1.getSelectionStart());
        ht.setEndIndex(jEditorPane1.getSelectionEnd());
        ht.setText(jEditorPane1.getDocument().getText(jEditorPane1.getSelectionStart(), jEditorPane1.getSelectionEnd() - jEditorPane1.getSelectionStart()));

        String fName = openedFile.substring(project.getProjectFolder().length());
        int ind = -1;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileName().equalsIgnoreCase(fName)) {
                ind = i;
                break;
            }

        }
        Theme t1 = themes.get(jList1.getSelectedIndex());
        if (ind != -1) {

            //int indx=-1;
            FileContent f = files.get(ind);
            ht.setTheme(t1);
            f.getText().add(ht);

        } else {
            FileContent f = new FileContent();
            f.setFileName(fName);
            ht.setTheme(t1);
            f.getText().add(ht);
            files.add(f);
        }


    }

    /**
     *
     * Removes the selected highlighted text
     */
    private void removeHighlight() throws Exception {
        System.out.println("remove");
        saved = false;

        String fName = openedFile.substring(project.getProjectFolder().length());
        int ind = -1;
        for (int i = 0; i < files.size(); i++) {
            if (files.get(i).getFileName().equalsIgnoreCase(fName)) {
                ind = i;
                break;
            }

        }
        //Theme t1 = themes.get(jList1.getSelectedIndex());
        if (ind != -1) {

            //int indx=-1;
            FileContent f = files.get(ind);
            ArrayList<HighlightedText> hg = f.getText();
            for (int i = 0; i < hg.size(); i++) {
                HighlightedText h = hg.get(i);
                if (h.getStartIndex() <= jEditorPane1.getSelectionStart() && h.getEndIndex() >= jEditorPane1.getSelectionEnd()) {
                    hg.remove(i);
                }
            }

        }
        jEditorPane1.revalidate();

    }
    /**
     *
     * Stores the last Highlighted text
     */
    HighlightedText ht = new HighlightedText();

    /**
     * Repaint the highlights on the file content area
     */
    private void refreshHighlights() {
        jEditorPane2.setText("");
        try {
            String fName = openedFile.substring(project.getProjectFolder().length());
            int ind = -1;
            for (int i = 0; i < files.size(); i++) {
                if (files.get(i).getFileName().equalsIgnoreCase(fName)) {
                    ind = i;
                    break;
                }

            }
            DefaultHighlighter h = (DefaultHighlighter) jEditorPane1.getHighlighter();

            h.removeAllHighlights();
            if (ind != -1) {
                FileContent f = files.get(ind);

                ArrayList<HighlightedText> high = f.getText();
                for (int i = 0; i < high.size(); i++) {
                    try {
                        HighlightedText hl = high.get(i);
                        if (hl.getText().equalsIgnoreCase(jEditorPane1.getDocument().getText(hl.getStartIndex(), hl.getEndIndex() - hl.getStartIndex()))) {
                            h.addHighlight(hl.getStartIndex(), hl.getEndIndex(), new MyHighlightPainter(hl.getTheme().getColor()));

                        }
                    } catch (Exception e) {
                        //  System.out.println(e);
                    }

                }
            }
            //String themeContent = "";
            try {
                FileContent f = files.get(ind);
                ArrayList<HighlightedText> high = f.getText();
                // String currentTheme = themes.get(jList1.getSelectedIndex()).getName();



                for (int i = 0; i < high.size(); i++) {
                    try {
                        HighlightedText hl = high.get(i);
                        if (hl.getStartIndex() <= jEditorPane1.getSelectionStart() && hl.getEndIndex() >= jEditorPane1.getSelectionEnd()) {
                            ht = hl;
                            jEditorPane2.setText(ht.getDescription());
                        }
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

            } catch (Exception e) {
                //System.out.println("Check Error " + e);
            }


        } catch (Exception e) {
            // System.out.println("Error "+e);
        }

    }

    /**
     *
     * Add Theme Event
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        new AddTheme(this, null, jList1.getModel().getSize()).setVisible(true);



    }//GEN-LAST:event_jButton4ActionPerformed
    /**
     *
     * Edit theme event, when user double clicks on a Theme
     */
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

        if (evt.getClickCount() == 2) {
            new AddTheme(this, themes.get(jList1.getSelectedIndex()), jList1.getSelectedIndex()).setVisible(true);
        }
    }//GEN-LAST:event_jList1MouseClicked
    /**
     *
     *
     * Delete Theme Button event
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int res = JOptionPane.showConfirmDialog(this, "Are you sure want to delete the selected Theme?", "Confirm Delete Theme", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (res == 0) {
            deleteTheme();
            try {
                jList1.setSelectedIndex(jList1.getModel().getSize() - 1);
            } catch (Exception e) {
                try {
                    jList1.setSelectedIndex(0);
                } catch (Exception ex) {
                }
            }
        }

    }//GEN-LAST:event_jButton5ActionPerformed
    /**
     *
     * Save Project Event
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            saveProject(true);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    /**
     *
     * Open Project Event
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (checkSaved()) {
            openProject();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     *
     *
     * Selected Theme change Event
     */
    private void jList1ValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jList1ValueChanged
        refreshHighlights();
        jButton5.setEnabled(jList1.getSelectedIndex() != -1);

    }//GEN-LAST:event_jList1ValueChanged

    /**
     *
     * Export button function
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (jCheckBox1.isSelected()) {
            project.setDelimiter("\n");
        } else if (jCheckBox2.isSelected()) {
            project.setDelimiter("\t");
        } else if (jCheckBox3.isSelected()) {
            project.setDelimiter(" ");
        }

        new ExportDialog(this, themes, project).setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     *
     * Select Button action
     */
    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        if (jToggleButton1.isSelected()) {
            jEditorPane1.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        } else {
            jEditorPane1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * Clear button action
     *
     */
    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        jEditorPane1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    /**
     *
     * About button action
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed


        new AboutDialog(this, true).setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     *
     * User drag over action when highlighting text
     */
    private void jEditorPane1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_jEditorPane1CaretUpdate
        if (jToggleButton1.isSelected() && jEditorPane1.getSelectedText() != null) {
            try {
                // h.setDrawsLayeredHighlights(true);
                jEditorPane1.setSelectionColor(Color.BLUE);
                addHighlight();

            } catch (Exception ex) {
                System.out.println("Selected Text : " + jEditorPane1.getSelectedText() + "\nStart Index: " + jEditorPane1.getSelectionStart() + "\nEnd Index: " + jEditorPane1.getSelectionEnd());
                System.out.println(ex);
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, "Selected Text : " + jEditorPane1.getSelectedText() + "\nStart Index: " + jEditorPane1.getSelectionStart() + "\nEnd Index: " + jEditorPane1.getSelectionEnd(), ex);
            }

        } else if (jToggleButton2.isSelected() && jEditorPane1.getSelectedText() != null) {
            try {
                // h.addHighlight(hl.getStartIndex(), hl.getEndIndex(), new MyHighlightPainter(hl.getTheme().getColor()));
                jEditorPane1.setSelectionColor(Color.BLUE);
                removeHighlight();
            } catch (Exception e) {
            }
        }
        refreshHighlights();
    }//GEN-LAST:event_jEditorPane1CaretUpdate

    /**
     *
     * Key Event on user types on Personal Notes area
     */
    private void jEditorPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEditorPane2KeyReleased
        if (ht != null) {
            ht.setDescription(jEditorPane2.getText());
        }
    }//GEN-LAST:event_jEditorPane2KeyReleased
    /**
     *
     * "Newline" Delimiter click action
     */
    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if (jCheckBox1.isSelected()) {
            try {
                project.setDelimiter("\n");
            } catch (Exception e) {
            }
        } else {
            try {
                project.setDelimiter("");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    /**
     *
     * "Tab" Delimiter click action
     */
    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            try {
                project.setDelimiter("\t");
            } catch (Exception e) {
            }
        } else {
            try {
                project.setDelimiter("");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed
    /**
     *
     * "Space" Delimiter click action
     */
    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        if (jCheckBox3.isSelected()) {
            try {
                project.setDelimiter(" ");
            } catch (Exception e) {
            }
        } else {
            try {
                project.setDelimiter("");
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    /**
     *
     * Opens project and shows the open dialog
     */
    private void openProject() {
        int res = jc.showOpenDialog(this);
        if (res == 0) {
            File f = jc.getSelectedFile();
            FileInputStream fis = null;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(f);

                ois = new ObjectInputStream(fis);
                ProjectData pro = (ProjectData) ois.readObject();
                selectTitle(new File(pro.getProjectFolder()));
                jEditorPane1.setText(null);
                this.project = pro;
                this.files = pro.getFiles();
                this.projectName = pro.getName();
                this.themes = pro.getThemes();
                this.projectFile = f.getPath();

                ht = new HighlightedText();
                if (project.getDelimiter().contains("\n")) {
                    try {
                        jCheckBox1.setSelected(true);

                    } catch (Exception e) {
                    }
                } else if (project.getDelimiter().contains("\t")) {
                    jCheckBox2.setSelected(true);
                } else if (!project.getDelimiter().isEmpty()) {
                    jCheckBox3.setSelected(true);
                }
                jButton4.setEnabled(true);
                jButton6.setEnabled(true);
                if (jt == null) {
                    jt = new JFileTree(new File(pro.getProjectFolder()), this);
                    jScrollPane4.setViewportView(jt);
                } else {
                    jt.setLocation(new File(pro.getProjectFolder()));
                }
                lst = new JListHandler(jList1, themes);
                lst.refreshThemes(themes);
                System.out.println("Opened");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     *
     * Shows the save dialog and saves the current project
     */
    private void saveProject(boolean saveAs) {
        int res = 0;
        boolean sa = false;
        if (saveAs || projectFile == null) {
            jc.setCurrentDirectory(new File(project.getProjectFolder()));
            res = jc.showSaveDialog(this);
            sa = true;
        }

        if (res == 0) {
            File f = null;
            if (sa) {
                f = jc.getSelectedFile();
            } else {
                f = new File(projectFile);
            }

            FileOutputStream fos = null;
            ObjectOutputStream ous = null;
            try {
                String path = f.getPath();
                if (!path.endsWith(".tct")) {
                    path += ".tct";
                }
                fos = new FileOutputStream(new File(path));
                project.setFiles(files);
                project.setThemes(themes);
                ous = new ObjectOutputStream(fos);
                ous.writeObject(project);
                JOptionPane.showMessageDialog(this, "Project saved sucessfully", "Save Project", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error saving project\n" + e.getMessage(), "Save Project", JOptionPane.ERROR_MESSAGE);
                System.out.println(e);
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                }

            }
        }
    }

    /**
     *
     *
     * Starting point fothe program to initialize
     */
    public static void main(String args[]) {


        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        boolean found = false;
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    found = true;
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        if (!found) {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }
        }

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    // End of variables declaration//GEN-END:variables
}
