package services;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class ImageService {
    public static String createImage (String img) throws Exception {

            BufferedImage image = new BufferedImage(128, 128, BufferedImage.TYPE_BYTE_GRAY);

            int x = 0;
            int y = 0;

            for (char c : img.toCharArray()) {

                if (c == '\n') {
                    y++;
                    x = 0;

                    if (y >= 128) {
                        break;
                    }
                    continue;
                }

                if (x >= 128) {
                    continue;
                }

                int grayValue = ConvertService.convertChar(c);

                int rgb = new Color(grayValue, grayValue, grayValue).getRGB();
                image.setRGB(x, y, rgb);
                x++;
            }
            return ConvertService.convertBase64(image);
        }

    };

