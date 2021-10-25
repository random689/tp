package seedu.address.logic.commands.teacher;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.teacher.Teacher;

/**
 * Clears the teacher address book.
 */
public class ClearTeacherCommand extends Command {

    public static final String COMMAND_WORD = "clearTeacher";
    public static final String MESSAGE_SUCCESS = "Teacher address book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyAddressBook readOnlyAddressBook = model.getAddressBook();
        AddressBook copiedAddressBook = new AddressBook(readOnlyAddressBook);
        List<Teacher> emptyTeacherList = new ArrayList<>();
        copiedAddressBook.setTeachers(emptyTeacherList);
        model.setAddressBook(copiedAddressBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
