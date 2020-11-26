import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

// Program to display a greeting to family, in a window.
public class HelloFamily extends JFrame
{
  // Constructor.
  public HelloFamily(int noOfRow, int noOfColumn,
                     int rowPixel, int columnPixel)
  {
    setTitle("Hello Family");
    Container contents = getContentPane();

    contents.setLayout(new GridLayout(noOfRow, noOfColumn,
                                      rowPixel, columnPixel));
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
  // Produce 10 windows, each having a different gap between the components.
  // The row gaps range from 2 to 20 in steps of 2 pixels.
  // The column gaps range from 4 to 40 in steps of 4.
  public static void main(String[] args)
  {
    int noOfRows = Integer.parseInt(args[0]);
    int noOfColumns = Integer.parseInt(args[1]);
    for (int gap = 2; gap <=20; gap += 2)
    {
      HelloFamily theHelloFamily = new HelloFamily(noOfRows, noOfColumns,
                                                   gap, 2 * gap);
      theHelloFamily.setVisible(true);
    }
    
  } // main

} // class HelloFamily
