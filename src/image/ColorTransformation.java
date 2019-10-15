/**
 * Class is part of the image package.
 */

package image;

/**
 * This abstract class represents a single ColorTransformation that contains rgb values, height, and
 * width of the image being transformed. The method performed in this class is transforming the
 * image. This class is extended by ImageAlter superclass and is extended to the Sepia, Greyscale,
 * Dither, and Mosaic subclasses.
 */
public abstract class ColorTransformation extends ImageAlter {

  /**
   * Constructs the ColorTransformation object that takes in and initializes it with the rgb values,
   * height, and width of the image that is invoked by the superclass ImageAlter's
   * constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public ColorTransformation(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Protected method that takes in the applyMatrix as a 2D array of doubles and returns a 3D array
   * of integers that represent the RGB values of every pixel that is being generated for the new
   * transformed image.
   *
   * @param applyMatrix 2D array of doubles that is being applied to the original image to
   *                     transform.
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  protected int[][][] transformHelp(double[][] applyMatrix) {
    double[][] matrix = applyMatrix;
    double value;
    int[][][] newrgb = createNewRGB(rgb);
    for (int c = 0; c < 3; c++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          value = (matrix[c][0] * rgb[i][j][0]) + (matrix[c][1] * rgb[i][j][1])
                  + (matrix[c][2] * rgb[i][j][2]);
          newrgb[i][j][c] = clampRGB(value);
        }
      }
    }
    return newrgb;
  }
}
