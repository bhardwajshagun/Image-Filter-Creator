/**
 * Class is part of image package.
 */

package image;

/**
 * This abstract class represents a single ImageAlter that contains rgb values, height, and
 * width of the image being altered. The methods performed in this class are clamping the rbg
 * numbers to be between 0 - 255 and creating a copy of rgb values taken in. This class is under the
 * Image interface and is extended to the ImageFilter and ColorTransformation subclasses.
 */
public abstract class ImageAlter implements Image {

  /**
   * 3D array of integers that contain the rgb values of the image being altered.
   */
  protected int[][][] rgb;

  /**
   * Height of the image as an int data type.
   */
  protected int height;

  /**
   * Width of the image as an int data type.
   */
  protected int width;

  /**
   * Constructs the ImageAlter object that takes in and initializes it with the rgb array
   * as a 3D array of integers, height, and width of the image as int data types. Throws an
   * IllegalArgumentException if the rgb array is null or height or width is not a positive number.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image being transformed.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   * @throws IllegalArgumentException if RGB array is null.
   * @throws IllegalArgumentException if height and width taken in is less than one.
   */
  public ImageAlter(int[][][] rgb, int height, int width) throws IllegalArgumentException {

    if (rgb == null) {
      throw new IllegalArgumentException("RGB array cannot be null.");
    }
    if (height <= 0 || width <= 0) {
      throw new IllegalArgumentException("Height and width must be greater than zero.");
    }
    this.rgb = rgb;
    this.height = height;
    this.width = width;
  }

  /**
   * Public getter method takes in no arguments as parameters and returns the height of the image
   * that is being altered.
   *
   * @return the height of the image as an int data type.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Public getter method takes in no arguments as parameters and returns the width of the image
   * that is being altered.
   *
   * @return the width of the image as an int data type.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Private helper method that takes in a 3D array of integers that represent the rgb values of an
   * image and returns a copy.
   *
   * @param rgbArray 3D array of integers that contain the rgb values of the image.
   * @return the copy of the rgbArray as a 3D array of integers data structure.
   */
  protected int[][][] createNewRGB(int[][][] rgbArray) {
    return new int[rgb.length][rgb[0].length][3];
  }

  /**
   * Protected helper method that takes in a value as a double data type and clamps that number to
   * be a number between 0 - 255, which is the range of appropriate values for rgb.
   *
   * @param value the number as a double being taken in and clamped.
   * @return the new value as a int data type
   */
  protected int clampRGB(double value) {
    int intValue = (int) Math.round(value);
    if (intValue > 255) {
      intValue = 255;
    } else if (intValue < 0) {
      intValue = 0;
    }
    return intValue;
  }
}
