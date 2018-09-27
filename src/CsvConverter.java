import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvConverter {

    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    private static final String FILE_HEADER = "name,dueDate,category";
    private static final int NAME_POSITION = 0;
    private static final int DATE_POSITION = 1;
    private static final int CATEGORY_POSITION = 2;

    private static final String filename = "tasks.csv";

    public static void writeToCSV(List<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);
        fileWriter.append(FILE_HEADER.toString());
        fileWriter.append(NEW_LINE_SEPARATOR);

        for (Task t : tasks) {
            fileWriter.append(t.getName());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(t.getDueDate().toString());
            fileWriter.append(COMMA_DELIMITER);
            fileWriter.append(t.getCategory().toString());
            fileWriter.append(NEW_LINE_SEPARATOR);
        }

        fileWriter.flush();
        fileWriter.close();
    }

    public static AllTasks readFromCSV() throws IOException {
        List<Task> tasks = new ArrayList<Task>();

        BufferedReader fileReader = new BufferedReader(new FileReader(filename));
        String line = "";

        //skip file header
        fileReader.readLine();

        while ((line = fileReader.readLine()) != null) {
            String[] tokens = line.split(COMMA_DELIMITER);
            String name = tokens[NAME_POSITION];
            Date dueDate = new Date(Integer.parseInt(tokens[DATE_POSITION]));
            Category category = Category.fromString(tokens[CATEGORY_POSITION]);
            Task t = new Task(name, dueDate, category);
            tasks.add(t);
        }
        return new AllTasks(tasks);
    }

}
