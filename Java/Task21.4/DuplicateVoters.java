import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

// A program to duplicate voter identifications, followed by the number
// of duplicates found.
// The program takes two command line arguments, the first being the name of
// the input file, the second being the name of the resulting report file.
public class DuplicateVoters
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

      // The set of all the voters found so far.
      Set<String> votersFound = new HashSet<String>();

      // The list for storing duplicate voters.
      List<String> duplicateVotersList = new ArrayList<String>();
      int noOfDuplicateVoters = 0;

      // Read the lines and add the voter's identification.
      String currentLine;
      while((currentLine = input.readLine()) != null)
      {
        // Add this voter to the set.
        // If the result of addition is false, it means this voter must be
        // a duplicate.
        if (! votersFound.add(currentLine))
        {
          duplicateVotersList.add(currentLine);
          noOfDuplicateVoters++;
        } // if
        // Skip the next line, which is the time and location of the vote cast.
        input.readLine();
      } // while

      // Print out the duplicate voters.
      for (int index = 0; index < noOfDuplicateVoters; index++)
        output.println(duplicateVotersList.get(index));
      output.println("There were " + noOfDuplicateVoters + " duplicate votes");
    } // try
    catch(Exception exception)
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

} // class DuplicateVoters
