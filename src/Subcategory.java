import java.awt.*;

public class Subcategory extends Category {

   private Category parentCategory;

    public Subcategory(String name, Color color, Category parent) {
        super(name, color);
        this.parentCategory = parent;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}