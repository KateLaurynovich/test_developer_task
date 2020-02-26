package application.requestTestRail.baseRequest;

import java.util.ArrayList;

public class Params {
    public static Params create() {
        return new Params();
    }

    private final ArrayList<Object> params;

    private Params() {
        params = new ArrayList<>();
    }

    public Params add(Integer value) {
        params.add(value);
        return this;
    }

    public Params add(Object value) {
        params.add(value);
        return this;
    }

    public String build() {
        if (params.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        params.stream().forEach(value -> {
            result.append('/').append(value);
        });
        return result.toString();
    }
}
