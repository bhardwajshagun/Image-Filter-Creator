/**
 * Class is part of the image package
 */

package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import image.Blur;
import image.CheckerboardImage;
import image.Dither;
import image.FranceFlag;
import image.GreeceFlag;
import image.Greyscale;
import image.HorizontalStripes;
import image.Image;
import image.Mosaic;
import image.Sepia;
import image.Sharpen;
import image.SwitzerlandFlag;
import image.VerticalStripes;
import utility.ImageUtil;
import view.JFrameView;

/**
 * This class represents a single GUIController that contains rgb list, height list, width list,
 * counter, and view of the controller. The method performed in this class is parsing the script the
 * user inputs in the GUI, producing a past image by undoing or redoing a change in the GUI,
 * resetting or incrementing the list in the GUI, setter and getter methods for the attributes, and
 * returning new RGB values after any image alternation and generation. This class is extended by
 * StartController superclass.
 */
public class GUIController extends StartController {

  /**
   * RGB list as a 3D array of integers.
   */
  private List<int[][][]> rgbList;

  /**
   * Height list as a list of integers.
   */
  private List<Integer> heightList;

  /**
   * Width list as a list of integers.
   */
  private List<Integer> widthList;

  /**
   * Counter as a int data type.
   */
  private int counter;

  /**
   * View as a JFrameView.
   */
  private JFrameView view;

  /**
   * Constructs the GUIController object that takes in nothing as a parameter and initializes it
   * with the view and with the rgb, height, width lists as new empty Linked Lists. The counter of
   * the controller is set to -1.
   */
  public GUIController() {
    view = new JFrameView(this);
    rgbList = new LinkedList<>();
    heightList = new LinkedList<>();
    widthList = new LinkedList<>();
    counter = -1;
  }

