package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CopyCommandDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        CopyCommandDescriptor phoneDescriptor = new CopyCommandDescriptor("phone");
        CopyCommandDescriptor emailDescriptor = new CopyCommandDescriptor("email");
        CopyCommandDescriptor invalidDescriptor = new CopyCommandDescriptor("fish");
        CopyCommandDescriptor anotherPhone = new CopyCommandDescriptor("phone");

        // same value, assert true
        assertTrue(anotherPhone.equals(phoneDescriptor));

        // same object, assert true
        assertTrue(anotherPhone.equals(anotherPhone));

        // invalid descriptor, check if it is invalid
        assertEquals(CopyCommandDescriptor.Field.INVALID, invalidDescriptor.getField());

        // different fields, check if both are equal
        assertNotEquals(invalidDescriptor, phoneDescriptor);

        // different descriptors, check if both are equal
        assertNotEquals(emailDescriptor, phoneDescriptor);
    }
}
