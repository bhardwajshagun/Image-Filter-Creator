/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single HorizontalStripes that contains the height, width, and direction
 * of the image being generated. The method performed in this class is creating the
 * HorizontalStripes image using the createhelper function in the StripesImage superclass. This
 * class is extended by the StripesImage abstract class.
 */
public class HorizontalStripes extends StripesImage {

  /**
   * The direction of the stripes on the image as an enum data type.
   */
  private StripePath direction;

  /**
   * Constructs the HorizontalStripes object that takes in and initializes it with the height and
   * width of the image and setting the direction to HORIZONTAL that is invoked by the superclass
   * StripesImage's constructor.
   *
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  public HorizontalStripes(int height, int width) {
    super(height, width);
    direction = StripePath.HORIZONTAL;
  }

  /**
   * Public method takes in no arguments as parameters and calls the helper method in the superclass
   * with the direction taken as HORIZONTAL and returns a 3D array of integers that represent the
   * RGB values of every pixel that is being generated for the new image.
   *
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *        integers
   */
  public int[][][] apply() {
    return createHelper(height, width, direction);
  }
}
