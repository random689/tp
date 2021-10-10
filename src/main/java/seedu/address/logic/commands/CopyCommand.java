package seedu.address.logic.commands;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import seedu.address.logic.commands.descriptors.CopyCommandDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class CopyCommand extends Command {

    CopyCommandDescriptor copyCommandDescriptor;

    public static final String COMMAND_WORD = "copy";
    public static final String MESSAGE_SUCCESS = "Fields have been copied to clipboard!";
    public static final String MESSAGE_NOT_VALID_FIELD = "Field specified is not a valid field! Only the 'phone' "
            + "field is supported.";
    public static final String NO_FIELD_PROVIDED = "At least one field to copy must be provided. Use the c/ prefix.";
    public static final String MESSAGE_USAGE = "Just get good lol";

    public CopyCommand(CopyCommandDescriptor copyCommandDescriptor) {
        this.copyCommandDescriptor = copyCommandDescriptor;
    }


    public void copyToClipBoard(List<Person> personList) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(getCopyContent(personList));
        clipboard.setContent(url);
    }

    public String getCopyContent(List<Person> personList) {
        // TODO: convert to switch statement soon
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.PHONE)) {
            return getPhoneContent(personList);
        }
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.EMAIL)) {
            return getEmailContent(personList);
        }
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.NAME)) {
            return getNameContent(personList);
        }
        // should not reach here, invalid fields should have been filtered out while parsing
        return "";
    }

    public String getPhoneContent(List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                // for phones, we put commas in case the user wants to paste it directly into whatsapp or some other
                // phone directory
                sb.append(personList.get(i).getPhone() + " ");
            }
        }
        return sb.toString();
    }

    public String getEmailContent(List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getEmail());
            } else {
                // for emails, we put commas in case the user wants to paste it directly into gmail
                sb.append(personList.get(i).getEmail() + ",");
            }
        }
        return sb.toString();
    }

    public String getNameContent(List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getName());
            } else {
                // for names, we put commas, else the spacing between the first and last names might be confused with
                // another name
                sb.append(personList.get(i).getName() + ", ");
            }
        }
        return sb.toString();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        copyToClipBoard(lastShownList);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CopyCommand // instanceof handles nulls
                && copyCommandDescriptor.equals(((CopyCommand) other).copyCommandDescriptor)); // state check
    }
}
