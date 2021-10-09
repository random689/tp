package seedu.address.logic.commands;

import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.editDescriptors.EditStudentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class EditStudentCommand extends EditCommand {

    private final Index index;
    public final EditStudentDescriptor editStudentDescriptor;
    /**
     * @param index of the person in the filtered person list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditStudentCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        requireNonNull(editStudentDescriptor);
        requireNonNull(index);
        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createdEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;
        Person person = EditCommand.createEditedPerson(studentToEdit, editStudentDescriptor);
        Phone updatedEmergencyContact =
                editStudentDescriptor.getEmergencyContact().orElse(studentToEdit.getEmergencyContact());
        FormClass updatedFormClass = editStudentDescriptor.getFormClass().orElse(studentToEdit.getFormClass());
        return new Student(person, updatedEmergencyContact, updatedFormClass);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Student personToEdit = (Student) lastShownList.get(index.getZeroBased());
        Student editedStudent = createdEditedStudent((Student) personToEdit, editStudentDescriptor);

        if (!personToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedStudent));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand e = (EditStudentCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

}
