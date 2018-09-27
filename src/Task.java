import java.util.Comparator;
import java.util.Date;

public class Task implements Comparable<Task> {

    private String name;
    private Date dueDate;
    private Category category;

    public Task(String name, Date dueDate, Category category) {
        this.name = name;
        this.dueDate = dueDate;
        this.category = category;
    }

    public Task(Task t) {
        this(t.name, t.dueDate, t.category);
    }

    @Override
    public int compareTo(Task otherTask) {
        if (null == this.dueDate) {
            return 1;
        } else if (null == otherTask.dueDate) {
            return -1;
        } else if (this.dueDate.equals(otherTask.dueDate)) {
            return this.name.compareTo(otherTask.name);
        } else {
            return this.dueDate.compareTo(otherTask.dueDate);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return dueDate.toString() + " - " + name;
    }

}