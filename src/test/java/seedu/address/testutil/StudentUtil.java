package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORM_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INVOLVEMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.StudentCommand;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getStudentCommand(Student person) {
        return StudentCommand.COMMAND_WORD + " " + getPersonDetails(person);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Student person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_ADDRESS + person.getAddress().value + " ");
        sb.append(PREFIX_INVOLVEMENT + person.getInvolvement().involvement + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        sb.append(PREFIX_EMERGENCY_CONTACT + person.getEmergencyContact().value + " ");
        sb.append(PREFIX_FORM_CLASS + person.getFormClass().formClass + " ");
        sb.append(PREFIX_GENDER + person.getGender().gender + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
        descriptor.getInvolvement().ifPresent(involvement -> sb.append(PREFIX_INVOLVEMENT)
                .append(involvement.involvement).append(" "));
        descriptor.getEmergencyContact().ifPresent(emergencyContact ->
                sb.append(PREFIX_EMERGENCY_CONTACT).append(emergencyContact.value).append(" "));
        descriptor.getFormClass().ifPresent(formClass ->
                sb.append(PREFIX_FORM_CLASS).append(formClass.formClass).append(" "));
        descriptor.getGender().ifPresent(genders ->
                sb.append(PREFIX_GENDER).append(genders.gender).append(" "));
        // tags should be the last, else bug
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}