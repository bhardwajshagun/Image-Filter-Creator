/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single Sepia that contains the rgb values, height, and width of the image
 * being converted to sepia. The method performed in this class is returning a 3D array of integers
 * that represent the RGB values of every pixel that is being generated for the new sepia image.
 * This subclass extends the ColorTransformation class.
 */
public class Sepia extends ColorTransformation {

  /**
   * Constructs the Sepia object that takes in and initializes it with the rgb values, height, and
   * width of the image that is invoked by the superclass ColorTransformation's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public Sepia(int[][][] rgb, int height, int width) {
    super(rgb, height, width);
  }

  /**
   * Public method takes in no arguments as parameters and sets up the kernel for the sepia matrix
   * that will be taken in by the transformHelp method in the superclass ColorTransformation and
   * returns a 3D array of integers that represent the RGB values of every pixel that is being
   * generated for the new sepia image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  @Override
  public int[][][] apply() {
    double[][] matrix = {{0.393, 0.769, 0.189},
                         {0.349, 0.686, 0.168},
                         {0.272, 0.534, 0.131}};
    return transformHelp(matrix);
  }
}