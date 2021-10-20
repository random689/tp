package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.teacher.Teacher;

public class CopyTeacherCommand extends CopyCommand {

    public static final String COMMAND_WORD = "copyTeacher";
    private final Logger logger = LogsCenter.getLogger(CopyTeacherCommand.class);

    /**
     * Constructor for {@code CopyTeacherCommand}
     *
     * @param copyCommandDescriptor the {@code copyCommandDescriptor to specify which field to copy}
     */

    public CopyTeacherCommand(CopyCommandDescriptor copyCommandDescriptor) {
        super(copyCommandDescriptor);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Teacher> lastShownList = model.getFilteredTeacherList();
        copyToClipBoard(lastShownList);
        logger.info("Contents copied to clipboard!");
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyTeacherCommand // instanceof handles nulls
                && super.equals(other)); // state check
    }
}
