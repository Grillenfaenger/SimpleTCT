package model;

import java.io.Serializable;

/**
 * This class is to store the data of user highlighted text. Needed when saving
 * project and exporting details.
 *
 */
public class HighlightedText implements Serializable {
    //This is only for serialize data on hard disk (optional)

    public static final long serialVersionUID = 122L;
    /**
     *
     * The user selected text to store.
     */
    private String text;
    /**
     * Starting point(index) of the user selected text in the file
     */
    private int startIndex;
    /**
     * End point(index) of the user selected text in the file
     */
    private int endIndex;
    /**
     * User selected Theme for highlighting
     */
    private Theme theme;
    /**
     *
     * Description text(Personal Notes) for the highlighted text
     */
    private String description = "";

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * @param startIndex the startIndex to set
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * @return the endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * @param endIndex the endIndex to set
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * @return the theme
     */
    public Theme getTheme() {
        return theme;
    }

    /**
     * @param theme the theme to set
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