  /**
   * Public method that taken in the user input from the GUI as a string and parses it using a
   * Scanner and returns nothing. It will call the necessary methods based on the script taken. The
   * GUIController will produce error messages if certain commands being called from the script
   * require more arguments, if the image could not be loaded or generated, or if the command could
   * not be found.
   *
   * @param input the user input taken in as a String data type.
   */
  public void parseScript(String input) {
    int[][][] rgb = null;
    int height = -1;
    int width = -1;
    Scanner sc = new Scanner(input);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      String[] terms = line.split(" ");
      if (terms[0].equals("generate")) {
        try {
          if (terms.length < 3) {
            JOptionPane.showMessageDialog(view,
                    "Generate needs more arguments",
                    "Error", JOptionPane.ERROR_MESSAGE);
          } else if (terms[1].equals("france")) {
            Image france = new FranceFlag(Integer.parseInt(terms[2]));
            rgb = france.apply();
            height = france.getHeight();
            width = france.getWidth();
          } else if (terms[1].equals("switzerland")) {
            Image swiss = new SwitzerlandFlag(Integer.parseInt(terms[2]));
            rgb = swiss.apply();
            height = swiss.getHeight();
            width = swiss.getWidth();
          } else if (terms[1].equals("greece")) {
            Image greece = new GreeceFlag(Integer.parseInt(terms[2]));
            rgb = greece.apply();
            height = greece.getHeight();
            width = greece.getWidth();
          } else if (terms[1].equals("checkerboard")) {
            Image checker = new CheckerboardImage(Integer.parseInt(terms[2]));
            rgb = checker.apply();
            height = checker.getHeight();
            width = checker.getWidth();
          } else if (terms[1].equals("verticalstripes")) {
            if (terms.length < 4) {
              JOptionPane.showMessageDialog(view,
                      "Vertical flag needs more arguments",
                      "Error", JOptionPane.ERROR_MESSAGE);
            } else {
              Image vertical = new VerticalStripes(Integer.parseInt(terms[2]),
                      Integer.parseInt(terms[3]));
              rgb = vertical.apply();
              height = vertical.getHeight();
              width = vertical.getWidth();
            }
          } else if (terms[1].equals("horizontalstripes")) {
            if (terms.length < 4) {
              JOptionPane.showMessageDialog(view,
                      "Horizontal flag needs more arguments.",
                      "Error", JOptionPane.ERROR_MESSAGE);
            } else {
              Image horizontal = new HorizontalStripes(Integer.parseInt(terms[2]),
                      Integer.parseInt(terms[3]));
              rgb = horizontal.apply();
              height = horizontal.getHeight();
              width = horizontal.getWidth();
            }
          } else {
            JOptionPane.showMessageDialog(view,
                    "Cannot generate image",
                    "Error", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NumberFormatException e) {
          JOptionPane.showMessageDialog(view,
                  "Invalid argument for generating image; must be integers",
                  "Error", JOptionPane.ERROR_MESSAGE);
        }
      } else if (terms[0].equals("load")) {
        try {
          rgb = ImageUtil.readImage(terms[1]);
          height = ImageUtil.getHeight(terms[1]);
          width = ImageUtil.getWidth(terms[1]);
        } catch (IOException e) {
          e.printStackTrace();
        }
        if (rgb == null) {
          JOptionPane.showMessageDialog(view,
                  "Could not load image",
                   "Error", JOptionPane.ERROR_MESSAGE);
        }
      } else if (rgb == null) {
        JOptionPane.showMessageDialog(view,
                "Must load image to alter or save it",
                "Error", JOptionPane.ERROR_MESSAGE);
      } else if (terms[0].equals("save")) {
        try {
          ImageUtil.writeImage(rgb, width, height, terms[1]);
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else if (terms[0].equals("dither")) {
        Image dither = new Dither(rgb, height, width);
        rgb = dither.apply();
      } else if (terms[0].equals("blur")) {
        Image blur = new Blur(rgb, height, width);
        rgb = blur.apply();
      } else if (terms[0].equals("sharpen")) {
        Image sharpen = new Sharpen(rgb, height, width);
        rgb = sharpen.apply();
      } else if (terms[0].equals("greyscale")) {
        Image greyscale = new Greyscale(rgb, height, width);
        rgb = greyscale.apply();
      } else if (terms[0].equals("sepia")) {
        Image sepia = new Sepia(rgb, height, width);
        rgb = sepia.apply();
      } else if (terms[0].equals("mosaic")) {
        Image mosaic = new Mosaic(rgb, height, width, Integer.parseInt(terms[1]));
        rgb = mosaic.apply();
      } else {
        JOptionPane.showMessageDialog(view,
                "Command not found",
                "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  /**
   * Public method that takes in nothing and reset the GUIController's attributes and returns
   * nothing. Method is called after the user saves the image currently on the GUI.
   */
  public void reset() {
    int[][][] rgb = rgbList.get(counter);
    int height = heightList.get(counter);
    int width = widthList.get(counter);
    rgbList = new LinkedList<>();
    heightList = new LinkedList<>();
    widthList = new LinkedList<>();
    rgbList.add(rgb);
    heightList.add(height);
    widthList.add(width);
    counter = 0;
  }

  /**
   * Public method that takes in nothing and returns a boolean statement based on if the user can
   * undo and go back to a past picture, whose rgb, height, and width values are in the linked
   * lists.
   *
   * @return a boolean statement is the undo step is currently possible in the GUI
   */
  public boolean undo() {
    if (counter < 1) {
      return false;
    }
    counter--;
    return true;
  }

  /**
   * Public method that takes in nothing and returns a boolean statement based on if the user can
   * redo and go back to a past picture, whose rgb, height, and width values are in the linked
   * lists.
   *
   * @return a boolean statement is the redo step is currently possible in the GUI
   */
  public boolean redo() {
    if (counter < (rgbList.size() - 1)) {
      counter++;
      return true;
    }
    return false;
  }

  /**
   * Public method that takes in a 3D array of integers that contain the rgb values of an image and
   * adds it to the rgb linked list and returns nothing.
   *
   * @param rgb 3D array of integers that contain the rgb values of the image.
   */
  public void setRgb(int[][][] rgb) {
    rgbList.add(rgb);
    counter++;
  }

  /**
   * Public method that takes in a integer that contain the height of an image and adds it to the
   * height linked list and returns nothing.
   *
   * @param height the height of the image as an int data type
   */
  public void setHeight(int height) {
    heightList.add(height);
  }

  /**
   * Public method that takes in a integer that contain the width of an image and adds it to the
   * width linked list and returns nothing.
   *
   * @param width the width of the image as an int data type
   */
  public void setWidth(int width) {
    widthList.add(width);
  }

  /**
   * Public getter method that takes in nothing and returns the height of the image as an int data
   * type if the height list is greater than zero. Returns -1 otherwise.
   *
   * @return the height of the image as an int data type
   */
  public int getHeight() {
    if (heightList.size() > 0) {
      return heightList.get(counter);
    } else {
      return -1;
    }
  }

  /**
   * Public getter method that takes in nothing and returns the width of the image as an int data
   * type if the width list is greater than zero. Returns -1 otherwise.
   *
   * @return the width of the image as an int data type
   */
  public int getWidth() {
    if (widthList.size() > 0) {
      return widthList.get(counter);
    } else {
      return -1;
    }
  }

  /**
   * Public getter method that takes in nothing and returns the RGB array of the image as a 3D array
   * of integers if the rgb list is greater than zero. Returns -1 otherwise.
   *
   * @return 3D array of integers that contain the rgb values of the image.
   */
  public int[][][] getRGB() {
    if (rgbList.size() > 0) {
      return rgbList.get(counter);
    } else {
      return null;
    }
  }

  /**
   * Public method that takes in nothing and blurs the image, whose parameters are taken from the
   * rgb, height, and width lists. The counter is the index of where the picture being blurred is in
   * the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] blur() {
    Image filter = new Blur(rgbList.get(counter), heightList.get(counter), widthList.get(counter));
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in nothing and sharpens the image, whose parameters are taken from the
   * rgb, height, and width lists. The counter is the index of where the picture being sharpened is
   * in the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] sharpen() {
    Image filter = new Sharpen(rgbList.get(counter),
                                heightList.get(counter), widthList.get(counter));
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in nothing and greyscales the image, whose parameters are taken from
   * the rgb, height, and width lists. The counter is the index of where the picture being
   * greyscaled is in the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] greyscale() {
    Image filter = new Greyscale(rgbList.get(counter),
                                  heightList.get(counter), widthList.get(counter));
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in nothing and sepias the image, whose parameters are taken from the
   * rgb, height, and width lists. The counter is the index of where the picture being sepiaed is in
   * the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] sepia() {
    Image filter = new Sepia(rgbList.get(counter), heightList.get(counter), widthList.get(counter));
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in nothing and mosaics the image, whose parameters are taken from the
   * rgb, height, and width lists. The counter is the index of where the picture being mosaiced is
   * in the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] mosaic(int seeds) {
    Image filter = new Mosaic(rgbList.get(counter),
                              heightList.get(counter), widthList.get(counter), seeds);
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in nothing and dithers the image, whose parameters are taken from the
   * rgb, height, and width lists. The counter is the index of where the picture being dithered is
   * in the linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] dither() {
    Image filter = new Dither(rgbList.get(counter),
                              heightList.get(counter), widthList.get(counter));
    int[][][] newRgb = filter.apply();
    incrementList(newRgb, heightList.get(counter), widthList.get(counter));
    return newRgb;
  }

  /**
   * Public method that takes in a height and width as int data types and generates a rainbow
   * horizontal stripes image based on the values of the parameters and adds the rgb, height, and
   * width values to the respective linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] horizontal(int height, int width) {
    Image create = new HorizontalStripes(height, width);
    int[][][] newRgb = create.apply();
    incrementList(newRgb, height, width);
    return newRgb;
  }

  /**
   * Public method that takes in a height and width as int data types and generates a rainbow
   * vertical stripes image based on the values of the parameters and adds the rgb, height, and
   * width values to the respective linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] vertical(int height, int width) {
    Image create = new VerticalStripes(height, width);
    int[][][] newRgb = create.apply();
    incrementList(newRgb, height, width);
    return newRgb;
  }

  /**
   * Public method that takes in a length of a side as an int data type and generates a checkerboard
   * image based on the value of the parameter and adds the rgb, height, and width values to the
   * respective linked lists.
   *
   * @return 3D array of integers that contain the rgb values of the new image.
   */
  public int[][][] checkerboard(int side) {
    Image checker = new CheckerboardImage(side);
    int[][][] newRgb = checker.apply();
    incrementList(newRgb, side * 8, side * 8);
    return newRgb;
  }

  /**
   * Private method that takes in rgb values, height, and width of an image and adds them to the
   * respective linked lists and increases the counter by 1.
   *
   * @param rgb    3D array of integers that contain the rgb values of the image.
   * @param height the height of the image as an int data type.
   * @param width  the width of the image as an int data type.
   */
  private void incrementList(int[][][] rgb, int height, int width) {
    counter++;
    rgbList.add(rgb);
    heightList.add(height);
    widthList.add(width);
  }
}
