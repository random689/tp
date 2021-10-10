package seedu.address.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

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
        List<Person> personList = model.getFilteredPersonList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                sb.append(personList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()),
                sb.toString());
    }

    @Test
    public void execute_validEmailCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("email");
        List<Person> personList = model.getFilteredPersonList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getEmail());
            } else {
                sb.append(personList.get(i).getEmail() + ",");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()),
                sb.toString());
    }

    @Test
    public void execute_validNameCommand_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("name");
        List<Person> personList = model.getFilteredPersonList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getName());
            } else {
                sb.append(personList.get(i).getName() + ",");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()),
                sb.toString());
    }

    @Test
    public void execute_differentCommand_failure() {
        // do a sanity check
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<Person> personList = model.getFilteredPersonList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getEmail());
            } else {
                sb.append(personList.get(i).getEmail() + ",");
            }
        }
        assertNotEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()),
                sb.toString());
    }

    @Test
    public void execute_invalidCommand_failure() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("fish");
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()), "");
    }

    @Test
    public void execute_fromFilteredList_success() {
        CopyCommandDescriptor copyCommandDescriptor = new CopyCommandDescriptor("phone");
        List<String> keywords = new ArrayList<>();
        keywords.add("Alice");
        NameContainsKeywordsPredicate nameContainsKeywordsPredicate = new NameContainsKeywordsPredicate(keywords);
        model.updateFilteredPersonList(nameContainsKeywordsPredicate);
        List<Person> personList = model.getFilteredPersonList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                sb.append(personList.get(i).getPhone() + " ");
            }
        }
        assertEquals(new CopyCommand(copyCommandDescriptor).getCopyContent(model.getFilteredPersonList()),
                sb.toString());
    }

}
