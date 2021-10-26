package seedu.address.model.person.teacher;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.student.StudentInvolvementContainsKeywordsPredicate;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeacherBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TeacherContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TeacherInvolvementContainsKeywordsPredicate firstPredicate =
                new TeacherInvolvementContainsKeywordsPredicate(firstPredicateKeywordList);
        TeacherInvolvementContainsKeywordsPredicate secondPredicate =
                new TeacherInvolvementContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
       TeacherInvolvementContainsKeywordsPredicate firstPredicateCopy =
                new TeacherInvolvementContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_involvementTag() {
        List<String> predicateKeywordList = Arrays.asList("first", "t/second");
        TeacherInvolvementContainsKeywordsPredicate predicate =
                new TeacherInvolvementContainsKeywordsPredicate(predicateKeywordList);
        assertFalse(predicate.test(new TeacherBuilder().withTags("second").build()));

        assertFalse(predicate.test(new TeacherBuilder().withInvolvement("first").build()));

        assertTrue(predicate.test(new TeacherBuilder().withInvolvement("first").withTags("second").build()));

        List<String> secondPredicateKeywordList = Arrays.asList("t/first", "second");

        TeacherInvolvementContainsKeywordsPredicate predicate2 =
                new TeacherInvolvementContainsKeywordsPredicate(secondPredicateKeywordList);

        assertTrue(predicate2.test(new TeacherBuilder().withTags("first", "second").build()));

        assertTrue(predicate2.test(new TeacherBuilder().withTags("first").build()));

        assertFalse(predicate2.test(new TeacherBuilder().withInvolvement("first").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TeacherInvolvementContainsKeywordsPredicate predicate =
                new TeacherInvolvementContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TeacherBuilder().withName("Ali").build()));

        // Non-matching keyword
        predicate = new TeacherInvolvementContainsKeywordsPredicate(Arrays.asList("Car"));
        assertFalse(predicate.test(new TeacherBuilder().withName("Ali Ben").build()));

        // Keywords match phone, email and offceTable, but does not match name
        predicate = new TeacherInvolvementContainsKeywordsPredicate(
                        Arrays.asList("12345", "ali@email.com", "12"));
        assertFalse(predicate.test(new TeacherBuilder().withName("Ali").withPhone("12345")
                .withEmail("ali@email.com").withOfficeTable("12").build()));
    }
}
