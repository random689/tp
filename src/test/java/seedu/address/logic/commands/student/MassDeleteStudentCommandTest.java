package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class MassDeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_massDelete_success() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("English");
        infoList.add("Class");

        MassDeleteStudentCommand deleteCommand = new MassDeleteStudentCommand(infoList);

        String expectedMessage = String.format(MassDeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, 2);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteStudent(FIONA);
        expectedModel.deleteStudent(GEORGE);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_massDelete_none() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("dank");
        infoList.add("mEmeS");

        MassDeleteStudentCommand deleteCommand = new MassDeleteStudentCommand(infoList);

        String expectedMessage = String.format(MassDeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, 0);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("English");

        List<String> infoList2 = new ArrayList<String>();
        infoList.add("Chinese");
        MassDeleteStudentCommand deleteFirstCommand = new MassDeleteStudentCommand(infoList);
        MassDeleteStudentCommand deleteSecondCommand = new MassDeleteStudentCommand(infoList2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        MassDeleteStudentCommand deleteFirstCommandCopy = new MassDeleteStudentCommand(infoList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
