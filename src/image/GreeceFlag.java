/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single GreeceFlag that contains the height of the image being generated.
 * The method performed in this class is returning a 3D array of integers that contain RGB values of
 * all the pixels that will be generated when the image is created. This subclass extends the
 * FlagImage class.
 */
public class GreeceFlag extends FlagImage {

  /**
   * Constructs the GreeceFlag object that takes in and initializes it with the height of the image
   * as an int data type invoked by the superclass FlagImage's constructor. The proportion is of the
   * flag 2:3, which is set when the width is initialized.
   *
   * @param height the height of the flag as an int data type.
   */
  public GreeceFlag(int height) {
    super(height);
    width = height * 3 / 2;
  }

  /**
   * Public method that takes in no arguments as parameters and returns a 3D array of integers that
   * represent the RGB values of every pixel that is being generated for the GreeceFlag object. The
   * colors used to generate the flag are represented in the 2D array colorRGB that is initialized
   * at the beginning of the function.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  @Override
  public int[][][] apply() {
    int[][] colorRGB = {
            {13, 94, 175},
            {255, 255, 255}};
    int width = (height * 3) / 2;
    int[][][] rgb = new int[height][width][3];
    int stripeSize = (int) Math.ceil((double) height / 9);
    int totalPerimeter = stripeSize * 5;
    int insidePerimeter = stripeSize * 2;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (j <= stripeSize) {
          rgb[j][i] = colorRGB[0];
        } else if (j <= stripeSize * 2) {
          rgb[j][i] = colorRGB[1];
        } else if (j <= stripeSize * 3) {
          rgb[j][i] = colorRGB[0];
        } else if (j <= stripeSize * 4) {
          rgb[j][i] = colorRGB[1];
        } else if (j <= stripeSize * 5) {
          rgb[j][i] = colorRGB[0];
        } else if (j <= stripeSize * 6) {
          rgb[j][i] = colorRGB[1];
        } else if (j <= stripeSize * 7) {
          rgb[j][i] = colorRGB[0];
        } else if (j <= stripeSize * 8) {
          rgb[j][i] = colorRGB[1];
        } else {
          rgb[j][i] = colorRGB[0];
        }
      }
    }

    for (int i = 0; i <= totalPerimeter; i++) {
      for (int j = 0; j <= totalPerimeter; j++) {
        if ((i <= insidePerimeter || i >= totalPerimeter - insidePerimeter)
                && (j <= insidePerimeter || j >= totalPerimeter - insidePerimeter)) {
          rgb[j][i] = colorRGB[0];
        } else {
          rgb[j][i] = colorRGB[1];
        }
      }
    }
    return rgb;
  }
}
