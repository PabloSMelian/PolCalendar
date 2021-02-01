package calendar1.view;

import java.util.ArrayList;
import java.util.List;

public class CommandDisplay implements Display{

    private List<String> output;

    public CommandDisplay() {
        this.output = new ArrayList<>();
    }

    @Override
    public void display() {

        output.forEach(System.out::println);
        output = new ArrayList<>();
    }

    @Override
    public void showErrorMessage(String message) {
        this.output.add(message);
    }

    @Override
    public void showInfoMessage(String message) {
        this.output.add(message);
    }

    public void addOutput(List<String> messages) {
        this.output.addAll(messages);
    }
}
