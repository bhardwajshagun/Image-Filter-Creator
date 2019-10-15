/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single SwitzerlandFlag that contains the height of the image being
 * generated. The method performed in this class is returning a 3D array of integers that contain
 * RGB values of all the pixels that will be generated when the image is created. This subclass
 * extends the FlagImage class.
 */
public class SwitzerlandFlag extends FlagImage {

  /**
   * Constructs the SwitzerlandFlag object that takes in and initializes it with the height of the
   * image as an int data type invoked by the superclass FlagImage's constructor. The proportion is
   * of the flag 1:1, which is set when the width is initialized.
   *
   * @param height the height of the flag as an int data type.
   */
  public SwitzerlandFlag(int height) {
    super(height);
    width = height;
  }

  /**
   * Public method that takes in no arguments as parameters and returns a 3D array of integers that
   * represent the RGB values of every pixel that is being generated for the SwitzerlandFlag object.
   * The colors used to generate the flag are represented in the 2D array colorRGB that is
   * initialized at the beginning of the function.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  @Override
  public int[][][] apply() {
    int[][] colorRGB = {
            {255, 0, 0},
            {255, 255, 255}};
    int width = height;
    int[][][] rgb = new int[height][width][3];
    int perimeter1 = height * 3 / 16;
    int perimeter2 = height - perimeter1;
    int insideRed = height * 7 / 32;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (i < perimeter1 || j < perimeter1 || i > perimeter2 || j > perimeter2) {
          rgb[i][j] = colorRGB[0];
        } else if ((i < perimeter1 + insideRed || i > perimeter2 - insideRed)
                && (j < perimeter1 + insideRed || j > perimeter2 - insideRed)) {
          rgb[j][i] = colorRGB[0];
        } else {
          rgb[j][i] = colorRGB[1];
        }
      }
    }
    return rgb;
  }
}
