/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single CheckerboardImage that contains the side length of the squares
 * that make up the image as an int data type. The methods performed in this class are retrieving
 * the height and width of the image and returning a 3D array of integers that contain RGB values of
 * all the pixels that will be generated when the image is created. This class is under the
 * Image interface.
 */
public class CheckerboardImage implements Image {

  /**
   * Length of a side of a square being generated as an int data type.
   */
  private int side;

  /**
   * Constructs the CheckerboardImage object that takes in and initializes it with the side length
   * of the squares as an int data type that will make up the image being generated. Throws an
   * IllegalArgumentException if the side taken in is not a positive number.
   *
   * @param side the length of a side of a square being generated as an int data type.
   * @throws IllegalArgumentException if the side length taken in is less than one.
   */
  public CheckerboardImage(int side) throws IllegalArgumentException {
    if (side <= 0) {
      throw new IllegalArgumentException("Must have a positive side length to create image.");
    }
    this.side = side;
  }

  /**
   * Public method that takes in no arguments as parameters and returns a 3D array of integers that
   * represent the RGB values of every pixel that is being generated for the CheckerboardImage
   * object.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  @Override
  public int[][][] apply() {

    int[][][] rgb = new int[side * 8][side * 8][3];

    for (int i = 0; i < side * 8; i++) {
      for (int j = 0; j < side * 8; j++) {
        int row = i / side;
        int column = j / side;
        if ((row + column) % 2 == 0) {
          rgb[i][j][0] = 255;
          rgb[i][j][1] = 255;
          rgb[i][j][2] = 255;
        } else {
          rgb[i][j][0] = 0;
          rgb[i][j][1] = 0;
          rgb[i][j][2] = 0;
        }
      }
    }
    return rgb;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the height of the
   * CheckerboardImage object that is generated based off the number of squares being generated.
   *
   * @return the height of the image as an int data type.
   */
  @Override
  public int getHeight() {
    return side * 8;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the width of the
   * CheckerboardImage object that is generated based off the number of squares being generated.
   *
   * @return the width of the image as an int data type.
   */
  @Override
  public int getWidth() {
    return side * 8;
  }
}
