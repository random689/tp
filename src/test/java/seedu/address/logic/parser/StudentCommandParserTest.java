package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.StudentCommand;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.StudentBuilder;

public class StudentCommandParserTest {
    private StudentCommandParser parser = new StudentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedPerson = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB , new StudentCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, new StudentCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, new StudentCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, new StudentCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_AMY
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, new StudentCommand(expectedPerson));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + EMERGENCY_CONTACT_DESC_BOB,
                new StudentCommand(expectedStudentMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
        assertParseSuccess(parser,
                NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                        + INVOLVEMENT_DESC_AMY+ EMERGENCY_CONTACT_DESC_AMY,
                new StudentCommand(expectedStudent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser,
                VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB
                        + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB,
                expectedMessage);

        // missing emergency contact prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + INVOLVEMENT_DESC_BOB + VALID_EMERGENCY_CONTACT_BOB,
                expectedMessage);

        // missing emergency contact prefix
        assertParseFailure(parser,
                NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                        + VALID_INVOLVEMENT_BOB + VALID_EMERGENCY_CONTACT_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser,
                VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB
                        + VALID_INVOLVEMENT_BOB + EMERGENCY_CONTACT_DESC_BOB,
                expectedMessage);


    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB+ TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
                + INVOLVEMENT_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVOLVEMENT_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // invalid involvement
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
                + INVALID_INVOLVEMENT_DESC+ TAG_DESC_HUSBAND + TAG_DESC_FRIEND
                + EMERGENCY_CONTACT_DESC_BOB, Involvement.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + INVOLVEMENT_DESC_BOB + EMERGENCY_CONTACT_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVOLVEMENT_DESC_BOB + TAG_DESC_HUSBAND
                        + TAG_DESC_FRIEND + EMERGENCY_CONTACT_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentCommand.MESSAGE_USAGE));
    }
}
