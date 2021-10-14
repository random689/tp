package seedu.address.logic.commands.teacher;


import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OFFICE_TABLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.descriptors.EditTeacherDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.teacher.OfficeTable;
import seedu.address.model.person.teacher.Teacher;

/**
 * Edits the details of an existing teacher in the address book.
 */
public class EditTeacherCommand extends EditCommand {

    public static final String COMMAND_WORD = "editTeacher";

    public static final String TARGET = "teacher";
    public static final String MESSAGE_EDIT_TEACHER_SUCCESS = "Edited teacher: %1$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + String.format(EditCommand.MESSAGE_USAGE, TARGET)
            + "[" + PREFIX_OFFICE_TABLE + "OFFICE TABLE NUMBER] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + EditCommand.EXAMPLE_USAGE;
    public static final String MESSAGE_DUPLICATE_TEACHER = String.format(EditCommand.MESSAGE_DUPLICATE_PERSON, TARGET);


    private final Index index;
    private final EditTeacherDescriptor editTeacherDescriptor;

    /**
     * @param index of the teacher in the filtered person list to edit
     * @param editTeacherDescriptor details to edit the teacher with
     */
    public EditTeacherCommand(Index index, EditTeacherDescriptor editTeacherDescriptor) {
        requireNonNull(index);
        requireNonNull(editTeacherDescriptor);

        this.index = index;
        this.editTeacherDescriptor = editTeacherDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!(personToEdit instanceof Teacher)) {
            throw new CommandException("The person you are trying to edit is not a teacher!");
        }
        Teacher teacherToEdit = (Teacher) personToEdit;
        Teacher editedTeacher = createdEditedTeacher(teacherToEdit, editTeacherDescriptor);

        if (!teacherToEdit.isSamePerson(editedTeacher) && model.hasPerson(editedTeacher)) {
            throw new CommandException(MESSAGE_DUPLICATE_TEACHER);
        }

        model.setPerson(teacherToEdit, editedTeacher);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_TEACHER_SUCCESS, editedTeacher));
    }

    /**
     * Creates and returns a {@code Teacher} with the details of {@code teacherToEdit}
     * edited with {@code editTeacherDescriptor}.
     */
    private static Teacher createdEditedTeacher(Teacher teacherToEdit, EditTeacherDescriptor editTeacherDescriptor) {
        assert teacherToEdit != null;

        Person person = EditCommand.createEditedPerson(teacherToEdit, editTeacherDescriptor);
        OfficeTable updatedOfficeTable = editTeacherDescriptor.getOfficeTable().orElse(teacherToEdit.getOfficeTable());
        return new Teacher(person, updatedOfficeTable);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTeacherCommand)) {
            return false;
        }

        // state check
        EditTeacherCommand e = (EditTeacherCommand) other;
        return index.equals(e.index) && editTeacherDescriptor.equals(e.editTeacherDescriptor);
    }
}
