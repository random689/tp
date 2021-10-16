package seedu.address.model;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import java.util.Collections;

import seedu.address.logic.commands.student.AddStudentCommand;
import seedu.address.logic.commands.student.DeleteStudentCommand;
import seedu.address.logic.commands.student.FilterStudentCommand;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;


public class TypicalModels {

    public static final StudentInvolvementContainsKeywordsPredicate FIRST_PREDICATE =
            new StudentInvolvementContainsKeywordsPredicate(Collections.singletonList("first"));

    public static final ModelManager ADD_ALICE =
            new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).build();

    public static final ModelManager ADD_MULTIPLE_PEOPLE =
            new ModelManagerBuilder().with(new AddStudentCommand(ALICE)).with(new AddStudentCommand(BOB)).build();

    public static final ModelManager DELETING_ALICE =
            new ModelManagerBuilder()
                    .with(new AddStudentCommand(ALICE))
                    .with(new AddStudentCommand(BOB))
                    .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                    .build();

    public static final ModelManager FILTER_BY_TAG =
            new ModelManagerBuilder()
                    .with(new AddStudentCommand(ALICE))
                    .with(new AddStudentCommand(BOB))
                    .with(new DeleteStudentCommand(INDEX_FIRST_STUDENT))
                    .with(new FilterStudentCommand(FIRST_PREDICATE))
                    .build();


}
