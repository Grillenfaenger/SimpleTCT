package controller;

import java.io.File;

/**
 * This interface contains the method to invoke when the user read files on Files directory list (FileTree)
 * 
 */
public interface TreeChange {
    public void changeValue(File f);
}
