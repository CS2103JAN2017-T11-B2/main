package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undoes last action if it involves modifying a todo. ";

    public static final String MESSAGE_SUCCESS = "Last modifying action undone";

    public static final String MESSAGE_NO_ACTION = "Error: no modifying action to undo";

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.loadPreviousState();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Model.NoPreviousStateException e) {
            throw new CommandException(MESSAGE_NO_ACTION);
        }
    }
}
