package application.enums;

public enum TableColumn {
    DURATION("Latest test duration (H:m:s.S)"),
    METHOD("Test method"),
    TEST("Test name"),
    START_TIME("Latest test start time"),
    END_TIME("Latest test end time"),
    RESULT("Latest test result");

    private String title;

    TableColumn(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
