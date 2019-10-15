/**
 * Class is part of the package controller
 */

package controller;

/**
 * This abstract class represents a single StartController that contains a main method that takes in
 * arguments as String Data types. This class is extended to the GUIController and ScriptController
 * subclasses and calls their controller based on parameter taken in by the main.
 */
public abstract class StartController {

  /**
   * Public static method that takes in arguments in the form of Strings and returns nothing. Will
   * implement either the GUIController or Script Controller based on the parameter taken in. Prints
   * that the command is not found otherwise.
   *
   * @param args the arguments taken in as String data types
   */
  public static void main(String[] args) {
    StartController controller;
    if (args.length == 0) {
      System.out.println("Program needs more arguments");
      System.exit(-1);
    }
    if (args[0].equals("-interactive")) {
      controller = new GUIController();
    } else if (args[0].equals("-script")) {
      if (args.length == 1) {
        System.out.println("Enter a script file");
      } else {
        int n = args.length - 1;
        String[] newArray = new String[n];
        System.arraycopy(args, 1, newArray, 0, n);
        ScriptController.main(newArray);
      }
    } else {
      System.out.println("Command not found");
    }
  }
}
