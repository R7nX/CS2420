package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * For testing the GrayscaleImage class.
 *
 * @author Ben Jones and Bao Phuc Do
 * @version 11 Jan 2024
 */
class GrayscaleImageTest {

    private GrayscaleImage smallSquare;
    private GrayscaleImage smallWide;


    @BeforeEach
    void setUp() {
        smallSquare = new GrayscaleImage(new double[][]{{1, 2}, {3, 4}});
        smallWide = new GrayscaleImage(new double[][]{{1, 2, 3}, {4, 5, 6}});
        GrayscaleImage horizontalRectangle = new GrayscaleImage(new double[][]{{1, 2, 3, 6, 1, 8, 0}, {4, 5, 6, 6, 4, 9, 1}});
        GrayscaleImage verticalRectangle = new GrayscaleImage(new double[][]{{1, 4, 5, 6}, {1, 2, 5, 7}, {5, 6, 2, 6}, {5, 8, 8, 0}, {1, 3, 6, 8}, {0, 9, 5, 1}, {3, 5, 7, 8}});
        GrayscaleImage square = new GrayscaleImage(new double[][]{{2, 2}, {2, 2}});

    }

    @Test
    void testContructEmptyImage() {
        assertThrows(IllegalArgumentException.class, () -> new GrayscaleImage(new double[][]{}));
    }

    @Test
    void testConstructorWithJaggedData() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[][] jaggedData = {{1.0, 2.0}, {3.0}};
            new GrayscaleImage(jaggedData);
        });
    }

    @Test
    void testGetPixel() {
        assertEquals(smallSquare.getPixel(0, 0), 1);
        assertEquals(smallSquare.getPixel(1, 0), 2);
        assertEquals(smallSquare.getPixel(0, 1), 3);
        assertEquals(smallSquare.getPixel(1, 1), 4);

    }

    @Test
    void testGetPixelWithInvalidCoordinates() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        GrayscaleImage image = new GrayscaleImage(data);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            image.getPixel(2, 1);
        });
    }

    @Test
    void testEquals() {
        assertEquals(smallSquare, smallSquare);
        var equivalent = new GrayscaleImage(new double[][]{{1, 2}, {3, 4}});
        assertEquals(smallSquare, equivalent);
    }

    @Test
    void testEqualsWithDifferentObjects() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        GrayscaleImage image = new GrayscaleImage(data);
        assertFalse(image.equals("notAnImage.png"));
    }

    @Test
    void testEqualsWithDifferentSizeImages() {
        double[][] data1 = {{1.0, 2.0}, {3.0, 4.0}};
        double[][] data2 = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        GrayscaleImage image1 = new GrayscaleImage(data1);
        GrayscaleImage image2 = new GrayscaleImage(data2);
        assertFalse(image1.equals(image2));
    }


    @Test
    void averageBrightness() {
        assertEquals(smallSquare.averageBrightness(), 2.5, 2.5 * .001);
        var bigZero = new GrayscaleImage(new double[1000][1000]);
        assertEquals(bigZero.averageBrightness(), 0);
    }

    @Test
    void averageBrightnessWithNegativeNumber() {
        GrayscaleImage negativeImage = new GrayscaleImage(new double[][]{{-1, -4.0, -2}, {0, -3, -1}});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            negativeImage.averageBrightness();
        });
    }

    @Test
    void normalized() {
        var smallNorm = smallSquare.normalized();
        assertEquals(smallNorm.averageBrightness(), 127, 127 * .001);
        var scale = 127 / 2.5;
        var expectedNorm = new GrayscaleImage(new double[][]{{scale, 2 * scale}, {3 * scale, 4 * scale}});
        for (var row = 0; row < 2; row++) {
            for (var col = 0; col < 2; col++) {
                assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
                        expectedNorm.getPixel(col, row) * .001,
                        "pixel at row: " + row + " col: " + col + " incorrect");
            }
        }
    }

    @Test
    void mirrored() {
        var expected = new GrayscaleImage(new double[][]{{2, 1}, {4, 3}});
        assertEquals(smallSquare.mirrored(), expected);
    }

    @Test
    void testMirroredWithSinglePixelImage() {
        double[][] singlePixelData = {{1.0}};
        GrayscaleImage singlePixelImage = new GrayscaleImage(singlePixelData);
        GrayscaleImage mirroredImage = singlePixelImage.mirrored();
        assertEquals(1.0, mirroredImage.getPixel(0, 0));
    }

    @Test
    void testMirroredWithSingleRowImage() {
        double[][] data = {{1.0, 2.0, 3.0}};
        double[][] dataReversed = {{3.0, 2.0, 1.0}};
        GrayscaleImage reversedImage = new GrayscaleImage(dataReversed);

        GrayscaleImage image = new GrayscaleImage(data);
        GrayscaleImage mirroredImage = image.mirrored();
        assertTrue(reversedImage.equals(mirroredImage));
    }

    @Test
    void cropped() {
        var cropped = smallSquare.cropped(1, 1, 1, 1);
        assertEquals(cropped, new GrayscaleImage(new double[][]{{4}}));
    }

    @Test
    void croppedNegativeRow() {
        assertThrows(IllegalArgumentException.class, () -> {
            smallSquare.cropped(-1, 1, 1, 1);
        });
    }

    @Test
    void croppedNegativeCol() {
        assertThrows(IllegalArgumentException.class, () -> {
            smallSquare.cropped(1, -1, 1, 1);
        });
    }

    @Test
    void croppedNegativeHeight() {
        assertThrows(IllegalArgumentException.class, () -> {
            smallSquare.cropped(1, 1, 1, -1);
        });
    }

    @Test
    void croppedNegativeWidth() {
        assertThrows(IllegalArgumentException.class, () -> {
            smallSquare.cropped(1, 1, -1, 1);
        });
    }

    @Test
    void testCroppedWithInvalidRectangle() {
        double[][] data = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}};
        GrayscaleImage image = new GrayscaleImage(data);
        assertThrows(IllegalArgumentException.class, () -> {
            image.cropped(0, 1, 4, 2);
        });
    }

    @Test
    void testCroppedWithFullImage() {
        double[][] data = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
        GrayscaleImage image = new GrayscaleImage(data);
        GrayscaleImage croppedImage = image.cropped(0, 0, 3, 3);
        assertEquals(image, croppedImage);
    }

    @Test
    void squarified() {
        var squared = smallWide.squarified();
        var expected = new GrayscaleImage(new double[][]{{1, 2}, {4, 5}});
        assertEquals(squared, expected);
    }

    @Test
    void testSquarifiedWithSquareImage() {
        double[][] squareData = {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
        GrayscaleImage squareImage = new GrayscaleImage(squareData);
        GrayscaleImage squarifiedImage = squareImage.squarified();
        assertTrue(squarifiedImage.equals(squareImage));
    }
}