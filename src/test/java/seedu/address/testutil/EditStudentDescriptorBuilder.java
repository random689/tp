package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.descriptors.EditStudentDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Involvement;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setAddress(student.getAddress());
        descriptor.setGender(student.getGender());
        descriptor.setInvolvement(student.getInvolvement());
        descriptor.setTags(student.getTags());
        descriptor.setEmergencyContact(student.getEmergencyContact());
        descriptor.setFormClass(student.getFormClass());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code emergencyContact} into a {@code Phone} of the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withEmergencyContact(String emergencyContact) {
        descriptor.setEmergencyContact(new Phone(emergencyContact));
        return this;
    }

    /**
     * Sets the {@code Involvement} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withInvolvement(String involvement) {
        descriptor.setInvolvement(new Involvement(involvement));
        return this;
    }

    /**
     * Sets the {@code Form Class} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withFormClass(String formClass) {
        descriptor.setFormClass(new FormClass(formClass));
        return this;
    }

    /**
     * Sets the {@code Gender} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withGender(String gender) {
        descriptor.setGender(new Gender(gender));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}