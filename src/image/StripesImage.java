/**
 * Class is part of image package.
 */

package image;

/**
 * This abstract class represents a single StripesImage that contains the height and width of the
 * image being generated. The methods performed in this class are retrieving the height and width of
 * the stripes image as an int data type and having a helper function for creating the rainbow
 * striped flag based on the direction taken in the subclasses. This class is under the interface
 * and is extended to the HorizontalStripes and VerticalStripes subclasses.
 */
public abstract class StripesImage implements Image {

  /**
   * Height of the StripesImage as an int data type.
   */
  protected int height;

  /**
   * Width of the StripesImage as an int data type.
   */
  protected int width;

  /**
   * Constructs the StripesImage object that takes in and initializes it with the height and width
   * of the image as an int data type that is then invoked to its subclasses's constructors.
   *
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   * @throws IllegalArgumentException if the height or width taken in is less than one.
   */
  public StripesImage(int height, int width) throws IllegalArgumentException {
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Height and width must be a positive number.");
    }
    this.height = height;
    this.width = width;
  }

  /**
   * Protected helper method that takes in the height, width, and direction of the image as
   * parameters and returns a 3D array of integers that represent the RGB values of every pixel that
   * is being generated for the StripesImage object. The colors used to generate the flag are
   * represented in the 2D array colorRGB that is initialized at the beginning of the function.
   *
   * @param height    the height of the image as an int data type.
   * @param width     the width of the image as an int data type.
   * @param direction the direction of the stripes on the image as an enum data type.
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  protected int[][][] createHelper(int height, int width, StripePath direction) {
    int[][] colorRGB = {{255, 0, 0},
                        {255, 200, 0},
                        {255, 255, 0},
                        {0, 255, 0},
                        {0, 0, 255},
                        {75, 0, 130},
                        {128, 0, 128}};

    int[][][] rgb = new int[height][width][3];
    int stripeSize;
    if (direction == StripePath.HORIZONTAL) {
      stripeSize = (int) Math.ceil((double) height / 7);
    } else {
      stripeSize = (int) Math.ceil((double) width / 7);
    }
    int stripeNum;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        if (direction == StripePath.HORIZONTAL) {
          stripeNum = j;
        } else {
          stripeNum = i;
        }
        if (stripeNum <= stripeSize) {
          rgb[j][i] = colorRGB[0];
        } else if (stripeNum <= stripeSize * 2) {
          rgb[j][i] = colorRGB[1];
        } else if (stripeNum <= stripeSize * 3) {
          rgb[j][i] = colorRGB[2];
        } else if (stripeNum <= stripeSize * 4) {
          rgb[j][i] = colorRGB[3];
        } else if (stripeNum <= stripeSize * 5) {
          rgb[j][i] = colorRGB[4];
        } else if (stripeNum <= stripeSize * 6) {
          rgb[j][i] = colorRGB[5];
        } else {
          rgb[j][i] = colorRGB[6];
        }
      }
    }
    return rgb;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the height of the
   * StripesImage object that is generated.
   *
   * @return the height of the image as an int data type.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the width of the
   * StripesImage object that is generated.
   *
   * @return the width of the image as an int data type.
   */
  public int getWidth() {
    return width;
  }
}
