package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.student.FormClass;

public class FormClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FormClass(null));
    }

    @Test
    public void constructor_invalidFormClass_throwsIllegalArgumentException() {
        String invalidFormClass = "";
        assertThrows(IllegalArgumentException.class, () -> new FormClass(invalidFormClass));
    }

    @Test
    public void isValidFormClass() {
        // null FormClass
        assertThrows(NullPointerException.class, () -> FormClass.isValidFormClass(null));

        // invalid FormClass
        assertFalse(FormClass.isValidFormClass("")); // empty string
        assertFalse(FormClass.isValidFormClass(" ")); // spaces only
        assertFalse(FormClass.isValidFormClass(" 4E1")); // contains whitespace at start
        assertFalse(FormClass.isValidFormClass("4E1E1")); // not in the format of Number-String-Number
        assertFalse(FormClass.isValidFormClass("4@1")); // contains non-alphanumeric character
        assertFalse(FormClass.isValidFormClass("E1")); // not in the format of Number-String-Number

        // valid FormClass
        assertTrue(FormClass.isValidFormClass("4e1")); // alphanumeric characters
        assertTrue(FormClass.isValidFormClass("4E1")); // with capital letters
        assertTrue(FormClass.isValidFormClass("4Harmony1")); // long names
        assertTrue(FormClass.isValidFormClass("10N12")); // more than digit at start and end
    }
}
