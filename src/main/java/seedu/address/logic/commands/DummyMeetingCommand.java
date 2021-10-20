package seedu.address.logic.commands;

import seedu.address.model.Model;

public class DummyMeetingCommand extends Command {
    public static final String COMMAND_WORD = "fish";

    public static final String MESSAGE_SUCCESS = "This is a dummy command, fish!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
