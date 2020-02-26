package application.pageObject;

public enum InfoCategory {
    PROJECT_NAME("Project"),
    TEST_NAME("Test name"),
    TEST_METHOD("Test method"),
    ENV("Environment");

    private String title;

    InfoCategory(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
