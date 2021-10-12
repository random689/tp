package seedu.address.logic.commands.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MEDICAL_HISTORY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Person;
import seedu.address.model.person.Student;
import seedu.address.testutil.StudentBuilder;

class MedicalHistoryCommandTest {
    private static final String MEDICAL_HISTORY_STUB = "Some medical history";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addMedicalHistoryUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_STUDENT.getZeroBased());
        StudentBuilder personInList = new StudentBuilder((Student) firstPerson);
        Student editedStudent = personInList.withMedicalHistory(MEDICAL_HISTORY_STUB).build();
        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().value));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteMedicalHistoryUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder((Student) firstPerson).withMedicalHistory("").build();

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().toString()));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_DELETE_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_STUDENT);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_STUDENT.getZeroBased());
        Student editedStudent = new StudentBuilder((Student) model.getFilteredPersonList().get(INDEX_FIRST_STUDENT
                .getZeroBased())).withMedicalHistory(MEDICAL_HISTORY_STUB).build();

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(editedStudent.getMedicalHistory().value));

        String expectedMessage = String.format(MedicalHistoryCommand.MESSAGE_ADD_MEDICAL_HISTORY_SUCCESS,
                editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedStudent);

        assertCommandSuccess(medicalHistoryCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(outOfBoundIndex,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB));

        assertCommandFailure(medicalHistoryCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_STUDENT);
        Index outOfBoundIndex = INDEX_SECOND_STUDENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        MedicalHistoryCommand medicalHistoryCommand = new MedicalHistoryCommand(outOfBoundIndex,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB));

        assertCommandFailure(medicalHistoryCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final MedicalHistoryCommand standardCommand = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY));

        // same values -> returns true
        MedicalHistoryCommand commandWithSameValues = new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new MedicalHistoryCommand(INDEX_SECOND_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_AMY))));

        // different MedicalHistory -> returns false
        assertFalse(standardCommand.equals(new MedicalHistoryCommand(INDEX_FIRST_STUDENT,
                new MedicalHistory(VALID_MEDICAL_HISTORY_BOB))));
    }
}