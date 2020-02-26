package framework.cloudinary;

import com.cloudinary.Cloudinary;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.logger.MyLogger;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;


public class CloudinaryRes {
    private static final MyLogger LOGGER = new MyLogger();

    public static CloudPhoto getResultUploadFile(String path, String cloudName, String apiKey, String apiSecret) {
        Map<Object, Object> CONFIG = new HashMap<>();
            CONFIG.put("cloud_name", cloudName);
            CONFIG.put("api_key", apiKey);
            CONFIG.put("api_secret", apiSecret);
        Cloudinary cloudinary = new Cloudinary(CONFIG);
        Map result = null;
        try {
            result = cloudinary.uploader().upload(path, CONFIG);
        } catch (IOException e) {
            LOGGER.error("IOException ", e);
        }
        JSONObject jsonObject = new JSONObject(result);
        StringReader reader = new StringReader(jsonObject.toString());
        CloudPhoto cloudPhoto = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            cloudPhoto = mapper.readValue(reader, CloudPhoto.class);
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        LOGGER.info("upload server  = ", cloudPhoto);
        return cloudPhoto;
    }
}
