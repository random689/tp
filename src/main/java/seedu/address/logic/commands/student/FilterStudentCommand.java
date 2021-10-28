package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;

/**
 * Filters and lists all students in address book whose involvement contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterStudentCommand extends Command {

    public static final String COMMAND_WORD = "filterStudent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all students by involvement and tag(s) "
            + "and displays them as a list with index numbers.\n"
            + "Note: You do not need both involvement and tag(s) at the same time, but either one must be present.\n"
            + "Parameters: [INVOLVEMENT] [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " donut buddies t/gym";

    private final StudentInvolvementContainsKeywordsPredicate predicate;

    public FilterStudentCommand(StudentInvolvementContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterStudentCommand // instanceof handles nulls
                && predicate.equals(((FilterStudentCommand) other).predicate)); // state check
    }
}

