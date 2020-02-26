package application.requestAPI.baseRequestApi;

public enum MethodsApi {
    TEST_GET_JSON("/test/get/json"),
    TEST_PUT_ATTACHMENT("/test/put/attachment"),
    TEST_PUT("/test/put"),
    GET_TOKEN("/token/get");

    private String title;

    MethodsApi(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
