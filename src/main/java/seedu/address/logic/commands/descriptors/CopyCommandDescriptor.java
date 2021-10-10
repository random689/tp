package seedu.address.logic.commands.descriptors;


import static java.util.Objects.requireNonNull;


public class CopyCommandDescriptor {

    public enum Field {

        PHONE("phone"), EMAIL("email"), NAME("name"), INVALID("invalid");

        private String field;

        Field(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return field;
        }
    }

    private Field fieldToCopy;

    public CopyCommandDescriptor(String fieldToCopy) {
        // TODO: change to switch statement soon
        requireNonNull(fieldToCopy);
        if (fieldToCopy.equals("phone")) {
            this.fieldToCopy = Field.PHONE;
        } else if (fieldToCopy.equals("email")) {
            this.fieldToCopy = Field.EMAIL;
        } else if (fieldToCopy.equals("name")) {
            this.fieldToCopy = Field.NAME;
        }
        else {
            this.fieldToCopy = Field.INVALID;
        }
    }

    public boolean hasValidField() {
        return !fieldToCopy.equals(Field.INVALID);
    }

    @Override
    public String toString() {
        return fieldToCopy.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommandDescriptor // instanceof handles nulls
                && fieldToCopy.equals(((CopyCommandDescriptor) other).fieldToCopy)); // state check
    }

    public Field getField() {
        return this.fieldToCopy;
    }

    @Override
    public int hashCode() {
        return fieldToCopy.hashCode();
    }
}
