package framework.utils.files;

import framework.logger.MyLogger;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class FileToBytes {
    private static MyLogger LOGGER = new MyLogger();

    public static byte[] convertFileContentToBlob(File file) {
        byte[] fileContent = null;
        try {
            fileContent = FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            LOGGER.error("IOException in method convertFileContentToBlob", e);
        }
        return fileContent;
    }

    public static void convertBase64StringToPng(String imageUrl, String imgPath) {
        String base64Image = imageUrl.split(",")[1];
        byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
        try {
            BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageBytes));
            File file = new File(imgPath);
            ImageIO.write(bufferedImage, "png", file);
        } catch (IOException e) {
            LOGGER.error("imageFile not created IOException", e);
        }
    }
}
