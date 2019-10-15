/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single Blur that contains the rgb values, height, and width of the image
 * being blurred. The method performed in this class is returning a 3D array of integers that
 * represent the RGB values of every pixel that is being generated for the new blurred image. This
 * subclass extends the ImageFilter class.
 */
public class Blur extends ImageFilter {

  /**
   * Constructs the Blur object that takes in and initializes it with the rgb values, height, and
   * width of the image that is invoked by the superclass ImageFilter's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public Blur(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Public method takes in no arguments as parameters and sets up the kernel for the blur apply
   * that will be taken in by the applyHelp method in the superclass ImageFilter and returns a 3D
   * array of integers that represent the RGB values of every pixel that is being generated for the
   * new blurred image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  public int[][][] apply() {
    double[][] blurKernel = new double[3][3];
    blurKernel[0][0] = 0.0625;
    blurKernel[0][1] = 0.125;
    blurKernel[0][2] = 0.0625;
    blurKernel[1][0] = 0.125;
    blurKernel[1][1] = 0.25;
    blurKernel[1][2] = 0.125;
    blurKernel[2][0] = 0.0625;
    blurKernel[2][1] = 0.125;
    blurKernel[2][2] = 0.0625;
    return applyHelp(blurKernel);
  }
}
