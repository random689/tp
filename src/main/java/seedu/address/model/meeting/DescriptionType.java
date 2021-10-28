package seedu.address.model.meeting;

public enum DescriptionType {
    TITLE() {
        @Override
        public String toString() {
            return "Title";
        }
    },
    VENUE() {
        @Override
        public String toString() {
            return "Venue";
        }
    };
}
