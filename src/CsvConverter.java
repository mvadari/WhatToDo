import java.awt.*;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvConverter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String TASK_FILE_HEADER = "name,dueDate,category";
    private static final int TASK_NAME_POSITION = 0;
    private static final int TASK_DATE_POSITION = 1;
    private static final int TASK_CATEGORY_POSITION = 2;

    private static final String CATEGORY_FILE_HEADER = "name,color"; //TODO need subcategory support
    private static final int CATEGORY_NAME_POSITION = 0;
    private static final int CATEGORY_COLOR_POSITION = 1;

    private static final String TASK_FILENAME = "tasks.csv";
    private static final String CATEGORY_FILENAME = "categories.csv";

    public static void writeToCSV(AllTasks tasks) throws IOException {
        Map<String, List<Task>> taskMap = tasks.getTasks();
        FileWriter taskFileWriter = new FileWriter(TASK_FILENAME);
        taskFileWriter.append(TASK_FILE_HEADER.toString());
        taskFileWriter.append(NEW_LINE_SEPARATOR);

        for (String category : taskMap.keySet()) {
            List<Task> categoryTasks = taskMap.get(category);
            for (Task t : categoryTasks) {
                taskFileWriter.append(t.getName());
                taskFileWriter.append(COMMA_DELIMITER);
                taskFileWriter.append(t.getDueDate().toString());
                taskFileWriter.append(COMMA_DELIMITER);
                taskFileWriter.append(category);
                taskFileWriter.append(NEW_LINE_SEPARATOR);
            }
        }

        taskFileWriter.flush();
        taskFileWriter.close();

        FileWriter categoryFileWriter = new FileWriter(CATEGORY_FILENAME);
        categoryFileWriter.append(CATEGORY_FILE_HEADER.toString());
        categoryFileWriter.append(NEW_LINE_SEPARATOR);

        Map<String, Category> categoryMap = tasks.getCategories();

        for (String name : categoryMap.keySet()) {
            Category c = categoryMap.get(name);
            categoryFileWriter.append(name);
            categoryFileWriter.append(COMMA_DELIMITER);
            categoryFileWriter.append(c.getColor().toString());
            categoryFileWriter.append(NEW_LINE_SEPARATOR);
        }

        categoryFileWriter.flush();
        categoryFileWriter.close();

    }

    public static AllTasks readFromCSV() throws IOException {
        AllTasks tasks = new AllTasks();

        Map<String,Category> categoryMap = readCategories();

        BufferedReader taskFileReader = new BufferedReader(new FileReader(TASK_FILENAME));
        String line = "";

        //skip file header
        taskFileReader.readLine();

        while ((line = taskFileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            String name = tokens[TASK_NAME_POSITION];
            Date dueDate = new Date(Integer.parseInt(tokens[TASK_DATE_POSITION]));
            String category = tokens[TASK_CATEGORY_POSITION];
            Task t = new Task(name, dueDate);
            tasks.addTask(t, categoryMap.get(category));
        }
        return tasks;
    }

    private static Map<String,Category> readCategories() throws IOException {
        Map<String, Category> categoryMap = new HashMap<String, Category>();

        BufferedReader categoryFileReader = new BufferedReader(new FileReader(CATEGORY_FILENAME));
        String line = "";

        //skip file header
        categoryFileReader.readLine();

        while ((line = categoryFileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            String categoryName = tokens[CATEGORY_NAME_POSITION];
            Color color = Color.getColor(tokens[CATEGORY_COLOR_POSITION]);
            Category c = new Category(categoryName, color);
            categoryMap.put(categoryName, c);
        }
        return categoryMap;
    }

}
