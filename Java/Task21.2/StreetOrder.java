import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Program to sort a file from house number order into delivery order, that is,
// the order of walking up one side of the street and down the other.
// All the streets are symmetrical, with odd numbered houses on the left, and
// even numbered ones on the right, both ascending in the same direction.
public class StreetOrder
{
  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      if (args.length != 2)
        throw new IllegalArgumentException
                    ("There must exactly two arguments: infile outfile");

      input = new BufferedReader(new FileReader(args[0]));
      output = new PrintWriter(new FileWriter(args[1]));

      // The List for storing the lines.
      List<String> lineList = new ArrayList<String>();

      // Read the lines into lineList.
      String currentLine;
      while ((currentLine = input.readLine()) != null)
        lineList.add(currentLine);

      // Now output them in street order.
      // Even number of lines.
      if (lineList.size() % 2 == 0)
      {
        // Loop through the even indices of the list (The odd number houses).
        for (int index = 0; index < lineList.size(); index += 2)
          output.println(lineList.get(index));
        // Loop through the odd indices of the list, in reversed.
        for (int index = lineList.size() - 1; index >= 0; index -= 2)
          output.println(lineList.get(index));
      } // if
      // Odd number of lines.
      else
      {
        // Loop through the even indices of the list (The odd number houses).
        for (int index = 0; index < lineList.size(); index += 2)
          output.println(lineList.get(index));
        // Loop through the odd indices of the list, in reversed.
        for (int index = lineList.size() - 2; index >= 0; index -= 2)
          output.println(lineList.get(index));
      } // else
    } // try
    catch (Exception exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try { if (input != null) input.close(); }
      catch (IOException exception)
        { System.err.println("Could not close input " + exception); }
      if (output != null)
      {
        output.close();
        if (output.checkError())
          System.err.println("Something went wrong with the output");
      } // if
    } // finally
  } // main

} // class StreetOrder
