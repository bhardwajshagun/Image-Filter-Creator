package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import image.Blur;
import image.Dither;
import image.Greyscale;
import image.Mosaic;
import image.Sepia;
import image.Sharpen;
import image.CheckerboardImage;
import image.FranceFlag;
import image.GreeceFlag;
import image.HorizontalStripes;
import image.Image;
import image.SwitzerlandFlag;
import image.VerticalStripes;

import utility.ImageUtil;

/**
 * This class contains the ScriptController that generates and alters the images based on the
 * commands given in the file used as the first argument. It also calls methods from the ImageUtil
 * class to create and alter said images.
 */
public class ScriptController extends StartController {

  /**
   * Main method that reads commands from the file provided as the first argument. Reads the image
   * data from files given using ImageUtil. Uses the classes in the image package to alter or create
   * new images. Saves the new images using the ImageUtil class.
   *
   * @param args the first argument is the file that contains the commands.
   */
  public static void main(String[] args) {
    ScriptController cObj = new ScriptController();
    int[][][] rgb = null;
    int height = -1;
    int width = -1;
    File filename = new File(args[0]);
    try {
      Scanner sc = new Scanner(filename);
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String[] terms = line.split(" ");
        if (terms[0].equals("generate")) {
          try {
            if (terms.length < 3) {
              System.out.println("Generate needs more arguments.");
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
                System.out.println("Vertical flag needs more arguments.");
              } else {
                Image vertical = new VerticalStripes(Integer.parseInt(terms[2]),
                        Integer.parseInt(terms[3]));
                rgb = vertical.apply();
                height = vertical.getHeight();
                width = vertical.getWidth();
              }
            } else if (terms[1].equals("horizontalstripes")) {
              if (terms.length < 4) {
                System.out.println("Horizontal flag needs more arguments.");
              } else {
                Image horizontal = new HorizontalStripes(Integer.parseInt(terms[2]),
                        Integer.parseInt(terms[3]));
                rgb = horizontal.apply();
                height = horizontal.getHeight();
                width = horizontal.getWidth();
              }
            } else {
              System.out.println("Cannot generate image.");
            }
          } catch (NumberFormatException e) {
            System.out.println("Invalid argument for generating image; must be integers.");
          }
        } else if (terms[0].equals("load")) {
          rgb = cObj.getRGB(terms[1]);
          height = cObj.getHeight(terms[1]);
          width = cObj.getWidth(terms[1]);
          if (rgb == null) {
            System.out.println("Could not load image.");
          }
        } else if (rgb == null) {
          System.out.println("Must load image to alter or save it.");
        } else if (terms[0].equals("save")) {
          cObj.saveImage(rgb, height, width, terms[1]);
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
          System.out.println("Command not found.");
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("File not found.");
    }
  }

  /**
   * Private method that opens an image and returns the RGB array of a image file. If file is not
   * found, returns null.
   *
   * @return the rgb array of the image.
   */
  private int[][][] getRGB(String filename) {
    try {
      return ImageUtil.readImage(filename);
    } catch (IOException e) {
      return null;
    }
  }

  /**
   * Private method that opens an image and returns the height of a image file. If file is not
   * found, returns -1.
   *
   * @return the height of the image.
   */
  private int getHeight(String filename) {
    try {
      return ImageUtil.getHeight(filename);
    } catch (IOException e) {
      return -1;
    }
  }

  /**
   * Private method that opens an image and returns the height of a image file. If file is not
   * found, returns -1.
   *
   * @return the width of the image.
   */
  private int getWidth(String filename) {
    try {
      return ImageUtil.getWidth(filename);
    } catch (IOException e) {
      return -1;
    }
  }

  /**
   * Private method that saves an image using the RGB array, height, width, and new filename.
   */
  private void saveImage(int[][][] rgb, int height, int width, String newFilename) {
    try {
      ImageUtil.writeImage(rgb, width, height, newFilename);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}