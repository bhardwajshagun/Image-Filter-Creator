/**
 * Class is part of image package.
 */

package image;

/**
 * This abstract class represents a single ImageFilter that contains rgb values, height, and width
 * of the image being applied. The method performed in this class is filtering the image. This
 * class is extended by ImageAlter superclass and is extended to the Sharpen and Blur subclasses.
 */
public abstract class ImageFilter extends ImageAlter {

  /**
   * Constructs the ImageFilter object that takes in and initializes it with the rgb values, height,
   * and width of the image that is invoked by the superclass ImageAlter's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public ImageFilter(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Protected method that takes in the applyKernel as a 2D array of doubles and returns a 3D array
   * of integers that represent the RGB values of every pixel that is being generated for the new
   * applied image.
   *
   * @param applyKernel 2D array of doubles that is being applied to the original image to apply.
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  protected int[][][] applyHelp(double[][] applyKernel) {
    double[][] kernel = applyKernel;
    double value;
    int num1;
    int num2;
    int[][][] newrgb = createNewRGB(rgb);
    int startIndex = (applyKernel.length - 1) / 2;
    for (int c = 0; c < 3; c++) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          value = 0;
          num1 = i - startIndex;
          num2 = j - startIndex;
          for (int a = 0; a < applyKernel.length; a++) {
            for (int b = 0; b < applyKernel.length; b++) {
              if (num1 < 0 || num2 < 0 || num1 >= height || num2 >= width) {
                //continue
              } else {
                value += kernel[a][b] * (double) rgb[num1][num2][c];
              }
              num2++;
            }
            num2 = j - startIndex;
            num1++;
          }
          newrgb[i][j][c] = clampRGB(value);
        }
      }
    }
    return newrgb;
  }
}
