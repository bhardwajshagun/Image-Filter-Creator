/**
 * Class is part of view package.
 */

package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.GUIController;
import utility.ImageUtil;

/**
 * This class represents a single JFrameView that contains the image panel, image label, script text
 * , and controller. The methods performed in this class are creating the View for the user to
 * implement, performing the actions done by the user, inputting the height and width, drawing the
 * image, and producing error messages when needed. This subclass extends the JFrame class and
 * implements the ActionListener and IView superclasses.
 */
public class JFrameView extends JFrame implements ActionListener, IView {

  /**
   * ImagePanel for the View as a JPanel.
   */
  private JPanel imagePanel;

  /**
   * ImageLabel for the View as a JLabel.
   */
  private JLabel imageLabel;

  /**
   * ScriptText for the View as a JTextArea.
   */
  private JTextArea scriptText;

  /**
   * Controller for the View as a GUIController.
   */
  private GUIController controller;

  /**
   * Constructs the JFrameView object that takes in and initializes it with the GUIController that
   * is invoked by the superclass IView's constructor.
   *
   * @param controller Controller for the View as a GUIController.
   */
  public JFrameView(GUIController controller) {
    super();
    this.controller = controller;
    JFrame mainFrame = new JFrame("Image");
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    imagePanel = new JPanel();
    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(500, 500));
    imagePanel.setBorder(BorderFactory.createTitledBorder("Showing image"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    imagePanel.add(imageScrollPane);
    scriptText = new JTextArea(10, 20);
    scriptText.setBorder(BorderFactory.createTitledBorder("Script"));
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem fileItem1 = new JMenuItem("Load");
    fileItem1.setActionCommand("Load Image");
    fileItem1.addActionListener(this);
    JMenuItem fileItem2 = new JMenuItem("Save");
    fileItem2.setActionCommand("Save Image");
    fileItem2.addActionListener(this);
    fileMenu.add(fileItem1);
    fileMenu.add(fileItem2);
    JMenu filterMenu = new JMenu("Filter");
    JMenuItem filterItem1 = new JMenuItem("Blur");
    filterItem1.setActionCommand("blur");
    filterItem1.addActionListener(this);
    JMenuItem filterItem2 = new JMenuItem("Sharpen");
    filterItem2.setActionCommand("sharpen");
    filterItem2.addActionListener(this);
    JMenuItem filterItem3 = new JMenuItem("Sepia");
    filterItem3.setActionCommand("sepia");
    filterItem3.addActionListener(this);
    JMenuItem filterItem4 = new JMenuItem("Greyscale");
    filterItem4.setActionCommand("greyscale");
    filterItem4.addActionListener(this);
    JMenuItem filterItem5 = new JMenuItem("Mosaic");
    filterItem5.setActionCommand("mosaic");
    filterItem5.addActionListener(this);
    JMenuItem filterItem6 = new JMenuItem("Dither");
    filterItem6.setActionCommand("dither");
    filterItem6.addActionListener(this);
    filterMenu.add(filterItem1);
    filterMenu.add(filterItem2);
    filterMenu.add(filterItem3);
    filterMenu.add(filterItem4);
    filterMenu.add(filterItem5);
    filterMenu.add(filterItem6);
    JMenu generateMenu = new JMenu("Create Image");
    JMenuItem generateItem1 = new JMenuItem("Vertical Rainbow Stripes");
    generateItem1.setActionCommand("vertical");
    generateItem1.addActionListener(this);
    JMenuItem generateItem2 = new JMenuItem("Horizontal Rainbow Stripes");
    generateItem2.setActionCommand("horizontal");
    generateItem2.addActionListener(this);
    JMenuItem generateItem3 = new JMenuItem("Checkerboard");
    generateItem3.setActionCommand("checkerboard");
    generateItem3.addActionListener(this);
    generateMenu.add(generateItem1);
    generateMenu.add(generateItem2);
    generateMenu.add(generateItem3);
    JMenu scriptMenu = new JMenu("Script");
    JMenuItem scriptItem = new JMenuItem("Read script");
    scriptItem.setActionCommand("script");
    scriptItem.addActionListener(this);
    scriptMenu.add(scriptItem);
    JMenu editMenu = new JMenu("Edit");
    JMenuItem editItem1 = new JMenuItem("Undo");
    editItem1.setActionCommand("undo");
    editItem1.addActionListener(this);
    editMenu.add(editItem1);
    JMenuItem editItem2 = new JMenuItem("Redo");
    editItem2.setActionCommand("redo");
    editItem2.addActionListener(this);
    editMenu.add(editItem1);
    editMenu.add(editItem2);
    menuBar.add(fileMenu);
    menuBar.add(filterMenu);
    menuBar.add(generateMenu);
    menuBar.add(scriptMenu);
    menuBar.add(editMenu);
    mainPanel.add(imagePanel);
    mainPanel.add(scriptText);
    mainFrame.setJMenuBar(menuBar);
    mainFrame.add(mainPanel);
    JFrameView.setDefaultLookAndFeelDecorated(false);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(600, 600);
    mainFrame.setVisible(true);
  }

  /**
   * Public method that takes in the ActionEvents as arguments in the parameters and performs
   * actions based on the cases taken in as String data types. Will produce the appropriate error
   * messages based on the action performed by the user.
   *
   * @param command the command performed in the user as an ActionEvent
   */
  public void actionPerformed(ActionEvent command) {
    int height = controller.getHeight();
    int width = controller.getWidth();
    int[][][] rgb;
    switch (command.getActionCommand()) {
      case "Load Image":
        JFileChooser fchooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG, JPG, GIF Images", "jpg", "gif", "png");
        fchooser.setFileFilter(filter);
        int retvalue = fchooser.showOpenDialog(JFrameView.this);
        if (retvalue == JFileChooser.APPROVE_OPTION) {
          File f = fchooser.getSelectedFile();
          imageLabel.setIcon(new ImageIcon(f.getAbsolutePath()));
          try {
            controller.setHeight(ImageUtil.getHeight(f.getAbsolutePath()));
            controller.setWidth(ImageUtil.getWidth(f.getAbsolutePath()));
            controller.setRgb(ImageUtil.readImage(f.getAbsolutePath()));
          } catch (IOException e) {
            e.printStackTrace();
          }
          imagePanel.updateUI();
        }
        break;
      case "Save Image":
        if (height == -1 || width == -1) {
          JOptionPane.showMessageDialog(JFrameView.this
                  , "Must load or create image before saving"
                  , "Error", JOptionPane.ERROR_MESSAGE);
        } else {
          JFileChooser chooser = new JFileChooser();
          chooser.setCurrentDirectory(new java.io.File("."));
          chooser.setDialogTitle("File Path");
          chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
          chooser.setAcceptAllFileFilterUsed(false);
          if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String filename = null;
            while (filename == null) {
              filename = JOptionPane.showInputDialog("Enter filename");
            }
            System.out.println("chooser folder" + chooser.getSelectedFile());
            System.out.println("filename: " + filename);
            String filepath = chooser.getSelectedFile() + "\\" + filename + ".png";
            System.out.println("filepath: " + filepath);
            rgb = controller.getRGB();
            controller.reset();
            try {
              ImageUtil.writeImage(rgb, width, height, filepath);
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        }
        break;
      case "blur":
        if (!errorMessage(height, width)) {
          rgb = controller.blur();
          drawImage(rgb, height, width);
        }
        break;
      case "sharpen":
        if (!errorMessage(height, width)) {
          rgb = controller.sharpen();
          drawImage(rgb, height, width);
        }
        break;
      case "greyscale":
        if (!errorMessage(height, width)) {
          rgb = controller.greyscale();
          drawImage(rgb, height, width);
        }
        break;
      case "sepia":
        if (!errorMessage(height, width)) {
          rgb = controller.sepia();
          drawImage(rgb, height, width);
        }
        break;
      case "dither":
        if (!errorMessage(height, width)) {
          rgb = controller.dither();
          drawImage(rgb, height, width);
        }
        break;
      case "mosaic":
        if (!errorMessage(height, width)) {
          int seeds = -1;
          while (seeds < 1) {
            seeds = Integer.parseInt(JOptionPane.showInputDialog("Enter number of seeds"));
            if (seeds < 1) {
              JOptionPane.showMessageDialog(JFrameView.this
                      , "Number of seeds must be greater than 0"
                      , "Error", JOptionPane.ERROR_MESSAGE);
            }
          }
          rgb = controller.mosaic(seeds);
          drawImage(rgb, height, width);
        }
        break;
      case "horizontal":
        height = inputHeight();
        width = inputWidth();
        rgb = controller.horizontal(height, width);
        drawImage(rgb, height, width);
        break;
      case "vertical":
        height = inputHeight();
        width = inputWidth();
        rgb = controller.vertical(height, width);
        drawImage(rgb, height, width);
        break;
      case "checkerboard":
        int square = -1;
        while (square < 1) {
          square = Integer.parseInt(JOptionPane.showInputDialog("Enter side of square"));
          if (square < 1) {
            JOptionPane.showMessageDialog(JFrameView.this
                    , "Square side must be greater than 0"
                    , "Error", JOptionPane.ERROR_MESSAGE);
          }
        }
        height = square * 8;
        width = height;
        controller.setHeight(height);
        controller.setWidth(width);
        rgb = controller.checkerboard(square);
        drawImage(rgb, height, width);
        break;
      case "script":
        controller.parseScript(scriptText.getText());
        break;
      case "undo":
        if (controller.undo()) {
          rgb = controller.getRGB();
          height = controller.getHeight();
          width = controller.getWidth();
          drawImage(rgb, height, width);
        } else {
          JOptionPane.showMessageDialog(JFrameView.this
                  , "Cannot undo"
                  , "Error", JOptionPane.ERROR_MESSAGE);
        }
        break;
      case "redo":
        if (controller.redo()) {
          rgb = controller.getRGB();
          height = controller.getHeight();
          width = controller.getWidth();
          drawImage(rgb, height, width);
        } else {
          JOptionPane.showMessageDialog(JFrameView.this
                  , "Cannot redo"
                  , "Error", JOptionPane.ERROR_MESSAGE);
        }
        break;
      default:
        //continue
    }
  }

  /**
   * Public method that takes in nothing and returns an int data type that is the height of the
   * image. Will produce an Error Message for the user in the view if the height is not a positive
   * number.
   *
   * @return the height as an int data type
   */
  public int inputHeight() {
    int height = -1;
    while (height < 1) {
      height = Integer.parseInt(JOptionPane.showInputDialog("Enter height"));
      if (height < 1) {
        JOptionPane.showMessageDialog(JFrameView.this
                , "Height must be greater than 0"
                , "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    return height;
  }

  /**
   * Public method that takes in nothing and returns an int data type that is the width of the
   * image. Will produce an Error Message for the user in the view if the width is not a positive
   * number.
   *
   * @return the width as an int data type
   */
  public int inputWidth() {
    int width = -1;
    while (width < 1) {
      width = Integer.parseInt(JOptionPane.showInputDialog("Enter width"));
      if (width < 1) {
        JOptionPane.showMessageDialog(JFrameView.this
                , "Width must be greater than 0"
                , "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    return width;
  }

  /**
   * Public method that takes in the RGB array, height, and width of an image and draws it in the
   * view for the user to see.
   *
   * @param rgb    3D array of integers containing the RGB values of every pixel in the image
   * @param height height of the image as an int data type
   * @param width  width of the image as an int data type
   */
  public void drawImage(int[][][] rgb, int height, int width) {
    BufferedImage output = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = rgb[i][j][0];
        int g = rgb[i][j][1];
        int b = rgb[i][j][2];
        int color = (r << 16) + (g << 8) + b;
        output.setRGB(j, i, color);
      }
    }
    imageLabel.setIcon(new ImageIcon(output));
    imagePanel.updateUI();
  }

  /**
   * Public method that takes in a height and width as int data types and return a boolean statement
   * if the user has reached a point in the view where an error message is appropriate. True if the
   * height or width is not a positive number, false otherwise.
   *
   * @param height height of the image as an int data type
   * @param width  width of the image as an int data type
   * @return boolean statement if the user has reached an error statement.
   */
  public boolean errorMessage(int height, int width) {
    if (height == -1 || width == -1) {
      JOptionPane.showMessageDialog(JFrameView.this
              , "Must load or create image before filtering"
              , "Error", JOptionPane.ERROR_MESSAGE);
      return true;
    }
    return false;
  }
}
