package seedu.address.logic.parser.student;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.student.MassDeleteStudentCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new {@code DeleteStudentCommand} object
 */
public class MassDeleteStudentCommandParser implements Parser<MassDeleteStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MassDeleteStudentCommand
     * and returns a MassDeleteStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MassDeleteStudentCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MassDeleteStudentCommand.MESSAGE_USAGE));
        }
        String[] keywords = trimmedArgs.split("\\s+");
        return new MassDeleteStudentCommand(Arrays.asList(keywords));
    }

}
