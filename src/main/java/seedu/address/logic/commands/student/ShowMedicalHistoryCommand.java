package seedu.address.logic.commands.student;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

public class ShowMedicalHistoryCommand extends Command {
    public static final String COMMAND_WORD = "showMedical";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the medical history of the desired student.\n"
            + "Example: " + COMMAND_WORD + "1";

    public static final String SHOWING_MEDICAL_HISTORY_MESSAGE = "Opened medical history window.";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_MEDICAL_HISTORY_MESSAGE, false, false, true);
    }
}
