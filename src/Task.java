import java.util.Comparator;
import java.util.Date;

public class Task implements Comparable<Task> {

    private String name;
    private Date dueDate;

    public Task(String name, Date dueDate) {
        this.name = name;
        this.dueDate = dueDate;
    }

    public Task(Task t) {
        this(t.name, new Date(t.dueDate.getTime()));
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

    @Override
    public String toString() {
        return dueDate.toString() + " - " + name;
    }

}