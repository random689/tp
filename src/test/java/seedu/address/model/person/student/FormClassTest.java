package seedu.address.model.person.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        assertFalse(FormClass.isValidFormClass("4@1")); // contains non-alphanumeric character
        assertFalse(FormClass.isValidFormClass("E1")); // does not start with 1-5
        assertFalse(FormClass.isValidFormClass("8E1")); // does not start with 1-5

        // valid FormClass
        assertTrue(FormClass.isValidFormClass("4e1")); // in the correct format
        assertTrue(FormClass.isValidFormClass("4E1")); // with capital letters
        assertTrue(FormClass.isValidFormClass("4Harmony1")); // long names
        assertTrue(FormClass.isValidFormClass("1N12")); // more than digit at start and end
        assertTrue(FormClass.isValidFormClass("1Science4Life")); // alphanumeric mix
    }
}
