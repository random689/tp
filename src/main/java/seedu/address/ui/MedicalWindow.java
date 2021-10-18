package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;

/**
 * Controller for a Medical page
 */
public class MedicalWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(MedicalWindow.class);
    public static final String DISPLAY_MESSAGE = "Alex \nADHD, break leg, need wheelchair, asthma";
    private static final String FXML = "MedicalWindow.fxml";

    @FXML
    private Label displayMessage;

    /**
     * Creates a new MedicalWindow.
     *
     * @param root Stage to use as the root of the MedicalWindow.
     */
    public MedicalWindow(Stage root) {
        super(FXML, root);
        displayMessage.setText(DISPLAY_MESSAGE);
    }

    /**
     * Creates a new MedicalWindow.
     */
    public MedicalWindow() {
        this(new Stage());
    }

    /**
     * Shows the medical window.
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
        logger.fine("Showing medical history of the student.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

}
