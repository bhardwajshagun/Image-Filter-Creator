/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single Sharpen that contains the rgb values, height, and width of the
 * image being sharpened. The method performed in this class is returning a 3D array of integers
 * that represent the RGB values of every pixel that is being generated for the new sharpened image.
 * This subclass extends the ImageFilter class.
 */
public class Sharpen extends ImageFilter {

  /**
   * Constructs the Sharpen object that takes in and initializes it with the rgb values, height, and
   * width of the image that is invoked by the superclass ImageFilter's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public Sharpen(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Public method takes in no arguments as parameters and sets up the kernel for the sharpen apply
   * that will be taken in by the applyHelp method in the superclass ImageFilter and returns a 3D
   * array of integers that represent the RGB values of every pixel that is being generated for the
   * new sharpened image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  public int[][][] apply() {
    double[][] sharpKernel = new double[5][5];
    sharpKernel[0][0] = -0.125;
    sharpKernel[0][1] = -0.125;
    sharpKernel[0][2] = -0.125;
    sharpKernel[0][3] = -0.125;
    sharpKernel[0][4] = -0.125;
    sharpKernel[1][0] = -0.125;
    sharpKernel[1][1] = 0.25;
    sharpKernel[1][2] = 0.25;
    sharpKernel[1][3] = 0.25;
    sharpKernel[1][4] = -0.125;
    sharpKernel[2][0] = -0.125;
    sharpKernel[2][1] = 0.25;
    sharpKernel[2][2] = 1;
    sharpKernel[2][3] = 0.25;
    sharpKernel[2][4] = -0.125;
    sharpKernel[3][0] = -0.125;
    sharpKernel[3][1] = 0.25;
    sharpKernel[3][2] = 0.25;
    sharpKernel[3][3] = 0.25;
    sharpKernel[3][4] = -0.125;
    sharpKernel[4][0] = -0.125;
    sharpKernel[4][1] = -0.125;
    sharpKernel[4][2] = -0.125;
    sharpKernel[4][3] = -0.125;
    sharpKernel[4][4] = -0.125;
    return applyHelp(sharpKernel);
  }
}

