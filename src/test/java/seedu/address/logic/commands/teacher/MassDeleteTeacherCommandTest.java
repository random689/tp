package seedu.address.logic.commands.teacher;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.BEN;
import static seedu.address.testutil.TypicalPersons.EL;
import static seedu.address.testutil.TypicalPersons.FIO;
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
public class MassDeleteTeacherCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_massDeleteInv_success() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("Math");
        infoList.add("Dept");

        MassDeleteTeacherCommand deleteCommand = new MassDeleteTeacherCommand(infoList);

        String expectedMessage = String.format(MassDeleteTeacherCommand.MESSAGE_DELETE_TEACHER_SUCCESS, 4);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTeacher(ALI);
        expectedModel.deleteTeacher(BEN);
        expectedModel.deleteTeacher(EL);
        expectedModel.deleteTeacher(FIO);


        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_massDelete_none() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("dank");
        infoList.add("mEmeS");

        MassDeleteTeacherCommand deleteCommand = new MassDeleteTeacherCommand(infoList);

        String expectedMessage = String.format(MassDeleteTeacherCommand.MESSAGE_DELETE_TEACHER_SUCCESS, 0);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        List<String> infoList = new ArrayList<String>();
        infoList.add("English");

        List<String> infoList2 = new ArrayList<String>();
        infoList.add("Chinese");
        MassDeleteTeacherCommand deleteFirstCommand = new MassDeleteTeacherCommand(infoList);
        MassDeleteTeacherCommand deleteSecondCommand = new MassDeleteTeacherCommand(infoList2);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        MassDeleteTeacherCommand deleteFirstCommandCopy = new MassDeleteTeacherCommand(infoList);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
