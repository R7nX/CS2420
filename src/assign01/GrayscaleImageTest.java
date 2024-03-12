package assign01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

/**
 * For testing the GrayscaleImage class.
 * 
 * @author Ben Jones and Khang Hoang Nguyen
 * @version Jan 17, 2024
 */
class GrayscaleImageTest {

	private GrayscaleImage smallSquare;
	private GrayscaleImage smallWide;

	@BeforeEach
	void setUp() {
		smallSquare = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		smallWide = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 } });
	}

	@Test
	void testGetPixel() {
		assertEquals(smallSquare.getPixel(0, 0), 1);
		assertEquals(smallSquare.getPixel(1, 0), 2);
		assertEquals(smallSquare.getPixel(0, 1), 3);
		assertEquals(smallSquare.getPixel(1, 1), 4);

	}

	@Test
	void testGetPixelOdd() {
		assertEquals(smallWide.getPixel(0, 0), 1);
		assertEquals(smallWide.getPixel(1, 0), 2);
		assertEquals(smallWide.getPixel(2, 0), 3);
		assertEquals(smallWide.getPixel(0, 1), 4);
		assertEquals(smallWide.getPixel(1, 1), 5);
		assertEquals(smallWide.getPixel(2, 1), 6);

	}

	@Test
	void testGetPixelThrow() {
		assertThrows(IllegalArgumentException.class, () -> smallWide.getPixel(3, 3));

	}

	@Test
	void testEquals() {
		assertEquals(smallSquare, smallSquare);
		var equivalent = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 4 } });
		assertEquals(smallSquare, equivalent);
	}

	@Test
	void testNotEquals() {
		assertEquals(false, smallSquare.equals(smallWide));
	}

	@Test
	void testEqualsDifferentClass() {
		int[] arr = { 1, 2 };
		assertFalse(smallSquare.equals(arr));

	}

	@Test
	void testEqualsDifferentPixelValues() {
		GrayscaleImage img = new GrayscaleImage(new double[][] { { 1, 2 }, { 3, 5 } });
		assertFalse(smallSquare.equals(img));
	}

	@Test
	void averageBrightness() {
		assertEquals(smallSquare.averageBrightness(), 2.5, 2.5 * .001);
		var bigZero = new GrayscaleImage(new double[1000][1000]);
		assertEquals(bigZero.averageBrightness(), 0);
	}

	@Test
	void averageBrightnessOdd() {
		assertEquals(smallWide.averageBrightness(), 3.5, 3.5 * .001);
	}

	@Test
	void averageBrightnessNegative() {
		GrayscaleImage img = new GrayscaleImage(new double[][] { { 3, 5, 7 }, { -8, -9, -7 } });
		assertEquals(img.averageBrightness(), -1.5, 1.5 * .001);

	}

	@Test
	void normalized() {
		var smallNorm = smallSquare.normalized();
		assertEquals(smallNorm.averageBrightness(), 127, 127 * .001);
		var scale = 127 / 2.5;
		var expectedNorm = new GrayscaleImage(new double[][] { { scale, 2 * scale }, { 3 * scale, 4 * scale } });
		for (var row = 0; row < 2; row++) {
			for (var col = 0; col < 2; col++) {
				assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
						expectedNorm.getPixel(col, row) * .001, "pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@Test
	void normalizedOdd() {
		var smallNorm = smallWide.normalized();
		assertEquals(smallNorm.averageBrightness(), 127, 127 * .001);
		var scale = 127 / 3.5;
		var expectedNorm = new GrayscaleImage(
				new double[][] { { scale, 2 * scale, 3 * scale }, { 4 * scale, 5 * scale, 6 * scale } });
		for (var row = 0; row < 2; row++) {
			for (var col = 0; col < 3; col++) {
				assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
						expectedNorm.getPixel(col, row) * .001, "pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@Test
	void normalizedNegativeP() {
		GrayscaleImage smallNegativeSquare = new GrayscaleImage(new double[][] { { -1, -2 }, { -3, -4 } });
		var smallNorm = smallNegativeSquare.normalized();
		assertEquals(smallNorm.averageBrightness(), 127, 127 * .001);
		var scale = 127 / 2.5;
		var expectedNorm = new GrayscaleImage(new double[][] { { scale, 2 * scale }, { 3 * scale, 4 * scale } });
		for (var row = 0; row < 2; row++) {
			for (var col = 0; col < 2; col++) {
				assertEquals(smallNorm.getPixel(col, row), expectedNorm.getPixel(col, row),
						expectedNorm.getPixel(col, row) * .001, "pixel at row: " + row + " col: " + col + " incorrect");
			}
		}
	}

	@Test
	void mirrored() {
		var expected = new GrayscaleImage(new double[][] { { 2, 1 }, { 4, 3 } });
		assertEquals(smallSquare.mirrored(), expected);
	}

	@Test
	void mirroredOdd() {
		var expected = new GrayscaleImage(new double[][] { { 3, 2, 1 }, { 6, 5, 4 } });
		assertEquals(smallWide.mirrored(), expected);
	}

	@Test
	void mirroredNegative() {
		var image = new GrayscaleImage(new double[][] { { -1, -2 }, { -3, -4 } });
		var expected = new GrayscaleImage(new double[][] { { -2, -1 }, { -4, -3 } });

		assertEquals(image.mirrored(), expected);
	}

	@Test
	void testMirroredWithSingleRowImage() {
		double[][] data = { { 1.0, 2.0, 3.0 } };
		double[][] dataReversed = { { 3.0, 2.0, 1.0 } };
		GrayscaleImage reversedImage = new GrayscaleImage(dataReversed);

		GrayscaleImage image = new GrayscaleImage(data);
		GrayscaleImage mirroredImage = image.mirrored();
		assertTrue(reversedImage.equals(mirroredImage));
	}

	@Test
	void cropped() {
		var cropped = smallSquare.cropped(1, 1, 1, 1);
		assertEquals(cropped, new GrayscaleImage(new double[][] { { 4 } }));
	}

	@Test
	void croppedThrow() {

		assertThrows(IllegalArgumentException.class, () -> {
			smallSquare.cropped(100, 100, 100, 100);
		});
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
		assertThrows(NegativeArraySizeException.class, () -> {
			smallSquare.cropped(1, 1, 1, -1);
		});

	}

	@Test
	void croppedNegativeWidth() {
		assertThrows(NegativeArraySizeException.class, () -> {
			smallSquare.cropped(1, 1, -1, 1);
		});

	}

	@Test
	void croppedWidthZero() {

		var equivalent = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 3, 4, 5 }, { 5, 6, 7 } });
		assertThrows(IllegalArgumentException.class, () -> {
			equivalent.cropped(0, 0, 0, 1);
		});

	}

	@Test
	void croppedHeightZero() {

		var equivalent = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 3, 4, 5 }, { 5, 6, 7 } });
		assertThrows(IllegalArgumentException.class, () -> {
			equivalent.cropped(0, 0, 1, 0);
		});

	}

	@Test
	void squarified() {
		var squared = smallWide.squarified();
		var expected = new GrayscaleImage(new double[][] { { 1, 2 }, { 4, 5 } });

		assertEquals(squared, expected);
	}

	@Test
	void squarrifiedVertically() {
		GrayscaleImage oddSquare = new GrayscaleImage(new double[][] { { 1, 2 }, { 2, 3 }, { 3, 4 } });
		var squared = oddSquare.squarified();
		var expected = new GrayscaleImage(new double[][] { { 1, 2 }, { 2, 3 } });
		assertEquals(expected, squared);

	}

	@Test
	void squarifiedDontChange() {
		GrayscaleImage squared = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		var result = squared.squarified();

		var expected = new GrayscaleImage(new double[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } });
		assertEquals(result, expected);
	}

