package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import model.Theme;

/**
 * This class contains the source code for Handling "Themes" area on MainWindow
 *
 */
public class JListHandler {

    /**
     * Themes List model
     */
    private DefaultListModel dls = new DefaultListModel();
    /**
     *
     * List of Themes to render
     */
    ArrayList<Theme> themes;

    public JListHandler(JList lst, ArrayList<Theme> themes) {

        this.themes = themes;

        lst.setCellRenderer(new ColorRenderer());
        lst.setModel(dls);
        refreshThemes(themes);

    }

    /**
     *
     *
     * Reloads the Themes
     */
    public void refreshThemes(ArrayList<Theme> themes) {
        dls.removeAllElements();
        this.themes = themes;
        for (int i = 0; i < themes.size(); i++) {
            dls.addElement(themes.get(i).getName());

        }


    }

    /**
     * To display colored list icons before the theme name
     */
    private class ColorRenderer extends DefaultListCellRenderer {

        public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean hasFocus) {
            BufferedImage bi = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.getGraphics();

            try {
                g.setColor(themes.get(index).getColor());
            } catch (Exception e) {
            }
            g.fillRect(0, 0, 19, 19);
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, 20, 20);
            JLabel label =
                    (JLabel) super.getListCellRendererComponent(list,
                    value,
                    index,
                    isSelected,
                    hasFocus);
            label.setIcon(new ImageIcon(bi));
            label.setText(value.toString());
            return label;
        }
    }
}
