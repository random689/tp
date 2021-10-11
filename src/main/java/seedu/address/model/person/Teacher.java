package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Teacher in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Teacher extends Person {

    private final OfficeTable officeTable;

    /**
     * Constructor for {@code Teacher}
     * @param name Name of the teacher
     * @param phone Phone number of the teacher
     * @param email Email of the teacher
     * @param officeTable Office table number of the teacher
     * @param involvement Involvement of the teacher
     * @param tags Tags associated to the teacher
     * @param gender Gender of the teacher
     */
    public Teacher(Name name, Phone phone, Email email, OfficeTable officeTable, Involvement involvement,
                   Gender gender, Set<Tag> tags) {
        super(name, phone, email, gender, involvement, tags);
        this.officeTable = officeTable;
    }

    /**
     * Constructor for {@code Teacher} from person
     *
     * @param person person to copy the data from
     * @param officeTable office table number of the teacher.
     */
    public Teacher(Person person, OfficeTable officeTable) {
        super(person.getName(), person.getPhone(), person.getEmail(), person.getGender(), person.getInvolvement(),
                person.getTags());
        this.officeTable = officeTable;
    }

    public OfficeTable getOfficeTable() {
        return this.officeTable;
    }

    /**
     * Returns true if both person are teachers, and have the same identity and data fields.
     * This defines a stronger notion of equality between two teachers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Teacher)) {
            return false;
        }

        Teacher otherStudent = (Teacher) other;
        return super.equals(otherStudent)
                && this.getOfficeTable().equals(otherStudent.getOfficeTable());
    }

    @Override
    public String toString() {
        return super.toString() + "; " + String.format("Office table number: %s", getOfficeTable());
    }
}
