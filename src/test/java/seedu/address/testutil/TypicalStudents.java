package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMERGENCY_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FORM_CLASS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FORM_CLASS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INVOLVEMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder()
            .withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withEmergencyContact("95614132")
            .withInvolvement("Math class")
            .withFormClass("4N1")
            .withGender("F")
            .build();
    public static final Student BENSON = new StudentBuilder()
            .withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withEmergencyContact("91939100")
            .withInvolvement("Math class")
            .withFormClass("3E3")
            .withGender("M")
            .build();
    public static final Student CARL = new StudentBuilder()
            .withName("Carl Kurz")
            .withPhone("95352563")
            .withEmail("heinz@example.com")
            .withAddress("wall street")
            .withEmergencyContact("48190514")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("M")
            .build();
    public static final Student DANIEL = new StudentBuilder()
            .withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withAddress("10th street")
            .withTags("friends")
            .withEmergencyContact("58141834")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("M")
            .build();
    public static final Student ELLE = new StudentBuilder()
            .withName("Elle Meyer")
            .withPhone("9482224")
            .withEmail("werner@example.com")
            .withAddress("michegan ave")
            .withEmergencyContact("92329700")
            .withInvolvement("Form class")
            .withFormClass("4E2")
            .withGender("F")
            .build();
    public static final Student FIONA = new StudentBuilder()
            .withName("Fiona Kunz")
            .withPhone("9482427")
            .withEmail("lydia@example.com")
            .withAddress("little tokyo")
            .withEmergencyContact("580947918074907019749087")
            .withInvolvement("English class")
            .withFormClass("1T1")
            .withGender("F")
            .build();
    public static final Student GEORGE = new StudentBuilder()
            .withName("George Best")
            .withPhone("9482442")
            .withEmail("anna@example.com")
            .withAddress("4th street")
            .withEmergencyContact("84041748901")
            .withInvolvement("English class")
            .withFormClass("1T1")
            .withGender("M")
            .build();

    // Manually added
    public static final Student HOON = new StudentBuilder()
            .withName("Hoon Meier")
            .withPhone("8482424")
            .withEmail("stefan@example.com")
            .withAddress("little india")
            .withEmergencyContact("91939100")
            .withInvolvement("English rep")
            .withFormClass("3E2")
            .withGender("N")
            .build();

    public static final Student IDA = new StudentBuilder()
            .withName("Ida Mueller")
            .withPhone("8482131")
            .withEmail("hans@example.com")
            .withAddress("chicago ave")
            .withEmergencyContact("91939100")
            .withInvolvement("Bio rep")
            .withFormClass("2T1")
            .withGender("F")
            .build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder()
            .withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAddress(VALID_ADDRESS_AMY)
            .withInvolvement(VALID_INVOLVEMENT_AMY)
            .withTags(VALID_TAG_FRIEND)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_AMY)
            .withFormClass(VALID_FORM_CLASS_AMY)
            .withGender(VALID_GENDER_AMY)
            .build();

    public static final Student BOB = new StudentBuilder()
            .withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withInvolvement(VALID_INVOLVEMENT_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withEmergencyContact(VALID_EMERGENCY_CONTACT_BOB)
            .withFormClass(VALID_FORM_CLASS_BOB)
            .withGender(VALID_GENDER_BOB)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addPerson(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
