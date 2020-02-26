package application.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import framework.logger.MyLogger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.List;

public class ParserJSON {

    private static final MyLogger LOGGER = new MyLogger();

    public static <T> T parseJSONToObject(String json, Class<T> myClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, myClass);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        throw new IllegalArgumentException("object not created");
    }

    public static <T> ArrayList<T> parseJSONToListObject(String json, Class<T> myClass) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Class.forName(myClass.getName())));
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        } catch (ClassNotFoundException e) {
            LOGGER.error("ClassNotFoundException", e);
        }
        return new ArrayList<>();
    }
}
