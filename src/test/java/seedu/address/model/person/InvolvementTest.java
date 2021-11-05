package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

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
        assertTrue(Involvement.isValidInvolvement("Math Class"));
        assertTrue(Involvement.isValidInvolvement("-")); // one character
        assertTrue(Involvement.isValidInvolvement("Math Representative"));
    }

    @Test
    public void hashable() {
        HashMap<Involvement, Integer> map = new HashMap<>();
        map.put(new Involvement("soccer buddies"), 0);
    }
}
