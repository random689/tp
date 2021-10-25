package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.teacher.MassDeleteTeacherCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code DeleteTeacherCommand} object
 */
public class MassDeleteTeacherCommandParser implements Parser<MassDeleteTeacherCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MassDeleteTeacherCommand
     * and returns a MassDeleteTeacherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MassDeleteTeacherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassDeleteTeacherCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return new MassDeleteTeacherCommand(Arrays.asList(keywords));
    }

}
