/**
 * Interface is part of view package.
 */

package view;

/**
 * This interface contains all operations that all IView classes should contain. It should contain
 * these methods: drawImage and errorMessage. It is implemented in the JFrameView subclass.
 */
public interface IView {

  /**
   * This method takes in a rgb array, height, and width and draws the image based on the parameters
   * taken in and returns nothing.
   *
   * @param rgb 3D array of integers containing the RGB values of every pixel in the image
   * @param height height of the image as an int data type
   * @param width width of the image as an int data type
   */
  void drawImage(int[][][] rgb, int height, int width);

  /**
   * This method takes in two parameters height and width as int data types and returns a boolean
   * statement if the user encounters an error when using view.
   *
   * @param height height of the image as an int data type
   * @param width width of the image as an int data type
   * @return a boolean determining if the user has reached an error
   */
  boolean errorMessage(int height, int width);
}
