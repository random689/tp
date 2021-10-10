package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COPY_FIELD;

import java.util.Optional;

import seedu.address.logic.commands.CopyCommand;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;



public class CopyCommandParser implements Parser<CopyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CopyCommand
     * and returns a CopyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CopyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COPY_FIELD);
        Optional<String> fieldToCopy = argMultimap.getValue(PREFIX_COPY_FIELD);
        if (!fieldToCopy.isPresent()) {
            throw new ParseException(CopyCommand.NO_FIELD_PROVIDED + "\n" + CopyCommand.MESSAGE_USAGE);
        } else {
            CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor(fieldToCopy.get());
            if (copyCommandDescriptor.hasValidField()) {
                return new CopyCommand(copyCommandDescriptor);
            } else {
                throw new ParseException(CopyCommand.MESSAGE_NOT_VALID_FIELD);
            }
        }
    }
}
