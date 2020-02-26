package application.requestTestRail.run;

public enum StatusEnum {
    PASSED(1),
    FAILED(5);

    private int title;

    StatusEnum(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }
}
