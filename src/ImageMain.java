import java.io.IOException;

import image.Blur;
import image.CheckerboardImage;
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

/**
 * This class contains a Main that generates and alters the images based on the images read and
 * written from the files provided. It also calls methods from the utility.ImageUtil class to create and
 * alter said images.
 */
public class ImageMain {

  /**
   * Main method that reads the image data from files given using utility.ImageUtil. Uses the classes in the
   * imagealteration or image package to alter or save images. Save the new images using
   * the utility.ImageUtil class.
   *
   * @param args the default parameter for a Java main.
   */
  public static void main(String[] args) {

    String[] filenames = new String[]{"test1"};
    for (String fname : filenames) {
      try {
        String fullFilename = fname + ".png";
        int[][][] rgb = ImageUtil.readImage(fullFilename);
        int height = ImageUtil.getHeight(fullFilename);
        int width = ImageUtil.getWidth(fullFilename);
        Image blur1 = new Blur(rgb, height, width);
        ImageUtil.writeImage(blur1.apply(), blur1.getWidth(),
                blur1.getHeight(), fname + "_blur.png");
        Image sharpen1 = new Sharpen(rgb, height, width);
        ImageUtil.writeImage(sharpen1.apply(), sharpen1.getWidth(),
                sharpen1.getHeight(), fname + "_sharpen.png");
        Image greyscale1 = new Greyscale(rgb, height, width);
        ImageUtil.writeImage(greyscale1.apply(), greyscale1.getWidth(),
                greyscale1.getHeight(), fname + "_greyscale.png");
        Image sepia1 = new Sepia(rgb, height, width);
        ImageUtil.writeImage(sepia1.apply(), sepia1.getWidth(),
                sepia1.getHeight(), fname + "_sepia.png");
        //testing mosaic
        Image mosaic1 = new Mosaic(rgb, height,width, 1000);
        ImageUtil.writeImage(mosaic1.apply(),mosaic1.getWidth()
                , mosaic1.getHeight(), fname + "_mosaic.png" );
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      int[][][] rgb = ImageUtil.readImage("test1_blur.png");
      int height = ImageUtil.getHeight("test1_blur.png");
      int width = ImageUtil.getWidth("test1_blur.png");
      Image blur2 = new Blur(rgb, height, width);
      ImageUtil.writeImage(blur2.apply(), blur2.getWidth(),
              blur2.getHeight(), "test1_blurx2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    String[] filenames3 = new String[]{"test1_sharpen", "test2_sharpen"};
    for (String fname : filenames3) {
      try {
        String fullFilename = fname + ".png";
        int[][][] rgb = ImageUtil.readImage(fullFilename);
        int height = ImageUtil.getHeight(fullFilename);
        int width = ImageUtil.getWidth(fullFilename);
        Image sharpen2 = new Sharpen(rgb, height, width);
        ImageUtil.writeImage(sharpen2.apply(), sharpen2.getWidth(),
                sharpen2.getHeight(), fname + "x2.png");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Image horizontal1 = new HorizontalStripes(65, 500);
    Image horizontal2 = new HorizontalStripes(700, 1000);
    try {
      ImageUtil.writeImage(horizontal1.apply(), horizontal1.getWidth(),
              horizontal1.getHeight(), "horizontal1.png");
      ImageUtil.writeImage(horizontal2.apply(), horizontal2.getWidth(),
              horizontal2.getHeight(), "horizontal2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }


    Image vertical1 = new VerticalStripes(500, 700);
    Image vertical2 = new VerticalStripes(400, 650);
    try {
      ImageUtil.writeImage(vertical1.apply(), vertical1.getWidth(),
              vertical1.getHeight(), "vertical1.png");
      ImageUtil.writeImage(vertical2.apply(), vertical2.getWidth(),
              vertical2.getHeight(), "vertical2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Image checkerboard1 = new CheckerboardImage(50);
    Image checkerboard2 = new CheckerboardImage(10);
    try {
      ImageUtil.writeImage(checkerboard1.apply(), checkerboard1.getWidth(),
              checkerboard1.getHeight(), "checkerboard1.png");
      ImageUtil.writeImage(checkerboard2.apply(), checkerboard2.getWidth(),
              checkerboard2.getHeight(), "checkerboard2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Image france1 = new FranceFlag(600);
    Image france2 = new FranceFlag(210);
    try {
      ImageUtil.writeImage(france1.apply(), france1.getWidth(),
              france1.getHeight(), "france1.png");
      ImageUtil.writeImage(france2.apply(), france2.getWidth(),
              france2.getHeight(), "france2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Image switzerland1 = new SwitzerlandFlag(500);
    Image switzerland2 = new SwitzerlandFlag(340);
    try {
      ImageUtil.writeImage(switzerland1.apply(), switzerland1.getWidth(),
              switzerland1.getHeight(), "switzerland1.png");
      ImageUtil.writeImage(switzerland2.apply(), switzerland2.getWidth(),
              switzerland2.getHeight(), "switzerland2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }

    Image greece1 = new GreeceFlag(700);
    Image greece2 = new GreeceFlag(500);
    try {
      ImageUtil.writeImage(greece1.apply(), greece1.getWidth(),
              greece1.getHeight(), "greece1.png");
      ImageUtil.writeImage(greece2.apply(), greece2.getWidth(),
              greece2.getHeight(), "greece2.png");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}