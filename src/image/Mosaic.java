/**
 * Class is part of image package.
 */

package image;

/**
 * This class represents a single Mosaic that contains the rgb values, height, and width of the
 * image being converted to mosaic. The method performed in this class is returning a 3D array of
 * integers that represent the RGB values of every pixel that is being generated for the new mosaic
 * image. This subclass extends the ColorTransformation class.
 */
public class Mosaic extends ColorTransformation {

  /**
   * The number of seeds taken in for the mosaic transformation.
   */
  private int seeds;

  /**
   * Constructs the Mosaic object that takes in and initializes it with the rgb values, height, and
   * width of the image that is invoked by the superclass ColorTransformation's constructor.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   * @throws IllegalArgumentException if the number of seeds is less than one.
   */
  public Mosaic(int[][][] rgb, int height, int width, int seeds) throws IllegalArgumentException {
    super(rgb, height, width);
    if (seeds < 1) {
      throw new IllegalArgumentException("Number of seeds must be greater than zero to create"
                                          + " a mosaic image.");
    }
    this.seeds = seeds;
  }

  /**
   * Public method that takes in no arguments as parameters and calls the mosaicHelp method to
   * return a 3D array of integers that represent the RGB values of every pixel that is being
   * generated for the Mosaic object.
   *
   * @return the RGB values of all the pixels being generated as a 3D array of integers.
   */
  public int[][][] apply() {
    return mosaicHelp(seeds);
  }

  /**
   * Protected method that takes in the number of seeds as an int data type and returns a 3D array
   * of integers that represent the RGB values of every pixel that is being generated for the new
   * transformed image.
   *
   * @param seeds the number of seeds taken in as a int data type.
   * @return the RGB values of all the pixels being generated for the new image as a 3D array of
   *      integers
   */
  private int[][][] mosaicHelp(int seeds) throws IllegalArgumentException {

    int[][] seedArray = getSeedArray(seeds);
    int[][] seedCluster = new int[height][width];
    int[][] rgbAverage = new int[seeds][3];
    int[] numPixels = new int[seeds];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        seedCluster[i][j] = seedIndex(seedArray, i, j);
        rgbAverage[seedCluster[i][j]][0] += rgb[i][j][0];
        rgbAverage[seedCluster[i][j]][1] += rgb[i][j][1];
        rgbAverage[seedCluster[i][j]][2] += rgb[i][j][2];
        numPixels[seedCluster[i][j]]++;
      }
    }
    for (int i = 0; i < rgbAverage.length; i++) {
      if (numPixels[i] != 0) {
        rgbAverage[i][0] /= numPixels[i];
        rgbAverage[i][1] /= numPixels[i];
        rgbAverage[i][2] /= numPixels[i];
      }
    }
    int[][][] mosaicRBGArray = new int[height][width][3];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        for (int k = 0; k < 3; k++) {
          mosaicRBGArray[i][j][k] = rgbAverage[seedCluster[i][j]][k];
        }
      }
    }
    return mosaicRBGArray;
  }

  /**
   * Private helper method that takes in a 2D array of integers, height as an int data type, and
   * width as an int data type and returns the index of the closest seed in the array given as a
   * parameter to the pixel indicated by the height and width taken in.
   *
   * @param seedArray a 2D array of integers containing the seed's height and width.
   * @param i         the height of the pixel taken in as an int data type.
   * @param j         the width of the pixel taken in as an int data type.
   * @return the index of the closest seed in the array taken in as an int data type
   */
  private int seedIndex(int[][] seedArray, int i, int j) {
    int index = -1;
    double distance = Double.MAX_VALUE;
    for (int k = 0; k < seedArray.length; k++) {
      double temp_distance = findEDistance(i, j, seedArray[k][0], seedArray[k][1]);
      if (temp_distance < distance) {
        distance = temp_distance;
        index = k;
      }
    }
    return index;
  }

  /**
   * Private helper method that takes in the number of seeds for the mosaic as an int data type and
   * returns a 2D array of integers that contain randomized integers between 0 and the max height
   * and width of the picture based on the number in the parameter.
   *
   * @param nSeed the number of seeds for the mosaic as an int data type.
   * @return a 2D array of integers containing height of width of randomized pixels
   */
  private int[][] getSeedArray(int nSeed) {

    int[][] seedsArray = new int[nSeed][2];
    for (int i = 0; i < nSeed; i++) {
      seedsArray[i][0] = (int) (Math.random() * height);
      seedsArray[i][1] = (int) (Math.random() * width);
    }
    return seedsArray;
  }

  /**
   * Private helper method that finds the Euclidean distance of the points given in the parameter
   * and returns that value.
   *
   * @return the Euclidean distance between the two points as a double data type.
   */
  private double findEDistance(int x1, int y1, int x2, int y2) {
    return Math.pow(Math.pow(x1 - x2, 2)
            + Math.pow(y1 - y2, 2), 0.5);
  }
}
