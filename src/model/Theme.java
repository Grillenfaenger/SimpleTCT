package model;

import java.awt.Color;
import java.io.Serializable;

public class Theme implements Serializable{
    public static final long serialVersionUID = 122L;

    private int index;
    private String name;
    private Color color;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Theme){
            Theme t=(Theme) obj;
            return t.getName().equalsIgnoreCase(this.getName());
        }else {
            return false;
        }
    }
    
    
}
