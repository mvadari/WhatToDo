import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

public class ConsoleTaskInput {

    private static final String PATTERN = "yyyy/MM/dd hh:mma";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(PATTERN);

    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please state the task in the form MM/DD HH:mmam/pm NAME");
        String text = reader.readLine();
        Category c = new Category("Academics", Color.BLUE);
        Task t = parseTask(text, c);
        System.out.println(t);
    }

    private static Task parseTask(String input, Category category) throws ParseException{
        int firstSpace = input.indexOf(" ");
        int secondSpace = input.indexOf(" ", firstSpace+1);
        String dateString = input.substring(0, secondSpace);
        Date dueDate = parseDate(dateString);
        String name = input.substring(secondSpace+1);
        return new Task(name, dueDate, category);
    }

    private static Date parseDate(String dateString) throws ParseException {
        int currentYear = Year.now().getValue();
        dateString = currentYear + "/" + dateString;
        System.out.println(dateString);
        Date date = DATE_FORMATTER.parse(dateString);
        return date;
    }
}
