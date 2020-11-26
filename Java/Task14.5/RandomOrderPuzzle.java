import java.io.File;
import java.util.Scanner;

/* Program that sets an interactive puzzle for the user to solve.
   The program is run with a command line argument which is the name of a file
   containing a few lines of text. These are read in and presented in random
   order to the user, who is invited to pick one line to be swapped with the
   last one, repeatedly, until they are back in their original order.
   The text might be part of the lyrics of a song, or a poem, or a quote, etc.,
   or may have some other quality about it that gives a clue for working out
   the correct order.
 */
public class RandomOrderPuzzle
{
  public static void main(String[] args) throws Exception
  {
    Scanner fileScanner = new Scanner(new File(args[0]));
    RandomOrderPuzzle puzzle = new RandomOrderPuzzle(fileScanner);

    Scanner inputScanner = new Scanner(System.in);
    System.out.println(puzzle);
    int moveCount = 0;
    while (! puzzle.isSorted())
    {
      System.out.print("Enter a line number to swap with the last one: ");
      puzzle.swapLine(inputScanner.nextInt());
      System.out.println(puzzle);
      moveCount++;
    } // while
    System.out.println("Game over in " + moveCount + " moves.");
  } // main

  // The number of lines.
  private int noOfLines;

  // The lines in original order.
  private String[] linesInOriginalOrder;

  // The lines in random order.
  private String[] linesInRandomOrder;

  // The constructor is given a Scanner from which to read.
  public RandomOrderPuzzle(Scanner scanner)
  {
    readLinesInOriginalOrder(scanner);
    linesInRandomOrder = copyPuzzle(linesInOriginalOrder);
    randomizeStringArrayOrder(linesInRandomOrder);    
  } // RandomOrderPuzzle

  // Initial size of the linesInOriginalOrder array.
  private static final int INITIAL_ARRAY_SIZE = 5;

  // When linesInOriginalOrder is full, extend it.
  private static final int ARRAY_INCREMENT = 1;

  // Read lines from the given Scanner, count them using noOfLines,
  // and store in linesInOriginalOrder.
  private void readLinesInOriginalOrder(Scanner scanner)
  {
    linesInOriginalOrder = new String[INITIAL_ARRAY_SIZE];
    noOfLines = 0;
    while (scanner.hasNextLine())
    {
      // Obtain the next line.
      String currentLine = readOneLine(scanner);
      // Extend the array if it is too small.
      if (noOfLines == linesInOriginalOrder.length)
      {
       	String[] biggerArray
          = new String[linesInOriginalOrder.length + ARRAY_INCREMENT];
        for (int index = 0; index < linesInOriginalOrder.length; index++)
          biggerArray[index] = linesInOriginalOrder[index];
        linesInOriginalOrder = biggerArray;
      } // if
      // Finally store the lines and update noOfLines.
      linesInOriginalOrder[noOfLines] = currentLine;
      noOfLines++;
    } // while
  } // readLinesInOriginalOrder

  // Read one line of text from the Scanner.
  private String readOneLine(Scanner scanner)
  {
    String line = scanner.nextLine();
    return line;
  } // readOneLine

  // Copy the array.
  private String[] copyPuzzle(String[] anArray)
  {
    String[] result = new String[anArray.length];
    for (int index = 0; index < anArray.length; index++)
      result[index] = anArray[index];
    return result;
  } // copyPuzzle

  // Randomize the order of a given array.
  private void randomizeStringArrayOrder(String[] anArray)
  {
    for (int itemsRemaining = anArray.length;
         itemsRemaining > 0; itemsRemaining--)
    {
      int anIndex = (int) (Math.random() * itemsRemaining);
      String itemAtAnIndex = anArray[anIndex];
      anArray[anIndex] = anArray[anArray.length - 1];
      anArray[anArray.length - 1] = itemAtAnIndex;
    } // for
  } // randomizeStringArrayOrder

  // Swap a given line of copied array with its last line.
  private void swapLine(int lineNo)
  {
    String wasAtLastLine = linesInRandomOrder[noOfLines - 1];
    linesInRandomOrder[noOfLines - 1] = linesInRandomOrder[lineNo];
    linesInRandomOrder[lineNo] = wasAtLastLine;
  } // swapLine

  // Check to see whther the lines of the copy array are in the same order as
  // the original one.
  private boolean isSorted()
  {
    boolean correctOrder;
    int noOfWrongLines = 0;
    for (int index = 0; index < noOfLines; index++)
      {
      if (linesInRandomOrder[index] != linesInOriginalOrder[index])
        noOfWrongLines ++;
      } // for
    if (noOfWrongLines > 0)
      correctOrder = false;
    else
      correctOrder = true;
    return correctOrder;
  } // isSorted

  // The correct line separator for this platform.
  private static final String NLS = System.getProperty("line.separator");

  // Return the lines from the randomized copy in their current order.
  public String toString()
  {
    String result = "";
    for (int index = 0; index < noOfLines; index++)
      result += index + "     " + linesInRandomOrder[index] + NLS;
    return result;
  } // toString

} // class RandomOrderPuzzle
