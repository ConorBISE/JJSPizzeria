package org.jjspizzeria.jjspizzeria.command;

public abstract class Command {
    public abstract void execute();
    public void undo() {};
    
    // Topping commands will override this default behaviour to become undoable
    public boolean isUndoable() {
        return false;
    }
}