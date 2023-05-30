package renderer;

import org.junit.jupiter.api.Test;

import java.awt.*;

class ImageWriterTest {

    /**
     * test for WriteToImage
     */
    @Test
    void testWriteToImage() {
        int nX = 800;
        int nY = 500;

        ImageWriter imageWriter = new ImageWriter("yellow", nX, nY);
        Color yellowColor = new Color(255, 255, 0); // yellow Color
        Color redColor = new Color(255, 0, 0);      //red Color
        for (int i = 0; i < nX; i++) {                      // for all row
            for (int j = 0; j < nY; j++) {                  // for all cow
                imageWriter.writePixel(i, j, yellowColor);
            }
        }
        for (int i = 0; i < nX; i += 50) {                      // for all row
            for (int j = 0; j < nY; j++) {                  // for all cow
                imageWriter.writePixel(i, j, redColor);
            }
        }
            for (int i = 0; i < nX; i++) {                      // for all row 90909090
                for (int j = 0; j < nY; j += 50) {                  // for all cow 0-0-0-0-
                    imageWriter.writePixel(i, j, redColor);
                }
            }
            imageWriter.writeToImage();
        }
    }
//        /**
//         * Produce a scene with view plane size 1000*1600, resolution 500*800 and render it into a jpeg image with a 10*16 grid.
//         */
//        String image_name = "MyFirstImageTest";
//        int width = 1000;
//        int height = 1600;
//        int nx = 500;
//        int ny = 800;
//        ImageWriter imageWriter = new ImageWriter(image_name, width, height, nx, ny);
//        for (int col = 0; col < ny/..; col++)
//            for (int row = 0; row < nx; row++)
//                if (col % 10 == 0 || row % 10 == 0)
//                    imageWriter.writePixel(row, col, Color.YELLOW);
//
//        imageWriter.writeToImage();
//    } }
