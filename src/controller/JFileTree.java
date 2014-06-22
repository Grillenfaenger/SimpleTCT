
package controller;


import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.*;
import javax.swing.tree.*;
/**
 * This class contains the source code for Handling File Directory View area on MainWindow
 * 
 */
public class JFileTree
        extends JTree implements Serializable {

    public static final String SINGLE_FILE_MODEL = "SINGLE_FILE_MODEL";
    public static final FileSystemView fileSystemView = FileSystemView.getFileSystemView();
    DefaultTreeModel treeModel;
    File roooot;
    TreeChange tc;
    private static final String types[]={".doc",".docx",".txt",".rtf"};
    private static boolean  checkType(File f){
        boolean ok=false;
        for (int i = 0; i < types.length; i++) {
            if(f.getName().toLowerCase().endsWith(types[i])){
                ok=true;
                break;
            }            
        }
        return ok;
    }
    public JFileTree(File root,  TreeChange tc) {
        roooot = root;
        this.tc = tc;
        treeModel = (DefaultTreeModel) UIManager.get(SINGLE_FILE_MODEL);
        if (treeModel == null) {

            FileNode rt;
            if (root == null) {
                rt = new FileNode(fileSystemView.getRoots()[0]);
            } else {
                rt = new FileNode(root);
            }

            treeModel = new DefaultTreeModel(rt);
            ((FileNode) treeModel.getRoot()).explore();
            UIManager.put(SINGLE_FILE_MODEL, treeModel);
        }
        try {
            setSelectFile(root);
        } catch (Exception ex) {
            //Logger.getLogger(JFileTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setModel(treeModel);
        addTreeExpansionListener(new JFileTreeExpandsionListener());
        setCellRenderer(new JFileTreeCellRenderer());
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    JFileTree.this.tc.changeValue(getSelectFile());
                }                
            }
        });
    }

    public void refresh() {
        setLocation(roooot);
    }

    public void setLocation(File root) {
        roooot = root;
        treeModel = (DefaultTreeModel) UIManager.get(SINGLE_FILE_MODEL);
      

        FileNode rt;

        rt = new FileNode(root);


        treeModel = new DefaultTreeModel(rt);
        ((FileNode) treeModel.getRoot()).explore();
        UIManager.put(SINGLE_FILE_MODEL, treeModel);
     
        try {
            setSelectFile(root);
        } catch (Exception ex) {
            //Logger.getLogger(JFileTree.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setModel(treeModel);

        addTreeExpansionListener(new JFileTreeExpandsionListener());
        setCellRenderer(new JFileTreeCellRenderer());
         addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2){
                    JFileTree.this.tc.changeValue(getSelectFile());
                }                
            }
        });
    }

    public FileNode getSelectFileNode() {
        TreePath path = getSelectionPath();
        if (path == null || path.getLastPathComponent() == null) {
            return null;
        }
        return (FileNode) path.getLastPathComponent();
    }

    public void setSelectFileNode(FileNode f) throws Exception {
        this.setSelectFile(f.getFile());
    }

    public File getSelectFile() {
        FileNode node = getSelectFileNode();
       // if(node !=null && node.isLeaf()){
         //   return node.getFile();
        //}else{
            return node == null ? null : node.getFile();
           /// return  null;
       // }       
        
    }

    public void setSelectFile(File f) throws Exception {
        FileNode node = this.expandFile(f);
        TreePath path = new TreePath(node.getPath());
        this.scrollPathToVisible(path);
        this.setSelectionPath(path);
        this.repaint();
    }

    public FileNode expandFile(File f) throws Exception {
        if (!f.exists()) {
            throw new java.io.FileNotFoundException(f.getAbsolutePath());
        }
        Vector vTemp = new Vector();
        File fTemp = f;
        while (fTemp != null) {
            vTemp.add(fTemp);
            fTemp = fileSystemView.getParentDirectory(fTemp);
        }

        FileNode nParent = (FileNode) treeModel.getRoot();
        for (int i = vTemp.size() - 1; i >= 0; i--) {
            fTemp = (File) vTemp.get(i);
            nParent.explore();
            for (int j = 0; j < nParent.getChildCount(); j++) {
                FileNode nChild = (FileNode) nParent.getChildAt(j);
                if (nChild.getFile().equals(fTemp)) {
                    nParent = nChild;
                }
            }
        }
        return nParent;
    }

    class JFileTreeCellRenderer
            extends DefaultTreeCellRenderer {

        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean sel, boolean expanded,
                boolean leaf, int row,
                boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row,
                    hasFocus);
            try {
                FileNode node = (FileNode) value;
                closedIcon = fileSystemView.getSystemIcon(((FileNode) value).getFile());
                openIcon = closedIcon;
                setIcon(closedIcon);
            } catch (Exception ex) {
            }
            return this;
        }
    }

    class JFileTreeExpandsionListener
            implements TreeExpansionListener {

        public JFileTreeExpandsionListener() {
        }

        public void treeExpanded(TreeExpansionEvent event) {
            TreePath path = event.getPath();
            if (path == null || path.getLastPathComponent() == null) {
                return;
            }
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            FileNode node = (FileNode) path.getLastPathComponent();
            node.explore();
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }

        public void treeCollapsed(TreeExpansionEvent event) {
        }
    }

    public static class FileNode
            extends DefaultMutableTreeNode {

        private boolean explored = false;

        public FileNode(File file) {
            setUserObject(file);
        }

        public boolean getAllowsChildren() {
            return isDirectory();
        }

        public boolean isDirectory() {
            return !isLeaf();
        }

        public boolean isLeaf() {
            return false;
        }

        public File getFile() {
            return (File) getUserObject();
        }

        public boolean isExplored() {
            return explored;
        }

        public void setExplored(boolean b) {
            explored = b;
        }

        public String toString() {
            if (getFile() instanceof File) {
                return fileSystemView.getSystemDisplayName((File) getFile());
            } else {
                return getFile().toString();
            }
        }

        public void explore() {
            if (!explored) {
                explored = true;
                File file = getFile();
                File[] children = file.listFiles();
                if (children == null || children.length == 0) {
                    return;
                }
                for (int i = 0; i < children.length; ++i) {
                    File f = children[i];
                    if (f.isDirectory()) {
                        add(new FileNode(f));
                    }
                }
                for (int i = 0; i < children.length; ++i) {
                    File f = children[i];
                    if (f.isFile() && checkType(f)) {
                        add(new FileNode(f));
                    }
                }
                DefaultTreeModel model = (DefaultTreeModel) UIManager.get(SINGLE_FILE_MODEL);
                if (model != null) {
                    model.nodeStructureChanged(this);
                }
            }
        }
    }

}
