package services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ConvertService {
    public static int convertChar(char c) {
            int ascii = (int) c;
        if (ascii >= 0 && ascii <= 32) {
            return 0;
        }
        else if (ascii >= 48 && ascii <= 57) {
            return 53;
        }
        else if (ascii >= 65 && ascii <= 90) {
            return 77;
        }
         else if (ascii >= 97 && ascii <= 122) {
            return 109;
        }
        else {
            return ascii;
        }
    };
    public static String convertBase64(BufferedImage image) throws IOException {
        String format = "png";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, format, outputStream);
            return Base64.getEncoder().encodeToString(outputStream.toByteArray());
        } finally {
            outputStream.close();
        }
        }

    }


