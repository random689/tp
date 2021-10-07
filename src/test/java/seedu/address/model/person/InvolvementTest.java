package seedu.address.model.person;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class InvolvementTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Involvement(null));
    }

    @Test
    public void constructor_invalidInvolvement_throwsIllegalArgumentException() {
        String invalidInvolvement = "";
        assertThrows(IllegalArgumentException.class, () -> new Involvement(invalidInvolvement));
    }

    @Test
    public void isValidInvolvement() {
        // null involvement
        assertThrows(NullPointerException.class, () -> Involvement.isValidInvolvement(null));

        // invalid Involvements
        assertFalse(Involvement.isValidInvolvement("")); // empty string
        assertFalse(Involvement.isValidInvolvement(" ")); // spaces only

        // valid Involvements
        assertTrue(Involvement.isValidInvolvement("Blk 456, Den Road, #01-355"));
        assertTrue(Involvement.isValidInvolvement("-")); // one character
        assertTrue(Involvement.isValidInvolvement("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }
}
