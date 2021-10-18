package seedu.address.model.meeting;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.person.Person;

public class Meeting implements Comparable<Meeting>{

    private final DateTime dateTime;
    private final Description description;
    private Person person;

    /**
     * Constructs a {@code Meeting}.
     *
     * @param dateTime datetime of the meeting.
     * @param description details of the meeting.
     * @param person person to meet with.
     */
    public Meeting(DateTime dateTime, Description description, Person person) {
        requireAllNonNull(dateTime, description, person);

        this.dateTime = dateTime;
        this.description = description;
        this.person = person;
    }

    /**
     * Returns true if there is a datetime conflict with the other meeting, false otherwise.
     *
     * @param other The other meeting.
     */
    public boolean hasConflictWith(Meeting other) {
        return this.dateTime.equals(other.dateTime);
    }

    @Override
    public String toString() {
        return String.format("Datetime: %s\nDescription: %s\nPerson: %s",
                dateTime.getUserFormat(), description.toString(), person.toString());
    }

    /**
     * Returns true if both meetings have the same datetime and description, and belong to the same person.
     * This defines a stronger notion of equality between two meetings.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;
        return this.dateTime.equals(otherMeeting.dateTime)
            && this.person.equals(otherMeeting.person)
            && this.description.equals(otherMeeting.description);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(dateTime, description, person);
    }

    @Override
    public int compareTo(Meeting other) {
        return this.dateTime.compareTo(other.dateTime);
    }
}
