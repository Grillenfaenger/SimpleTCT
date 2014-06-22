
package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * This class is to store the data of user selected files for a each project. 
 * To store file name, and Highlighted Text details for each file. 
 * Check the "HighlightedText" class for its description.
 * 
 */
public class FileContent implements Serializable{
    
    //This is only for serialize data on hard disk (optional)
    public static final long serialVersionUID = 122L;
    
    
    
    /**
     * 
     * Stores file name
     */
    private String fileName;
    
    /**
     * 
     * Stores list of Highlighted Text details
     */
    private ArrayList<HighlightedText> text=new ArrayList<HighlightedText>();

   
  

    @Override
    public String toString() {
        return this.getFileName();
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the text
     */
    public ArrayList<HighlightedText> getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(ArrayList<HighlightedText> text) {
        this.text = text;
    }
    
}
