package joe.ui;

import java.util.ArrayList;
import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import joe.JoeApp;
import joe.controller.Controller;
import joe.parser.Parser;
import joe.task.Task;

/**
 * Handles the user interface of the program.
 */
public class Ui {
    private MainWindow mainWindow;
    private Stage stage;

    public static final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    public static final String TASK_COUNT_MESSAGE = "Now you have %d tasks in the list.";

    private final String CHATBOT_NAME;

    public Ui(String chatbotName) {
        this.CHATBOT_NAME = chatbotName;
    }

    public void start(Stage stage, Parser<Controller> parser) {
//        this.stage = stage;
//        this.stage.setTitle(CHATBOT_NAME);
//        mainWindow = new MainWindow();
//        mainWindow.setParser(parser);
//        Scene scene = new Scene(mainWindow);
//        this.stage.setScene(scene);
//        this.stage.show();
        mainWindow = new MainWindow();
        this.stage = stage;
        Scene scene = new Scene(mainWindow);
        this.stage.setTitle(CHATBOT_NAME);
        this.stage.setScene(scene);
        mainWindow.setParser(parser);
        greet();
        this.stage.show();
    }

    /**
     * Ends the program by closing the window.
     */
    public void stop() {
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> stage.close());
        delay.play();
    }

    public void printBotResponse(String... response) {
        String formattedResponse = String.join("\n", response);
        mainWindow.printBotResponse(formattedResponse);
    }

    public void greet() {
        String greeting = "Hello! I'm " + CHATBOT_NAME;
        printBotResponse(greeting, "What can I do for you?");
    }

    public void farewell() {
        printBotResponse("Bye. Hope to see you again soon!");
    }

    public void printHelp() {
        printBotResponse("Here are the commands you can use:",
                "list - List all tasks",
                "todo <task> - Add a todo task",
                "deadline <task> /by <date> - Add a deadline task with date in yyyy-mm-dd format",
                "event <task> /from <date> /to <date> - Add an event task with dates in yyyy-mm-dd format",
                "mark <index> - Mark a task as done",
                "unmark <index> - Mark a task as not done yet",
                "delete <index> - Delete a task",
                "help - Show this help message",
                "bye - Exit the program");
    }

    public void printListMessage(ArrayList<Task> list) {
        String[] listStrings = new String[list.size() + 1];
        listStrings[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= list.size(); i++) {
            listStrings[i] = i + "." + list.get(i - 1);
            System.out.println(listStrings[i]);
        }
        System.out.println(listStrings.toString());
        printBotResponse(listStrings);
    }

    public void printDoneMessage(Task task) {
        printBotResponse("Nice! I've marked this task as done:", task.toString());
    }

    public void printUndoneMessage(Task task) {
        printBotResponse("Nice! I've marked this task as not done yet:", task.toString());
    }

    public void printDeleteMessage(Task task, int size) {
        printBotResponse(
                "Noted. I've removed this task:",
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    public void printTodoMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    public void printDeadlineMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    public void printEventMessage(Task task, int size) {
        printBotResponse(
                ADD_TASK_MESSAGE,
                task.toString(),
                String.format(TASK_COUNT_MESSAGE, size));
    }

    public void printEmptyTaskErrorMessage() {
        printBotResponse("Don't expect me to remember nothing!");
    }

    public void printEmptyByErrorMessage() {
        printBotResponse("BY WHEN??!!");
    }

    public void printInvalidEventDateErrorMessage() {
        printBotResponse("Give me a valid from and to!");
    }

    public void printInvalidDateFormatErrorMessage() {
        printBotResponse("Invalid date format! Please use yyyy-mm-dd format.");
    }

    public void printFindMessage(ArrayList<Task> list) {
        String[] listStrings = new String[list.size() + 1];
        listStrings[0] = "Here are the matching tasks in your list:";
        for (int i = 1; i < list.size(); i++) {
            listStrings[i] = i + "." + list.get(i);
        }
        printBotResponse(listStrings);
    }
}
