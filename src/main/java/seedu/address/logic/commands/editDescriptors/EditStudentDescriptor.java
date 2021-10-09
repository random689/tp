package seedu.address.logic.commands.editDescriptors;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Phone;

import java.util.Optional;

public class EditStudentDescriptor extends EditPersonDescriptor {
    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    private Phone emergencyContact;
    private FormClass formClass;

    public EditStudentDescriptor() {

    }

    public EditStudentDescriptor(EditStudentDescriptor toCopy) {
        super(toCopy);
        setEmergencyContact(toCopy.emergencyContact);
        setFormClass(toCopy.formClass);
    }

    public void setEmergencyContact(Phone phone) {
        this.emergencyContact = phone;
    }

    public Optional<Phone> getEmergencyContact() {
        return Optional.ofNullable(emergencyContact);
    }

    public void setFormClass(FormClass formClass) {
        this.formClass = formClass;
    }

    public Optional<FormClass> getFormClass() {
        return Optional.ofNullable(formClass);
    }


    @Override
    public boolean isAnyFieldEdited() {
        return super.isAnyFieldEdited() || CollectionUtil.isAnyNonNull(emergencyContact, formClass);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentDescriptor)) {
            return false;
        }

        // state check
        EditStudentDescriptor e = (EditStudentDescriptor) other;

        return super.equals(e)
                && emergencyContact.equals(e.emergencyContact)
                && formClass.equals(e.formClass);
    }

}
