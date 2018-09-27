import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;

public class ConsoleInput {

    private final BufferedReader reader;
    private final AllTasks tasks;

    private static final String PATTERN = "yyyy/MM/dd hh:mma";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(PATTERN);

    public ConsoleInput() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        AllTasks temp = null;
        try {
            temp = CsvConverter.readFromCSV();
        } catch (IOException e) {
            temp = new AllTasks();
        }
        tasks = temp;
    }

    public void run() throws IOException, ParseException {
        int optionNumber = printMenu();
        doMenuOption(optionNumber);
        while (optionNumber != 4) {
            System.out.println("Would you like to do anything else? ");
            optionNumber = printMenu();
            doMenuOption(optionNumber);
        }
    }

    private int printMenu() throws IOException {
        System.out.println("Here are your choices: ");
        System.out.println("1) Add more tasks");
        System.out.println("2) View tasks");
        System.out.println("3) Check off tasks");
        System.out.println("4) Quit");
        System.out.print("What would you like to do? Please enter the number corresponding with the option. ");
        String text = reader.readLine();
        while (true) {
            try {
                int option = Integer.parseInt(text);
                if (option>0 && option<5) {
                    return option;
                } else {
                    System.out.print("\nI'm sorry, that is not a valid number. Please try again. ");
                }
            } catch (NumberFormatException e) {
                System.out.print("\nI'm sorry, I could not understand that number. Please try again. ");
                text = reader.readLine();
            }
        }
    }

    private void doMenuOption(int option) throws IOException, ParseException {
        switch (option) {
            case 1:
                addTasks();
                break;
            case 2:
                viewTasks();
                break;
            case 3:
                //check off tasks
                break;
            case 4:
                quit();
                break;
            default:
                throw new RuntimeException("Should not get here");
        }
    }

    private void addTasks() throws IOException, ParseException {
        //want to add more tasks
        System.out.print("What is the category you would like to add tasks for? ");
        String category = reader.readLine();
        //TODO color things
        //TODO check if category already exists
        Category c = new Category(category, Color.BLUE);

        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Please state the task in the form MM/DD HH:mmam/pm - NAME. If you don't want to add another task type \"q\"");
            String text = reader.readLine();
            if (text.equals("q")) {
                keepGoing = false;
            } else {
                Task t = parseTask(text, c);
                tasks.addTask(t);
            }
        }
    }

    private void viewTasks() {
        if (tasks.size()==0) {
            System.out.println("There are no tasks to display. ");
        } else {
            System.out.println(tasks.commandLineDisplay());
        }
    }

    private void quit() {
        System.out.println("Okay. Goodbye. ");
    }

    private static Task parseTask(String input, Category category) throws ParseException {
        int dashPosition = input.indexOf("-");
        String dateString = input.substring(0, dashPosition);
        Date dueDate = parseDate(dateString);
        String name = input.substring(dashPosition+2);
        return new Task(name, dueDate, category);
    }

    private static Date parseDate(String dateString) throws ParseException {
        int currentYear = Year.now().getValue();
        dateString = currentYear + "/" + dateString;
        System.out.println(dateString);
        return DATE_FORMATTER.parse(dateString);
    }

}
