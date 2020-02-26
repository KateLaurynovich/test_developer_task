package application.requestTestRail.run;

public enum RunMethods {
    ADD_RUN("add_run"),
    GET_RUN("get_run"),
    DELETE_RUN("delete_run"),
    ADD_RESULT_FOR_CASE("add_result_for_case"),
    GET_RESULT_FOR_CASE("get_results_for_case");

    private String title;

    RunMethods(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
