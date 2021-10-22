package seedu.address.logic.commands.student;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;

public class MassDeleteStudentCommand extends Command {

    public static final String COMMAND_WORD = "massDeleteStudent";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted %d students";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by their involvement/tags.\n"
            + "Parameters: [INVOLVEMENT FILTER] [t/] [TAG FILTER]...\n"
            + "Example: " + COMMAND_WORD + " class t/chairman";

    private final List<String> targets;

    private final List<String> involvementList = new ArrayList<>();

    private final List<String> tagList = new ArrayList<>();

    private final List<Student> studentsToEliminate = new ArrayList<>();

    public MassDeleteStudentCommand(List<String> target) {
        this.targets = target;
    }

    private void processInput(List<String> targets) {
        boolean firstArray = true;
        for (int i = 0; i < targets.size(); i++) {
            if (targets.get(i).startsWith("t/")) {
                String toLStrip = targets.get(i);
                toLStrip = toLStrip.substring(2);
                targets.set(i, toLStrip);
                firstArray = false;
            }

            if (firstArray) {
                involvementList.add(targets.get(i));
            } else {
                tagList.add(targets.get(i));
            }
        }
    }

    private boolean checkInvolvement(String involvement) {
        if (involvementList.size() < 1) {
            return true;
        }
        boolean containsAllInvolvement = true;

        for (String s : involvementList) {
            String lowerCaseInvolvement = involvement.toLowerCase();
            if (!lowerCaseInvolvement.contains(s.toLowerCase())) {
                containsAllInvolvement = false;
            }
        }

        return containsAllInvolvement;
    }

    private boolean checkTags(Set<Tag> tags) {
        if (tagList.size() < 1) {
            return true;
        }

        int tagCounter = 0;

        for (String value : tagList) {
            for (Tag s : tags) {
                String tagNameLowerCase = s.tagName.toLowerCase();
                String keywordCurrent = value.toLowerCase();
                if (keywordCurrent.contains(tagNameLowerCase)) {
                    tagCounter++;
                }
            }
        }

        return tagCounter == tagList.size();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        processInput(targets);
        List<Student> lastShownList = model.getFilteredStudentList();

        for (Student studentInList : lastShownList) {
            String studentInvolvement = studentInList.getInvolvement().value;
            Set<Tag> studentTag = studentInList.getTags();

            if (checkInvolvement(studentInvolvement) && checkTags(studentTag)) {
                studentsToEliminate.add(studentInList);
            }
        }
        model.massDeleteStudent(studentsToEliminate);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentsToEliminate.size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MassDeleteStudentCommand // instanceof handles nulls
                && targets.equals(((MassDeleteStudentCommand) other).targets)); // state check
    }


}
