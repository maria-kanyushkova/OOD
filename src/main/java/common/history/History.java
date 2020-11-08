package common.history;

import java.util.Stack;

public class History {
    // TODO: стек на redo не будет возвращать на последуюзие команды послу undo - надо заменить на Array
    private final Stack<ICommand> history = new Stack<>();

    public void push(ICommand command, boolean execute) {
        if (execute) {
            command.execute();
        }

        push(command);
    }

    public void push(ICommand command) {
        history.push(command);
    }

    public void pop(ICommand command) {
        history.push(command);
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }
}
