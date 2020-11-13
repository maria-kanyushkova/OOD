package common.history;

import java.util.ArrayList;
import java.util.List;

public class History implements IHistory {
    private final List<ICommand> commands = new ArrayList<>();
    private int currentCommandIndex = 0;

    public void push(ICommand command) {
        command.execute();
        commands.add(command);
        ++currentCommandIndex;
    }

    public boolean isEmpty() {
        return commands.isEmpty();
    }

    public void undo() {
        if (isEmpty()) {
            return;
        }
        if (currentCommandIndex > 0) {
            var command = commands.get(--currentCommandIndex);
            command.reset();
        }
    }

    public void redo() {
        if (isEmpty()) {
            return;
        }
        if (currentCommandIndex < commands.size()) {
            var command = commands.get(currentCommandIndex++);
            command.execute();
        }
    }
}
