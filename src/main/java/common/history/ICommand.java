package common.history;

public interface ICommand {
    void execute();

    void reset();
}
