package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's form class in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidFormClass(String)}
 */
public class FormClass {

    public static final String MESSAGE_CONSTRAINTS =
            "Form class should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of FormClass must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String formClass;

    /**
     * Constructs an {@code FormClass}.
     *
     * @param formClass A valid formClass.
     */
    public FormClass(String formClass) {
        requireNonNull(formClass);
        checkArgument(isValidFormClass(formClass), MESSAGE_CONSTRAINTS);
        this.formClass = formClass;
    }

    /**
     * Returns true if a given string is a valid form class.
     */
    public static boolean isValidFormClass(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return formClass;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FormClass // instanceof handles nulls
                && formClass.equals(((FormClass) other).formClass)); // state check
    }

    @Override
    public int hashCode() {
        return formClass.hashCode();
    }
}
