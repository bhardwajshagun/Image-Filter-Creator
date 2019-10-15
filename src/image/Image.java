/**
 * Class is part of image package.
 */

package image;

/**
 * This interface contains all operations that all Image classes should contain. It should contain
 * these methods: apply, getHeight, and getWidth. It is implemented in the abstract classes
 * ImageAlter, FlagImage, CheckerboardImage, and StripesImage.
 */
public interface Image {

  /**
   * This method takes in no arguments as parameters and returns a 3D array of integers that
   * represents the RGB values of every pixel that is being applied to the new image.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  int[][][] apply();

  /**
   * This method takes in no arguments as parameters and returns the height of the image that is
   * generated.
   *
   * @return the height of the image as an int data type.
   */
  int getHeight();

  /**
   * This method takes in no arguments as parameters and returns the width of the image that is
   * generated.
   *
   * @return the width of the image as an int data type.
   */
  int getWidth();
}
