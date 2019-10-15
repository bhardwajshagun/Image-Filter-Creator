/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single VerticalStripes that contains the height, width, and direction of
 * the image being generated. The method performed in this class is creating the VerticalStripes
 * image using the createHelper function in the StripesImage superclass. This class is extended by
 * the StripesImage abstract class.
 */
public class VerticalStripes extends StripesImage {

  /**
   * The direction of the stripes on the image as an enum data type.
   */
  private StripePath direction;

  /**
   * Constructs the VerticalStripes object that takes in and initializes it with the height and
   * width of the image and setting the direction to VERTICAL that is invoked by the superclass
   * StripesImage's constructor.
   *
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public VerticalStripes(int height, int width) {
    super(height, width);
    direction = StripePath.VERTICAL;
  }

  /**
   * Public method takes in no arguments as parameters and calls the helper method in the superclass
   * with the direction taken as VERTICAL and returns a 3D array of integers that represent the RGB
   * values of every pixel that is being generated for the new image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  public int[][][] apply() {
    return createHelper(height, width, direction);
  }
}
