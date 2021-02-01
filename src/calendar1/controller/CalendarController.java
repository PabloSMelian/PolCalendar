package calendar1.controller;

import calendar1.model.Calendar;
import calendar1.view.CommandDisplay;
import calendar1.view.Display;

import java.util.Scanner;

public class CalendarController {

    private Display display;
    private final CommandHandler commandHandler;
    private final Calendar calendar;

    public CalendarController() {
        this.display = new CommandDisplay();
        this.calendar = new Calendar();
        this.commandHandler = new CommandHandler(calendar);
    }

    public void execute(){
        String input = new Scanner(System.in).nextLine();;

        while(true) {
            if(!input.isEmpty()) {
                commandHandler.setDisplay(display);
                display.display();
                commandHandler.executeCommand(input);
                display.display();
                input = new Scanner(System.in).nextLine();
            }
        }
    }
}
