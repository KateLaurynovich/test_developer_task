package application.requestAPI.baseRequestApi;

import java.util.HashMap;

public class ParamsApi {
    public static ParamsApi create() {
        return new ParamsApi();
    }

    private final HashMap<String, Object> params;

    private ParamsApi() {
        params = new HashMap<>();
    }

    public ParamsApi add(String key, String value) {
        params.put(key, value);
        return this;
    }

    public ParamsApi add(String key, int value) {
        params.put(key, value);
        return this;
    }

    public String build() {
        if (params.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        params.keySet().stream().forEach(key -> {
            result.append(key).append('=').append(params.get(key)).append('&');
        });
        return "?" + result.toString();
    }
}
