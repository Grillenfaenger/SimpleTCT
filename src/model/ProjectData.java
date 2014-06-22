package model;

import java.io.Serializable;
import java.util.ArrayList;

public class ProjectData implements Serializable {
    //This is only for serialize data on hard disk (optional)

    public static final long serialVersionUID = 122L;
    /**
     *
     * The path of the selected project folder when creating new project
     *
     */
    private String projectFolder;
    /**
     * The project name to store
     *
     */
    private String name;
    /**
     * Details of the selected files for a this(specific) project
     *
     */
    private ArrayList<FileContent> files;
    /**
     * Themes created for this(specific) project
     */
    private ArrayList<Theme> themes;
    /**
     *
     * The delimiter character for this(specific) project (Optional)
     */
    private String delimiter = "";

    /**
     * @return the projectFolder
     */
    public String getProjectFolder() {
        return projectFolder;
    }

    /**
     * @param projectFolder the projectFolder to set
     */
    public void setProjectFolder(String projectFolder) {
        this.projectFolder = projectFolder;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the files
     */
    public ArrayList<FileContent> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(ArrayList<FileContent> files) {
        this.files = files;
    }

    /**
     * @return the themes
     */
    public ArrayList<Theme> getThemes() {
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /**
     * @return the delimiter
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * @param delimiter the delimiter to set
     */
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }
}
