package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for a help page
 */
public class MeetingWindow extends UiPart<Stage> {

    public static final String DISPLAY_MESSAGE = "Hi fill me up gary tq, change a new picture also if can, "
            + "colour also can, add command also can like help";

    private static final Logger logger = LogsCenter.getLogger(MeetingWindow.class);
    private static final String FXML = "MeetingWindow.fxml";

    @FXML
    private Label displayMessage;

    /**
     * Creates a new MeetingWindow.
     *
     * @param root Stage to use as the root of the MeetingWindow.
     */
    public MeetingWindow(Stage root) {
        super(FXML, root);
        displayMessage.setText(DISPLAY_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public MeetingWindow() {
        this(new Stage());
    }

    /**
     * Shows the meeting window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing help page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the meeting window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meeting window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meeting window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
