package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    private Phone emergencyContact;
    private FormClass formClass;
    private Gender gender;

    /**
     * Constructor for {@code Student}
     * @param name Name of student
     * @param phone Phone number of student
     * @param email Email of student
     * @param address Address of student
     * @param involvement Involvement of the student
     * @param tags Tags associated to student
     * @param emergencyContact Emergency contact of student
     * @param formClass        Form Class of Student
     * @param gender           Gender of student
     */

    public Student(Name name, Phone phone, Email email, Address address, Involvement involvement, Set<Tag> tags,
                   Phone emergencyContact, FormClass formClass, Gender gender) {
        super(name, phone, email, address, involvement, tags);
        this.emergencyContact = emergencyContact;
        this.formClass = formClass;
        this.gender = gender;
    }

    public Student(Person person, Phone emergencyContact, FormClass formClass) {
        super(person.getName(), person.getPhone(), person.getEmail(), person.getAddress(), person.getInvolvement(),
                person.getTags());
        this.emergencyContact = emergencyContact;
        this.formClass = formClass;
    }

    public Phone getEmergencyContact() {
        return emergencyContact;
    }

    public FormClass getFormClass() {
        return formClass;
    }

    public Gender getGender() {
        return this.gender;
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
                && this.getFormClass().equals(otherStudent.getFormClass());
    }

    @Override
    public String toString() {
        return super.toString() + "; " + String.format("Emergency contact: %s", getEmergencyContact());
    }
}
