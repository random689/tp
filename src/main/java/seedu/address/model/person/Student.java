package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Set;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private Phone emergencyContact;

    public Student(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Phone emergencyContact) {
        super(name, phone, email, address, tags);
        this.emergencyContact = emergencyContact;
    }

    public Phone getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Returns true if both person are students, and have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return super.equals(otherStudent)
                && this.getEmergencyContact().equals(otherStudent.getEmergencyContact());
    }

    @Override
    public String toString() {
        return super.toString() + "; " + String.format("Emergency contact: %s", getEmergencyContact());
    }
}
