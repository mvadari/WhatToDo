import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class AllTasks {

    private Map<String, List<Task>> tasks; //category name to task
    private Map<String, Category> categories; //category name to category object
    //TODO implement subcategory functionality

    //TODO change representation to Map

    public AllTasks() {
        tasks = new HashMap<String, List<Task>>();
        categories = new HashMap<String, Category>();
    }

    public AllTasks(Map<String, List<Task>> tasks, Map<String, Category> categories) {
        this();
        for (String name : tasks.keySet()) {
            List<Task> categoryTasks = tasks.get(name);
            tasks.put(name, new ArrayList<Task>());
            for (Task t : categoryTasks) {
                Task copy = new Task(t);
                tasks.get(name).add(copy);
            }
        }
    }

    public void addTask(Task task, Category c) {
        String name = c.getName();
        if (tasks.containsKey(name)) {
            tasks.get(name).add(new Task(task));
        } else { //new category
            tasks.put(name, new ArrayList<Task>());
            tasks.get(name).add(new Task(task));
            categories.put(name, c);
        }
    }

    public int size() {
        int size = 0;
        for (String name : tasks.keySet()) {
            size += tasks.get(name).size();
        }
        return size;
    }

    public Map<String,List<Task>> getTasks() {
        Map<String,List<Task>> returnMap = new HashMap<String,List<Task>>();
        for (String name : tasks.keySet()) {
            List<Task> categoryTasks = tasks.get(name);
            returnMap.put(name, new ArrayList<Task>());
            for (Task t : categoryTasks) {
                returnMap.get(name).add(new Task(t));
            }
        }
        return returnMap;
    }

    public Map<String, Category> getCategories() {
        Map<String,Category> returnMap = new HashMap<String,Category>();
        for (String name : categories.keySet()) {
            Category category = categories.get(name);
            returnMap.put(name, new Category(category));
        }
        return returnMap;
    }

    public String commandLineDisplay() {
        String output = "";
        for (String categoryName : tasks.keySet()) {
            String categoryOutput = "Category: " + categoryName + "\n---------\n";
            List<Task> categoryTasks = tasks.get(categoryName); //TODO maybe sort?
            for (Task t : categoryTasks) {
                categoryOutput += t.toString() + "\n";
            }
            output += categoryOutput;
        }
        return output;
    }
}
