import java.awt.*;

public class Category {

    public static Category fromString(String c) {
        int splitter = c.lastIndexOf(";");
        String name = c.substring(0, splitter);
        String color = c.substring(splitter+1);
        return new Category(name, Color.getColor(color));
    }

    private String name;
    private Color color;

    public Category(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name + ";" + color.toString();
    }
}