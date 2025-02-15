package org.jjspizzeria.jjspizzeria.command;

public abstract class Command {
    abstract void execute();
    abstract void undo();
    // Topping commands will override this default behaviour to become undoable
    public boolean isUndoable() {
        return false;
    }
}