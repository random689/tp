package seedu.address.logic.parser.teacher;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.teacher.FilterTeacherCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.teacher.TeacherInvolvementContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new {@code FilterTeacherCommand} object
 */

public class FilterTeacherCommandParser implements Parser<FilterTeacherCommand> {

    private static final String TAG_REGEX = "^[a-zA-Z0-9]*$";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterTeacherCommand
     * and returns a FilterTeacherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterTeacherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        List<String> processedNameKeywords = processInput(nameKeywords);

        return new FilterTeacherCommand(new TeacherInvolvementContainsKeywordsPredicate(processedNameKeywords));
    }


    private List<String> processInput(String[] keywords) throws ParseException {
        boolean isTagsOnwards = false;
        List<String> listToReturn = new ArrayList<>();
        for (int i = 0; i < keywords.length; i++) {
            if (keywords[i].startsWith("t/")) {
                if (!keywords[i].substring(2).matches(TAG_REGEX)) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
                }
            } else {
                if (!keywords[i].matches(TAG_REGEX)) {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
                }
            }
            if (!isTagsOnwards) {
                if (keywords[i].startsWith("t/") && validFirstTag(keywords[i])) {
                    isTagsOnwards = true;
                    listToReturn.add(keywords[i]);
                } else if (keywords[i].startsWith("t/") && !validFirstTag(keywords[i])) {
                    isTagsOnwards = true;
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
                } else {
                    listToReturn.add(keywords[i]);
                }
            } else {
                if (validOtherTag(keywords[i])) {
                    listToReturn.add(keywords[i].substring(2));
                } else {
                    throw new ParseException(
                            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterTeacherCommand.MESSAGE_USAGE));
                }
            }
        }

        return listToReturn;
    }

    private boolean validFirstTag(String keyword) {
        if (keyword.length() <= 2) {
            return false;
        }
        if (keyword.substring(2).matches(TAG_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validOtherTag(String keyword) {
        if (!keyword.startsWith("t/")) {
            return false;
        } else if (keyword.length() <= 2) {
            return false;
        } else if (keyword.substring(2).matches(TAG_REGEX)) {
            return true;
        } else {
            return false;
        }
    }

}
