import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFrame;

// Program to display an m-times table with n entries, in a window.
// It takes two integer command line arguments, m and n.
public class TimesTable extends JFrame
{
  // Constructor
  public TimesTable(int multiplier, int noOfEntries)
  {
    setTitle("Times Table");
    Container contents = getContentPane();

    contents.setLayout(new GridLayout(noOfEntries, 5, 60, 10));
    for (int number = 1; number <= noOfEntries; number++)
    {
      int product = number * multiplier;

      contents.add(new JLabel("" + number));
      contents.add(new JLabel("X"));
      contents.add(new JLabel("" + multiplier));
      contents.add(new JLabel("="));
      contents.add(new JLabel("" + product));
    }

    contents.setBackground(Color.blue);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    pack();
  } // class TimesTable

  // Create a TimesTable and make it appear on screen.
  public static void main(String[] args)
  {
    int multiplierInt = Integer.parseInt(args[0]);
    int numberOfEntries = Integer.parseInt(args[1]);
    TimesTable theTimesTable = new TimesTable(multiplierInt, numberOfEntries);
    theTimesTable.setVisible(true);
  } // main

} // TimesTable
