/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single FranceFlag that contains the height of the image being generated.
 * The method performed in this class is returning a 3D array of integers that contain RGB values
 * omf all the pixels that will be generated when the image is created. This subclass extends the
 * FlagImage class.
 */
public class FranceFlag extends FlagImage {

  /**
   * Constructs the FranceFlag object that takes in and initializes it with the height of the image
   * as an int data type invoked by the superclass FlagImage's constructor. The proportion is of the
   * flag 2:3, which is set when the width is initialized.
   *
   * @param height the height of the flag as an int data type.
   */
  public FranceFlag(int height) {
    super(height);
    width = height * 3 / 2;
  }

  /**
   * Public method that takes in no arguments as parameters and returns a 3D array of integers that
   * represent the RGB values of every pixel that is being generated for the FranceFlag object. The
   * colors used to generate the flag are represented in the 2D array colorRGB that is initialized
   * at the beginning of the function.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  @Override
  public int[][][] apply() {
    int[][] colorRGB = {
            {0, 85, 164},
            {255, 255, 255},
            {239, 65, 53}};
    int[][][] rgb = new int[height][width][3];
    int stripeSize = (int) Math.ceil((double) width / 3);
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i <= stripeSize) {
          rgb[j][i] = colorRGB[0];
        } else if (i <= stripeSize * 2) {
          rgb[j][i] = colorRGB[1];
        } else {
          rgb[j][i] = colorRGB[2];
        }
      }
    }
    return rgb;
  }
}
