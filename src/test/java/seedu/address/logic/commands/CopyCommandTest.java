package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.student.StudentNameContainsKeywordsPredicate;
import seedu.address.model.person.student.Student;


/**
 * Contains integration tests (interaction with the Model) and unit tests for CopyCommand.
 */

public class CopyCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validPhoneCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<Student> studentList = model.getFilteredStudentList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < studentList.size(); i++) {
            if (i == studentList.size() - 1) {
                sb.append(studentList.get(i).getPhone());
            } else {
                sb.append(studentList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()),
                sb.toString());
    }

    @Test
    public void execute_validEmailCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("email");
        List<Student> studentList = model.getFilteredStudentList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < studentList.size(); i++) {
            if (i == studentList.size() - 1) {
                sb.append(studentList.get(i).getEmail());
            } else {
                sb.append(studentList.get(i).getEmail() + ",");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()),
                sb.toString());
    }

    @Test
    public void execute_validNameCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("name");
        List<Student> studentList = model.getFilteredStudentList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < studentList.size(); i++) {
            if (i == studentList.size() - 1) {
                sb.append(studentList.get(i).getName());
            } else {
                sb.append(studentList.get(i).getName() + ", ");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()),
                sb.toString());
    }

    @Test
    public void execute_differentCommand_failure() {
        // do a sanity check
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<Student> studentList = model.getFilteredStudentList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < studentList.size(); i++) {
            if (i == studentList.size() - 1) {
                sb.append(studentList.get(i).getEmail());
            } else {
                sb.append(studentList.get(i).getEmail() + ",");
            }
        }
        assertNotEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()),
                sb.toString());
    }

    @Test
    public void execute_invalidCommand_failure() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("fish");
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()), "");
    }

    @Test
    public void execute_fromFilteredList_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<String> keywords = new ArrayList<>();
        keywords.add("Alice");
        StudentNameContainsKeywordsPredicate nameContainsKeywordsPredicate = new StudentNameContainsKeywordsPredicate(keywords);
        model.updateFilteredStudentList(nameContainsKeywordsPredicate);
        List<Student> personList = model.getFilteredStudentList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                sb.append(personList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredStudentList()),
                sb.toString());
    }

}
