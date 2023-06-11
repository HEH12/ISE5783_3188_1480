package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    @Test
    void testWriteToImage() {
        int nX = 800; //row
        int nY = 500; //cow
        Color yellow  = new Color(255d,255d,0d);
        Color red = new Color(255d,0d,0d);

        ImageWriter imageWriter = new ImageWriter("yellowTestWithGrid",nX,nY);
        imageWriter.fillBackground(yellow);
        imageWriter.printGrid(50,red);
        imageWriter.writeToImage();
    }
}