import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

// A program that takes an input file, andcopies it line by line, except
// that it deletes one of the fields on each line, and produce the result in
// an output file.
// The fields are separated by a single tab character, and are numbered one
// upwards.
// The number of the field to be deleted is given as the first command line
// argument. The two file names are given as the second and third command
// line arguments.
// If a filename is missing, or is "-", then standard input/output is used.
public class DeleteField
{
  public static void main(String[] args)
  {
    BufferedReader input = null;
    PrintWriter output = null;
    try
    {
      // The number of the field to be deleted.
      int fieldToDelete = Integer.parseInt(args[0]);

      // The number of args cannot exceed 3.
      if (args.length > 3)
        throw new DeleteFieldException("Too many arguments");
      if (fieldToDelete < 1)
        throw new NumberFormatException
                    ("The number of the field to be deleted must be at "
                     + "least 1.");

      if (args.length == 1 || args[1].equals("-"))
        input = new BufferedReader(new InputStreamReader(System.in));
      else
        input = new BufferedReader(new FileReader(args[1]));

      if (args.length < 3 || args[2].equals("-"))
        output = new PrintWriter(System.out, true);
      else
      {
        if (new File(args[2]).exists())
          throw new DeleteFieldException("Output file "
                                        + args[2] + " already exists");

        output = new PrintWriter(new FileWriter(args[2]));
      } // else

      String currentLine;
      while ((currentLine = input.readLine()) != null)
      {
        // Divide the line into fields using tab as delimiter.
        String[] fields = currentLine.split("\t");
        String editedLine = "";
        if (fields.length < fieldToDelete)
          editedLine = currentLine;
        else
	      {
          // We build the new line in parts.
          // Add the fields before the one to be deleted.
          for (int index = 0; index < fieldToDelete - 1; index++)
            if (editedLine.equals("")) editedLine = fields[index];
            else                       editedLine += "\t" + fields[index];
          // Add the fields after the one to be deleted.
          for (int index = fieldToDelete; index < fields.length; index++)
            if (editedLine.equals("")) editedLine = fields[index];
            else                       editedLine += "\t" + fields[index];
        } // else
        output.println(editedLine);
      } // while
    } // try
    catch (DeleteFieldException exception)
    {
      System.out.println(exception.getMessage());
    } // catch
    catch (NumberFormatException exception)
    {
      System.out.println(exception.getMessage());
    } // catch
    catch (IOException exception)
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

} // class 
