package org.jjspizzeria.jjspizzeria.components;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import org.jjspizzeria.jjspizzeria.command.*;
import org.jjspizzeria.jjspizzeria.framework.FXMLComponent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PizzaCommandBar extends FXMLComponent {

    private final Deque<Command> undoStack = new ArrayDeque<>();

    private Map<String, Command> toppingCommandMapping;

    public PizzaCommandBar() {
        super("/org/jjspizzeria/jjspizzeria/layouts/pizzaCommandBar.fxml");
    
        toppingCommandMapping = Map.of(
            "cheese", new AddCheeseCommand(),
            "jalapenos", new AddJalapenoCommand(),
            "ham", new AddHamCommand(),
            "mushrooms", new AddMushroomCommand(),
            "pineapple", new AddPineappleCommand(),
            "pepperoni", new AddPepperoniCommand(),
            "tomato", new AddTomatoCommand(),
            "onions", new AddOnionCommand()
        );
    }

    @FXML
    private void onAddTopping(ActionEvent e) {
        IconButton button = (IconButton) e.getSource();
        executeCommand(toppingCommandMapping.get(button.getIconName()));
    }


    @FXML
    private void bakeNormal() {
        executeCommand(new BakeCommand("normal"));
    }

    @FXML
    private void bakeCrispy() {
        executeCommand(new BakeCommand("crispy"));
    }

    @FXML
    private void slice4() {
        executeCommand(new SliceCommand(4));
    }

    @FXML
    private void slice6() {
        executeCommand(new SliceCommand(6));
    }

    @FXML
    private void slice8() {
        executeCommand(new SliceCommand(8));
    }

    @FXML
    private void box() {
        executeCommand(new BoxCommand());
    }

    @FXML
    private void finish() {
        executeCommand(new FinishCommand());
    }


    public void executeCommand(Command command) {
        command.execute();
        if (command.isUndoable()) {
            undoStack.push(command);
        }
    }

    public void undoLastCommand() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
        }
    }
}
