package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMERGENCY_CONTACT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FORM_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.descriptors.EditStudentDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.FormClass;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Student;

/**
 * Edits the fields of an existing student in the New Address Book.
 */
public class EditStudentCommand extends EditCommand {
    public static final String TARGET = "student";
    public static final String COMMAND_WORD = "editStudent";
    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = String.format(EditCommand.MESSAGE_DUPLICATE_PERSON, TARGET);
    public static final String MESSAGE_USAGE = COMMAND_WORD + String.format(EditCommand.MESSAGE_USAGE, TARGET)
        + "[" + PREFIX_ADDRESS + "ADDRESS] "
        + "[" + PREFIX_FORM_CLASS + "FORM CLASS] "
        + "[" + PREFIX_EMERGENCY_CONTACT + "EMERGENCY CONTACT] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + EditCommand.EXAMPLE_USAGE;

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered person list to edit
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
        Address updatedAddress = editStudentDescriptor.getAddress().orElse(studentToEdit.getAddress());
        MedicalHistory updatedMedicalHistory = studentToEdit.getMedicalHistory(); //edit command does not edit medical
        return new Student(person, updatedEmergencyContact, updatedFormClass, updatedAddress, updatedMedicalHistory);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!(personToEdit instanceof Student)) {
            throw new CommandException("The person you are trying to edit is not a student!");
        }
        Student studentToEdit = (Student) personToEdit;
        Student editedStudent = createdEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSamePerson(editedStudent) && model.hasPerson(editedStudent)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        model.setPerson(studentToEdit, editedStudent);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
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