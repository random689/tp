package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookStudents;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookTeachers;
import static seedu.address.testutil.TypicalPersons.getTypicalTeachers;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.student.ClearStudentCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearStudentCommand(), model, ClearStudentCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBookTeachers(), new UserPrefs());

        // after clearing students, should only be left with teachers.
        assertCommandSuccess(new ClearStudentCommand(), model, ClearStudentCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
