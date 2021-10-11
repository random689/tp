package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private final Phone emergencyContact;
    private final FormClass formClass;
    private final Address address;

    /**
     * Constructor for {@code Student}
     * @param name Name of student
     * @param phone Phone number of student
     * @param email Email of student
     * @param gender Gender of student
     * @param involvement Involvement of the student
     * @param address Address of student
     * @param emergencyContact Emergency contact of student
     * @param formClass Form Class of Student
     * @param tags Tags associated to student
     */

    public Student(Name name, Phone phone, Email email, Gender gender, Involvement involvement, Address address,
                   Phone emergencyContact, FormClass formClass, Set<Tag> tags) {
        super(name, phone, email, gender, involvement, tags);
        this.emergencyContact = emergencyContact;
        this.formClass = formClass;
        this.address = address;
    }

    /**
     * Constructor for {@code Student} from person
     *
     * @param person person to copy the data from
     * @param emergencyContact Emergency contact of student
     * @param formClass Form Class of Student
     * @param address Address of student
     */

    public Student(Person person, Phone emergencyContact, FormClass formClass, Address address) {
        super(person.getName(), person.getPhone(), person.getEmail(), person.getGender(), person.getInvolvement(),
                person.getTags());
        this.emergencyContact = emergencyContact;
        this.formClass = formClass;
        this.address = address;
    }

    public Phone getEmergencyContact() {
        return emergencyContact;
    }

    public FormClass getFormClass() {
        return formClass;
    }

    public Address getAddress() {
        return this.address;
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
                && this.getEmergencyContact().equals(otherStudent.getEmergencyContact())
                && this.getFormClass().equals(otherStudent.getFormClass())
                && this.getAddress().equals(otherStudent.getAddress());
    }

    @Override
    public String toString() {
        return super.toString() + "; " + String.format("Emergency contact: %s", getEmergencyContact())
                + String.format("Form class: %s", getFormClass())
                + String.format("Address: %s", getAddress());
    }
}
