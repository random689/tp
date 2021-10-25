package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.student.Student;

/**
 * Clears the student address book.
 */
public class ClearStudentCommand extends Command {

    public static final String COMMAND_WORD = "clearStudent";
    public static final String MESSAGE_SUCCESS = "Student Address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyAddressBook readOnlyAddressBook = model.getAddressBook();
        AddressBook copiedAddressBook = new AddressBook(readOnlyAddressBook);
        List<Student> emptyStudentList = new ArrayList<>();
        copiedAddressBook.setStudents(emptyStudentList);
        model.setAddressBook(copiedAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
