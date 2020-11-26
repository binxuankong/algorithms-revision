import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Program to display a greeting to family, in a window.
public class HelloFamily extends JFrame
{
  // Constructor.
  public HelloFamily()
  {
    setTitle("Hello Family");
    Container contents = getContentPane();

    contents.setLayout(new FlowLayout());
    contents.add(new JLabel("Hello Aaron!"));
    contents.add(new JLabel("Hello Bin Xuan!"));
    contents.add(new JLabel("Hello Fei Shyuan!"));
    contents.add(new JLabel("Hello Ian!"));
    contents.add(new JLabel("Hello Jae Sheng!"));
    contents.add(new JLabel("Hello Kay Li!"));
    contents.add(new JLabel("Hello Matthew!"));
    contents.add(new JLabel("Hello Megan!"));
    contents.add(new JLabel("Hello Pui Yee!"));
    contents.add(new JLabel("Hello Sera!"));
    contents.add(new JLabel("Hello Stuart!"));
    contents.add(new JLabel("Hello Zheng Yang!"));
 
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  } // HelloFamily

  // Create a HelloFamily and make it appear on screen.
  public static void main(String[] args)
  {
    HelloFamily theHelloFamily = new HelloFamily();
    theHelloFamily.setVisible(true);
  } // main

} // class HelloFamily
