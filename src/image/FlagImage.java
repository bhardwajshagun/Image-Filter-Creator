/**
 * Class is part of image package.
 */

package image;

/**
 * This abstract class represents a single FlagImage that contains the height of the image being
 * generated. The methods performed in this class are retrieving the height and width of the flag as
 * an int data type. This class is under the Image interface and is extended to the FranceFlag,
 * GreeceFlag, and SwitzerlandFlag subclasses. The creation of the image is performed in the
 * respective subclasses.
 */
public abstract class FlagImage implements Image {

  /**
   * Height of the FlagImage as an int data type.
   */
  protected int height;

  /**
   * Width of the FlagImage as an int data type.
   */
  protected int width;

  /**
   * Constructs the FlagImage object that takes in and initializes it with the height of the image
   * as an int data type that is then invoked to its subclasses's constructors. The proportion of
   * the flag is set in the subclasses when the width is initialized.
   *
   * @param height the height of the flag as an int data type.
   * @throws IllegalArgumentException if the height taken in is less than one.
   */
  public FlagImage(int height) throws IllegalArgumentException {
    if (height <= 0) {
      throw new IllegalArgumentException("Must have a positive height to create image.");
    }
    this.height = height;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the height of the
   * FlagImage object that is generated.
   *
   * @return the height of the flag as an int data type.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Public getter method that takes in no arguments as parameters and returns the width of the
   * FlagImage object that is generated.
   *
   * @return the width of the flag as an int data type.
   */
  @Override
  public int getWidth() {
    return width;
  }
}
