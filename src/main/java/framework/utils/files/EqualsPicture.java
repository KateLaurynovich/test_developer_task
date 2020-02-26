package framework.utils.files;

import framework.logger.MyLogger;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class EqualsPicture {
    private static MyLogger LOGGER = new MyLogger();

    public static double compareImages(String path1, String path2) {
        double percentage = 0;
        try {
            BufferedImage imgA = ImageIO.read(new File(path1));
            BufferedImage imgB = ImageIO.read(new File(path2));
            if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
                int count = 0;
                int all = 0;
                int width = imgA.getWidth();
                int height = imgA.getHeight();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        all++;
                        if (imgA.getRGB(x, y) == imgB.getRGB(x, y)) {
                            count = count + 1;
                        }
                    }
                }
                percentage = count * 100 / all;
                LOGGER.info("\ncount = " + count + "\n all = " + all + "\n percentage = " + percentage);
            }
        } catch (Exception e) {
            LOGGER.error("Failed to compare image files ...", e);
        }
        return percentage;
    }
}
