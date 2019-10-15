/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single Dither that contains the rgb values, height, and width of the
 * image being dithered. The method performed in this class is returning a 3D array of
 * integers that represent the RGB values of every pixel that is being generated for the new
 * dithered image. This subclass extends the ColorTransformation class.
 */
public class Dither extends ColorTransformation {

  /**
   * Constructs the Dither object that takes in and initializes it with the rgb values, height,
   * and width of the image that is invoked by the superclass ColorTransformation's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public Dither(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Public method takes in no arguments as parameters and sets up the kernel for the greyscale
   * matrix that will be taken in by the ditherHelp method in the superclass ColorTransformation
   * and returns a 3D array of integers that represent the RGB values of every pixel that is being
   * generated for the new dithered image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  @Override
  public int[][][] apply() {
    double[][] greyscaleMatrix = {{0.2126, 0.7152, 0.0722},
                                  {0.2126, 0.7152, 0.0722},
                                  {0.2126, 0.7152, 0.0722}};
    int[][][] greyscaleRGB = transformHelp(greyscaleMatrix);
    return ditherHelp(greyscaleRGB);
  }

  /**
   * Protected helper method that takes in the RGBarray that has been previously been converted to
   * greyscale and applies the Floyd-Steinberg algorithm to return a new 3D array of integers that
   * represent the RGB values of every pixel that is being generated for the new dithered image.
   *
   * @param rgbArray 3D array of integers that contain the rgb values of the image.
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  protected int[][][] ditherHelp(int[][][] rgbArray) {
    int newColor;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int oldColor = rgbArray[i][j][0];
        if (oldColor > 127) {
          newColor = 255;
        } else {
          newColor = 0;
        }
        int error = oldColor - newColor;
        for (int c = 0; c < 3; c++) {
          rgbArray[i][j][c] = newColor;
        }
        if (j + 1 <= width - 1) {
          for (int c = 0; c < 3; c++) {
            rgbArray[i][j + 1][c] += ((7.0 / 16.0) * error);
          }
        }
        if (i + 1 <= height - 1 && j - 1 >= 0) {
          for (int c = 0; c < 3; c++) {
            rgbArray[i + 1][j - 1][c] += ((3.0 / 16.0) * error);
          }
        }
        if (i + 1 <= height - 1) {
          for (int c = 0; c < 3; c++) {
            rgbArray[i + 1][j][c] += ((5.0 / 16.0) * error);
          }
        }
        if (i + 1 <= height - 1 && j + 1 <= width - 1) {
          for (int c = 0; c < 3; c++) {
            rgbArray[i + 1][j + 1][c] += ((1.0 / 16.0) * error);
          }
        }
      }
    }
    return rgbArray;
  }

}
