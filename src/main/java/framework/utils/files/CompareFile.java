package framework.utils.files;

public enum CompareFile {
    FULL(100.0),
    ZERO(0.0);
    private double title;

    CompareFile(double title) {
        this.title = title;
    }

    public double getTitle() {
        return title;
    }

}
