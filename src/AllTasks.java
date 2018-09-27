import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class AllTasks {

    private List<Task> tasks;
    //TODO implement subcategory functionality

    //TODO change representation to Map

    public AllTasks() {
        tasks = new ArrayList<Task>();
    }

    public AllTasks(List<Task> tasks) {
        this.tasks = new ArrayList<Task>();
        for (Task  t : tasks) {
            Task copy = new Task(t);
            this.tasks.add(copy);
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    private Map<Category,List<Task>> generateCategoryMap() {
        //TODO not threadsafe
        Map<Category, List<Task>> categoryMap = new HashMap<Category, List<Task>>();
        for (Task task : tasks) {
            Category category = task.getCategory();
            if (!(categoryMap.containsKey(category))) {
                categoryMap.put(category, new ArrayList<Task>());
            }
            categoryMap.get(category).add(task);
        }
        for (Category category : categoryMap.keySet()) {
            Collections.sort(categoryMap.get(category));
        }
        return categoryMap;
    }

    public String commandLineDisplay() {
        Map<Category, List<Task>> categoryMap = generateCategoryMap();
        String output = "";
        for (Category c : categoryMap.keySet()) {
            String categoryOutput = "Category: " + c.getName() + "\n---------\n";
            List<Task> tasks = categoryMap.get(c);
            for (Task t : tasks) {
                categoryOutput += t.toString() + "\n";
            }
            output += categoryOutput;
        }
        return output;
    }
}
