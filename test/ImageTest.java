import org.junit.Test;

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

/**
 * A JUnit test class for the classes from the image package.
 */
public class ImageTest {

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlur1() {
    Image illegalBlur = new Blur(null, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlur2() {
    Image illegalBlur = new Blur(new int [5][5][3], -5, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalBlur3() {
    Image illegalBlur = new Blur(new int [10][200][3], 100, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSharpen1() {
    Image illegalSharpen = new Sharpen(null, 500, 1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSharpen2() {
    Image illegalSharpen = new Sharpen(new int [100][100][3], -1, 250);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSharpen3() {
    Image illegalSharpen = new Sharpen(new int [500][500][3], 150, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreyscale1() {
    Image illegalGreyscale = new Greyscale(null, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreyscale2() {
    Image illegalGreyscale = new Greyscale(new int [1000][1000][3], 0, 400);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreyscale3() {
    Image illegalGreyscale = new Greyscale(new int [200][200][3], 500, -8);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSepia1() {
    Image illegalSepia = new Sepia(null, 100, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSepia2() {
    Image illegalSepi3 = new Sepia(new int [200][200][3], 480, -5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSepia3() {
    Image illegalSepia = new Sepia(new int [200][200][3], 0, 275);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalFranceFlag() {
    Image illegalFranceFlag = new FranceFlag(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalSwitzerlandFlag() {
    Image illegalSwitzerlandFlag = new SwitzerlandFlag(-5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalGreeceFlag() {
    Image illegalGreeceFlag = new GreeceFlag(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHorizontalStripes1() {
    Image illegalHorizontalStripes = new HorizontalStripes(0, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalHorizontalStripes2() {
    Image illegalHorizontalStripes = new HorizontalStripes(20, -4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalVerticalStripes1() {
    Image illegalVerticalStripes = new VerticalStripes(10, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalVerticalStripes2() {
    Image illegalVerticalStripes = new VerticalStripes(-8, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCheckerboard1() {
    Image illegalCheckerboard = new CheckerboardImage(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCheckerboard2() {
    Image illegalCheckerboard = new CheckerboardImage(-20);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMosaic() {
    Image illegalMosaic = new Mosaic(new int[200][200][3], 40, 20, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMosaic2() {
    Image illegalMosaic = new Mosaic(new int[200][200][3], 40, 20, -5);
  }

}
