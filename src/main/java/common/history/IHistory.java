package common.history;

public interface IHistory {
    void undo();
    void redo();

    void push(ICommand command);
}
