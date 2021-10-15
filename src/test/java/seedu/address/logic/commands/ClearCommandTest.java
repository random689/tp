package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

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
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel.setAddressBook(new AddressBook());

        System.out.println(model.getAddressBook());
        ClearStudentCommand clearStudentCommand = new ClearStudentCommand();
        clearStudentCommand.execute(model);
        System.out.println(model.getAddressBook());
        System.out.println(expectedModel.getAddressBook());

        assertCommandSuccess(new ClearStudentCommand(), model, ClearStudentCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
