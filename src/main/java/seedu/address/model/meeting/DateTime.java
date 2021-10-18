package seedu.address.model.meeting;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Meeting's datetime in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateTime(String)}
 */
public class DateTime implements Comparable<DateTime>{

    public static final String MESSAGE_CONSTRAINTS =
            "Meeting datetime should be of the format YYYY-MM-DD HH:mm";
    public static final String PRESENT_CONSTRAINT =
            "Meeting datetime must not be in the past. The current datetime is: %s";
    public static final String VALIDATION_REGEX = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";

    public final String value;
    private final LocalDateTime dateTime;

    /**
     * Constructs a {@code DateTime}.
     *
     * @param input A valid datetime.
     */
    public DateTime(String input) {
        requireNonNull(input);
        checkArgument(isValidDateTime(input), MESSAGE_CONSTRAINTS);
        dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
        checkArgument(isPresentDateTime(dateTime), PRESENT_CONSTRAINT);
        value = input;
    }

    /**
     *
     * @return datetime in user-friendly format.
     */
    public String getUserFormat() {
        return dateTime.format(DateTimeFormatter.ofPattern("EEE dd MMM yyyy, hh:mm a"));
    }

    /**
     * Returns true if a given string is a valid datetime.
     */
    public static boolean isValidDateTime(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            try {
                LocalDateTime.parse(test, DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm"));
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns true if a given datetime is not in the past.
     */
    private static boolean isPresentDateTime(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTime // instanceof handles nulls
                && value.equals(((DateTime) other).value))
                && dateTime.equals(((DateTime) other).dateTime); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(DateTime other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