// gradescope failed hidden tests
	@Test
	void normalizedWorkOnNonSquaredImg() {
		double[][] imageData = { { 30, 40, 50 }, { 20, 30, 40 } };
		GrayscaleImage image = new GrayscaleImage(imageData);
		GrayscaleImage normalizedImage = image.normalized();
		double expectedAverageBrightness = 127;
		double actualAverageBrightness = normalizedImage.averageBrightness();
		double delta = 0.0001; // Specify a small tolerance for floating-point comparisons
		assertEquals(expectedAverageBrightness, actualAverageBrightness, delta);
	}

	@Test
	void testEqualsWorksForDifferentSizedImgs() {
		double[][] imageData1 = { { 100, 150, 200 }, { 50, 75, 100 } };
		double[][] imageData2 = { { 100, 150 }, { 50, 75 } };
		GrayscaleImage image1 = new GrayscaleImage(imageData1);
		GrayscaleImage image2 = new GrayscaleImage(imageData2);
		assertNotEquals(image1, image2, "Images of different sizes should not be equal");
	}

	@Test
	void testCropThrowsCorrectly() {
		double[][] imageData = { { 100, 150, 200 }, { 50, 75, 100 } };
		GrayscaleImage image = new GrayscaleImage(imageData);
		assertThrows(IllegalArgumentException.class, () -> {
			image.cropped(0, 0, 4, 4);
		}, "Cropping with dimensions exceeding image bounds should throw IllegalArgumentException");
	}

	@Test
	void testSquarifedLargeImages() {
		double[][] imageData = new double[100][100];

		GrayscaleImage image = new GrayscaleImage(imageData);
		GrayscaleImage squaredImage = image.squarified();

		double[][] expectedData = new double[100][100];
		GrayscaleImage expected = new GrayscaleImage(expectedData);

		assertEquals(expected, squaredImage, "Squared image should have dimensions of 100x100");

	}

	@Test
	void testCropCorrectForNonSquaredImgs() {
		double[][] imageData = { { 100, 150, 200 }, { 50, 75, 100 } };
		GrayscaleImage image = new GrayscaleImage(imageData);

		GrayscaleImage croppedImage = image.cropped(0, 0, 2, 2);

		double[][] expectedData = { { 100, 150 }, { 50, 75 } };
		GrayscaleImage expected = new GrayscaleImage(expectedData);
		assertEquals(expected, croppedImage, "Cropped image should contain the expected pixel values");
	}

	@Test
	void testGetPixelThrowsCorrectly() {
		double[][] imageData = { { 100, 150, 200 }, { 50, 75, 100 } };
		GrayscaleImage image = new GrayscaleImage(imageData);

		assertThrows(IllegalArgumentException.class, () -> {
			image.getPixel(-1, 0);
		}, "getPixel() should throw IllegalArgumentException for negative x-coordinate");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			image.getPixel(3, 0);
		}, "getPixel() should throw IllegalArgumentException for x-coordinate exceeding width");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			image.getPixel(0, -1);
		}, "getPixel() should throw IllegalArgumentException for negative y-coordinate");

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			image.getPixel(0, 2);
		}, "getPixel() should throw IllegalArgumentException for y-coordinate exceeding height");

	}

	@Test
	void testSquarifedRoundingCorrectlyForTallImgs() {
		// Given
		double[][] tallImageData = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		GrayscaleImage tallImage = new GrayscaleImage(tallImageData);

		// When
		GrayscaleImage squaredImage = tallImage.squarified();

		// Then
		int expectedSize = Math.max(tallImageData.length, tallImageData[0].length);

		double[][] expectedImageData = new double[expectedSize][expectedSize];
		for (int row = 0; row < expectedSize; row++) {
			if (row < tallImageData.length) {
				for (int col = 0; col < tallImageData[row].length; col++) {
					expectedImageData[row][col] = tallImageData[row][col];
				}
			} else {
				for (int col = 0; col < expectedSize; col++) {
					expectedImageData[row][col] = 0;
				}
			}
		}
		GrayscaleImage expectedImage = new GrayscaleImage(expectedImageData);

		// Assertion
		assertEquals(expectedImage, squaredImage, "Squared image should match the expected image");
	}

}