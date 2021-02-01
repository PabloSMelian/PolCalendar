package calendar1.controller;

import calendar1.model.Calendar;
import calendar1.model.Event;
import calendar1.view.CommandDisplay;
import calendar1.view.Display;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CommandHandler {

    private final Calendar calendar;
    private Display display;
    private final Map<Command, Function<List<String>, Boolean>> commandMap;

    public CommandHandler(Calendar calendar) {
        this.calendar = calendar;
        this.commandMap = new HashMap<>() {{
            put(Command.NEWEVENT, args -> executeNewEvent(args));
            put(Command.SHOWEVENT, args -> executeShowEvent(args));
            put(Command.REMOVEEVENT,args-> executeRemoveEvent(args));
            put(Command.HELP,args-> executeHelp());
            put(Command.SHOWTODAYEVENTS,args->executeGetTodayEvents());
        }};
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public void executeCommand(String input){
        if (input.isEmpty()) return;
        String[] fields = input.split("/");
        try{
            Command command = Command.valueOf(fields[0].toUpperCase());
            if (command != null) {
                boolean result = commandMap.get(command).apply(Arrays.asList(fields).subList(1, fields.length));
                if(result) display.showInfoMessage(command.getMessage());
            }else{
                display.showErrorMessage("El comando no existe");
            }
        }catch(Exception e){
            System.out.println("The command " + fields[0]+ " doesn't exist use the command HELP to show all commands");
        }
        /*if (command != null) {
            boolean result = commandMap.get(command).apply(Arrays.asList(fields).subList(1, fields.length));
            if(result) display.showInfoMessage(command.getMessage());
        }else{
            display.showErrorMessage("El comando no existe");
        }*/
    }

    public boolean executeNewEvent(List<String> args) {
        Event event = new Event(args.get(0), doParse(args), 0, 0);
        calendar.getEvents().add(event);
        /*if (display.getClass().equals(CommandDisplay.class)){
            ((CommandDisplay) display).setOutput();
        }*/
        return true;
    }

    public boolean executeRemoveEvent(List<String> args) {
        String name = args.get(0);
        LocalDateTime instant = doParse(args);

        calendar.setEvents(calendar.getEvents().stream().filter(event -> !event.getName().equals(name)
                && !event.getStartInstant().equals(instant))
                .collect(Collectors.toList()));

        return true;
    }

    private boolean executeShowEvent(List<String> args) {
        if (display.getClass().equals(CommandDisplay.class)){
            ((CommandDisplay) display).addOutput(getEventListToShow(calendar.getEvents()));
            return true;
        }
        return false;
    }

    private List<String> getEventListToShow(List<Event> events) {

        return events.stream()
                .map(event -> event.getName() + " " + event.getStartInstant().toString())
                .collect(Collectors.toList());
    }

    private boolean executeHelp() {

        String changeOutColorToYellow = "\u001B[33m";
        String resetOutColor = "\u001B[0m";

        display.showInfoMessage(changeOutColorToYellow+"+NEWEVENT:\n"+resetOutColor +
                "      NEWEVENT/name/Date and Hour(YYYY-MM-DD/HH:MM:SS)\n" +
                changeOutColorToYellow+"+SHOWEVENT\n "+resetOutColor +" -Show all events\n  -Syntax:\n      SHOWEVENT\n" +
                changeOutColorToYellow+"+REMOVEEVENT\n "+resetOutColor +
                " -Remove all events whith the name and at the instant that you write\n" +
                "  -Syntax:\n      REMOVEEVENT/name/Date and Hour(YYYY-MM-DD/HH:MM:SS)");
        return true;
    }

    private Boolean executeGetTodayEvents() {
        LocalDateTime now = LocalDateTime.now();

        List<Event> todayEvents = calendar.getEvents().stream().filter(event ->
                event.getStartInstant().getDayOfMonth() == now.getDayOfMonth())
                .collect(Collectors.toList());

        ((CommandDisplay)display).addOutput(getEventListToShow(todayEvents));
        return true;
    }
    private Boolean executeGetThisDayEvent(List<String> args){
        LocalDateTime thisDay = doParse(args);
        List<Event> todayEvents = calendar.getEvents().stream().filter(event ->
                event.getStartInstant().getDayOfMonth() == thisDay.getDayOfMonth())
                .collect(Collectors.toList());
        return true;
    }

    public LocalDateTime doParse(List<String> args){
        return LocalDateTime.parse(args.get(1)+"T"+args.get(2));
    }
}