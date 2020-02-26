package application.utils;

import java.util.HashMap;

public class JsonGenerate {

    public static JsonGenerate create() {
        return new JsonGenerate();
    }

    private final HashMap<String, Object> json;

    private JsonGenerate() {
        json = new HashMap<>();
    }

    public JsonGenerate add(String key, String value) {
        json.put(key, "\""+value+"\"");
        return this;
    }

    public JsonGenerate addArr(String key, String value) {
        json.put(key, "["+value+"]");
        return this;
    }
    public JsonGenerate add(String key, Integer value) {
        json.put(key, value);
        return this;
    }

    public JsonGenerate add(String key, Boolean value) {
        json.put(key, value);
        return this;
    }
    public String build() {
        if (json.isEmpty()) return "";
        final StringBuilder result = new StringBuilder();
        result.append("{\n");
        json.keySet().stream().forEach(key -> {
            result.append("\t\"").append(key).append("\": ").append(json.get(key)).append(",\n");
        });
        result.deleteCharAt(result.length()-2).append("}");
        return result.toString();
    }
}
