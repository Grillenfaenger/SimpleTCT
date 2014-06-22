package controller;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JWindow;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;

/**
 * This class contains the source code for Handling File Browser window on new
 * project. Also this class contains the reading functions for the user selected
 * files
 *
 */
public class FileHandler {

    /**
     *
     * File Browser UI dialog
     */
    final private static JFileChooser jf = new JFileChooser();
    /**
     * File filter to display only .txt .doc .rtf and .docx files
     */
    final private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Highlighter Projects(*.txth)", "txth");

    /**
     * set File Browser settings on initialize
     *
     */
    static {

        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setDialogTitle("Select Project Folder");
        jf.setApproveButtonText("Select");
        jf.setAcceptAllFileFilterUsed(false);
        jf.setMultiSelectionEnabled(false);

    }

    /**
     * Start to display file browser dialog (On "Open Project")
     */
    public static File showProjectOpen(JWindow jm) {
        jf.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jf.setDialogTitle("Select project file to open");
        jf.resetChoosableFileFilters();
        jf.addChoosableFileFilter(filter);
        int res = jf.showOpenDialog(jm);
        if (res == 0) {
            return jf.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Start to display file browser dialog (On "New Project", Project Location)
     */
    public static File showProjectLocation(JDialog jm) {
        jf.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jf.setDialogTitle("Select project location");
        jf.resetChoosableFileFilters();
        int res = jf.showOpenDialog(jm);
        if (res == 0) {
            return jf.getSelectedFile();
        } else {
            return null;
        }
    }

    /**
     * Reads the .doc files when user opens
     */
    public static void readDoc(File f, JEditorPane pane) {

        try {
            Process p = Runtime.getRuntime().exec("Docx2Rtf -a \"" + f.getPath() + "\""); //Start the "Docx2Rtf" program and convert the doc file to .rtf, to easily read from Java
            p.waitFor();//Waiting till converts the file
            File rtf = new File(f.getParent() + File.separator + (f.getName().substring(0, f.getName().length() - 4) + ".rtf"));//Path of the converted .rtf file (removes the .doc extension)

            readRTF(rtf, pane);//read the converted .rtf file
        } catch (Exception ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//            
//            System.out.println("Index :"+doc.getRange().text().indexOf("\t"));
////            Pattern p=Pattern.compile("\n+?");
////            System.out.println(doc.getRange().text());
////            Matcher m=p.matcher(doc.getRange().text());
////            ArrayList<Integer> pos=new ArrayList<Integer>();
////            
////            while (m.find()) {
////                pos.add(m.start());
////                System.out.println("Start : "+m.start());
////               
////            }
//            
//            System.out.println("Sections : "+range.numSections());
//            for (int i = 0; i < range.numCharacterRuns(); i++) {
//                CharacterRun run = range.getCharacterRun(i);
//
//                SimpleAttributeSet atts1 = new SimpleAttributeSet();
//
//                StyleConstants.setBold(atts1, run.isBold());
//                StyleConstants.setItalic(atts1, run.isItalic());
//                StyleConstants.setUnderline(atts1, run.isVanished());
//                StyleConstants.setFontSize(atts1, (run.getFontSize() / 2)+3);               
//                StyleConstants.setFontFamily(atts1, run.getFontName());
//                StyleConstants.setStrikeThrough(atts1, run.isStrikeThrough());
//                doc2.insertString(doc2.getLength(), run.text(), atts1);
//
//            }
//
//            pane.setDocument(doc2);
// 
    public static void readDocx(File f, JEditorPane pane) {
        try {
            Process p = Runtime.getRuntime().exec("Docx2Rtf -a \"" + f.getPath() + "\"");//Start the "Docx2Rtf" program and convert the docx file to .rtf, to easily read from Java
            p.waitFor();//Waiting till converts the file
            File rtf = new File(f.getParent() + File.separator + (f.getName().substring(0, f.getName().length() - 5) + ".rtf"));//Path of the converted .rtf file (removes the .docx extension)

            readRTF(rtf, pane);//read the converted .rtf file
        } catch (Exception ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Reads .rtf file and display on JEditorPane(File Content area)
     */
    public static void readRTF(File f, JEditorPane e) {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            RTFEditorKit rte = new RTFEditorKit();

            e.setEditorKit(rte);
            StyleContext sc = new StyleContext();

            DefaultStyledDocument ds = new DefaultStyledDocument(sc);

            rte.read(fis, ds, 0);

            e.setDocument(ds);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (Exception ex) {
            }
        }
    }

    /**
     * Reads .txt file and display on JEditorPane(File Content area)
     */
    public static void readTxt(File f, JEditorPane pane) {
        FileReader fr = null;
        StyleContext sc = new StyleContext();
        DefaultStyledDocument ds = new DefaultStyledDocument(sc);
        BufferedReader br = null;
        try {
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String s = "";

            while ((s = br.readLine()) != null) {
                ds.insertString(ds.getLength(), s + "\n", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
            } catch (Exception e) {
            }
        }
        pane.setDocument(ds);
    }
}
