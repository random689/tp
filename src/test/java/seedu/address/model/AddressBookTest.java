package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MONITOR;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALI;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.exceptions.DuplicateStudentException;
import seedu.address.model.person.teacher.Teacher;
import seedu.address.model.person.teacher.exceptions.DuplicateTeacherException;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TeacherBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void studentConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getStudentList());
    }

    @Test
    public void teacherConstructor() {
        assertEquals(Collections.emptyList(), addressBook.getTeacherList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        //same name and address = same student
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_MONITOR)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub();
        newData.setStudent(newStudents);

        assertThrows(DuplicateStudentException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateTeachers_throwsDuplicateTeacherException() {
        // Two teachers with the same identity fields
        //same name and table number = same teacher
        Teacher editedAli = new TeacherBuilder(ALI).withTags(VALID_TAG_MONITOR)
                .build();
        List<Teacher> newTeachers = Arrays.asList(ALI, editedAli);
        AddressBookStub newData = new AddressBookStub();
        newData.setTeacher(newTeachers);

        assertThrows(DuplicateTeacherException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasStudent_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasStudent(null));
    }

    @Test
    public void hasTeacher_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasTeacher(null));
    }

    @Test
    public void hasPerson_studentNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addStudent(ALICE);
        assertTrue(addressBook.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        //same name and address = same person
        addressBook.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_MONITOR)
                .build();
        assertTrue(addressBook.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Teacher> teachers = FXCollections.observableArrayList();

        AddressBookStub() {}

        public void setStudent(Collection<Student> students) {
            this.students.setAll(students);
        }

        public void setTeacher(Collection<Teacher> teacher) {
            this.teachers.setAll(teacher);
        }


        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Teacher> getTeacherList() {
            return teachers;
        }
    }

}
