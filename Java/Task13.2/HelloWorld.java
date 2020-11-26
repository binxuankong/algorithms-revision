import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Program to display a Hello World greeting in Chinese in a window.
public class HelloWorld extends JFrame
{
  // Constructor
  public HelloWorld()
  {
    setTitle("Hello World in the");
    Container contents = getContentPane();
    contents.add(new JLabel("Ni hao, shi jie!"));
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  } // HelloWorld

  // Create a HelloWorld and make it appear on screen.
  public static void main(String[] args)
  {
    HelloWorld theHelloWorld1 = new HelloWorld();
    theHelloWorld1.setVisible(true);

    // Make a second greeting window.
    HelloWorld theHelloWorld2 = new HelloWorld();
    theHelloWorld2.setVisible(true);
  } // main

} // class HelloWorld
