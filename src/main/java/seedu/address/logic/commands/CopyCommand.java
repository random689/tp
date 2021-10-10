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
    public static final String MESSAGE_NOT_COPIED = "At least one field to copy must be provided. Use the c/ prefix.";
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
        if (copyCommandDescriptor.getField().equals(CopyCommandDescriptor.Field.PHONE)) {
            return getPhoneContent(personList);
        }
        // should not reach here
        return "";
    }

    public String getPhoneContent(List<Person> personList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < personList.size(); i++) {
            if (i == personList.size() - 1) {
                sb.append(personList.get(i).getPhone());
            } else {
                sb.append(personList.get(i).getPhone() + " ");
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
